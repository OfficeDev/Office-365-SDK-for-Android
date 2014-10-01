/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import com.microsoft.office365.exchange.services.*;

public class AttachmentODataComponent extends BaseEntityODataComponent<Attachment> implements Executable<Attachment> {

	 public AttachmentODataComponent(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Attachment.class);
    }
}