/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.fileservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.*;
import com.microsoft.services.odata.Readable;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.fileservices.*; 
import com.microsoft.fileservices.*;       

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
        super(urlComponent, parent, Folder.class, FolderOperations.class);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the fetcher
     */
    public FolderFetcher addParameter(String name, Object value) {
        addCustomParameter(name, value);
        return this;
    }

     /**
     * Add header.
     *
     * @param name the name
     * @param value the value
     * @return the fetcher
     */
    public FolderFetcher addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }

    	     /**
     * Gets children.
     *
     * @return the children
     */
    public ODataCollectionFetcher<Item, ItemFetcher, ItemCollectionOperations> getchildren() {
        return new ODataCollectionFetcher<Item, ItemFetcher,ItemCollectionOperations>("children", this, Item.class,ItemCollectionOperations.class);
    }

    /**
     * Gets child.
     *
     * @return the child
     */
    public ItemFetcher getchild(String id){
         return new ODataCollectionFetcher<Item, ItemFetcher,ItemCollectionOperations>("children", this, Item.class,ItemCollectionOperations.class).getById(id);
    }
}