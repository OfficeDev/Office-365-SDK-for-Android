/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.odata;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.services.odata.interfaces.HttpVerb;
import com.microsoft.services.odata.interfaces.LogLevel;
import com.microsoft.services.odata.interfaces.ODataResponse;
import com.microsoft.services.odata.interfaces.ODataURL;
import com.microsoft.services.odata.interfaces.Request;

import java.util.List;

import static com.microsoft.services.odata.Helpers.addCustomParametersToODataRequest;
import static com.microsoft.services.odata.Helpers.transformToEntityListenableFuture;
import static com.microsoft.services.odata.Helpers.transformToStringListenableFuture;

/**
 * The type ODataCollectionFetcher.
 *
 * @param <TEntity>      the type parameter
 * @param <TFetcher>     the type parameter
 * @param <TOperations>  the type parameter
 */
public class ODataCollectionFetcher<TEntity, TFetcher extends ODataEntityFetcher, TOperations extends ODataOperations>
        extends ODataFetcher<TEntity>
        implements Readable<List<TEntity>> {

    private int top = -1;
    private int skip = -1;
    private String selectedId = null;
    private TOperations operations;
    private String select = null;
    private String expand = null;
    private String filter = null;
    private String orderBy = null;
    private String search = null;

    /**
     * Instantiates a new ODataCollectionFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     * @param clazz the clazz
     * @param operationClazz the operation clazz
     */
    public ODataCollectionFetcher(String urlComponent, ODataExecutable parent,
                                  Class<TEntity> clazz, Class<TOperations> operationClazz) {
        super(urlComponent, parent, clazz);

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
        this.orderBy = null;
        this.search = null;
    }

    /**
     * Top ODataCollectionFetcher.
     *
     * @param top the top
     * @return the o data collection fetcher
     */
    public ODataCollectionFetcher<TEntity, TFetcher, TOperations> top(int top) {
        this.top = top;
        return this;
    }

    /**
     * Skip ODataCollectionFetcher.
     *
     * @param skip the skip
     * @return the o data collection fetcher
     */
    public ODataCollectionFetcher<TEntity, TFetcher, TOperations> skip(int skip) {
        this.skip = skip;
        return this;
    }

    /**
     * Select ODataCollectionFetcher.
     *
     * @param select the select
     * @return the o data collection fetcher
     */
    public ODataCollectionFetcher<TEntity, TFetcher, TOperations> select(String select) {
        this.select = select;
        return this;
    }

    /**
     * Expand ODataCollectionFetcher.
     *
     * @param expand the expand
     * @return the o data collection fetcher
     */
    public ODataCollectionFetcher<TEntity, TFetcher, TOperations> expand(String expand) {
        this.expand = expand;
        return this;
    }

    /**
     * Filter ODataCollectionFetcher.
     *
     * @param filter the filter
     * @return the o data collection fetcher
     */
    public ODataCollectionFetcher<TEntity, TFetcher, TOperations> filter(String filter) {
        this.filter = filter;
        return this;
    }

    /**
     * Filter ODataCollectionFetcher.
     *
     * @param search the search
     * @return the o data collection fetcher
     */
    public ODataCollectionFetcher<TEntity, TFetcher, TOperations> search(String search) {
        this.search = search;
        return this;
    }

    /**
     * Order ODataCollectionFetcher.
     *
     * @param orderBy the orderBy
     * @return the o data collection fetcher
     */
    public ODataCollectionFetcher<TEntity, TFetcher, TOperations> orderBy(String orderBy) {
        this.orderBy = orderBy;
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
    protected ListenableFuture<ODataResponse> oDataExecute(Request request) {
        if (selectedId == null) {
            setPathForCollections(request.getUrl(), urlComponent, top, skip, select, expand, filter, orderBy, search);
        } else {
            setSelectorUrl(request.getUrl(), urlComponent, selectedId);
        }
        addCustomParametersToODataRequest(request, getParameters(), getHeaders());
        return parent.oDataExecute(request);
    }

    /**
     * Reads
     *
     * @return the listenable future
     */
    @Override
    public ListenableFuture<List<TEntity>> read() {
        return Helpers.transformToEntityListListenableFuture(readRaw(), this.clazz, getResolver());
    }

    /**
     * Reads raw
     *
     * @return the listenable future
     */
    @Override
    public ListenableFuture<String> readRaw() {
        return super.readRaw();
    }

    /**
     * Add listenable future.
     *
     * @param entity the entity
     * @return the listenable future
     */
    public ListenableFuture<TEntity> add(TEntity entity) {
        ListenableFuture<String> future = addRaw(getResolver().getJsonSerializer().serialize(entity));
        return transformToEntityListenableFuture(future, this.clazz, getResolver());
    }

    /**
     * Add raw.
     *
     * @param payload the payload
     * @return the listenable future
     */
    public ListenableFuture<String> addRaw(String payload) {
        byte[] payloadBytes = payload.getBytes(Constants.UTF8);

        Request request = getResolver().createRequest();
        request.setContent(payloadBytes);
        request.setVerb(HttpVerb.POST);

        ListenableFuture<ODataResponse> future = oDataExecute(request);
        return transformToStringListenableFuture(future);

    }

    /**
     * Gets operations.
     *
     * @return the operations
     */
    public TOperations getOperations() {
        return this.operations;
    }

    /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the ODataCollectionFetcher
     */
    public ODataCollectionFetcher<TEntity, TFetcher, TOperations> addParameter(String name, Object value) {
        addCustomParameter(name, value);
        return this;
    }

    /**
     * Add header.
     *
     * @param name the name
     * @param value the value
     * @return the ODataCollectionFetcher
     */
    public ODataCollectionFetcher<TEntity, TFetcher, TOperations> addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }


    /**
     * Sets path for collections.
     *
     * @param url the url
     * @param urlComponent the url component
     * @param top the top
     * @param skip the skip
     * @param select the select
     * @param expand the expand
     * @param filter the filter
     * @param orderBy the order by
     * @param search the order by
     */
    protected void setPathForCollections(ODataURL url, String urlComponent, int top, int skip, String select, String expand, String filter, String orderBy, String search) {
        if (top > -1) {
            url.addQueryStringParameter("$top", Integer.valueOf(top).toString());
        }

        if (skip > -1) {
            url.addQueryStringParameter("$skip", Integer.valueOf(skip).toString());
        }

        if (select != null) {
            url.addQueryStringParameter("$select", select);
        }

        if (expand != null) {
            url.addQueryStringParameter("$expand", expand);
        }

        if (filter != null) {
            url.addQueryStringParameter("$filter", filter);
        }

        if (orderBy != null) {
            url.addQueryStringParameter("$orderby", orderBy);
        }

        if (search != null) {
            url.addQueryStringParameter("$search", search);
        }

        url.prependPathComponent(urlComponent);
    }
}