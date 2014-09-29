package com.odata;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.infrastructure.Credentials;
import com.infrastructure.DependencyResolver;
import com.infrastructure.HttpConnection;
import com.infrastructure.http.Constants;
import com.infrastructure.http.Request;
import com.infrastructure.http.Response;

public abstract class BaseODataContainer extends ODataExecutable {

    private String url;
    private Credentials credentials;
    private DependencyResolver resolver;

    public BaseODataContainer(String url, Credentials credentials, DependencyResolver resolver) {
        this.url = url;
        this.credentials = credentials;
        this.resolver = resolver;
    }

    @Override
    ListenableFuture<byte[]> oDataExecute(String path) {

        HttpConnection connection = resolver.getHttpConnection();
        Request request = new Request(Constants.HTTP_GET);
        request.setUrl(url + "/" + path);
        request.addHeader("Authorization", "Bearer " + credentials.getToken());

        final ListenableFuture<Response> future = connection.execute(request);
        final SettableFuture<byte[]> result = SettableFuture.create();

        Futures.addCallback(future, new FutureCallback<Response>() {

            @Override
            public void onSuccess(Response response) {
                try {
                    byte[] data = response.readAllBytes();

                    result.set(data);
                } catch (Throwable t) {
                    result.setException(t);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                result.setException(throwable);
            }
        });
        return result;
    }

    @Override
    DependencyResolver getResolver() {
        return resolver;
    }

}
