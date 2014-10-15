/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.files.services.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.services.files.services.*;

public class ItemCollectionOperations extends ODataOperations {

    public ItemCollectionOperations(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent);
    }

	public ItemCollectionOperations addParameter(String name, Object value) {
		addCustomParameter(name, value);
		return this;
	}
	public ListenableFuture<Item> add(String name, String nameConflict, String type, byte[] content) {
        final SettableFuture<Item> result = SettableFuture.create();

        ODataURL url = getResolver().createODataURL();
		url.appendPathComponent("Add");

        ListenableFuture<byte[]> future = oDataExecute(url, null, HttpVerb.POST);

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

        ODataURL url = getResolver().createODataURL();
		url.appendPathComponent("GetByPath");

        ListenableFuture<byte[]> future = oDataExecute(url, null, HttpVerb.POST);

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
