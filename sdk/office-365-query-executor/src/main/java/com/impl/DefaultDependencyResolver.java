package com.impl;

import com.impl.http.ApacheHttpConnection;
import com.interfaces.*;

public class DefaultDependencyResolver implements DependencyResolver {

    private CredentialsFactory credentialsFactory;

    @Override
    public HttpTransport getHttpTransport() {
        return new ApacheHttpConnection();
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
