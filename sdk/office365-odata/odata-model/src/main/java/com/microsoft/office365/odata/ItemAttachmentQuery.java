/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.exchange.services.*;

public class ItemAttachmentQuery extends ODataEntityQuery<ItemAttachment> implements Executable<ItemAttachment> {

	 public ItemAttachmentQuery(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, ItemAttachment.class);
    }
	public ItemQuery getItem() {
        return new ItemQuery("Item", this);
    }
}