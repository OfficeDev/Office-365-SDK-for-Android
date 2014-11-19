package com.microsoft.services.odata.interfaces;

/**
 * The interface ODataResponse.
 */
public interface ODataResponse {

    /**
     * Get payload.
     *
     * @return the byte [ ]
     */
    public byte[] getPayload();

    /**
     * Gets response.
     *
     * @return the response
     */
    public Response getResponse();
}
