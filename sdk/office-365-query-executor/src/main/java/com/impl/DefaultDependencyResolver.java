package com.impl;

import com.impl.http.CredentialsImpl;
import com.impl.http.JavaHttpConnection;
import com.interfaces.Credentials;
import com.interfaces.CredentialsFactory;
import com.interfaces.DependencyResolver;
import com.interfaces.HttpTransport;
import com.interfaces.JsonSerializer;
import com.interfaces.Logger;

public class DefaultDependencyResolver implements DependencyResolver {

    private CredentialsFactory credentialsFactory;

    @Override
    public HttpTransport getHttpTransport() {
        return new JavaHttpConnection();
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
