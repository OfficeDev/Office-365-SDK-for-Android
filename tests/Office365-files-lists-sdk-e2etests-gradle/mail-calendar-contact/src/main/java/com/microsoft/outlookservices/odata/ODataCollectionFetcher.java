/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;
import java.util.List;

import static com.microsoft.services.odata.EntityCollectionFetcherHelper.addListResultCallback;
import static com.microsoft.services.odata.EntityFetcherHelper.addEntityResultCallback;
import static com.microsoft.services.odata.EntityFetcherHelper.setPathForCollections;
import static com.microsoft.services.odata.EntityFetcherHelper.setSelectorUrl;
import static com.microsoft.services.odata.Helpers.serializeToJsonByteArray;
import static com.microsoft.services.odata.Helpers.addCustomParametersToODataURL;

/**
 * The type ODataCollectionFetcher.
 * @param <T>  the type parameter
 * @param <U>  the type parameter
 * @param <V>  the type parameter
 */
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

	 /**
     * Instantiates a new ODataCollectionFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     * @param clazz the clazz
     * @param operationClazz the operation clazz
     */
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

	/**
     * Reset void.
     */
	public void reset() {
		this.top = -1;
		this.skip = -1;
		this.selectedId = null;
		this.select = null;
		this.expand = null;
		this.filter = null;
	}

	 /**
     * Top ODataCollectionFetcher.
     *
     * @param top the top
     * @return the o data collection fetcher
     */
    public ODataCollectionFetcher<T, U, V> top(int top) {
        this.top = top;
        return this;
    }

	/**
     * Skip ODataCollectionFetcher.
     *
     * @param skip the skip
     * @return the o data collection fetcher
     */
    public ODataCollectionFetcher<T, U, V> skip(int skip) {
        this.skip = skip;
        return this;
    }

	/**
     * Select ODataCollectionFetcher.
     *
     * @param select the select
     * @return the o data collection fetcher
     */
    public ODataCollectionFetcher<T, U, V> select(String select) {
        this.select = select;
        return this;
    }

	/**
     * Expand ODataCollectionFetcher.
     *
     * @param expand the expand
     * @return the o data collection fetcher
     */
    public ODataCollectionFetcher<T, U, V> expand(String expand) {
        this.expand = expand;
        return this;
    }

	/**
     * Filter ODataCollectionFetcher.
     *
     * @param filter the filter
     * @return the o data collection fetcher
     */
    public ODataCollectionFetcher<T, U, V> filter(String filter) {
        this.filter = filter;
        return this;
    }

	 /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
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

	 /**
     * Add listenable future.
     *
     * @param entity the entity
     * @return the listenable future
     */
    public ListenableFuture<T> add(T entity) {
		final SettableFuture<T> result = SettableFuture.create();
        byte[] payloadBytes = serializeToJsonByteArray(entity, getResolver());
        ListenableFuture<byte[]> future = oDataExecute(getResolver().createODataURL(), payloadBytes, HttpVerb.POST);
        addEntityResultCallback(result, future, getResolver(), clazz);

        return result;
    }

	 /**
     * Gets operations.
     *
     * @return the operations
     */
    public V getOperations() {
        return this.operations;
    }
}