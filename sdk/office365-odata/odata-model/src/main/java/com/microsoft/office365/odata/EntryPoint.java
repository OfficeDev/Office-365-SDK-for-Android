package com.microsoft.office365.odata;

import com.microsoft.office365.odata.interfaces.DependencyResolver;

public class EntryPoint extends BaseODataContainer {
    public EntryPoint(String url, DependencyResolver resolver) {
        super(url, resolver);
    }

    public UserQuery getMe() {
        return new UserQuery("Me", this);
    }
}
