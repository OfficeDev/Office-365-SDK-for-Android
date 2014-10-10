package com.microsoft.office365.odata.impl.http;

import android.net.Uri;

import com.microsoft.office365.odata.interfaces.ODataURL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ODataURLImpl implements ODataURL{

    String baseUrl;
    List<String> pathComponents = new ArrayList<String>();
    Map<String, String> queryStringParameters = new HashMap<String, String>();

    @Override
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public void appendPathComponent(String pathComponent) {
        this.pathComponents.add(pathComponent);
    }

    @Override
    public void prependPathComponent(String pathComponent) {
        this.pathComponents.add(0, pathComponent);
    }

    @Override
    public void addQueryStringParameter(String name, String value) {
        queryStringParameters.put(name, value);
    }

    @Override
    public Map<String, String> getQueryStringParameters() {
        return new HashMap<String, String>(queryStringParameters);
    }

    @Override
    public String getBaseUrl() {
        return this.baseUrl;
    }

    @Override
    public List<String> getPathComponents() {
        return new ArrayList<String>(this.pathComponents);
    }

    @Override
    public String toString() {
        Uri.Builder builder = new Uri.Builder();

        builder.appendPath(this.baseUrl);

        for (String component : this.pathComponents) {
            builder.appendPath(component);
        }

        Set<String> parameterNames = this.queryStringParameters.keySet();
        for (String name : parameterNames) {
            builder.appendQueryParameter(name, this.queryStringParameters.get(name));
        }

        return builder.build().toString();
    }
}
