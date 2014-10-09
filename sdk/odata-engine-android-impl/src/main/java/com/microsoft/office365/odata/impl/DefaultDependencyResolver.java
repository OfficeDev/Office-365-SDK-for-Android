package com.microsoft.office365.odata.impl;

import com.microsoft.office365.odata.impl.http.AndroidHttpTransport;
import com.microsoft.office365.odata.interfaces.*;

public class DefaultDependencyResolver implements DependencyResolver {

    private CredentialsFactory credentialsFactory;

    @Override
    public HttpTransport getHttpTransport() {
        return new AndroidHttpTransport();
    }

    @Override
    public LoggerImpl getLogger() {
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
