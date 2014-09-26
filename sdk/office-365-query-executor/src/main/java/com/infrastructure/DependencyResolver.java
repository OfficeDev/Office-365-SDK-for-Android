package com.infrastructure;

/**
 * Created by marcote on 9/25/14.
 */
public class DependencyResolver {
    public HttpClient getHttpClient() {
        return new HttpClient();
    }

    public JsonSerializer getJsonSerializer() {
        return new JsonSerializer();
    }
}
