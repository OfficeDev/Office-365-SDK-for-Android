/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

/**
 * The type Event.
*/
public class Event extends Item {
	private String subject;


     /**
     * Gets the subject.		
     *
     * @return the subject
     */
	public String getSubject() {
		 return subject; 
	}
	
     /**
     * Sets the subject.		
     *
     * @param value the value
     */
	public void setSubject(String value) { 
		subject = value; 
	}
	private ItemBody body;


     /**
     * Gets the body.		
     *
     * @return the body
     */
	public ItemBody getBody() {
		 return body; 
	}
	
     /**
     * Sets the body.		
     *
     * @param value the value
     */
	public void setBody(ItemBody value) { 
		body = value; 
	}
	private String bodyPreview;


     /**
     * Gets the body preview.		
     *
     * @return the body preview
     */
	public String getBodyPreview() {
		 return bodyPreview; 
	}
	
     /**
     * Sets the body preview.		
     *
     * @param value the value
     */
	public void setBodyPreview(String value) { 
		bodyPreview = value; 
	}
	private Importance importance;


     /**
     * Gets the importance.		
     *
     * @return the importance
     */
	public Importance getImportance() {
		 return importance; 
	}
	
     /**
     * Sets the importance.		
     *
     * @param value the value
     */
	public void setImportance(Importance value) { 
		importance = value; 
	}
	private Boolean hasAttachments;


     /**
     * Gets the has attachments.		
     *
     * @return the has attachments
     */
	public Boolean getHasAttachments() {
		 return hasAttachments; 
	}
	
     /**
     * Sets the has attachments.		
     *
     * @param value the value
     */
	public void setHasAttachments(Boolean value) { 
		hasAttachments = value; 
	}
	private java.util.Calendar start;


     /**
     * Gets the start.		
     *
     * @return the start
     */
	public java.util.Calendar getStart() {
		 return start; 
	}
	
     /**
     * Sets the start.		
     *
     * @param value the value
     */
	public void setStart(java.util.Calendar value) { 
		start = value; 
	}
	private java.util.Calendar end;


     /**
     * Gets the end.		
     *
     * @return the end
     */
	public java.util.Calendar getEnd() {
		 return end; 
	}
	
     /**
     * Sets the end.		
     *
     * @param value the value
     */
	public void setEnd(java.util.Calendar value) { 
		end = value; 
	}
	private Location location;


     /**
     * Gets the location.		
     *
     * @return the location
     */
	public Location getLocation() {
		 return location; 
	}
	
     /**
     * Sets the location.		
     *
     * @param value the value
     */
	public void setLocation(Location value) { 
		location = value; 
	}
	private FreeBusyStatus showAs;


     /**
     * Gets the show as.		
     *
     * @return the show as
     */
	public FreeBusyStatus getShowAs() {
		 return showAs; 
	}
	
     /**
     * Sets the show as.		
     *
     * @param value the value
     */
	public void setShowAs(FreeBusyStatus value) { 
		showAs = value; 
	}
	private Boolean isAllDay;


     /**
     * Gets the is all day.		
     *
     * @return the is all day
     */
	public Boolean getIsAllDay() {
		 return isAllDay; 
	}
	
     /**
     * Sets the is all day.		
     *
     * @param value the value
     */
	public void setIsAllDay(Boolean value) { 
		isAllDay = value; 
	}
	private Boolean isCancelled;


     /**
     * Gets the is cancelled.		
     *
     * @return the is cancelled
     */
	public Boolean getIsCancelled() {
		 return isCancelled; 
	}
	
     /**
     * Sets the is cancelled.		
     *
     * @param value the value
     */
	public void setIsCancelled(Boolean value) { 
		isCancelled = value; 
	}
	private Boolean isOrganizer;


     /**
     * Gets the is organizer.		
     *
     * @return the is organizer
     */
	public Boolean getIsOrganizer() {
		 return isOrganizer; 
	}
	
     /**
     * Sets the is organizer.		
     *
     * @param value the value
     */
	public void setIsOrganizer(Boolean value) { 
		isOrganizer = value; 
	}
	private Boolean responseRequested;


     /**
     * Gets the response requested.		
     *
     * @return the response requested
     */
	public Boolean getResponseRequested() {
		 return responseRequested; 
	}
	
     /**
     * Sets the response requested.		
     *
     * @param value the value
     */
	public void setResponseRequested(Boolean value) { 
		responseRequested = value; 
	}
	private EventType type;


     /**
     * Gets the type.		
     *
     * @return the type
     */
	public EventType getType() {
		 return type; 
	}
	
     /**
     * Sets the type.		
     *
     * @param value the value
     */
	public void setType(EventType value) { 
		type = value; 
	}
	private String seriesMasterId;


     /**
     * Gets the series master id.		
     *
     * @return the series master id
     */
	public String getSeriesMasterId() {
		 return seriesMasterId; 
	}
	
     /**
     * Sets the series master id.		
     *
     * @param value the value
     */
	public void setSeriesMasterId(String value) { 
		seriesMasterId = value; 
	}
	private java.util.List<Attendee> attendees;


     /**
     * Gets the attendees.		
     *
     * @return the attendees
     */
	public java.util.List<Attendee> getAttendees() {
		 return attendees; 
	}
	
     /**
     * Sets the attendees.		
     *
     * @param value the value
     */
	public void setAttendees(java.util.List<Attendee> value) { 
		attendees = value; 
	}
	private PatternedRecurrence recurrence;


     /**
     * Gets the recurrence.		
     *
     * @return the recurrence
     */
	public PatternedRecurrence getRecurrence() {
		 return recurrence; 
	}
	
     /**
     * Sets the recurrence.		
     *
     * @param value the value
     */
	public void setRecurrence(PatternedRecurrence value) { 
		recurrence = value; 
	}
	private Recipient organizer;


     /**
     * Gets the organizer.		
     *
     * @return the organizer
     */
	public Recipient getOrganizer() {
		 return organizer; 
	}
	
     /**
     * Sets the organizer.		
     *
     * @param value the value
     */
	public void setOrganizer(Recipient value) { 
		organizer = value; 
	}
}