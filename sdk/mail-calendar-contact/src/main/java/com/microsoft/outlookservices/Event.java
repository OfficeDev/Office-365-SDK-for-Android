/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type Event.
*/
public class Event extends Item {
	private String subject;

	/**
	* Gets the Subject.
	*
	* @return the String
	*/
	public String getSubject() {
		 return subject; 
	}

	/**
	* Sets the Subject.
	*
	* @param value the String
	*/
	public void setSubject(String value) { 
		subject = value; 
	}
	private ItemBody body;

	/**
	* Gets the Body.
	*
	* @return the ItemBody
	*/
	public ItemBody getBody() {
		 return body; 
	}

	/**
	* Sets the Body.
	*
	* @param value the ItemBody
	*/
	public void setBody(ItemBody value) { 
		body = value; 
	}
	private String bodyPreview;

	/**
	* Gets the Body Preview.
	*
	* @return the String
	*/
	public String getBodyPreview() {
		 return bodyPreview; 
	}

	/**
	* Sets the Body Preview.
	*
	* @param value the String
	*/
	public void setBodyPreview(String value) { 
		bodyPreview = value; 
	}
	private Importance importance;

	/**
	* Gets the Importance.
	*
	* @return the Importance
	*/
	public Importance getImportance() {
		 return importance; 
	}

	/**
	* Sets the Importance.
	*
	* @param value the Importance
	*/
	public void setImportance(Importance value) { 
		importance = value; 
	}
	private Boolean hasAttachments;

	/**
	* Gets the Has Attachments.
	*
	* @return the Boolean
	*/
	public Boolean getHasAttachments() {
		 return hasAttachments; 
	}

	/**
	* Sets the Has Attachments.
	*
	* @param value the Boolean
	*/
	public void setHasAttachments(Boolean value) { 
		hasAttachments = value; 
	}
	private java.util.Calendar start;

	/**
	* Gets the Start.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getStart() {
		 return start; 
	}

	/**
	* Sets the Start.
	*
	* @param value the java.util.Calendar
	*/
	public void setStart(java.util.Calendar value) { 
		start = value; 
	}
	private java.util.Calendar end;

	/**
	* Gets the End.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getEnd() {
		 return end; 
	}

	/**
	* Sets the End.
	*
	* @param value the java.util.Calendar
	*/
	public void setEnd(java.util.Calendar value) { 
		end = value; 
	}
	private Location location;

	/**
	* Gets the Location.
	*
	* @return the Location
	*/
	public Location getLocation() {
		 return location; 
	}

	/**
	* Sets the Location.
	*
	* @param value the Location
	*/
	public void setLocation(Location value) { 
		location = value; 
	}
	private FreeBusyStatus showAs;

	/**
	* Gets the Show As.
	*
	* @return the FreeBusyStatus
	*/
	public FreeBusyStatus getShowAs() {
		 return showAs; 
	}

	/**
	* Sets the Show As.
	*
	* @param value the FreeBusyStatus
	*/
	public void setShowAs(FreeBusyStatus value) { 
		showAs = value; 
	}
	private Boolean isAllDay;

	/**
	* Gets the Is All Day.
	*
	* @return the Boolean
	*/
	public Boolean getIsAllDay() {
		 return isAllDay; 
	}

	/**
	* Sets the Is All Day.
	*
	* @param value the Boolean
	*/
	public void setIsAllDay(Boolean value) { 
		isAllDay = value; 
	}
	private Boolean isCancelled;

	/**
	* Gets the Is Cancelled.
	*
	* @return the Boolean
	*/
	public Boolean getIsCancelled() {
		 return isCancelled; 
	}

	/**
	* Sets the Is Cancelled.
	*
	* @param value the Boolean
	*/
	public void setIsCancelled(Boolean value) { 
		isCancelled = value; 
	}
	private Boolean isOrganizer;

	/**
	* Gets the Is Organizer.
	*
	* @return the Boolean
	*/
	public Boolean getIsOrganizer() {
		 return isOrganizer; 
	}

	/**
	* Sets the Is Organizer.
	*
	* @param value the Boolean
	*/
	public void setIsOrganizer(Boolean value) { 
		isOrganizer = value; 
	}
	private Boolean responseRequested;

	/**
	* Gets the Response Requested.
	*
	* @return the Boolean
	*/
	public Boolean getResponseRequested() {
		 return responseRequested; 
	}

	/**
	* Sets the Response Requested.
	*
	* @param value the Boolean
	*/
	public void setResponseRequested(Boolean value) { 
		responseRequested = value; 
	}
	private EventType type;

	/**
	* Gets the Type.
	*
	* @return the EventType
	*/
	public EventType getType() {
		 return type; 
	}

	/**
	* Sets the Type.
	*
	* @param value the EventType
	*/
	public void setType(EventType value) { 
		type = value; 
	}
	private String seriesMasterId;

	/**
	* Gets the Series Master Id.
	*
	* @return the String
	*/
	public String getSeriesMasterId() {
		 return seriesMasterId; 
	}

	/**
	* Sets the Series Master Id.
	*
	* @param value the String
	*/
	public void setSeriesMasterId(String value) { 
		seriesMasterId = value; 
	}
	private java.util.List<Attendee> attendees;

	/**
	* Gets the Attendees.
	*
	* @return the java.util.List<Attendee>
	*/
	public java.util.List<Attendee> getAttendees() {
		 return attendees; 
	}

	/**
	* Sets the Attendees.
	*
	* @param value the java.util.List<Attendee>
	*/
	public void setAttendees(java.util.List<Attendee> value) { 
		attendees = value; 
	}
	private PatternedRecurrence recurrence;

	/**
	* Gets the Recurrence.
	*
	* @return the PatternedRecurrence
	*/
	public PatternedRecurrence getRecurrence() {
		 return recurrence; 
	}

	/**
	* Sets the Recurrence.
	*
	* @param value the PatternedRecurrence
	*/
	public void setRecurrence(PatternedRecurrence value) { 
		recurrence = value; 
	}
	private Recipient organizer;

	/**
	* Gets the Organizer.
	*
	* @return the Recipient
	*/
	public Recipient getOrganizer() {
		 return organizer; 
	}

	/**
	* Sets the Organizer.
	*
	* @param value the Recipient
	*/
	public void setOrganizer(Recipient value) { 
		organizer = value; 
	}
}