package com.microsoft.services.odata.impl.desktop;

import com.microsoft.services.odata.Constants;
import com.microsoft.services.odata.impl.ODataURLImpl;
import com.microsoft.services.odata.impl.desktop.http.DesktopHttpTransport;
import com.microsoft.services.odata.impl.http.RequestImpl;
import com.microsoft.services.odata.interfaces.CredentialsFactory;
import com.microsoft.services.odata.interfaces.DependencyResolver;
import com.microsoft.services.odata.interfaces.HttpTransport;
import com.microsoft.services.odata.interfaces.JsonSerializer;
import com.microsoft.services.odata.interfaces.ODataURL;
import com.microsoft.services.odata.interfaces.Request;

/**
 * The type Default dependency resolver.
 */
public class DesktopDependencyResolver implements DependencyResolver {

    private CredentialsFactory credentialsFactory;
    private LoggerImpl logger;

    /**
     * Instantiates a new Default dependency resolver.
     */
    public DesktopDependencyResolver() {
        this.logger = new LoggerImpl();
    }

    @Override
    public HttpTransport getHttpTransport() {
        return new DesktopHttpTransport();
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
    public Request createRequest() {
        return new RequestImpl();
    }

    @Override
    public String getPlatformUserAgent(String productName) {

        String osName = System.getProperty("os.name");
        return String.format(
                "%s/1.0 (lang=%s; os=%s; version=%s)",
                productName, "Java", osName, Constants.SDK_VERSION);
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
