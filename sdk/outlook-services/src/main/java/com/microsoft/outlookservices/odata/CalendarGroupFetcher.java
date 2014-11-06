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
 * The type  CalendarGroupFetcher.
 */
public class CalendarGroupFetcher extends ODataEntityFetcher<CalendarGroup,CalendarGroupOperations> 
                                     implements Readable<CalendarGroup> {

     /**
     * Instantiates a new CalendarGroupFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
     public CalendarGroupFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, CalendarGroup.class,CalendarGroupOperations.class);
    }

         /**
     * Gets calendars.
     *
     * @return the calendars
     */
    public ODataCollectionFetcher<Calendar, CalendarFetcher, CalendarCollectionOperations> getCalendars() {
        return new ODataCollectionFetcher<Calendar, CalendarFetcher,CalendarCollectionOperations>("Calendars", this, Calendar.class,CalendarCollectionOperations.class);
    }

    /**
     * Gets calendar.
     *
     * @return the calendar
     */
    public CalendarFetcher getCalendar(String id){
         return new ODataCollectionFetcher<Calendar, CalendarFetcher,CalendarCollectionOperations>("Calendars", this, Calendar.class,CalendarCollectionOperations.class).getById(id);
    }
}