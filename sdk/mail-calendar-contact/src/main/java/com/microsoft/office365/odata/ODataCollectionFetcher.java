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

public class ODataCollectionFetcher<T, U, V> extends ODataExecutable implements Executable<List<T>> {

    private int top = -1;
    private int skip = -1;
    private String selectedId = null;
    private String urlComponent;
    private ODataExecutable parent;
    private Class<T> clazz;
    private V operations;
    private String select = null;
    private String expand = null;
    private String filter = null;

    public ODataCollectionFetcher(String urlComponent, ODataExecutable parent,
                           Class<T> clazz, Class<V> operationClazz) {
        this.urlComponent = urlComponent;
        this.parent = parent;
        this.clazz = clazz;

		this.reset();

        try {
            this.operations = operationClazz.getConstructor(String.class,
                         ODataExecutable.class).newInstance("", this);
        } catch (Throwable ignored) {
        }
    }

	public void reset() {
		this.top = -1;
		this.skip = -1;
		this.selectedId = null;
		this.select = null;
		this.expand = null;
		this.filter = null;
	}

    public ODataCollectionFetcher<T, U, V> top(int top) {
        this.top = top;
        return this;
    }

    public ODataCollectionFetcher<T, U, V> skip(int skip) {
        this.skip = skip;
        return this;
    }

    public ODataCollectionFetcher<T, U, V> select(String select) {
        this.select = select;
        return this;
    }

    public ODataCollectionFetcher<T, U, V> expand(String expand) {
        this.expand = expand;
        return this;
    }

    public ODataCollectionFetcher<T, U, V> filter(String filter) {
        this.filter = filter;
        return this;
    }

    public U getById(String id) {
        this.selectedId = id;

        String[] classNameParts = (clazz.getCanonicalName() + "Fetcher").split("\\.");

        String className = "com.microsoft.office365.odata." + classNameParts[classNameParts.length - 1];

        try {
            Class entityQueryClass = Class.forName(className);
            ODataEntityFetcher odataEntityQuery = (ODataEntityFetcher) entityQueryClass
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
            StringBuilder query = new StringBuilder();

            query.append("?");
            if (top > -1) {
                query.append("&$top=");
                query.append(top);
            }

            if (skip> -1) {
                query.append("&$skip=");
                query.append(skip);
            }

            if (select != null) {
                query.append("&$select=");
                query.append(select);
            }

            if (expand != null) {
                query.append("&expand=");
                query.append(expand);
            }

            if (filter!= null) {
                query.append("&filter=");
                query.append(filter);
            }

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
                    list = getResolver().getJsonSerializer().deserializeList(string, clazz);
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
                    T entity = getResolver().getJsonSerializer().deserialize(string, clazz);
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
        return this.operations;
    }
}