/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import com.microsoft.office365.exchange.services.*;

public class CalendarODataComponent extends BaseEntityODataComponent<Calendar> implements Executable<Calendar> {

	 public CalendarODataComponent(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Calendar.class);
    }
	public ODataCollection<Event, EventODataComponent, EventCollectionOperations> getEvents() {
        return new ODataCollection<Event, EventODataComponent,EventCollectionOperations>("Events", this, Event.class,EventCollectionOperations.class);
    }
}