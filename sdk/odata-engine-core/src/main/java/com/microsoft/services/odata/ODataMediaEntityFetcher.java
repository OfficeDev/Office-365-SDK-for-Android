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
import com.microsoft.services.odata.interfaces.ODataResponse;
import com.microsoft.services.odata.interfaces.ODataURL;
import com.microsoft.services.odata.interfaces.Request;

import static com.microsoft.services.odata.Helpers.transformToVoidListenableFuture;


/**
 * The type ODataMediaEntityFetcher.
 *
 * @param <TEntity>     the type parameter
 * @param <TOperations> the type parameter
 */
public abstract class ODataMediaEntityFetcher<TEntity, TOperations extends ODataOperations>
        extends ODataEntityFetcher<TEntity, TOperations>
        implements Readable<TEntity> {

    /**
     * Instantiates a new ODataMediaEntityFetcher.
     *
     * @param urlComponent   the url component
     * @param parent         the parent
     * @param clazz          the clazz
     * @param operationClazz the operation clazz
     */

    public ODataMediaEntityFetcher(String urlComponent, ODataExecutable parent, Class<TEntity> clazz, Class<TOperations> operationClazz) {
        super(urlComponent, parent, clazz, operationClazz);
    }

    public ListenableFuture<byte[]> getContent() {

        final SettableFuture<byte[]> result = SettableFuture.create();

        Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.GET);
        ODataURL url = request.getUrl();
        url.appendPathComponent("$value");

        ListenableFuture<ODataResponse> future = oDataExecute(request);

        Futures.addCallback(future, new FutureCallback<ODataResponse>() {
                @Override
                public void onSuccess(ODataResponse response) {
                    result.set(response.getPayload());
                }

                @Override
                public void onFailure(Throwable t) {
                    result.setException(t);
                }
            }
        );
        return result;
    }

    public ListenableFuture<Void> putContent(byte[] content) {

        Request request = getResolver().createRequest();
        request.setContent(content);
        request.setVerb(HttpVerb.PUT);
        ODataURL url = request.getUrl();
        url.appendPathComponent("$value");

        ListenableFuture<ODataResponse> future = oDataExecute(request);

        return transformToVoidListenableFuture(future);
    }
}