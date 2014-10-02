/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.exchange.services;

public class Event extends Item {
	private java.util.Calendar start;

	public java.util.Calendar getStart() {
		 return start; 
	}

	public void setStart(java.util.Calendar value) { 
		start = value; 
	}
	private java.util.Calendar end;

	public java.util.Calendar getEnd() {
		 return end; 
	}

	public void setEnd(java.util.Calendar value) { 
		end = value; 
	}
	private Location location;

	public Location getLocation() {
		 return location; 
	}

	public void setLocation(Location value) { 
		location = value; 
	}
	private FreeBusyStatus showAs;

	public FreeBusyStatus getShowAs() {
		 return showAs; 
	}

	public void setShowAs(FreeBusyStatus value) { 
		showAs = value; 
	}
	private Boolean isAllDay;

	public Boolean getIsAllDay() {
		 return isAllDay; 
	}

	public void setIsAllDay(Boolean value) { 
		isAllDay = value; 
	}
	private Boolean isCancelled;

	public Boolean getIsCancelled() {
		 return isCancelled; 
	}

	public void setIsCancelled(Boolean value) { 
		isCancelled = value; 
	}
	private Boolean isOrganizer;

	public Boolean getIsOrganizer() {
		 return isOrganizer; 
	}

	public void setIsOrganizer(Boolean value) { 
		isOrganizer = value; 
	}
	private Boolean responseRequested;

	public Boolean getResponseRequested() {
		 return responseRequested; 
	}

	public void setResponseRequested(Boolean value) { 
		responseRequested = value; 
	}
	private EventType type;

	public EventType getType() {
		 return type; 
	}

	public void setType(EventType value) { 
		type = value; 
	}
	private String seriesId;

	public String getSeriesId() {
		 return seriesId; 
	}

	public void setSeriesId(String value) { 
		seriesId = value; 
	}
	private java.util.List<Attendee> attendees;

	public java.util.List<Attendee> getAttendees() {
		 return attendees; 
	}

	public void setAttendees(java.util.List<Attendee> value) { 
		attendees = value; 
	}
	private PatternedRecurrence recurrence;

	public PatternedRecurrence getRecurrence() {
		 return recurrence; 
	}

	public void setRecurrence(PatternedRecurrence value) { 
		recurrence = value; 
	}
}