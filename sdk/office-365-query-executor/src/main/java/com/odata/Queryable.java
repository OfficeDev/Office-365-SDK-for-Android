package com.odata;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.infrastructure.DependencyResolver;
import com.infrastructure.Executable;
import com.infrastructure.Logger;
import com.infrastructure.http.Constants;

import java.util.List;

public class Queryable<T,U> extends ODataExecutable implements Executable<List<T>> {
    private int top;
    private int skip;
    private String selectedId = null;
    private String urlComponent;
    private ODataExecutable parent;
    private Class<T> clazz;

    public Queryable(String urlComponent, ODataExecutable parent, Class<T> clazz) {
        this.urlComponent = urlComponent;
        this.parent = parent;
        this.clazz = clazz;
    }

    public Queryable<T, U> top(int top) {

        this.top = top;
        return this;
    }

    public Queryable<T, U> skip(int skip) {

        this.skip = skip;
        return this;
    }

    public U getById(String id) {
        this.selectedId = id;

        String[] classNameParts = (clazz.getCanonicalName() + "Query").split("\\.");

        // TODO: use proper namespace resolution for this class!!
        String className = "com.odata." + classNameParts[classNameParts.length - 1];

        try {
            Class entityQueryClass = Class.forName(className);
            ODataEntityQuery odataEntityQuery = (ODataEntityQuery) entityQueryClass
                    .getConstructor(String.class, ODataExecutable.class)
                    .newInstance("", this);

            return (U)odataEntityQuery;
        } catch (Throwable e) {
            // if this happens, we couldn't find the xxxQuery class at runtime.
            // this must NEVER happen
            throw new RuntimeException(e);
        }
    }

    @Override
    ListenableFuture<byte[]> oDataExecute(String path) {
        if (selectedId == null) {
            String query = "?$top=" + top + "&$skip=" + skip;
            return parent.oDataExecute(urlComponent + query);
        } else {
            String selector = "('" + selectedId + "')";

            return parent.oDataExecute(urlComponent + selector + "/" + path);
        }
    }

    @Override
    public DependencyResolver getResolver() {
        return parent.getResolver();
    }

    @Override
    public ListenableFuture<List<T>> execute() {
        final SettableFuture<List<T>> result = SettableFuture.create();
        ListenableFuture<byte[]> future = oDataExecute("");
        Futures.addCallback(future, new FutureCallback<byte[]>() {
            @Override
            public void onSuccess(byte[] payload) {
                List<T> list;
                try {
                    String string = new String(payload, Constants.UTF8_NAME);
                    DependencyResolver resolver = getResolver();
                    list = resolver.getJsonSerializer().deserializeList(string, clazz);
                    result.set(list);
                } catch (Throwable e) {
                    result.setException(e);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                result.setException(throwable);
            }
        });

        return result;
    }
}
