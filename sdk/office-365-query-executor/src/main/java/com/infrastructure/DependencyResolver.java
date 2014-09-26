package com.infrastructure;

import com.infrastructure.http.Platform;

/**
 * Created by marcote on 9/25/14.
 */
public class DependencyResolver {
    public HttpConnection getHttpConnection() {
        return Platform.createHttpConnection();
    }

    public JsonSerializer getJsonSerializer() {
        return new JsonSerializer();
    }

}
