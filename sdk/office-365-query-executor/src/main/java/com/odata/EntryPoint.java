package com.odata;

import com.interfaces.DependencyResolver;

public class EntryPoint extends BaseODataContainer {
    public EntryPoint(String url, DependencyResolver resolver) {
        super(url, resolver);
    }

    public UserQuery getMe() {
        return new UserQuery("Me", this);
    }
}
