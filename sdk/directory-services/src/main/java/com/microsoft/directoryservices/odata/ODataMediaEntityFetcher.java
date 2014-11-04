/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;

import static com.microsoft.services.odata.EntityFetcherHelper.addEntityResultCallback;
import static com.microsoft.services.odata.EntityFetcherHelper.addNullResultCallback;
import static com.microsoft.services.odata.Helpers.serializeToJsonByteArray;
import static com.microsoft.services.odata.Helpers.addCustomParametersToODataURL;

/**
 * The type ODataMediaEntityFetcher.
 * @param <E>  the type parameter
 * @param <V>  the type parameter
 */
public abstract class ODataMediaEntityFetcher<E, V> extends ODataEntityFetcher<E, V> implements Readable<E> {

   /**
     * Instantiates a new ODataMediaEntityFetcher.
     *
     * @param urlComponent   the url component
     * @param parent         the parent
     * @param clazz          the clazz
     * @param operationClazz the operation clazz
     */

    public ODataMediaEntityFetcher(String urlComponent, ODataExecutable parent, Class<E> clazz, Class<V> operationClazz) {
        super(urlComponent, parent, clazz, operationClazz);
    }

    public ListenableFuture<byte[]> getContent() {
        ODataURL url = getResolver().createODataURL();
        url.appendPathComponent("$value");
        return oDataExecute(url, null, HttpVerb.GET);
    }

    public ListenableFuture<Void> putContent(byte[] content) {
        ODataURL url = getResolver().createODataURL();
        url.appendPathComponent("$value");
        ListenableFuture<byte[]> future = oDataExecute(url, content, HttpVerb.PUT);

        SettableFuture<Void> result = SettableFuture.create();
        addNullResultCallback(result, future);

        return result;
    }
}