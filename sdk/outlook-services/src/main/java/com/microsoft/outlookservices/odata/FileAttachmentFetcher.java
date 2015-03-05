/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

import com.microsoft.outlookservices.*;
import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.*;
import com.microsoft.services.odata.Readable;
import com.microsoft.services.odata.interfaces.*;

/**
 * The type  FileAttachmentFetcher.
 */
public class FileAttachmentFetcher extends ODataEntityFetcher<FileAttachment,FileAttachmentOperations> 
                                     implements Readable<FileAttachment> {

     /**
     * Instantiates a new FileAttachmentFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
     public FileAttachmentFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, FileAttachment.class, FileAttachmentOperations.class);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the fetcher
     */
    public FileAttachmentFetcher addParameter(String name, Object value) {
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
    public FileAttachmentFetcher addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }

        
}
