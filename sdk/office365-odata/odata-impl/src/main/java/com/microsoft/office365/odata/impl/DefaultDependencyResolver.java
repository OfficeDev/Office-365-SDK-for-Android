package com.microsoft.office365.odata.impl;

import com.microsoft.office365.odata.impl.http.AndroidHttpConnection;
import com.microsoft.office365.odata.interfaces.*;

public class DefaultDependencyResolver implements DependencyResolver {

    private CredentialsFactory credentialsFactory;

    @Override
    public HttpTransport getHttpTransport() {
        return new AndroidHttpConnection();
    }

    @Override
    public Logger getLogger() {
        return new LoggerImpl();
    }

    @Override
    public JsonSerializer getJsonSerializer() {
        return new GsonSerializer();
    }

    @Override
    public CredentialsFactory getCredentialsFactory() {
        return credentialsFactory;
    }

    public void setCredentialsFactory(CredentialsFactory credentialsFactory) {
        this.credentialsFactory = credentialsFactory;
    }
}
