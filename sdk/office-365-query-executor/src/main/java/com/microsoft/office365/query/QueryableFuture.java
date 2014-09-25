package com.microsoft.office365.query;

import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by marcote on 9/25/14.
 */
public abstract class QueryableFuture<V> extends AbstractFuture<V> implements QueryExecutor {

    public QueryExecutor parent;

    abstract String getEntityName();

    public boolean set(V value) {
        return super.set(value);
    }

    @Override
    public boolean setException(Throwable throwable) {
        return super.setException(throwable);
    }

    @Override
    public <E> ListenableFuture<?> executeQuery(String path, Class<E> clazz) {
        return parent.executeQuery("/" + getEntityName() + path, clazz);
    }

    private int mTop = 0;
    private int mSkip = 0;
    public QueryableFuture<V> top(int top) {
        mTop = top;
        return this;
    }

    public QueryableFuture<V> skip(int skip) {
        mSkip = skip;
        return this;
    }

    public <E> ListenableFuture<List<E>> execute(Class<E> clazz) {

        Class<?> arrayClass = (Class<?>) Array.newInstance(clazz, 0).getClass();

        String query = "?$top=" + mTop + "&$skip=" + mSkip;

        ListenableFuture<?> future = (ListenableFuture<?>) executeQuery(query, arrayClass);

        final SettableFuture<List<E>> result = SettableFuture.create();

        Futures.addCallback((ListenableFuture<E>) future, new FutureCallback<E>() {
            @Override
            public void onSuccess(E elements) {
                result.set(Arrays.asList((E[]) elements));
            }

            @Override
            public void onFailure(Throwable throwable) {
                result.setException(throwable);
            }
        });

        return result;
    }
}
