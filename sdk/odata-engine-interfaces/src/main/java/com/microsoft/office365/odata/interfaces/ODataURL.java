package com.microsoft.office365.odata.interfaces;

import java.util.List;
import java.util.Map;

/**
 * The interface OData uRL.
 */
public interface ODataURL {
    /**
     * Sets base url.
     *
     * @param baseUrl the base url
     */
    void setBaseUrl(String baseUrl);

    /**
     * Append path component.
     *
     * @param pathComponent the path component
     */
    void appendPathComponent(String pathComponent);

    /**
     * Prepend path component.
     *
     * @param pathComponent the path component
     */
    void prependPathComponent(String pathComponent);

    /**
     * Add query string parameter.
     *
     * @param name the name
     * @param value the value
     */
    void addQueryStringParameter(String name, String value);

    /**
     * Gets query string parameters.
     *
     * @return the query string parameters
     */
    Map<String, String> getQueryStringParameters();

    /**
     * Gets base url.
     *
     * @return the base url
     */
    String getBaseUrl();

    /**
     * Gets path components.
     *
     * @return the path components
     */
    List<String> getPathComponents();
}
