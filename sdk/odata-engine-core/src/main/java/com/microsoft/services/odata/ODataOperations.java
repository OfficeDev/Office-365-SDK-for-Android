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
import com.microsoft.services.odata.interfaces.DependencyResolver;
import com.microsoft.services.odata.interfaces.ODataResponse;
import com.microsoft.services.odata.interfaces.Request;

import static com.microsoft.services.odata.Helpers.addCustomParametersToODataURL;

/**
 * The type ODataOperations.
 */
public abstract class ODataOperations extends ODataExecutable {
    private String urlComponent;
    private ODataExecutable parent;

	 /**
     * Instantiates a new ODataOperation.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
    public ODataOperations(String urlComponent, ODataExecutable parent) {
        this.urlComponent = urlComponent;
        this.parent = parent;
    }

    @Override
    protected ListenableFuture<ODataResponse> oDataExecute(Request request) {
        request.getUrl().prependPathComponent(urlComponent);
        addCustomParametersToODataURL(request.getUrl(), getParameters());
        return parent.oDataExecute(request);
    }

    public <TEntity> void addEntityResultCallback(final SettableFuture<TEntity> result,
                                            ListenableFuture<ODataResponse> future,
                                            final Class<TEntity> clazz) {
        Futures.addCallback(future, new FutureCallback<ODataResponse>() {
            @Override
            public void onSuccess(ODataResponse response) {
                try {
                    String string = new String(response.getPayload(), Constants.UTF8_NAME);
                    TEntity entity = getResolver().getJsonSerializer().deserialize(string, clazz);
                    result.set(entity);
                } catch (Throwable e) {
                    result.setException(e);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                result.setException(throwable);
            }
        });
    }

    @Override
    protected  DependencyResolver getResolver() {
        return parent.getResolver();
    }
}
