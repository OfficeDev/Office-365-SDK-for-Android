/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;

import static com.microsoft.services.odata.EntityFetcherHelper.addEntityResultCallback;
import static com.microsoft.services.odata.EntityFetcherHelper.addNullResultCallback;
import static com.microsoft.services.odata.Helpers.serializeToJsonByteArray;
import static com.microsoft.services.odata.Helpers.addCustomParametersToODataURL;

/**
 * The type ODataMediaEntityFetcher.
 * @param <TEntity>  the type parameter
 * @param <TOperation>  the type parameter
 */
public abstract class ODataMediaEntityFetcher<TEntity, TOperation extends ODataOperations> extends ODataEntityFetcher<TEntity, TOperation> implements Readable<TEntity> {

   /**
     * Instantiates a new ODataMediaEntityFetcher.
     *
     * @param urlComponent   the url component
     * @param parent         the parent
     * @param clazz          the clazz
     * @param operationClazz the operation clazz
     */

    public ODataMediaEntityFetcher(String urlComponent, ODataExecutable parent, Class<TEntity> clazz, Class<TOperation> operationClazz) {
        super(urlComponent, parent, clazz, operationClazz);
    }

    public ListenableFuture<byte[]> getContent() {
        ODataURL url = getResolver().createODataURL();
        url.appendPathComponent("$value");
        ListenableFuture<byte[]> future = oDataExecute(url, null, HttpVerb.GET, getCustomHeaders());

        return future;
    }

    public ListenableFuture<Void> putContent(byte[] content) {
        ODataURL url = getResolver().createODataURL();
        url.appendPathComponent("$value");
        ListenableFuture<byte[]> future = oDataExecute(url, content, HttpVerb.PUT, getCustomHeaders());

        SettableFuture<Void> result = SettableFuture.create();
        addNullResultCallback(result, future);

        return result;
    }
}