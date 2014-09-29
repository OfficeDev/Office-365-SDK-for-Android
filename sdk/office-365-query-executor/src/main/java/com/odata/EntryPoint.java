package com.odata;

import com.infrastructure.Credentials;
import com.infrastructure.DependencyResolver;


public class EntryPoint extends BaseODataContainer {
    public EntryPoint(String url, Credentials credentials, DependencyResolver resolver) {
        super(url, credentials, resolver);
    }

    public UserQuery getMe() {
        return new UserQuery("Me", this);
    }
}
