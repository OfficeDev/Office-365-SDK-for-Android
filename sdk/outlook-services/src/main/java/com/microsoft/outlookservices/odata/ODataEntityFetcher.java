/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;

import java.util.HashMap;
import java.util.Map;

import static com.microsoft.services.odata.EntityFetcherHelper.addEntityResultCallback;
import static com.microsoft.services.odata.EntityFetcherHelper.addNullResultCallback;
import static com.microsoft.services.odata.Helpers.serializeToJsonByteArray;
import static com.microsoft.services.odata.Helpers.addCustomParametersToODataURL;

/**
 * The type ODataEntityFetcher.
 * @param <TEntity>  the type parameter
 * @param <TOperation>  the type parameter
 */
public abstract class ODataEntityFetcher<TEntity, TOperation extends ODataOperations> extends ODataExecutable implements Readable<TEntity> {

    protected String urlComponent;
    protected ODataExecutable parent;
    private Class<TEntity> clazz;
    private TOperation operations;

	 /**
     * Instantiates a new ODataEntityFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     * @param clazz the clazz
     * @param operationClazz the operation clazz
     */
    public ODataEntityFetcher(String urlComponent, ODataExecutable parent, Class<TEntity> clazz, Class<TOperation> operationClazz) {
        this.urlComponent = urlComponent;
        this.parent = parent;
        this.clazz = clazz;

        try {
            this.operations = operationClazz.getConstructor(String.class,
                    ODataExecutable.class).newInstance("", this);
        } catch (Throwable ignored) {
        }
    }

	public ODataEntityFetcher<TEntity,TOperation> addHeader(String name, String value) {
        this.addCustomHeader(name, value);
		return this;
    }

    @Override
    ListenableFuture<byte[]> oDataExecute(ODataURL path, byte[] content, HttpVerb verb, Map<String, String> headers) {
		path.prependPathComponent(urlComponent);
        addCustomParametersToODataURL(path, getCustomParameters(), getResolver());
		Map<String, String> newHeaders = new HashMap<String, String>(getCustomHeaders());
        newHeaders.putAll(headers);
        return parent.oDataExecute(path, content, verb, newHeaders);
    }

    @Override
    DependencyResolver getResolver() {
        return parent.getResolver();
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
        ListenableFuture<byte[]> future = oDataExecute(getResolver().createODataURL(), payloadBytes, HttpVerb.PATCH, getCustomHeaders());
        addEntityResultCallback(result, future, getResolver(), clazz);
        return result;
    }

	 /**
     * Deletes
     *
     * @return the listenable future
     */
    public ListenableFuture delete() {
	    final SettableFuture<TEntity> result = SettableFuture.create();
        ListenableFuture<byte[]> future = oDataExecute(getResolver().createODataURL(), null, HttpVerb.DELETE, getCustomHeaders());
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
        ListenableFuture<byte[]> future = oDataExecute(getResolver().createODataURL(), null, HttpVerb.GET, getCustomHeaders());
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