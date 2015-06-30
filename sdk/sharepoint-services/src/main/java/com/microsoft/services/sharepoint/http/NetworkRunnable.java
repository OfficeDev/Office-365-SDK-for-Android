/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.sharepoint.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.google.common.util.concurrent.SettableFuture;

/**
 * Runnable that executes a network operation
 */
class NetworkRunnable implements Runnable {

	HttpURLConnection mConnection = null;
	InputStream mResponseStream = null;
	Request mRequest;
	SettableFuture<Response> mFuture;
	final Object mCloseLock = new Object();

	/**
	 * Initializes the network runnable
	 * 
	 * @param request
	 *            The request to execute
	 * @param future
	 *            Future for the operation
     */
	public NetworkRunnable(Request request, SettableFuture<Response> future) {
		mRequest = request;
		mFuture = future;
	}

	@Override
	public void run() {
		try {
			int responseCode = -1;
			synchronized (mCloseLock) {
				if (!mFuture.isCancelled()) {
					if (mRequest == null) {
						mFuture.setException(new IllegalArgumentException(
								"request"));
						return;
					}

					mConnection = createHttpURLConnection(mRequest);

					responseCode = mConnection.getResponseCode();

					if (responseCode >= 400) {
						mResponseStream = mConnection.getErrorStream();
					} else {
						mResponseStream = mConnection.getInputStream();
					}
				}
			}

			if (mResponseStream != null && !mFuture.isCancelled()) {
				mFuture.set(new StreamResponse(mResponseStream, responseCode,
						mConnection.getHeaderFields()));
			}
		} catch (Throwable e) {
			if (!mFuture.isCancelled()) {
				if (mConnection != null) {
					mConnection.disconnect();
				}

				mFuture.setException(e);
			}
		} finally {
			closeStreamAndConnection();
		}

	}

	/**
	 * Closes the stream and connection, if possible
	 */
	void closeStreamAndConnection() {
		synchronized (mCloseLock) {
			if (mResponseStream != null) {
				try {
					mResponseStream.close();
				} catch (IOException e) {
				}
			}

			if (mConnection != null) {
				mConnection.disconnect();
			}
		}
	}

	/**
	 * Creates an HttpURLConnection
	 * 
	 * @param request
	 *            The request info
	 * @return An HttpURLConnection to execute the request
	 * @throws java.io.IOException
	 */
	static HttpURLConnection createHttpURLConnection(Request request)
			throws IOException {
		URL url = new URL(request.getUrl());

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setRequestMethod(request.getVerb());

		Map<String, String> headers = request.getHeaders();

		for (String key : headers.keySet()) {
			connection.setRequestProperty(key, headers.get(key));
		}

		if (request.getContent() != null) {
			connection.setDoOutput(true);
			// OutputStreamWriter out = new
			// OutputStreamWriter(connection.getOutputStream());
			byte[] requestContent = request.getContent();
			OutputStream stream = connection.getOutputStream();
			stream.write(requestContent, 0, requestContent.length);
			stream.close();
			// out.write(requestContent);
			// out.close();

		}

		return connection;
	}

}
