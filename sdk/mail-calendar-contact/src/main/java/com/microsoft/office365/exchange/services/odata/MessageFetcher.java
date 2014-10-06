/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.exchange.services.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.exchange.services.model.*;

public class MessageFetcher extends ODataEntityFetcher<Message,MessageOperations> implements Executable<Message> {

	 public MessageFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Message.class,MessageOperations.class);
    }
	public ODataCollectionFetcher<Attachment, AttachmentFetcher, AttachmentCollectionOperations> getAttachments() {
        return new ODataCollectionFetcher<Attachment, AttachmentFetcher,AttachmentCollectionOperations>("Attachments", this, Attachment.class,AttachmentCollectionOperations.class);
    }
}