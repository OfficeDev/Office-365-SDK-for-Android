
package com.core;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.office365.Credentials;
import com.microsoft.office365.OfficeClient;
import com.microsoft.office365.http.Request;
public class BaseClient<V> extends OfficeClient {

	private GsonBuilder mBuilder = new GsonBuilder();
	
	public BaseClient(Credentials credentials) {
		super(credentials);
	}

	public ListenableFuture<List<V>> getList(String url, final Class<V[]> type) {
		final SettableFuture<List<V>> future = SettableFuture.create();

		Map<String, String> headers = new HashMap<String, String>();

		headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		headers.put("Content-Type", "application/json;odata.metadata=full");
		headers.put("Expect", "100-continue");

		ListenableFuture<JSONObject> requestFuture = this.executeRequestJson(url, "GET",headers,null);

		Futures.addCallback(requestFuture, new FutureCallback<JSONObject>() {
			@Override
			public void onFailure(Throwable error) {
				future.setException(error);
			}

			@Override
			public void onSuccess(JSONObject result) {
				if (result != null) {
					Gson gson = mBuilder.create();
					String json = null;
					try {
						json = result.getJSONArray("value").toString();
					} catch (JSONException e) {
						future.setException(e);
						return;
					}

					List<V> entity = Arrays.asList(gson.fromJson(json, type));
					future.set(entity);
				} else {
					future.set(null);
				}
			}
		});

		return future;
	}

	public ListenableFuture<String> execute(String url,V entity, String method) {
		final SettableFuture<String> future = SettableFuture.create();

		Gson gson = new Gson();
		String json = entity != null ? gson.toJson(entity) : null;

		Map<String, String> headers = new HashMap<String, String>();

		headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		headers.put("Content-Type", "application/json;odata.metadata=full");
		headers.put("Expect", "100-continue");

		ListenableFuture<JSONObject> requestFuture = this.executeRequestJson(url, method, headers, getBytes(json));//(url, "PUT",);

		Futures.addCallback(requestFuture, new FutureCallback<JSONObject>() {
			@Override
			public void onFailure(Throwable error) {
				future.setException(error);
			}

			@Override
			public void onSuccess(JSONObject result) {
				if (result != null) {
					String id = null;
					try {
						id = result.get("Id").toString();
					} catch (JSONException e) {
						future.setException(e);
						return;
					}

					future.set(id);
				} else {
					future.set("success");
				}
			}
		});

		return future;
	}

	public ListenableFuture<V> execute(String url, String json, final Class<V> type, String method) {
		final SettableFuture<V> future = SettableFuture.create();

		Map<String, String> headers = new HashMap<String, String>();

		headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		headers.put("Content-Type", "application/json;odata.metadata=full");
		headers.put("Expect", "100-continue");
		
		ListenableFuture<JSONObject> requestFuture = this.executeRequestJson(url, method, headers, getBytes(json));

		Futures.addCallback(requestFuture, new FutureCallback<JSONObject>() {
			@Override
			public void onFailure(Throwable error) {
				future.setException(error);
			}

			@Override
			public void onSuccess(JSONObject result) {
				if (result != null) {
					Gson gson = mBuilder.create();
					String json = result.toString();

					V entity = (V) gson.fromJson(json, type);
					future.set(entity);
				} else {
					future.set(null);
				}
			}
		});

		return future;
	}

	protected void prepareRequest(Request request) {

        // fix for accept
        if (!request.getHeaders().containsKey("Accept")) {
            request.addHeader("Accept", "application/json;odata=verbose");
        }

        request.addHeader("X-ClientService-ClientTag", "SDK-JAVA");

        int contentLength = 0;
        if (request.getContent() != null) {
            contentLength = request.getContent().length;
        }
        request.addHeader("Content-Length", String.valueOf(contentLength));

        this.getCredentials().prepareRequest(request);
    }

	private byte[] getBytes(String s) {
		if(s == null) return null;

		try {
			return s.getBytes(com.microsoft.office365.Constants.UTF8_NAME);
		} catch (UnsupportedEncodingException e) {
			return s.getBytes();
		}
	}	
}