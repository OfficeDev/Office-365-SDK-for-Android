
package com.microsoft.exchange.services.odata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



import com.microsoft.exchange.services.odata.model.Event;



public class Calendar extends Entity {

	@SerializedName("Name")
	@Expose
	private String name;

	public String getName() {
		 return name; 
	}

	public void setName(String value) { 
		name = value; 
	}

	@SerializedName("ChangeKey")
	@Expose
	private String changeKey;

	public String getChangeKey() {
		 return changeKey; 
	}

	public void setChangeKey(String value) { 
		changeKey = value; 
	}

	@SerializedName("Events")
	@Expose
	private java.util.List<Event> events;

	public java.util.List<Event> getEvents() {
		 return events; 
	}

	public void setEvents(java.util.List<Event> value) { 
		events = value; 
	}

}