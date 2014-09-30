/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata.impl.http;

import com.microsoft.office365.odata.interfaces.Response;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Response implementation based on an InputStream
 */
public class ResponseImpl implements Response {
    private InputStream mStream;
    private int mStatus;
    Map<String, List<String>> mHeaders;
    CloseableHttpClient mClient;

    public ResponseImpl(InputStream stream, int status, Map<String, List<String>> headers, CloseableHttpClient client) {
        mHeaders = new HashMap<String, List<String>>(headers);
        mStream = stream;
        mStatus = status;
        mClient = client;
    }

    @Override
    public int getStatus() {
        return mStatus;
    }

    @Override
    public InputStream getStream() throws IOException {
        return mStream;
    }

    @Override
    public void close() throws IOException {
        mClient.close();
    }

    @Override
    public Map<String, List<String>> getHeaders() {
        return new HashMap<String, List<String>>(mHeaders);
    }

    @Override
    public List<String> getHeaders(String headerName) {
        return mHeaders.get(headerName);
    }
}
