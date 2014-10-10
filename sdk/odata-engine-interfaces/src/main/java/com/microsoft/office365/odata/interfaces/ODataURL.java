package com.microsoft.office365.odata.interfaces;

import java.util.List;
import java.util.Map;

public interface ODataURL {
    void setBaseUrl(String baseUrl);

    void appendPathComponent(String pathComponent);

    void prependPathComponent(String pathComponent);

    void addQueryStringParameter(String name, String value);

    Map<String, String> getQueryStringParameters();

    String getBaseUrl();

    List<String> getPathComponents();
}
