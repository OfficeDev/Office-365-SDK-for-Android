/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.sharepointservices.odata;

import com.microsoft.services.odata.interfaces.DependencyResolver;
import com.microsoft.fileservices.*;
import com.microsoft.fileservices.odata.*;

/**
 * The type EntityContainerClient.
 */
public class EntityContainerClient extends BaseODataContainer {

	 /**
     * Instantiates a new EntityContainerClient.
     *
     * @param url the url
     * @param resolver the resolver
     */
    public EntityContainerClient(String url, DependencyResolver resolver) {
        super(url, resolver);
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
     * Gets me.
     *
     * @return the me
     */
    public CurrentUserRequestContextFetcher getme() {
        return new CurrentUserRequestContextFetcher("me", this);
    }
	 /**
     * Gets Item.
     *
     * @return the Item
     */
	public ODataCollectionFetcher<Item, ItemFetcher, ItemCollectionOperations> getfiles() {
        return new ODataCollectionFetcher<Item, ItemFetcher,ItemCollectionOperations>("files", this, Item.class,ItemCollectionOperations.class);
    }
}