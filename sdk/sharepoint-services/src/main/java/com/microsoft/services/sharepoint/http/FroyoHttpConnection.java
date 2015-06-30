/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.sharepoint.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.services.sharepoint.Platform;

import android.annotation.SuppressLint;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Build;

/**
 * Froyo HttpConnection implementation, based on AndroidHttpClient and AsyncTask
 * for async operations
 */
public class FroyoHttpConnection implements HttpConnection {

	@Override
	public ListenableFuture<Response> execute(final Request request) {

		final SettableFuture<Response> future = SettableFuture.create();

		final RequestTask requestTask = new RequestTask() {

			AndroidHttpClient mClient;
			InputStream mResponseStream;

			@Override
			protected Void doInBackground(Void... voids) {
				if (request == null) {
					future.setException(new IllegalArgumentException("request"));
				}

				mClient = AndroidHttpClient.newInstance(Platform.getUserAgent());
				mResponseStream = null;
				URI uri;

				try {
					HttpRequest realRequest = createRealRequest(request);
                    assert request != null;
                    uri = new URI(request.getUrl());

					HttpHost host = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());

					HttpResponse response;

					try {
						response = mClient.execute(host, realRequest);
					} catch (SocketTimeoutException timeoutException) {
						closeStreamAndClient();
						future.setException(timeoutException);
						return null;
					}

					mResponseStream = response.getEntity().getContent();
					Header[] headers = response.getAllHeaders();
					Map<String, List<String>> headersMap = new HashMap<String, List<String>>();
					for (Header header : headers) {
						String headerName = header.getName();
						if (headersMap.containsKey(headerName)) {
							headersMap.get(headerName).add(header.getValue());
						} else {
							List<String> headerValues = new ArrayList<String>();
							headerValues.add(header.getValue());
							headersMap.put(headerName, headerValues);
						}
					}

					future.set(new StreamResponse(mResponseStream, response.getStatusLine().getStatusCode(), headersMap));
					closeStreamAndClient();
				} catch (Exception e) {
					closeStreamAndClient();

					future.setException(e);
				}

				return null;
			}

			protected void closeStreamAndClient() {
				if (mResponseStream != null) {
					try {
						mResponseStream.close();
					} catch (IOException e) {
					}
				}

				if (mClient != null) {
					mClient.close();
				}
			}
		};

		Futures.addCallback(future, new FutureCallback<Response>() {
			@Override
			public void onFailure(Throwable arg0) {
				requestTask.closeStreamAndClient();

			}
			
			@Override
			public void onSuccess(Response response) {
			}
		});
		
		executeTask(requestTask);

		return future;
	}

	@SuppressLint("NewApi")
	private void executeTask(AsyncTask<Void, Void, Void> task) {
		// If it's running with Honeycomb or greater, it must execute each
		// request in a different thread
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} else {
			task.execute();
		}
	}

	/**
	 * Internal class to represent an async operation that can close a stream
	 */
	private abstract class RequestTask extends AsyncTask<Void, Void, Void> {

		/**
		 * Closes the internal stream and http client, if they exist
		 */
		abstract protected void closeStreamAndClient();
	}

	/**
	 * Creates a request that can be accepted by the AndroidHttpClient
	 * 
	 * @param request
	 *            The request information
	 */
	private static BasicHttpEntityEnclosingRequest createRealRequest(Request request) {
		BasicHttpEntityEnclosingRequest realRequest = new BasicHttpEntityEnclosingRequest(request.getVerb(),
				request.getUrl());

		if (request.getContent() != null) {
			realRequest.setEntity(new ByteArrayEntity(request.getContent()));
		}

		Map<String, String> headers = request.getHeaders();

		for (String key : headers.keySet()) {
			realRequest.addHeader(key, headers.get(key));
		}

		return realRequest;
	}
}
