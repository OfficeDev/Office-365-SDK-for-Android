/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.odata.listservices;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonObject;
import com.microsoft.services.odata.interfaces.DependencyResolver;
import com.microsoft.services.odata.interfaces.HttpTransport;
import com.microsoft.services.odata.interfaces.HttpVerb;
import com.microsoft.services.odata.interfaces.LogLevel;
import com.microsoft.services.odata.interfaces.Logger;
import com.microsoft.services.odata.interfaces.Request;
import com.microsoft.services.odata.interfaces.Response;

import java.util.HashMap;
import java.util.Map;

public class SharepointClient extends OfficeClient {
    private String mServerUrl;
    private String mSiteRelativeUrl;
    private DependencyResolver mResolver;

    protected String getSiteUrl() {
        return mServerUrl + mSiteRelativeUrl;
    }

    protected String getServerUrl() {
        return mServerUrl;
    }

    protected String getSiteRelativeUrl() {
        return mSiteRelativeUrl;
    }

    public SharepointClient(String serverUrl, String siteRelativeUrl,
                            DependencyResolver resolver) {
        this(serverUrl, siteRelativeUrl, resolver, null);
    }

    public SharepointClient(String serverUrl, String siteRelativeUrl,
                            DependencyResolver resolver, Logger logger) {
        super(resolver);

        mResolver = resolver;

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

        HttpTransport connection = mResolver.getHttpTransport();
        Request request = connection.createRequest();
        request.setUrl(getSiteUrl() + "_api/contextinfo");
        prepareRequest(request);

        log("Generate request for getFormDigest", LogLevel.INFO);

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

                        //TODO:JUST TO MAKE IT COMPILE!
                        String responseContent = response.getStream().toString();

                        com.google.gson.JsonParser parser = new com.google.gson.JsonParser();
                        JsonObject json = parser.parse(responseContent).getAsJsonObject();


                        result.set(String.valueOf(json.getAsJsonObject("d")
                                .getAsJsonObject("GetContextWebInformation")
                                .getAsJsonObject("FormDigestValue")));
                    } else {
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
     * @param url     the url
     * @param method  the method
     * @param headers the headers
     * @param payload the payload
     * @return OfficeFuture<JsonObject>
     */
    protected ListenableFuture<JsonObject> executeRequestJsonWithDigest(
            final String url, final HttpVerb method,
            final Map<String, String> headers, final byte[] payload) {

        final SettableFuture<JsonObject> result = SettableFuture.create();
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

                finalHeaders.put("Content-Type","application/json;odata=verbose");
                finalHeaders.put("X-RequestDigest", digest);
                ListenableFuture<JsonObject> request = executeRequestJson(url,method, finalHeaders, payload);

                Futures.addCallback(request, new FutureCallback<JsonObject>() {
                    @Override
                    public void onFailure(Throwable t) {
                        result.setException(t);
                    }

                    @Override
                    public void onSuccess(JsonObject json) {
                        result.set(json);
                    }
                });
            }
        });
        return result;
    }

    public ListenableFuture<String> getWebTitle() {
        final SettableFuture<String> result = SettableFuture.create();

        ListenableFuture<JsonObject> request = executeRequestJson(mServerUrl + "_api/web/title", HttpVerb.GET);

        Futures.addCallback(request, new FutureCallback<JsonObject>() {
            @Override
            public void onFailure(Throwable t) {
                result.setException(t);
            }

            @Override
            public void onSuccess(JsonObject json) {
                result.set(json.getAsJsonObject("d").get("Title").getAsString());
            }
        });

        return result;
    }
}
