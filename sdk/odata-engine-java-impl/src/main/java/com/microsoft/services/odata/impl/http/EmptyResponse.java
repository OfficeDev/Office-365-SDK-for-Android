package com.microsoft.services.odata.impl.http;

import com.microsoft.services.odata.interfaces.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Empty response.
 */
public class EmptyResponse implements Response {

    private Map<String, List<String>> mHeaders = new HashMap<String, List<String>>();
    private int statusCode;

    /**
     * Instantiates a new Empty response.
     *
     * @param statusCode the status code
     * @param headers the headers
     */
    public EmptyResponse(int statusCode, Map<String, List<String>> headers) {
        this.mHeaders = headers;
        this.statusCode = statusCode;
    }

    @Override
    public Map<String, List<String>> getHeaders() {
        return new HashMap<String, List<String>>(mHeaders);
    }

    @Override
    public List<String> getHeaders(String headerName) {
        return mHeaders.get(headerName);
    }

    @Override
    public int getStatus() {
        return statusCode;
    }

    @Override
    public InputStream getStream() throws IOException {
        return null;
    }

    @Override
    public void close() throws IOException {
    }
}
