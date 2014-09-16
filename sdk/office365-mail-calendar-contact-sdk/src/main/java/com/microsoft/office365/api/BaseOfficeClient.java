package com.microsoft.office365.api;

import java.net.URI;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.olingo.client.api.http.HttpMethod;
import org.apache.olingo.client.api.v4.EdmEnabledODataClient;
import org.apache.olingo.client.core.http.DefaultHttpUriRequestFactory;
import org.apache.olingo.commons.api.format.ODataFormat;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.microsoft.office365.oauth.OAuthCredentials;
import com.microsoft.office365.Service;
import com.microsoft.office365.microsoft.exchange.services.odata.model.EntityContainer;

/**
 * The Class BaseOfficeClient.
 */
public abstract class BaseOfficeClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseOfficeClient.class);

    private final String odataEndpoint;
    private final String resourceId;
    private Service<EdmEnabledODataClient> service;
    private EntityContainer container;

    protected BaseOfficeClient(Builder builder) {

        odataEndpoint = builder.getODataEndpoint();
        resourceId = builder.getResourceId();

        initialize(builder);
    }

    public EntityContainer getEntityContainer(){
        return container;
    }

    protected void initialize(final Builder builder) {

        service = Service.getV4(resourceId + odataEndpoint, false);
        service.getClient().getConfiguration()
                .setDefaultPubFormat(ODataFormat.JSON_FULL_METADATA);

        service.getClient().getConfiguration().setUseChuncked(false);
        service.getClient().getConfiguration().setAddressingDerivedTypes(false);
        service.getClient().getConfiguration().setUseUrlOperationFQN(false);

        service.getClient().getConfiguration()
                .setHttpUriRequestFactory(new DefaultHttpUriRequestFactory() {
                    @Override
                    public HttpUriRequest create(HttpMethod method, URI uri) {

                        HttpUriRequest request = super.create(method, uri);
                        request.addHeader("Authorization", "Bearer " + builder.getCredentials().getToken());

                        LOGGER.debug("URI" + uri.getRawQuery());

                        return request;
                    }
                });

        container = service.getEntityContainer(EntityContainer.class);
    }

    /**
     * The Class Builder.
     */
    public abstract static class Builder {

        private OAuthCredentials mCredentials;
        private String mResourceId;
        private String mOdataEndpoint;

        protected Builder(final OAuthCredentials credentials,
                          String resourceId, String odataEndpoint) {

            mCredentials = credentials;
            mResourceId = resourceId;
            mOdataEndpoint = odataEndpoint;
        }

        /**
         * Instantiates a new builder.
         */
        public Builder() {
        }

        /**
         * Builds the.
         *
         * @return the base office client
         */
        public abstract BaseOfficeClient build();

        /**
         * Sets the resource id.
         *
         * @param resourceId the resource id
         * @return the builder
         */
        public Builder setResourceId(String resourceId) {
            mResourceId = resourceId;
            return this;
        }

        /**
         * Gets the resource id.
         *
         * @return the resource id
         */
        public String getResourceId() {
            return mResourceId;
        }

        /**
         * Sets the odata endpoint.
         *
         * @param odataEndpoint the odata endpoint
         * @return the builder
         */
        public Builder setODataEndpoint(String odataEndpoint) {
            mOdataEndpoint = odataEndpoint;
            return this;
        }

        /**
         * Sets the credentials.
         *
         * @param credentials the credentials
         * @return the builder
         */
        public Builder setCredentials(OAuthCredentials credentials) {
            mCredentials = credentials;
            return this;
        }

        /**
         * Gets the odata endpoint.
         *
         * @return the odata endpoint
         */
        public String getODataEndpoint() {
            return mOdataEndpoint;
        }

        /**
         * Gets the credentials.
         *
         * @return the credentials
         */
        public OAuthCredentials getCredentials() {
            return mCredentials;
        }
    }
}
