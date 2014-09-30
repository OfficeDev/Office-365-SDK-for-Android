package com.interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface Response {
    public Map<String, List<String>> getHeaders();

    public List<String> getHeaders(String headerName);

    public int getStatus();

    public InputStream getStream() throws IOException;

    public void close() throws IOException;
}
