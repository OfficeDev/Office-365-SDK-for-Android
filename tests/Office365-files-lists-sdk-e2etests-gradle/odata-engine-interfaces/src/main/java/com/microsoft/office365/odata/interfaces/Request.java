package com.microsoft.office365.odata.interfaces;

import java.util.Map;

public interface Request {
    public void setContent(byte[] content);

    public byte[] getContent();

    public Map<String, String> getHeaders();

    public void setHeaders(Map<String, String> headers);

    public void addHeader(String name, String value);

    public void removeHeader(String name);

    public HttpVerb getVerb();

    public void setVerb(HttpVerb httpVerb);

    public void setUrl(String url);

    public String getUrl();
}
