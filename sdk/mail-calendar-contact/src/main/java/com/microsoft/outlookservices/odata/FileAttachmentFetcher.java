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
 * The type  FileAttachmentFetcher.
 */
public class FileAttachmentFetcher extends ODataEntityFetcher<FileAttachment,FileAttachmentOperations> implements Readable<FileAttachment> {

     /**
     * Instantiates a new FileAttachmentFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
	 public FileAttachmentFetcher(String urlComponent, ODataExecutable parent) {
		super(urlComponent, parent, FileAttachment.class,FileAttachmentOperations.class);
    }
}