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
 * The type  CalendarFetcher.
 */
public class CalendarFetcher extends ODataEntityFetcher<Calendar,CalendarOperations> 
                                     implements Readable<Calendar> {

     /**
     * Instantiates a new CalendarFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
     public CalendarFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Calendar.class,CalendarOperations.class);
    }

         /**
     * Gets calendar view.
     *
     * @return the calendar view
     */
    public ODataCollectionFetcher<Event, EventFetcher, EventCollectionOperations> getCalendarView() {
        return new ODataCollectionFetcher<Event, EventFetcher,EventCollectionOperations>("CalendarView", this, Event.class,EventCollectionOperations.class);
    }

    /**
     * Gets calendar view.
     *
     * @return the calendar view
     */
    public EventFetcher getCalendarView(String id){
         return new ODataCollectionFetcher<Event, EventFetcher,EventCollectionOperations>("CalendarView", this, Event.class,EventCollectionOperations.class).getById(id);
    }
     /**
     * Gets events.
     *
     * @return the events
     */
    public ODataCollectionFetcher<Event, EventFetcher, EventCollectionOperations> getEvents() {
        return new ODataCollectionFetcher<Event, EventFetcher,EventCollectionOperations>("Events", this, Event.class,EventCollectionOperations.class);
    }

    /**
     * Gets event.
     *
     * @return the event
     */
    public EventFetcher getEvent(String id){
         return new ODataCollectionFetcher<Event, EventFetcher,EventCollectionOperations>("Events", this, Event.class,EventCollectionOperations.class).getById(id);
    }
}