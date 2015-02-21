/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.odata.impl.http;

import android.net.http.AndroidHttpClient;

import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.services.odata.Constants;
import com.microsoft.services.odata.interfaces.Request;
import com.microsoft.services.odata.interfaces.Response;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.EntityEnclosingRequestWrapper;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Runnable that executes a network operation
 */
public class AndroidNetworkRunnable extends NetworkRunnable {

    /**
     * Initializes the network runnable
     *
     * @param request The request to execute
     * @param future  Future for the operation
     */
    public AndroidNetworkRunnable(Request request, SettableFuture<Response> future) {
        super(request, future);
    }


    @Override
    public void run() {
        AndroidHttpClient client = null;
        try {

            String userAgent = mRequest.getHeaders().get(Constants.USER_AGENT_HEADER);

            if (userAgent == null) {
                userAgent = "";
            }

            client = AndroidHttpClient.newInstance(userAgent);

            BasicHttpEntityEnclosingRequest realRequest = new BasicHttpEntityEnclosingRequest(mRequest.getVerb().toString(),
                                                                                              mRequest.getUrl().toString());
            EntityEnclosingRequestWrapper wrapper = new EntityEnclosingRequestWrapper(realRequest);

            Map<String, String> headers = mRequest.getHeaders();
            for (String key : headers.keySet()) {
                wrapper.addHeader(key, headers.get(key));
            }

            if (mRequest.getContent() != null) {
                ByteArrayEntity entity = new ByteArrayEntity(mRequest.getContent());
                wrapper.setEntity(entity);
            } else if (mRequest.getStreamedContent() != null) {
                InputStream stream = mRequest.getStreamedContent();
                InputStreamEntity entity = new InputStreamEntity(stream, mRequest.getStreamedContentSize());
                wrapper.setEntity(entity);
            }

            HttpResponse realResponse = client.execute(wrapper);
            int status = realResponse.getStatusLine().getStatusCode();

            Map<String, List<String>> responseHeaders = new HashMap<String, List<String>>();
            for (Header header : realResponse.getAllHeaders()) {
                List<String> headerValues = new ArrayList<String>();
                for (HeaderElement element : header.getElements()) {
                    headerValues.add(element.getValue());
                }
                responseHeaders.put(header.getName(), headerValues);
            }

            HttpEntity entity = realResponse.getEntity();
            InputStream stream = null;

            if (entity != null) {
                stream = entity.getContent();
            }

            if (stream != null) {
                final AndroidHttpClient finalClient = client;
                Closeable closeable = new Closeable() {
                    @Override
                    public void close() throws IOException {
                        finalClient.close();
                    }
                };

                Response response = new ResponseImpl(
                        stream,
                        status,
                        responseHeaders,
                        closeable);

                mFuture.set(response);
            } else {
                client.close();
                mFuture.set(new EmptyResponse(status, responseHeaders));
            }

        } catch (Throwable t) {
            if (client != null) {
                client.close();
            }

            mFuture.setException(t);
        }
    }
}
