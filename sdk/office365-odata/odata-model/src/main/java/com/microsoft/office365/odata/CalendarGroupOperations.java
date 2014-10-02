/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.exchange.services.*;

public class CalendarGroupOperations extends BaseEntityOperations<CalendarGroup> implements Executable<CalendarGroup> {

	 public CalendarGroupOperations(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, CalendarGroup.class);
    }
	public ODataCollectionFetcher<Calendar, CalendarOperations, CalendarCollectionOperations> getCalendars() {
        return new ODataCollectionFetcher<Calendar, CalendarOperations,CalendarCollectionOperations>("Calendars", this, Calendar.class,CalendarCollectionOperations.class);
    }
}