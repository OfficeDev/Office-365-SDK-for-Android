package com.microsoft.office365.odata.impl;

import com.microsoft.office365.odata.ODataURLImpl;
import com.microsoft.office365.odata.impl.http.AndroidHttpTransport;
import com.microsoft.office365.odata.interfaces.*;

/**
 * The type Default dependency resolver.
 */
public class DefaultDependencyResolver implements DependencyResolver {

    private CredentialsFactory credentialsFactory;
    private LoggerImpl logger;

    /**
     * Instantiates a new Default dependency resolver.
     */
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

    /**
     * Sets credentials factory.
     *
     * @param credentialsFactory the credentials factory
     */
    public void setCredentialsFactory(CredentialsFactory credentialsFactory) {
        this.credentialsFactory = credentialsFactory;
    }
}
