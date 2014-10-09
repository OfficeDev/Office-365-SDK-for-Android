/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.exchange.services.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;

import static com.microsoft.office365.odata.EntityFetcherHelper.addEntityResultCallback;
import static com.microsoft.office365.odata.EntityFetcherHelper.addNullResultCallback;
import static com.microsoft.office365.odata.EntityFetcherHelper.getODataExecuteUrlForPath;
import static com.microsoft.office365.odata.Helpers.serializeToJsonByteArray;

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
    ListenableFuture<byte[]> oDataExecute(String path, byte[] content, HttpVerb verb) {
	String url = getODataExecuteUrlForPath(path, urlComponent);
        return parent.oDataExecute(url, content, verb);
    }

    @Override
    DependencyResolver getResolver() {
        return parent.getResolver();
    }

    public ListenableFuture<E> update(E updatedEntity) {
	final SettableFuture<E> result = SettableFuture.create();
        byte[] payloadBytes = serializeToJsonByteArray(updatedEntity, getResolver());
        ListenableFuture<byte[]> future = oDataExecute("", payloadBytes, HttpVerb.PATCH);
        addEntityResultCallback(result, future, getResolver(), clazz);
        return result;
    }

    public ListenableFuture delete() {
	final SettableFuture<E> result = SettableFuture.create();
        ListenableFuture<byte[]> future = oDataExecute("", null, HttpVerb.DELETE);
        addNullResultCallback(result, future);
        return result;
    }

    public ListenableFuture<E> read() {
	final SettableFuture<E> result = SettableFuture.create();
        ListenableFuture<byte[]> future = oDataExecute("", null, HttpVerb.GET);
        addEntityResultCallback(result, future, getResolver(), clazz);
        return result;
    }

    public V getOperations() {
        return this.operations;
    }
}