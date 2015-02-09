package com.microsoft.services.odata.impl.desktop.http;

import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.services.odata.impl.http.EmptyResponse;
import com.microsoft.services.odata.impl.http.NetworkRunnable;
import com.microsoft.services.odata.impl.http.ResponseImpl;
import com.microsoft.services.odata.interfaces.Request;
import com.microsoft.services.odata.interfaces.Response;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.entity.*;
import org.apache.http.impl.client.*;
import org.apache.http.message.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JvmNetworkRunnable extends NetworkRunnable {
    /**
     * Initializes the network runnable
     *
     * @param request The request to execute
     * @param future  Future for the operation
     */
    public JvmNetworkRunnable(Request request, SettableFuture<Response> future) {
        super(request, future);
    }

    @Override
    public void run() {
        CloseableHttpClient client = null;
        try {

            client = HttpClients.createDefault();

            BasicHttpEntityEnclosingRequest realRequest = new BasicHttpEntityEnclosingRequest(mRequest.getVerb().toString(), mRequest.getUrl().toString());
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
                Response response = new ResponseImpl(
                        stream,
                        status,
                        responseHeaders,
                        client);

                mFuture.set(response);
            } else {
                client.close();
                mFuture.set(new EmptyResponse(status, responseHeaders));
            }

        } catch (Throwable t) {
            if (client != null) {
                try {
                    client.close();
                } catch (Throwable ignore) {
                }
            }

            mFuture.setException(t);
        }
    }
}
