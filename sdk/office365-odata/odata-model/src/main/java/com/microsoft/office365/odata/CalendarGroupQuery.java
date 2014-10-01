/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.exchange.services.*;

public class CalendarGroupQuery extends ODataEntityQuery<CalendarGroup> implements Executable<CalendarGroup> {

	 public CalendarGroupQuery(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, CalendarGroup.class);
    }
	public ODataCollection<Calendar, CalendarQuery, CalendarCollectionOperations> getCalendars() {
        return new ODataCollection<Calendar, CalendarQuery,CalendarCollectionOperations>("Calendars", this, Calendar.class,CalendarCollectionOperations.class);
    }
}