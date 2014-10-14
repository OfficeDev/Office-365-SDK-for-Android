/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.outlook.services.*;

public class CalendarGroupFetcher extends ODataEntityFetcher<CalendarGroup,CalendarGroupOperations> implements Readable<CalendarGroup> {

	 public CalendarGroupFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, CalendarGroup.class,CalendarGroupOperations.class);
    }

	public CalendarGroupFetcher addParameter(String name, Object value) {
	    addCustomParameter(name, value);
		return this;
	}
	public ODataCollectionFetcher<Calendar, CalendarFetcher, CalendarCollectionOperations> getCalendars() {
        return new ODataCollectionFetcher<Calendar, CalendarFetcher,CalendarCollectionOperations>("Calendars", this, Calendar.class,CalendarCollectionOperations.class);
    }
}