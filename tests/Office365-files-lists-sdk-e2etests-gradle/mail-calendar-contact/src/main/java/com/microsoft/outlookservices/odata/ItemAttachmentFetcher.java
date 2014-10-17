/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.outlookservices.*;

/**
 * The type  ItemAttachmentFetcher.
 */
public class ItemAttachmentFetcher extends ODataEntityFetcher<ItemAttachment,ItemAttachmentOperations> implements Readable<ItemAttachment> {

     /**
     * Instantiates a new ItemAttachmentFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
	 public ItemAttachmentFetcher(String urlComponent, ODataExecutable parent) {
		super(urlComponent, parent, ItemAttachment.class,ItemAttachmentOperations.class);
    }
	 /**
     * Gets item.
     *
     * @return the item
     */
	public ItemFetcher getItem() {
		return new ItemFetcher("Item", this);
    }
}