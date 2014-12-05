/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.odata;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.services.odata.interfaces.HttpVerb;
import com.microsoft.services.odata.interfaces.ODataResponse;
import com.microsoft.services.odata.interfaces.ODataURL;
import com.microsoft.services.odata.interfaces.Request;

import static com.microsoft.services.odata.Helpers.addCustomParametersToODataRequest;
import static com.microsoft.services.odata.Helpers.transformToEntityListenableFuture;
import static com.microsoft.services.odata.Helpers.transformToStringListenableFuture;
import static com.microsoft.services.odata.Helpers.transformToVoidListenableFuture;

/**
 * The type ODataEntityFetcher.
 *
 * @param <TEntity>     the type parameter
 * @param <TOperations> the type parameter
 */
public abstract class ODataEntityFetcher<TEntity, TOperations extends ODataOperations>
        extends ODataFetcher<TEntity>
        implements Readable<TEntity> {
    private TOperations operations;
    private String select;
    private String expand;

    /**
     * Instantiates a new ODataEntityFetcher.
     *
     * @param urlComponent   the url component
     * @param parent         the parent
     * @param clazz          the clazz
     * @param operationClazz the operation clazz
     */
    public ODataEntityFetcher(String urlComponent, ODataExecutable parent, Class<TEntity> clazz, Class<TOperations> operationClazz) {
        super(urlComponent, parent, clazz);

        try {
            this.operations = operationClazz.getConstructor(String.class,
                    ODataExecutable.class).newInstance("", this);
        } catch (Throwable ignored) {
        }
    }

    @Override
    protected ListenableFuture<ODataResponse> oDataExecute(Request request) {

        ODataURL oDataURL = request.getUrl();

        if (select != null) {
            oDataURL.addQueryStringParameter("$select", select);
        }

        if (expand != null) {
            oDataURL.addQueryStringParameter("$expand", expand);
        }

        oDataURL.prependPathComponent(urlComponent);

        addCustomParametersToODataRequest(request, getParameters(), getHeaders());
        return parent.oDataExecute(request);
    }


    /**
     * Updates the given entity.
     *
     * @param updatedEntity the updated entity
     * @return the listenable future
     */
    public ListenableFuture<TEntity> update(TEntity updatedEntity) {
        ListenableFuture<String> future = updateRaw(getResolver().getJsonSerializer().serialize(updatedEntity));
        return transformToEntityListenableFuture(future, this.clazz, getResolver());
    }

    /**
     * Updates the given entity.
     *
     * @param payload the updated entity
     * @return the listenable future
     */
    public ListenableFuture<String> updateRaw(String payload) {
        byte[] payloadBytes = payload.getBytes(Constants.UTF8);

        Request request = getResolver().createRequest();
        request.setContent(payloadBytes);
        request.setVerb(HttpVerb.PATCH);

        ListenableFuture<ODataResponse> future = oDataExecute(request);

        return transformToStringListenableFuture(future);
    }

    /**
     * Deletes
     *
     * @return the listenable future
     */
    public ListenableFuture delete() {
        Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.DELETE);

        ListenableFuture<ODataResponse> future = oDataExecute(request);
        return transformToVoidListenableFuture(future);
    }

    /**
     * Reads
     *
     * @return the listenable future
     */
    public ListenableFuture<TEntity> read() {
        return transformToEntityListenableFuture(readRaw(), this.clazz, getResolver());
    }

    /**
     * Reads raw
     *
     * @return the listenable future
     */
    public ListenableFuture<String> readRaw() {
        return super.readRaw();
    }


    /**
     * Select ODataCollectionFetcher.
     *
     * @param select the select
     * @return the o data collection fetcher
     */
    public ODataEntityFetcher<TEntity, TOperations> select(String select) {
        this.select = select;
        return this;
    }

    /**
     * Expand ODataCollectionFetcher.
     *
     * @param expand the expand
     * @return the o data collection fetcher
     */
    public ODataEntityFetcher<TEntity, TOperations> expand(String expand) {
        this.expand = expand;
        return this;
    }

    /**
     * Gets operations.
     *
     * @return the operations
     */
    public TOperations getOperations() {
        return this.operations;
    }
}