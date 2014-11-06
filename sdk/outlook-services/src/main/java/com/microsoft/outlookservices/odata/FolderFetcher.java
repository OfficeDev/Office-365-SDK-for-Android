/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.outlookservices.*; 
import com.microsoft.outlookservices.*;       

/**
 * The type  FolderFetcher.
 */
public class FolderFetcher extends ODataEntityFetcher<Folder,FolderOperations> 
                                     implements Readable<Folder> {

     /**
     * Instantiates a new FolderFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
     public FolderFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Folder.class,FolderOperations.class);
    }

         /**
     * Gets child folders.
     *
     * @return the child folders
     */
    public ODataCollectionFetcher<Folder, FolderFetcher, FolderCollectionOperations> getChildFolders() {
        return new ODataCollectionFetcher<Folder, FolderFetcher,FolderCollectionOperations>("ChildFolders", this, Folder.class,FolderCollectionOperations.class);
    }

    /**
     * Gets child folder.
     *
     * @return the child folder
     */
    public FolderFetcher getChildFolder(String id){
         return new ODataCollectionFetcher<Folder, FolderFetcher,FolderCollectionOperations>("ChildFolders", this, Folder.class,FolderCollectionOperations.class).getById(id);
    }
     /**
     * Gets messages.
     *
     * @return the messages
     */
    public ODataCollectionFetcher<Message, MessageFetcher, MessageCollectionOperations> getMessages() {
        return new ODataCollectionFetcher<Message, MessageFetcher,MessageCollectionOperations>("Messages", this, Message.class,MessageCollectionOperations.class);
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public MessageFetcher getMessage(String id){
         return new ODataCollectionFetcher<Message, MessageFetcher,MessageCollectionOperations>("Messages", this, Message.class,MessageCollectionOperations.class).getById(id);
    }
}