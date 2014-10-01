/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.exchange.services.*;

public class CalendarQuery extends ODataEntityQuery<Calendar> implements Executable<Calendar> {

	 public CalendarQuery(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Calendar.class);
    }
	public ODataCollection<Event, EventQuery, EventCollectionOperations> getEvents() {
        return new ODataCollection<Event, EventQuery,EventCollectionOperations>("Events", this, Event.class,EventCollectionOperations.class);
    }
}