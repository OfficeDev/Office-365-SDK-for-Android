/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.file.services.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.file.services.model.*;

public class ItemCollectionOperations extends ODataOperations {

    public ItemCollectionOperations(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent);
    }
	public ListenableFuture<Item> add(String name, String nameConflict, String type, byte[] content) {
        final SettableFuture<Item> result = SettableFuture.create();

        ListenableFuture<byte[]> future = oDataExecute("Add", null, HttpVerb.POST);

        Futures.addCallback(future, new FutureCallback<byte[]>() {
            @Override
            public void onSuccess(byte[] item) {
                DependencyResolver resolver = getResolver();

                try {
                    result.set(resolver.getJsonSerializer().deserialize(new String(item, Constants.UTF8), Item.class));
                } catch (Throwable throwable) {
                    result.setException(throwable);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                result.setException(t);
            }
        });

        return result;
    }			
	public ListenableFuture<Item> getByPath(String path) {
        final SettableFuture<Item> result = SettableFuture.create();

        ListenableFuture<byte[]> future = oDataExecute("GetByPath", null, HttpVerb.POST);

        Futures.addCallback(future, new FutureCallback<byte[]>() {
            @Override
            public void onSuccess(byte[] item) {
                DependencyResolver resolver = getResolver();

                try {
                    result.set(resolver.getJsonSerializer().deserialize(new String(item, Constants.UTF8), Item.class));
                } catch (Throwable throwable) {
                    result.setException(throwable);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                result.setException(t);
            }
        });

        return result;
    }			
}
