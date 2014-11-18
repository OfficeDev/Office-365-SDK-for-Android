/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type Calendar.
*/
public class Calendar extends Entity {

    public Calendar(){
        setODataType("#Microsoft.OutlookServices.Calendar");
    }

    
    private java.util.List<Event> CalendarView = new java.util.ArrayList<Event>();

    /**
    * Gets the Calendar View.
    *
    * @return the CalendarView
    */
    public java.util.List<Event> getCalendarView() {
        return this.CalendarView; 
    }
        
    private java.util.List<Event> Events = new java.util.ArrayList<Event>();

    /**
    * Gets the Events.
    *
    * @return the Events
    */
    public java.util.List<Event> getEvents() {
        return this.Events; 
    }
            
	private String Name;

    /**
    * Gets the Name.
    *
    * @return the String
    */
    public String getName() {
        return this.Name; 
    }

    /**
    * Sets the Name.
    *
    * @param value the String
    */
    public void setName(String value) { 
        this.Name = value; 
    }
    
	private String ChangeKey;

    /**
    * Gets the Change Key.
    *
    * @return the String
    */
    public String getChangeKey() {
        return this.ChangeKey; 
    }

    /**
    * Sets the Change Key.
    *
    * @param value the String
    */
    public void setChangeKey(String value) { 
        this.ChangeKey = value; 
    }
}