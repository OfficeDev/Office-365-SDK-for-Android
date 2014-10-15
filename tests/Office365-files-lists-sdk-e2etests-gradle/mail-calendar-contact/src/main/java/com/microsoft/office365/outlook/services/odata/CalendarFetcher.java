/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.outlook.services.*;

public class CalendarFetcher extends ODataEntityFetcher<Calendar,CalendarOperations> implements Readable<Calendar> {

	 public CalendarFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Calendar.class,CalendarOperations.class);
    }

	public CalendarFetcher addParameter(String name, Object value) {
	    addCustomParameter(name, value);
		return this;
	}
	public ODataCollectionFetcher<Event, EventFetcher, EventCollectionOperations> getCalendarView() {
        return new ODataCollectionFetcher<Event, EventFetcher,EventCollectionOperations>("CalendarView", this, Event.class,EventCollectionOperations.class);
    }
	public ODataCollectionFetcher<Event, EventFetcher, EventCollectionOperations> getEvents() {
        return new ODataCollectionFetcher<Event, EventFetcher,EventCollectionOperations>("Events", this, Event.class,EventCollectionOperations.class);
    }
}