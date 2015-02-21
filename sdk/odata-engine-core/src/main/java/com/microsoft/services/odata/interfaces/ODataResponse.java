package com.microsoft.services.odata.interfaces;

import java.io.IOException;
import java.io.InputStream;
/**
 * The interface ODataResponse.
 */
public interface ODataResponse {

    /**
     * Get payload.
     *
     * @return the byte [ ]
     */
    public byte[] getPayload() throws IOException;

    /**
     * Gets response.
     *
     * @return the response
     */
    public Response getResponse();

    /**
     * Opens a streamed response.
     *
     * @return the stream
     */
    InputStream openStreamedResponse();

    /**
     * Closes the streamed response
     * @throws IOException
     */
    void closeStreamedResponse() throws IOException;
}
