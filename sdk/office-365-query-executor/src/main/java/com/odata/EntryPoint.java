package com.odata;

import com.google.common.util.concurrent.ListenableFuture;
import com.infrastructure.Credentials;
import com.infrastructure.DependencyResolver;
import com.infrastructure.HttpConnection;
import com.infrastructure.http.Constants;
import com.infrastructure.http.Request;
import com.infrastructure.http.Response;

/**
 * Created by PabloZaiden on 26/09/14.
 */
public class EntryPoint extends ODataExecutable {

    private String url;
    private Credentials credentials;
    private DependencyResolver resolver;

    public EntryPoint(String url, Credentials credentials, DependencyResolver resolver) {
        this.url = url;
        this.credentials = credentials;
        this.resolver = resolver;
    }

    @Override
    ListenableFuture<Response> oDataExecute(String path) {

        HttpConnection connection = resolver.getHttpConnection();
        Request request = new Request(Constants.HTTP_GET);
        request.setUrl(url + "/" + path);
        request.addHeader("Authorization", "Bearer " + credentials.getToken());

        return connection.execute(request);
    }

    @Override
    public DependencyResolver getResolver() {
        return resolver;
    }

    public UserQuery getMe() {
        return new UserQuery("Me", this);
    }
}
