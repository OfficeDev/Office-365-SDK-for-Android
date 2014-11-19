package com.microsoft.services.odata.impl;

import android.os.Build;

import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.services.odata.Constants;
import com.microsoft.services.odata.impl.http.AndroidHttpTransport;
import com.microsoft.services.odata.impl.http.RequestImpl;
import com.microsoft.services.odata.interfaces.Credentials;
import com.microsoft.services.odata.interfaces.DependencyResolver;
import com.microsoft.services.odata.interfaces.HttpTransport;
import com.microsoft.services.odata.interfaces.JsonSerializer;
import com.microsoft.services.odata.interfaces.ODataURL;
import com.microsoft.services.odata.interfaces.Request;

/**
 * The type Default dependency resolver.
 */
public class DefaultDependencyResolver implements DependencyResolver {

    private LoggerImpl logger;
    private AuthenticationContext authenticationContext;
    private String resourceId;
    private String clientId;

    /**
     * Instantiates a new Default dependency resolver.
     */
    public DefaultDependencyResolver(AuthenticationContext authenticationContext,
                                     final String resourceId, final String clientId ) {
        this.authenticationContext = authenticationContext;
        this.resourceId = resourceId;
        this.clientId = clientId;
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
    public ODataURL createODataURL() {
        return new ODataURLImpl();
    }

    @Override
    public Request createRequest() {
        return new RequestImpl();
    }

    @Override
    public String getPlatformUserAgent(String productName) {

        return String.format(
                "%s/1.0 (lang=%s; os=%s; os_version=%s; arch=%s; version=%s)",
                productName, "Java", "Android", Build.VERSION.RELEASE,
                Build.CPU_ABI, Constants.SDK_VERSION);
    }

    @Override
    public Credentials getCredentials() {
        return new Credentials() {
            @Override
            public void prepareRequest(Request request) {
                DefaultDependencyResolver.this.authenticationContext.acquireTokenSilentSync(resourceId, clientId, null);
            }
        };
    }
}
