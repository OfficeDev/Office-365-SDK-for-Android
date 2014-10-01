/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.exchange.services.*;

public class ItemQuery extends ODataEntityQuery<Item> implements Executable<Item> {

	 public ItemQuery(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Item.class);
    }
	public ODataCollection<Attachment, AttachmentQuery, AttachmentCollectionOperations> getAttachments() {
        return new ODataCollection<Attachment, AttachmentQuery,AttachmentCollectionOperations>("Attachments", this, Attachment.class,AttachmentCollectionOperations.class);
    }
}