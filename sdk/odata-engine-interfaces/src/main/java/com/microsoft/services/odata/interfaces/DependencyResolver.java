package com.microsoft.services.odata.interfaces;

/**
 * The interface Dependency resolver.
 */
public interface DependencyResolver {
    /**
     * Gets http transport.
     *
     * @return the http transport
     */
    HttpTransport getHttpTransport();

    /**
     * Gets logger.
     *
     * @return the logger
     */
    Logger getLogger();

    /**
     * Gets json serializer.
     *
     * @return the json serializer
     */
    JsonSerializer getJsonSerializer();

    /**
     * Gets credentials factory.
     *
     * @return the credentials factory
     */
    CredentialsFactory getCredentialsFactory();

    /**
     * Create o data uRL.
     *
     * @return the o data uRL
     */
    ODataURL createODataURL();

    /**
     * Gets the user agent for a specific platform
     * @param productName the product name
     * @return the user agent
     */
    String getPlatformUserAgent(String productName);
}
