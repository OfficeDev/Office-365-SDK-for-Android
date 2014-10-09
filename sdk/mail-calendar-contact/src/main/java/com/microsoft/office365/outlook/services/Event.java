/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

public class Event extends Item {
	private String subject;

	public String getSubject() {
		 return subject; 
	}

	public void setSubject(String value) { 
		subject = value; 
	}
	private ItemBody body;

	public ItemBody getBody() {
		 return body; 
	}

	public void setBody(ItemBody value) { 
		body = value; 
	}
	private String bodyPreview;

	public String getBodyPreview() {
		 return bodyPreview; 
	}

	public void setBodyPreview(String value) { 
		bodyPreview = value; 
	}
	private Importance importance;

	public Importance getImportance() {
		 return importance; 
	}

	public void setImportance(Importance value) { 
		importance = value; 
	}
	private boolean hasAttachments;

	public boolean getHasAttachments() {
		 return hasAttachments; 
	}

	public void setHasAttachments(boolean value) { 
		hasAttachments = value; 
	}
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
	private boolean isAllDay;

	public boolean getIsAllDay() {
		 return isAllDay; 
	}

	public void setIsAllDay(boolean value) { 
		isAllDay = value; 
	}
	private boolean isCancelled;

	public boolean getIsCancelled() {
		 return isCancelled; 
	}

	public void setIsCancelled(boolean value) { 
		isCancelled = value; 
	}
	private boolean isOrganizer;

	public boolean getIsOrganizer() {
		 return isOrganizer; 
	}

	public void setIsOrganizer(boolean value) { 
		isOrganizer = value; 
	}
	private boolean responseRequested;

	public boolean getResponseRequested() {
		 return responseRequested; 
	}

	public void setResponseRequested(boolean value) { 
		responseRequested = value; 
	}
	private EventType type;

	public EventType getType() {
		 return type; 
	}

	public void setType(EventType value) { 
		type = value; 
	}
	private String seriesMasterId;

	public String getSeriesMasterId() {
		 return seriesMasterId; 
	}

	public void setSeriesMasterId(String value) { 
		seriesMasterId = value; 
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
	private Recipient organizer;

	public Recipient getOrganizer() {
		 return organizer; 
	}

	public void setOrganizer(Recipient value) { 
		organizer = value; 
	}
}