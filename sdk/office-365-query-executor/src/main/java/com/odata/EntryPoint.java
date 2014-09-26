package com.odata;

import com.google.common.util.concurrent.ListenableFuture;
import com.infrastructure.DependencyResolver;

/**
 * Created by PabloZaiden on 26/09/14.
 */
public class EntryPoint extends ODataExecutable {

    private String url;
    private DependencyResolver resolver;

    public EntryPoint(String url, DependencyResolver resolver) {
        this.url = url;
        this.resolver = resolver;
    }

    @Override
    ListenableFuture<String> oDataExecute(String path) {
        return resolver.getHttpClient().executeRequest(url + "/" + path);
    }

    @Override
    public DependencyResolver getResolver() {
        return resolver;
    }

    public UserQuery getMe() {
        return new UserQuery("Me", this);
    }
}
