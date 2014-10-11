/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.outlook.services.*;

public class ItemFetcher extends ODataEntityFetcher<Item,ItemOperations> implements Readable<Item> {

	 public ItemFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Item.class,ItemOperations.class);
    }
}