/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.exchange.services.odata;

import com.microsoft.office365.exchange.services.model.*;

public class AttachmentFetcher extends ODataEntityFetcher<Attachment,AttachmentOperations> implements Readable<Attachment> {

	 public AttachmentFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Attachment.class,AttachmentOperations.class);
    }
}