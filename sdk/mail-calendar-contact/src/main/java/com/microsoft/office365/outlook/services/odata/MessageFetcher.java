/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.outlook.services.*;

public class MessageFetcher extends ODataEntityFetcher<Message,MessageOperations> implements Readable<Message> {

	 public MessageFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Message.class,MessageOperations.class);
    }

	public MessageFetcher addParameter(String name, Object value) {
	    addCustomParameter(name, value);
		return this;
	}
	public ODataCollectionFetcher<Attachment, AttachmentFetcher, AttachmentCollectionOperations> getAttachments() {
        return new ODataCollectionFetcher<Attachment, AttachmentFetcher,AttachmentCollectionOperations>("Attachments", this, Attachment.class,AttachmentCollectionOperations.class);
    }
}