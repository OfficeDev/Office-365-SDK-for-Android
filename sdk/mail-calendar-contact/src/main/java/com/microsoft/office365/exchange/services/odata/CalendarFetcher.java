/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.exchange.services.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.exchange.services.model.*;

public class CalendarFetcher extends ODataEntityFetcher<Calendar,CalendarOperations> implements Executable<Calendar> {

	 public CalendarFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Calendar.class,CalendarOperations.class);
    }
	public ODataCollectionFetcher<Event, EventFetcher, EventCollectionOperations> getEvents() {
        return new ODataCollectionFetcher<Event, EventFetcher,EventCollectionOperations>("Events", this, Event.class,EventCollectionOperations.class);
    }
}