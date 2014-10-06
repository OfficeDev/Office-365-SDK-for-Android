/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.file.services.odata;

import com.microsoft.office365.odata.interfaces.DependencyResolver;
import com.microsoft.office365.file.services.model.*;

public class ApiDataClient extends BaseODataContainer {

    public ApiDataClient(String url, DependencyResolver resolver) {
        super(url, resolver);
    }
	
    public DriveFetcher getdrive() {
        return new DriveFetcher("drive", this);
    }
	
	public ODataCollectionFetcher<Item, ItemFetcher, ItemCollectionOperations> getfiles() {
        return new ODataCollectionFetcher<Item, ItemFetcher,ItemCollectionOperations>("files", this, Item.class,ItemCollectionOperations.class);
    }
}