/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.sharepoint;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.services.sharepoint.http.HttpConnection;
import com.microsoft.services.sharepoint.http.Request;
import com.microsoft.services.sharepoint.http.Response;

public class SharePointClient extends OfficeClient {

	private String mServerUrl;
	private String mSiteRelativeUrl;

	protected String getSiteUrl() {
		return mServerUrl + mSiteRelativeUrl;
	}

	protected String getServerUrl() {
		return mServerUrl;
	}

	protected String getSiteRelativeUrl() {
		return mSiteRelativeUrl;
	}

	public SharePointClient(String serverUrl, String siteRelativeUrl,
							Credentials credentials) {
		this(serverUrl, siteRelativeUrl, credentials, null);
	}

	public SharePointClient(String serverUrl, String siteRelativeUrl,
							Credentials credentials, Logger logger) {
		super(credentials, logger);

		if (serverUrl == null) {
			throw new IllegalArgumentException("serverUrl must not be null");
		}

		if (siteRelativeUrl == null) {
			throw new IllegalArgumentException(
					"siteRelativeUrl must not be null");
		}

		mServerUrl = serverUrl;
		mSiteRelativeUrl = siteRelativeUrl;

		if (!mServerUrl.endsWith("/")) {
			mServerUrl += "/";
		}

		if (mSiteRelativeUrl.startsWith("/")) {
			mSiteRelativeUrl = mSiteRelativeUrl.substring(1);
		}

		if (!mSiteRelativeUrl.endsWith("/") && mSiteRelativeUrl.length() > 0) {
			mSiteRelativeUrl += "/";
		}
	}

	protected ListenableFuture<String> getFormDigest() {

		HttpConnection connection = Platform.createHttpConnection();
		Request request = new Request("POST");
		request.setUrl(getSiteUrl() + "_api/contextinfo");
		prepareRequest(request);

		log("Generate request for getFormDigest", LogLevel.Verbose);
		request.log(getLogger());

		final SettableFuture<String> result = SettableFuture.create();
		ListenableFuture<Response> future = connection.execute(request);

		Futures.addCallback(future, new FutureCallback<Response>() {
			@Override
			public void onFailure(Throwable t) {
				result.setException(t);
			}

			@Override
			public void onSuccess(Response response) {
				try {
					int statusCode = response.getStatus();
					if (isValidStatus(statusCode)) {
						String responseContent = response.readToEnd();

						JSONObject json = new JSONObject(responseContent);

						result.set(json.getJSONObject("d")
								.getJSONObject("GetContextWebInformation")
								.getString("FormDigestValue"));
					} else {
						result.setException(new Exception(
								"Invalid status code " + statusCode + ": "
										+ response.readToEnd()));
					}
				} catch (Exception e) {
					log(e);
				}
			}
		});

		return result;
	}

	/**
	 * Execute request json with digest.
	 * 
	 * @param url
	 *            the url
	 * @param method
	 *            the method
	 * @param headers
	 *            the headers
	 * @param payload
	 *            the payload
	 * @return OfficeFuture<JSONObject>
	 */
	protected ListenableFuture<JSONObject> executeRequestJsonWithDigest(
			final String url, final String method,
			final Map<String, String> headers, final byte[] payload) {

		final SettableFuture<JSONObject> result = SettableFuture.create();
		ListenableFuture<String> digestFuture = getFormDigest();

		Futures.addCallback(digestFuture, new FutureCallback<String>() {
			@Override
			public void onFailure(Throwable t) {
				result.setException(t);
			}

			@Override
			public void onSuccess(String digest) {
				Map<String, String> finalHeaders = new HashMap<String, String>();

				if (headers != null) {
					for (String key : headers.keySet()) {
						finalHeaders.put(key, headers.get(key));
					}
				}

				finalHeaders.put("Content-Type",
						"application/json;odata=verbose");
				finalHeaders.put("X-RequestDigest", digest);

				ListenableFuture<JSONObject> request = executeRequestJson(url,
						method, finalHeaders, payload);

				Futures.addCallback(request, new FutureCallback<JSONObject>() {
					@Override
					public void onFailure(Throwable t) {
						result.setException(t);
					}

					@Override
					public void onSuccess(JSONObject json) {
						result.set(json);
					}
				});
			}
		});
		return result;
	}

	public ListenableFuture<String> getWebTitle() {
		final SettableFuture<String> result = SettableFuture.create();

		ListenableFuture<JSONObject> request = executeRequestJson(mServerUrl
				+ "_api/web/title", "GET");

		Futures.addCallback(request, new FutureCallback<JSONObject>() {
			@Override
			public void onFailure(Throwable t) {
				result.setException(t);
			}

			@Override
			public void onSuccess(JSONObject json) {
				try {
					result.set(json.getJSONObject("d").getString("Title"));
				} catch (JSONException e) {
					log(e);
				}
			}
		});

		return result;
	}

    public ListenableFuture<JSONObject> getUserByID(String id){
        final SettableFuture<JSONObject> result = SettableFuture.create();

        ListenableFuture<JSONObject> request = executeRequestJson(mServerUrl
         + "_api/web/getuserbyid("+id+")", "GET");

        Futures.addCallback(request, new FutureCallback<JSONObject>() {
            @Override
            public void onFailure(Throwable t) { result.setException(t);}

            @Override
            public void onSuccess(JSONObject json) {
                result.set(json);
            }
        });
        return result;
    }
}
