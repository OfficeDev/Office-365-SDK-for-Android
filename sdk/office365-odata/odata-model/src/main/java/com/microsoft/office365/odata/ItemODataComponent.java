/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import com.microsoft.office365.exchange.services.*;

public class ItemODataComponent extends BaseEntityODataComponent<Item> implements Executable<Item> {

	 public ItemODataComponent(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Item.class);
    }
	public ODataCollection<Attachment, AttachmentODataComponent, AttachmentCollectionOperations> getAttachments() {
        return new ODataCollection<Attachment, AttachmentODataComponent,AttachmentCollectionOperations>("Attachments", this, Attachment.class,AttachmentCollectionOperations.class);
    }
}