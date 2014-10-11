/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.files.services.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.files.services.*;

public class FolderFetcher extends ODataEntityFetcher<Folder,FolderOperations> implements Readable<Folder> {

	 public FolderFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Folder.class,FolderOperations.class);
    }

	public FolderFetcher addParameter(String name, Object value) {
	    addCustomParameter(name, value);
		return this;
	}
	public ODataCollectionFetcher<Item, ItemFetcher, ItemCollectionOperations> getchildren() {
        return new ODataCollectionFetcher<Item, ItemFetcher,ItemCollectionOperations>("children", this, Item.class,ItemCollectionOperations.class);
    }
}