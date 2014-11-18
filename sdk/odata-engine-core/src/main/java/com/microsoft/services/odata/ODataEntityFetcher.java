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
import static com.microsoft.services.odata.Helpers.serializeToJsonByteArray;

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
        final SettableFuture<TEntity> result = SettableFuture.create();
        byte[] payloadBytes = serializeToJsonByteArray(updatedEntity, getResolver());

        Request request = getResolver().createRequest();
        request.setContent(payloadBytes);
        request.setVerb(HttpVerb.PATCH);

        ListenableFuture<ODataResponse> future = oDataExecute(request);
        addEntityResultCallback(result, future);
        return result;
    }

    /**
     * Deletes
     *
     * @return the listenable future
     */
    public ListenableFuture delete() {
        final SettableFuture<TEntity> result = SettableFuture.create();

        Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.DELETE);

        ListenableFuture<ODataResponse> future = oDataExecute(request);
        addNullResultCallback(result, future);
        return result;
    }

    /**
     * Reads
     *
     * @return the listenable future
     */
    public ListenableFuture<TEntity> read() {
        final SettableFuture<TEntity> result = SettableFuture.create();

        Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.GET);

        ListenableFuture<ODataResponse> future = oDataExecute(request);
        addEntityResultCallback(result, future);
        return result;
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