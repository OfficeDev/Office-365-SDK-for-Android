package com.microsoft.office365.odata.impl;

import com.microsoft.office365.odata.impl.http.AndroidHttpTransport;
import com.microsoft.office365.odata.impl.http.ODataURLImpl;
import com.microsoft.office365.odata.interfaces.*;

public class DefaultDependencyResolver implements DependencyResolver {

    private CredentialsFactory credentialsFactory;
    private LoggerImpl logger;

    public DefaultDependencyResolver() {
        this.logger = new LoggerImpl();
    }

    @Override
    public HttpTransport getHttpTransport() {
        return new AndroidHttpTransport();
    }

    @Override
    public LoggerImpl getLogger() {
        return this.logger;
    }

    @Override
    public JsonSerializer getJsonSerializer() {
        return new GsonSerializer();
    }

    @Override
    public CredentialsFactory getCredentialsFactory() {
        return credentialsFactory;
    }

    @Override
    public ODataURL createODataURL() {
        return new ODataURLImpl();
    }

    public void setCredentialsFactory(CredentialsFactory credentialsFactory) {
        this.credentialsFactory = credentialsFactory;
    }
}
