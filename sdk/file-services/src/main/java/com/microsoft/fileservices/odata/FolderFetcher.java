/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.fileservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.fileservices.*; 
import com.microsoft.fileservices.*;		

/**
 * The type  FolderFetcher.
 */
public class FolderFetcher extends ODataEntityFetcher<Folder,FolderOperations> implements Readable<Folder> {

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
     * Gets children.
     *
     * @return the children
     */
	public ODataCollectionFetcher<Item, ItemFetcher, ItemCollectionOperations> getchildren() {
		return new ODataCollectionFetcher<Item, ItemFetcher,ItemCollectionOperations>("children", this, Item.class,ItemCollectionOperations.class);
    }
}