package com.microsoft.services.odata.interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * The interface Response.
 */
public interface Response {
    /**
     * Gets headers.
     *
     * @return the headers
     */
    public Map<String, List<String>> getHeaders();

    /**
     * Gets headers.
     *
     * @param headerName the header name
     * @return the headers
     */
    public List<String> getHeaders(String headerName);

    /**
     * Gets status.
     *
     * @return the status
     */
    public int getStatus();

    /**
     * Gets stream.
     *
     * @return the stream
     */
    public InputStream getStream();

    /**
     * Close void.
     *
     * @throws IOException the iO exception
     */
    public void close() throws IOException;
}
