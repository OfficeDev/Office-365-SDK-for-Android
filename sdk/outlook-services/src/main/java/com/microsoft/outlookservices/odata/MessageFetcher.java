/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.*;
import com.microsoft.services.odata.Readable;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.outlookservices.*; 
import com.microsoft.outlookservices.*;       

/**
 * The type  MessageFetcher.
 */
public class MessageFetcher extends ODataEntityFetcher<Message,MessageOperations> 
                                     implements Readable<Message> {

     /**
     * Instantiates a new MessageFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
     public MessageFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Message.class, MessageOperations.class);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the fetcher
     */
    public MessageFetcher addParameter(String name, Object value) {
        addCustomParameter(name, value);
        return this;
    }

     /**
     * Add header.
     *
     * @param name the name
     * @param value the value
     * @return the fetcher
     */
    public MessageFetcher addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }

    	     /**
     * Gets attachments.
     *
     * @return the attachments
     */
    public ODataCollectionFetcher<Attachment, AttachmentFetcher, AttachmentCollectionOperations> getAttachments() {
        return new ODataCollectionFetcher<Attachment, AttachmentFetcher,AttachmentCollectionOperations>("Attachments", this, Attachment.class,AttachmentCollectionOperations.class);
    }

    /**
     * Gets attachment.
     *
     * @return the attachment
     */
    public AttachmentFetcher getAttachment(String id){
         return new ODataCollectionFetcher<Attachment, AttachmentFetcher,AttachmentCollectionOperations>("Attachments", this, Attachment.class,AttachmentCollectionOperations.class).getById(id);
    }
}