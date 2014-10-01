/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import com.microsoft.office365.exchange.services.*;

public class FileAttachmentODataComponent extends BaseEntityODataComponent<FileAttachment> implements Executable<FileAttachment> {

	 public FileAttachmentODataComponent(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, FileAttachment.class);
    }
}