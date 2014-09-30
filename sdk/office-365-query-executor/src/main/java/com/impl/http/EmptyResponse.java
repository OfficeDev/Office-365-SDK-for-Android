package com.impl.http;

import com.interfaces.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmptyResponse implements Response {

    private Map<String, List<String>> mHeaders = new HashMap<String, List<String>>();
    private int statusCode;

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
