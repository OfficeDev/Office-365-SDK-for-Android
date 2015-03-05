/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.fileservices.odata;

import com.microsoft.fileservices.*;
import com.microsoft.services.odata.*;
import com.microsoft.services.odata.interfaces.DependencyResolver;

/**
 * The type SharePointClient.
 */
public class SharePointClient extends BaseODataContainer {

     /**
     * Instantiates a new SharePointClient.
     *
     * @param url the url
     * @param resolver the resolver
     */
    public SharePointClient(String url, DependencyResolver resolver) {
        super(url, resolver);
    }

    /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the client
     */
    public SharePointClient addParameter(String name, Object value) {
        addCustomParameter(name, value);
        return this;
    }

     /**
     * Add header.
     *
     * @param name the name
     * @param value the value
     * @return the client
     */
    public SharePointClient addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }

     /**
     * Gets Item.
     *
     * @return the Item
     */
    public ODataCollectionFetcher<Item, ItemFetcher, ItemCollectionOperations> getfiles() {
        return new ODataCollectionFetcher<Item, ItemFetcher, ItemCollectionOperations>("files", this, Item.class,ItemCollectionOperations.class);
    }
     /**
     * Gets drive.
     *
     * @return the drive
     */
    public DriveFetcher getdrive() {
        return new DriveFetcher("drive", this);
    }
}