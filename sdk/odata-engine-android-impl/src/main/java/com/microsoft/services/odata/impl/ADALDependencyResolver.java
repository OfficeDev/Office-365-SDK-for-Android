package com.microsoft.services.odata.impl;


import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationResult;
import com.microsoft.services.odata.interfaces.Credentials;

/**
 * The type ADAL dependency resolver.
 */
public class ADALDependencyResolver extends DefaultDependencyResolver {

    private AuthenticationContext context;
    private String resourceId;
    private String clientId;

    /**
     * Instantiates a new dependency resolver.
     * @param context the context
     * @param resourceId the resource id
     * @param clientId the client id
     */
    public ADALDependencyResolver(AuthenticationContext context, String resourceId, String clientId) {
        super("");
        this.context = context;
        this.resourceId = resourceId;
        this.clientId = clientId;
    }

    /**
     * Sets resource id.
     *
     * @param resourceId the resource id
     */
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * Gets resource id.
     *
     * @return the resource id
     */
    public String getResourceId() {
        return this.resourceId;
    }

    @Override
    public Credentials getCredentials() {
        AuthenticationResult result = this.context.acquireTokenSilentSync(resourceId, clientId, null);
        return new OAuthCredentials(result.getAccessToken());
    }
}
