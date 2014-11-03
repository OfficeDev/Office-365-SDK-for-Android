/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;

import static com.microsoft.services.odata.EntityCollectionFetcherHelper.addListResultCallback;
import static com.microsoft.services.odata.EntityFetcherHelper.addEntityResultCallback;
import static com.microsoft.services.odata.EntityFetcherHelper.setPathForCollections;
import static com.microsoft.services.odata.EntityFetcherHelper.setSelectorUrl;
import static com.microsoft.services.odata.Helpers.serializeToJsonByteArray;
import static com.microsoft.services.odata.Helpers.addCustomParametersToODataURL;
import java.util.*;

/**
 * The type ODataCollectionFetcher.
 * @param <TEntity>  the type parameter
 * @param <TFetcher>  the type parameter
 * @param <TOperation>  the type parameter
 */
public class ODataCollectionFetcher<TEntity, TFetcher extends ODataEntityFetcher, TOperation extends ODataOperations> extends ODataExecutable implements Readable<List<TEntity>> {

    private int top = -1;
    private int skip = -1;
    private String selectedId = null;
    private String urlComponent;
    private ODataExecutable parent;
    private Class<TEntity> clazz;
    private TOperation operations;
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
                                  Class<TEntity> clazz, Class<TOperation> operationClazz) {
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
    public ODataCollectionFetcher<TEntity, TFetcher, TOperation> top(int top) {
        this.top = top;
        return this;
    }

	/**
     * Skip ODataCollectionFetcher.
     *
     * @param skip the skip
     * @return the o data collection fetcher
     */
    public ODataCollectionFetcher<TEntity, TFetcher, TOperation> skip(int skip) {
        this.skip = skip;
        return this;
    }

	/**
     * Select ODataCollectionFetcher.
     *
     * @param select the select
     * @return the o data collection fetcher
     */
    public ODataCollectionFetcher<TEntity, TFetcher, TOperation> select(String select) {
        this.select = select;
        return this;
    }

	/**
     * Expand ODataCollectionFetcher.
     *
     * @param expand the expand
     * @return the o data collection fetcher
     */
    public ODataCollectionFetcher<TEntity, TFetcher, TOperation> expand(String expand) {
        this.expand = expand;
        return this;
    }

	/**
     * Filter ODataCollectionFetcher.
     *
     * @param filter the filter
     * @return the o data collection fetcher
     */
    public ODataCollectionFetcher<TEntity, TFetcher, TOperation> filter(String filter) {
        this.filter = filter;
        return this;
    }

	 /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public TFetcher getById(String id) {
        this.selectedId = id;
	    String packageName = operations.getClass().getPackage().getName();
        String[] classNameParts = (clazz.getCanonicalName() + "Fetcher").split("\\.");
        String className = packageName + "." + classNameParts[classNameParts.length - 1];

        try {
            Class entityQueryClass = Class.forName(className);
            ODataEntityFetcher odataEntityQuery = (ODataEntityFetcher) entityQueryClass
                    .getConstructor(String.class, ODataExecutable.class)
                    .newInstance("", this);

            return (TFetcher) odataEntityQuery;
        } catch (Throwable e) {
            // if this happens, we couldn't find the xxxQuery class at runtime.
            // this must NEVER happen
            throw new RuntimeException(e);
        }
    }

    @Override
    ListenableFuture<byte[]> oDataExecute(ODataURL path, byte[] content, HttpVerb verb, Map<String, String> headers) {
		if (selectedId == null) {
			setPathForCollections(path, urlComponent, top, skip, select, expand, filter);
        } else {
            setSelectorUrl(path, urlComponent, selectedId);
        }
		addCustomParametersToODataURL(path, getCustomParameters(), getResolver());
        Map<String, String> newHeaders = new HashMap<String, String>(getCustomHeaders());
        newHeaders.putAll(headers);
		return parent.oDataExecute(path, content, verb, newHeaders);
    }

    @Override
    public DependencyResolver getResolver() {
        return parent.getResolver();
    }

    @Override
    public ListenableFuture<List<TEntity>> read() {
		final SettableFuture<List<TEntity>> result = SettableFuture.create();
        ListenableFuture<byte[]> future = oDataExecute(getResolver().createODataURL(), null, HttpVerb.GET, getCustomHeaders());
        addListResultCallback(result, future, getResolver(), clazz);

        return result;
    }

	 /**
     * Add listenable future.
     *
     * @param entity the entity
     * @return the listenable future
     */
    public ListenableFuture<TEntity> add(TEntity entity) {
		final SettableFuture<TEntity> result = SettableFuture.create();
        byte[] payloadBytes = serializeToJsonByteArray(entity, getResolver());
        ListenableFuture<byte[]> future = oDataExecute(getResolver().createODataURL(), payloadBytes, HttpVerb.POST, getCustomHeaders());
        addEntityResultCallback(result, future, getResolver(), clazz);

        return result;
    }

	 /**
     * Gets operations.
     *
     * @return the operations
     */
    public TOperation getOperations() {
        return this.operations;
    }
}