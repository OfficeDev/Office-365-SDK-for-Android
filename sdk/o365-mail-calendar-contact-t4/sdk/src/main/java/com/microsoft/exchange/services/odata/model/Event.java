
package com.microsoft.exchange.services.odata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.core.*;


import com.microsoft.exchange.services.odata.model.Location;



import com.microsoft.exchange.services.odata.model.FreeBusyStatus;



import com.microsoft.exchange.services.odata.model.EventType;



import com.microsoft.exchange.services.odata.model.Attendee;



import com.microsoft.exchange.services.odata.model.PatternedRecurrence;



import com.microsoft.exchange.services.odata.model.Calendar;



public class Event extends Item {

	@SerializedName("Start")
	@Expose
	private java.util.Date start;

	public java.util.Date getStart() {
		 return start; 
	}

	public void setStart(java.util.Date value) { 
		start = value; 
	}

	@SerializedName("End")
	@Expose
	private java.util.Date end;

	public java.util.Date getEnd() {
		 return end; 
	}

	public void setEnd(java.util.Date value) { 
		end = value; 
	}

	@SerializedName("Location")
	@Expose
	private Location location;

	public Location getLocation() {
		 return location; 
	}

	public void setLocation(Location value) { 
		location = value; 
	}

	@SerializedName("ShowAs")
	@Expose
	private FreeBusyStatus showAs;

	public FreeBusyStatus getShowAs() {
		 return showAs; 
	}

	public void setShowAs(FreeBusyStatus value) { 
		showAs = value; 
	}

	@SerializedName("IsAllDay")
	@Expose
	private Boolean isAllDay;

	public Boolean getIsAllDay() {
		 return isAllDay; 
	}

	public void setIsAllDay(Boolean value) { 
		isAllDay = value; 
	}

	@SerializedName("IsCancelled")
	@Expose
	private Boolean isCancelled;

	public Boolean getIsCancelled() {
		 return isCancelled; 
	}

	public void setIsCancelled(Boolean value) { 
		isCancelled = value; 
	}

	@SerializedName("IsOrganizer")
	@Expose
	private Boolean isOrganizer;

	public Boolean getIsOrganizer() {
		 return isOrganizer; 
	}

	public void setIsOrganizer(Boolean value) { 
		isOrganizer = value; 
	}

	@SerializedName("ResponseRequested")
	@Expose
	private Boolean responseRequested;

	public Boolean getResponseRequested() {
		 return responseRequested; 
	}

	public void setResponseRequested(Boolean value) { 
		responseRequested = value; 
	}

	@SerializedName("Type")
	@Expose
	private EventType type;

	public EventType getType() {
		 return type; 
	}

	public void setType(EventType value) { 
		type = value; 
	}

	@SerializedName("SeriesId")
	@Expose
	private String seriesId;

	public String getSeriesId() {
		 return seriesId; 
	}

	public void setSeriesId(String value) { 
		seriesId = value; 
	}

	@SerializedName("Attendees")
	@Expose
	private java.util.List<Attendee> attendees;

	public java.util.List<Attendee> getAttendees() {
		 return attendees; 
	}

	public void setAttendees(java.util.List<Attendee> value) { 
		attendees = value; 
	}

	@SerializedName("Recurrence")
	@Expose
	private PatternedRecurrence recurrence;

	public PatternedRecurrence getRecurrence() {
		 return recurrence; 
	}

	public void setRecurrence(PatternedRecurrence value) { 
		recurrence = value; 
	}

	@SerializedName("Calendar")
	@Expose
	private Calendar calendar;

	public Calendar getCalendar() {
		 return calendar; 
	}

	public void setCalendar(Calendar value) { 
		calendar = value; 
	}

	public EventActions getOperations(){
		return new EventActions(this.getId());
	}

}