/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.exchange.services.*;

public class EventFetcher extends ODataEntityFetcher<Event,EventOperations> implements Executable<Event> {

	 public EventFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Event.class,EventOperations.class);
    }
	public ODataCollectionFetcher<Attachment, AttachmentFetcher, AttachmentCollectionOperations> getAttachments() {
        return new ODataCollectionFetcher<Attachment, AttachmentFetcher,AttachmentCollectionOperations>("Attachments", this, Attachment.class,AttachmentCollectionOperations.class);
    }
	public CalendarFetcher getCalendar() {
        return new CalendarFetcher("Calendar", this);
    }
}