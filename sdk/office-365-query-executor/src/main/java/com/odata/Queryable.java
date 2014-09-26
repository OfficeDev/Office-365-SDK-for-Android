package com.odata;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.infrastructure.DependencyResolver;
import com.infrastructure.http.Response;

import java.io.IOException;
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
    ListenableFuture<Response> oDataExecute(String path) {

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

        ListenableFuture<Response> future = oDataExecute("");
        Futures.addCallback(future, new FutureCallback<Response>() {
            @Override
            public void onSuccess(Response s) {
                List<T> list = null;
                try {
                    list = Arrays.asList(getResolver().getJsonSerializer().deserialize(s.readToEnd(), clazz));
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
