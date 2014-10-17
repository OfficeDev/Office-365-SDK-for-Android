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
 * The type  ItemFetcher.
 */
public class ItemFetcher extends ODataEntityFetcher<Item,ItemOperations> implements Readable<Item> {

     /**
     * Instantiates a new ItemFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
	 public ItemFetcher(String urlComponent, ODataExecutable parent) {
		super(urlComponent, parent, Item.class,ItemOperations.class);
    }
}