/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.fileservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.fileservices.*; 
import com.microsoft.coreservices.*;		

/**
 * The type  CurrentUserRequestContextFetcher.
 */
public class CurrentUserRequestContextFetcher extends ODataEntityFetcher<CurrentUserRequestContext,CurrentUserRequestContextOperations> implements Readable<CurrentUserRequestContext> {

     /**
     * Instantiates a new CurrentUserRequestContextFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
	 public CurrentUserRequestContextFetcher(String urlComponent, ODataExecutable parent) {
		super(urlComponent, parent, CurrentUserRequestContext.class,CurrentUserRequestContextOperations.class);
    }

		 /**
     * Gets drive.
     *
     * @return the drive
     */
	public DriveFetcher getdrive() {
		return new DriveFetcher("drive", this);
    }
     /**
     * Gets files.
     *
     * @return the files
     */
	public ODataCollectionFetcher<Item, ItemFetcher, ItemCollectionOperations> getfiles() {
		return new ODataCollectionFetcher<Item, ItemFetcher,ItemCollectionOperations>("files", this, Item.class,ItemCollectionOperations.class);
    }
}