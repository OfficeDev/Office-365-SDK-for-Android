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
 * The type  EventFetcher.
 */
public class EventFetcher extends ODataEntityFetcher<Event,EventOperations> implements Readable<Event> {

     /**
     * Instantiates a new EventFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
	 public EventFetcher(String urlComponent, ODataExecutable parent) {
		super(urlComponent, parent, Event.class,EventOperations.class);
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
     * Gets calendar.
     *
     * @return the calendar
     */
	public CalendarFetcher getCalendar() {
		return new CalendarFetcher("Calendar", this);
    }
     /**
     * Gets instances.
     *
     * @return the instances
     */
	public ODataCollectionFetcher<Event, EventFetcher, EventCollectionOperations> getInstances() {
		return new ODataCollectionFetcher<Event, EventFetcher,EventCollectionOperations>("Instances", this, Event.class,EventCollectionOperations.class);
    }
}