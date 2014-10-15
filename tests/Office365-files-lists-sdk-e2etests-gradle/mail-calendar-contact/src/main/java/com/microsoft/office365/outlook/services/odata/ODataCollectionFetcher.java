/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import java.util.List;

import static com.microsoft.office365.odata.EntityCollectionFetcherHelper.addListResultCallback;
import static com.microsoft.office365.odata.EntityFetcherHelper.addEntityResultCallback;
import static com.microsoft.office365.odata.EntityFetcherHelper.setPathForCollections;
import static com.microsoft.office365.odata.EntityFetcherHelper.setSelectorUrl;
import static com.microsoft.office365.odata.Helpers.serializeToJsonByteArray;
import static com.microsoft.office365.odata.Helpers.addCustomParametersToODataURL;

public class ODataCollectionFetcher<T, U, V> extends ODataExecutable implements Readable<List<T>> {

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
	    String packageName = operations.getClass().getPackage().getName();
        String[] classNameParts = (clazz.getCanonicalName() + "Fetcher").split("\\.");
        String className = packageName + "." + classNameParts[classNameParts.length - 1];

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
    ListenableFuture<byte[]> oDataExecute(ODataURL path, byte[] content, HttpVerb verb) {
		if (selectedId == null) {
			setPathForCollections(path, urlComponent, top, skip, select, expand, filter);
        } else {
            setSelectorUrl(path, urlComponent, selectedId);
        }
		addCustomParametersToODataURL(path, getCustomParameters(), getResolver());
		return parent.oDataExecute(path, content, verb);

    }

    @Override
    public DependencyResolver getResolver() {
        return parent.getResolver();
    }

    @Override
    public ListenableFuture<List<T>> read() {
		final SettableFuture<List<T>> result = SettableFuture.create();
        ListenableFuture<byte[]> future = oDataExecute(getResolver().createODataURL(), null, HttpVerb.GET);
        addListResultCallback(result, future, getResolver(), clazz);

        return result;
    }

    public ListenableFuture<T> add(T entity) {
		final SettableFuture<T> result = SettableFuture.create();
        byte[] payloadBytes = serializeToJsonByteArray(entity, getResolver());
        ListenableFuture<byte[]> future = oDataExecute(getResolver().createODataURL(), payloadBytes, HttpVerb.POST);
        addEntityResultCallback(result, future, getResolver(), clazz);

        return result;
    }

    public V getOperations() {
        return this.operations;
    }

    public ODataCollectionFetcher<T, U, V> addParameter(String name, Object value) {
	    addCustomParameter(name, value);
		return this;
    }

}