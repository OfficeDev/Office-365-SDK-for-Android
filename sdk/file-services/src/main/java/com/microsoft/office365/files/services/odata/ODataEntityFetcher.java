/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.files.services.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;

import static com.microsoft.office365.odata.EntityFetcherHelper.addEntityResultCallback;
import static com.microsoft.office365.odata.EntityFetcherHelper.addNullResultCallback;
import static com.microsoft.office365.odata.Helpers.serializeToJsonByteArray;
import static com.microsoft.office365.odata.Helpers.addCustomParametersToODataURL;

public abstract class ODataEntityFetcher<E, V> extends ODataExecutable implements Readable<E> {

    private String urlComponent;
    private ODataExecutable parent;
    private Class<E> clazz;
    private V operations;

    public ODataEntityFetcher(String urlComponent, ODataExecutable parent, Class<E> clazz, Class<V> operationClazz) {
        this.urlComponent = urlComponent;
        this.parent = parent;
        this.clazz = clazz;

        try {
            this.operations = operationClazz.getConstructor(String.class,
                    ODataExecutable.class).newInstance("", this);
        } catch (Throwable ignored) {
        }
    }

    @Override
    ListenableFuture<byte[]> oDataExecute(ODataURL path, byte[] content, HttpVerb verb) {
		path.prependPathComponent(urlComponent);
		addCustomParametersToODataURL(path, getCustomParameters(), getResolver());
        return parent.oDataExecute(path, content, verb);
    }

    @Override
    DependencyResolver getResolver() {
        return parent.getResolver();
    }

    public ListenableFuture<E> update(E updatedEntity) {
	final SettableFuture<E> result = SettableFuture.create();
        byte[] payloadBytes = serializeToJsonByteArray(updatedEntity, getResolver());
        ListenableFuture<byte[]> future = oDataExecute(getResolver().createODataURL(), payloadBytes, HttpVerb.PATCH);
        addEntityResultCallback(result, future, getResolver(), clazz);
        return result;
    }

    public ListenableFuture delete() {
	    final SettableFuture<E> result = SettableFuture.create();
        ListenableFuture<byte[]> future = oDataExecute(getResolver().createODataURL(), null, HttpVerb.DELETE);
        addNullResultCallback(result, future);
        return result;
    }

    public ListenableFuture<E> read() {
        final SettableFuture<E> result = SettableFuture.create();
        ListenableFuture<byte[]> future = oDataExecute(getResolver().createODataURL(), null, HttpVerb.GET);
        addEntityResultCallback(result, future, getResolver(), clazz);
        return result;
    }

    public V getOperations() {
        return this.operations;
    }
}