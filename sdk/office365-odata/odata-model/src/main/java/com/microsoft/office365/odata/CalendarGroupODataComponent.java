/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import com.microsoft.office365.exchange.services.Calendar;
import com.microsoft.office365.exchange.services.CalendarGroup;

public class CalendarGroupODataComponent extends BaseEntityODataComponent<CalendarGroup> implements Executable<CalendarGroup> {

    public CalendarGroupODataComponent(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, CalendarGroup.class);
    }

    public ODataCollection<Calendar, CalendarODataComponent, CalendarCollectionOperations> getCalendars() {
        return new ODataCollection<Calendar, CalendarODataComponent, CalendarCollectionOperations>("Calendars", this, Calendar.class, CalendarCollectionOperations.class);
    }
}