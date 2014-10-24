/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.outlookservices.*; 
import com.microsoft.outlookservices.*;		

/**
 * The type  AttachmentFetcher.
 */
public class AttachmentFetcher extends ODataEntityFetcher<Attachment,AttachmentOperations> implements Readable<Attachment> {

     /**
     * Instantiates a new AttachmentFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
	 public AttachmentFetcher(String urlComponent, ODataExecutable parent) {
		super(urlComponent, parent, Attachment.class,AttachmentOperations.class);
    }

	     public FileAttachmentFetcher asFileAttachment(){
	      return new FileAttachmentFetcher(this.urlComponent, this.parent);
     }	
	     public ItemAttachmentFetcher asItemAttachment(){
	      return new ItemAttachmentFetcher(this.urlComponent, this.parent);
     }	
	}