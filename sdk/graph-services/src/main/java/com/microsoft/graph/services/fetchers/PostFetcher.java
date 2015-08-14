/*******************************************************************************
**NOTE** This code was generated by a tool and will occasionally be
overwritten. We welcome comments and issues regarding this code; they will be
addressed in the generation tool. If you wish to submit pull requests, please
do so for the templates in that tool.

This code was generated by Vipr (https://github.com/microsoft/vipr) using
the T4TemplateWriter (https://github.com/msopentech/vipr-t4templatewriter).

Copyright (c) Microsoft Open Technologies, Inc. All Rights Reserved.
Licensed under the Apache License 2.0; see LICENSE in the source repository
root for authoritative license information.﻿
******************************************************************************/
package com.microsoft.graph.services.fetchers;

import com.microsoft.graph.services.*;
import com.google.common.util.concurrent.*;
import com.microsoft.services.orc.core.*;
import com.microsoft.services.orc.core.Readable;

/**
 * The type  Post
 .
 */
public class PostFetcher extends OrcEntityFetcher<Post,PostOperations> 
                                     implements Readable<Post> {

     /**
     * Instantiates a new PostFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
     public PostFetcher(String urlComponent, OrcExecutable parent) {
        super(urlComponent, parent, Post.class, PostOperations.class);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the fetcher
     */
    public PostFetcher addParameter(String name, Object value) {
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
    public PostFetcher addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }

        
     /**
     * Gets attachments.
     *
     * @return the attachments
     */
    public OrcCollectionFetcher<Attachment, AttachmentFetcher, AttachmentCollectionOperations> getAttachments() {
        return new OrcCollectionFetcher<Attachment, AttachmentFetcher, AttachmentCollectionOperations>("Attachments", this, Attachment.class, AttachmentCollectionOperations.class);
    }

    /**
     * Gets attachment.
     *
     * @return the attachment
     */
    public AttachmentFetcher getAttachment(String id){
         return new OrcCollectionFetcher<Attachment, AttachmentFetcher, AttachmentCollectionOperations>("Attachments", this, Attachment.class, AttachmentCollectionOperations.class).getById(id);
    }
     /**
     * Gets inreplyto.
     *
     * @return the in reply to
     */
    public PostFetcher getInReplyTo() {
        return new PostFetcher("InReplyTo", this);
    }

}
