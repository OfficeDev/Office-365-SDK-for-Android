/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ODataCollection<T, U, V> extends ODataExecutable implements Executable<List<T>> {
    private int top;
    private int skip;
    private String selectedId = null;
    private String urlComponent;
    private ODataExecutable parent;
    private Class<T> clazz;
    private V operations;

    public ODataCollection(String urlComponent, ODataExecutable parent, Class<T> clazz, Class<V> operationClazz) {
        this.urlComponent = urlComponent;
        this.parent = parent;
        this.clazz = clazz;

        try {
            operations = operationClazz.getConstructor(String.class, ODataExecutable.class).newInstance("", this);
        } catch (Throwable t) {
        }
    }

    public ODataCollection<T, U, V> top(int top) {

        this.top = top;
        return this;
    }

    public ODataCollection<T, U, V> skip(int skip) {

        this.skip = skip;
        return this;
    }

    public U getById(String id) {
        this.selectedId = id;

        String[] classNameParts = (clazz.getCanonicalName() + "Query").split("\\.");

        String className = "com.microsoft.office365.odata." + classNameParts[classNameParts.length - 1];

        try {
            Class entityQueryClass = Class.forName(className);
            ODataEntityQuery odataEntityQuery = (ODataEntityQuery) entityQueryClass
                    .getConstructor(String.class, ODataExecutable.class)
                    .newInstance("", this);

            return (U) odataEntityQuery;
        } catch (Throwable e) {
            // if this happens, we couldn't find the xxxQuery class at runtime.
            // this must NEVER happen
            throw new RuntimeException(e);
        }
    }

    @Override
    ListenableFuture<byte[]> oDataExecute(String path, byte[] content, HttpVerb verb) {
        if (selectedId == null) {
            String query = "?$top=" + top + "&$skip=" + skip;
            return parent.oDataExecute(urlComponent + query, content, verb);
        } else {
            String selector = "('" + selectedId + "')";
            return parent.oDataExecute(urlComponent + selector + "/" + path, content, verb);
        }
    }

    @Override
    public DependencyResolver getResolver() {
        return parent.getResolver();
    }

    @Override
    public ListenableFuture<List<T>> execute() {
        final SettableFuture<List<T>> result = SettableFuture.create();
        ListenableFuture<byte[]> future = oDataExecute("", null, HttpVerb.GET);
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

    public ListenableFuture<T> add(T entity) {
        final SettableFuture<T> result = SettableFuture.create();

        String payload = getResolver().getJsonSerializer().serialize(entity);

        ListenableFuture<byte[]> future = oDataExecute("", payload.getBytes(Constants.UTF8), HttpVerb.POST);

        Futures.addCallback(future, new FutureCallback<byte[]>() {
            @Override
            public void onSuccess(byte[] payload) {
                try {
                    String string = new String(payload, Constants.UTF8_NAME);
                    DependencyResolver resolver = getResolver();
                    T entity = resolver.getJsonSerializer().deserialize(string, clazz);
                    result.set(entity);
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

    public V getOperations() {
        return operations;
    }
}