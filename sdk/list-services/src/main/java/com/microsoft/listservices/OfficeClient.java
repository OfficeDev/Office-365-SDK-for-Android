package com.microsoft.listservices;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.services.odata.BaseODataContainerHelper;
import com.microsoft.services.odata.Constants;
import com.microsoft.services.odata.interfaces.DependencyResolver;
import com.microsoft.services.odata.interfaces.HttpVerb;
import com.microsoft.services.odata.interfaces.LogLevel;
import com.microsoft.services.odata.interfaces.ODataURL;
import com.microsoft.services.odata.interfaces.Request;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;

public class OfficeClient {

    DependencyResolver mResolver;

    public OfficeClient(DependencyResolver resolver) {

        mResolver = resolver;
    }

    protected void log(String message, LogLevel level) {
        mResolver.getLogger().log(message, level);
    }

    protected void log(Throwable error) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        error.printStackTrace(pw);
        String stackTrace = sw.toString();
        mResolver.getLogger().log(error.toString() + "\nStack Trace: " + stackTrace, LogLevel.ERROR);
    }


    protected String generateODataQueryString(Query query) {
        StringBuilder sb = new StringBuilder();
        if (query != null) {
            query.ensureIdProperty();
            sb.append("$filter=");
            sb.append(queryEncode(query.toString()));

            String rowSetModifiers = query.getRowSetModifiers().trim();
            if (rowSetModifiers != "") {

                if (!rowSetModifiers.startsWith("&")) {
                    sb.append("&");
                }
                sb.append(rowSetModifiers);
            }
        }

        return sb.toString();
    }

    protected ListenableFuture<byte[]> executeRequest(String url, HttpVerb method) {
        return executeRequest(url, method, null, null);
    }

    protected ListenableFuture<byte[]> executeRequest(String url, HttpVerb method, Map<String, String> headers,
                                                      byte[] payload) {

        ODataURL oDataURL = mResolver.createODataURL();
        return BaseODataContainerHelper.oDataExecute(oDataURL, payload, method, url, mResolver, "");
    }

    protected ListenableFuture<JsonObject> executeRequestJson(String url, HttpVerb method) {
        return executeRequestJson(url, method, null, null);
    }

    protected ListenableFuture<JsonObject> executeRequestJson(String url, HttpVerb method,
                                                              Map<String, String> headers,
                                                              byte[] payload) {

        final SettableFuture<JsonObject> result = SettableFuture.create();
        final ListenableFuture<byte[]> request = executeRequest(url, method, headers, payload);

        Futures.addCallback(request, new FutureCallback<byte[]>() {
            @Override
            public void onFailure(Throwable t) {
                result.setException(t);
            }

            @Override
            public void onSuccess(byte[] b) {
                String string;
                try {
                    string = new String(b, Constants.UTF8_NAME);
                    if (string == null || string.length() == 0) {
                        result.set(null);
                    } else {
                        com.google.gson.JsonParser parser = new JsonParser();
                        JsonObject json = parser.parse(string).getAsJsonObject();
                        result.set(json);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
        return result;
    }

    /*
    public ListenableFuture<List<DiscoveryInformation>> getDiscoveryInfo() {
		return getDiscoveryInfo("https://api.office.com/discovery/me/services");
	}


	public ListenableFuture<List<DiscoveryInformation>> getDiscoveryInfo(String discoveryEndpoint) {
		final SettableFuture<List<DiscoveryInformation>> result = SettableFuture.create();
		final ListenableFuture<JsonObject> request = executeRequestJson(discoveryEndpoint, "GET");

		Futures.addCallback(request, new FutureCallback<JsonObject>() {
			@Override
			public void onFailure(Throwable t) {
				result.setException(t);
			}

			@Override
			public void onSuccess(JsonObject json) {
				List<DiscoveryInformation> discoveryInfo;
				try {
					discoveryInfo = DiscoveryInformation.listFromJson(json, DiscoveryInformation.class);
					result.set(discoveryInfo);
				} catch (JSONException e) {
					log(e.getMessage(), LogLevel.Critical);
				}
			}
		});
		return result;
	}
   */

    protected void prepareRequest(Request request) {
        request.addHeader("Accept", "application/json;odata=verbose");
        request.addHeader("X-ClientService-ClientTag", "SDK-JAVA");

        int contentLength = 0;
        if (request.getContent() != null) {
            contentLength = request.getContent().length;
        }
        request.addHeader("Content-Length", String.valueOf(contentLength));
    }

    protected static boolean isValidStatus(int status) {
        return status >= 200 && status <= 299;
    }

    protected String queryEncode(String query) {

        String encoded = null;

        try {
            encoded = query.replaceAll("\\s", "+");
        } catch (Exception e) {
            encoded = query;
        }
        return encoded;
    }

    protected String urlEncode(String str) {
        String encoded = null;
        try {
            encoded = URLEncoder.encode(str, Constants.UTF8_NAME);
        } catch (UnsupportedEncodingException e) {
            encoded = str;
        }

        encoded = encoded.replaceAll("\\+", "%20").replaceAll("\\%21", "!").replaceAll("\\%27", "'")
                .replaceAll("\\%28", "(").replaceAll("\\%29", ")").replaceAll("\\%7E", "~");
        return encoded;
    }

    protected String UUIDtoString(UUID id) {
        return id.toString().replace("-", "");
    }
}
