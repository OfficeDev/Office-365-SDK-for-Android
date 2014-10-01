/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.exchange.services.*;

public class FolderODataComponent extends BaseEntityODataComponent<Folder> implements Executable<Folder> {

	 public FolderODataComponent(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Folder.class);
    }
	public ODataCollection<Folder, FolderODataComponent, FolderCollectionOperations> getChildFolders() {
        return new ODataCollection<Folder, FolderODataComponent,FolderCollectionOperations>("ChildFolders", this, Folder.class,FolderCollectionOperations.class);
    }
	public ODataCollection<Message, MessageODataComponent, MessageCollectionOperations> getMessages() {
        return new ODataCollection<Message, MessageODataComponent,MessageCollectionOperations>("Messages", this, Message.class,MessageCollectionOperations.class);
    }
			
	public ListenableFuture<Folder> copy(String destinationid) {
        final SettableFuture<Folder> result = SettableFuture.create();

        ListenableFuture<byte[]> future = oDataExecute("Copy", null, HttpVerb.POST);

        Futures.addCallback(future, new FutureCallback<byte[]>() {
            @Override
            public void onSuccess(byte[] folder) {
                DependencyResolver resolver = getResolver();

                try {
                    result.set(resolver.getJsonSerializer().deserialize(new String(folder, Constants.UTF8), Folder.class));
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
			
	public ListenableFuture<Folder> move(String destinationid) {
        final SettableFuture<Folder> result = SettableFuture.create();

        ListenableFuture<byte[]> future = oDataExecute("Move", null, HttpVerb.POST);

        Futures.addCallback(future, new FutureCallback<byte[]>() {
            @Override
            public void onSuccess(byte[] folder) {
                DependencyResolver resolver = getResolver();

                try {
                    result.set(resolver.getJsonSerializer().deserialize(new String(folder, Constants.UTF8), Folder.class));
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