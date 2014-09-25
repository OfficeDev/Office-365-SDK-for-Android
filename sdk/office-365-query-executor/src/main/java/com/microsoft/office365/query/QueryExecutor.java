package com.microsoft.office365.query;

import com.google.common.util.concurrent.ListenableFuture;

/**
 * Created by marcote on 9/25/14.
 */
public interface QueryExecutor {
    public <E> ListenableFuture<?> executeQuery(String path, Class<E> clazz);
}
