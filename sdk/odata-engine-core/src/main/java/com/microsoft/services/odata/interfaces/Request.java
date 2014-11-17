package com.microsoft.services.odata.interfaces;

import java.util.Map;

/**
 * The interface Request.
 */
public interface Request {
    /**
     * Sets content.
     *
     * @param content the content
     */
    public void setContent(byte[] content);

    /**
     * Get content.
     *
     * @return the byte [ ]
     */
    public byte[] getContent();

    /**
     * Gets headers.
     *
     * @return the headers
     */
    public Map<String, String> getHeaders();

    /**
     * Sets headers.
     *
     * @param headers the headers
     */
    public void setHeaders(Map<String, String> headers);

    /**
     * Add header.
     *
     * @param name the name
     * @param value the value
     */
    public void addHeader(String name, String value);

    /**
     * Remove header.
     *
     * @param name the name
     */
    public void removeHeader(String name);

    /**
     * Gets verb.
     *
     * @return the verb
     */
    public HttpVerb getVerb();

    /**
     * Sets verb.
     *
     * @param httpVerb the http verb
     */
    public void setVerb(HttpVerb httpVerb);

    /**
     * Sets url.
     *
     * @param url the url
     */
    public void setUrl(ODataURL url);

    /**
     * Gets url.
     *
     * @return the url
     */
    public ODataURL getUrl();
}
