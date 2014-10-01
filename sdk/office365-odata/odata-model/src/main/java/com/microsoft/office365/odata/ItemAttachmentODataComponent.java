/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import com.microsoft.office365.exchange.services.*;

public class ItemAttachmentODataComponent extends BaseEntityODataComponent<ItemAttachment> implements Executable<ItemAttachment> {

	 public ItemAttachmentODataComponent(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, ItemAttachment.class);
    }
	public ItemODataComponent getItem() {
        return new ItemODataComponent("Item", this);
    }
}