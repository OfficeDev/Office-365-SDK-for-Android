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
 * The type  MessageFetcher.
 */
public class MessageFetcher extends ODataEntityFetcher<Message,MessageOperations> implements Readable<Message> {

     /**
     * Instantiates a new MessageFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
	 public MessageFetcher(String urlComponent, ODataExecutable parent) {
		super(urlComponent, parent, Message.class,MessageOperations.class);
    }
     /**
     * Gets attachments.
     *
     * @return the attachments
     */
	public ODataCollectionFetcher<Attachment, AttachmentFetcher, AttachmentCollectionOperations> getAttachments() {
		return new ODataCollectionFetcher<Attachment, AttachmentFetcher,AttachmentCollectionOperations>("Attachments", this, Attachment.class,AttachmentCollectionOperations.class);
    }
}