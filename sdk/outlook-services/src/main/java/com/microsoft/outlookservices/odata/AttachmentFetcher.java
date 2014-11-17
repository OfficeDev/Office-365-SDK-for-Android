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
 * The type  AttachmentFetcher.
 */
public class AttachmentFetcher extends ODataEntityFetcher<Attachment,AttachmentOperations> 
                                     implements Readable<Attachment> {

     /**
     * Instantiates a new AttachmentFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
     public AttachmentFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Attachment.class, AttachmentOperations.class);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the fetcher
     */
    public AttachmentFetcher addParameter(String name, Object value) {
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
    public AttachmentFetcher addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }

    
    public FileAttachmentFetcher asFileAttachment(){
        return new FileAttachmentFetcher(this.urlComponent, this.parent);
    }	   

    public ItemAttachmentFetcher asItemAttachment(){
        return new ItemAttachmentFetcher(this.urlComponent, this.parent);
    }	   
	}