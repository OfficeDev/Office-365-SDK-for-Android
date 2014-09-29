package com.infrastructure.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by marcote on 9/29/14.
 */
public class EmptyResponse implements Response {

    private Map<String, List<String>> mHeaders = new HashMap<String, List<String>>();
    private int statusCode;

    public EmptyResponse(int statusCode, Map<String, List<String>> headers) {
        this.mHeaders = headers;
        this.statusCode = statusCode;
    }

    @Override
    public Map<String, List<String>> getHeaders() {
        return mHeaders;
    }

    @Override
    public List<String> getHeader(String headerName) {
        return null;
    }

    @Override
    public String readToEnd() throws IOException {
        return "";
    }

    @Override
    public String readLine() throws IOException {
        return "";
    }

    @Override
    public byte[] readAllBytes() throws IOException {
        return new byte[0];
    }

    @Override
    public int getStatus() {
        return statusCode;
    }
}
