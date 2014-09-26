package com.odata;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.infrastructure.DependencyResolver;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by PabloZaiden on 26/09/14.
 */
public class Queryable<T> extends ODataExecutable implements Executable<List<T>> {
    private int top;
    private int skip;
    private String urlComponent;
    private ODataExecutable parent;
    private Class<T> clazz;

    public Queryable(String urlComponent, ODataExecutable parent, Class<T> clazz) {
        this.urlComponent = urlComponent;
        this.parent = parent;
        this.clazz = (Class<T>) Array.newInstance(clazz, 0).getClass();
    }

    public Queryable<T> top(int top) {

        this.top = top;
        return this;
    }

    public Queryable<T> skip(int skip) {

        this.skip = skip;
        return this;
    }

    @Override
    ListenableFuture<String> oDataExecute(String path) {
        String query = "?$top=" + top + "&$skip=" + skip;
        return parent.oDataExecute(urlComponent + query);
    }

    @Override
    public DependencyResolver getResolver() {
        return parent.getResolver();
    }

    @Override
    public ListenableFuture<List<T>> execute() {
        final SettableFuture<List<T>> result = SettableFuture.create();

        ListenableFuture<String> future = oDataExecute("");
        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(String s) {
                List<T> list = Arrays.asList(getResolver().getJsonSerializer().deserialize(s, clazz));
                result.set(list);
            }

            @Override
            public void onFailure(Throwable throwable) {
                result.setException(throwable);
            }
        });

        return result;
    }
}
