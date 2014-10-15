package com.microsoft.services.odata.impl;

import android.os.Build;

import com.microsoft.services.odata.Constants;
import com.microsoft.services.odata.ODataURLImpl;
import com.microsoft.services.odata.impl.http.AndroidHttpTransport;
import com.microsoft.services.odata.interfaces.*;

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

    @Override
    public String getPlatformUserAgent(String productName) {
        String userAgent = String.format(
                "%s/1.0 (lang=%s; os=%s; os_version=%s; arch=%s; version=%s)",
                productName, "Java", "Android", Build.VERSION.RELEASE,
                Build.CPU_ABI, Constants.SDK_VERSION);

        return userAgent;

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
