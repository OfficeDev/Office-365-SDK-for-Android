/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.outlook.services.*;

public class ItemAttachmentFetcher extends ODataEntityFetcher<ItemAttachment,ItemAttachmentOperations> implements Readable<ItemAttachment> {

	 public ItemAttachmentFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, ItemAttachment.class,ItemAttachmentOperations.class);
    }
	public ItemFetcher getItem() {
        return new ItemFetcher("Item", this);
    }
}