package com.impl.apache;

import com.google.common.util.concurrent.ListenableFuture;
import com.impl.http.RequestImpl;
import com.interfaces.HttpTransport;
import com.interfaces.Request;
import com.interfaces.Response;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Created by marcote on 9/30/14.
 */
public class ApacheHttpClientImpl  implements HttpTransport{
    @Override
    public Request createRequest() {
        return new RequestImpl();
    }

    @Override
    public ListenableFuture<Response> execute(Request request) {
        CloseableHttpClient client = HttpClients.createDefault();


        return null;
    }
}
