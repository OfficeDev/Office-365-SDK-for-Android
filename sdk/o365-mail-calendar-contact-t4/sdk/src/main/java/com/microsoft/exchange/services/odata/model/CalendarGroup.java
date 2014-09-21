
package com.microsoft.exchange.services.odata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



import com.microsoft.exchange.services.odata.model.Calendar;



public class CalendarGroup extends Entity {

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

	@SerializedName("ClassId")
	@Expose
	private java.util.UUID classId;

	public java.util.UUID getClassId() {
		 return classId; 
	}

	public void setClassId(java.util.UUID value) { 
		classId = value; 
	}

	@SerializedName("Calendars")
	@Expose
	private java.util.List<Calendar> calendars;

	public java.util.List<Calendar> getCalendars() {
		 return calendars; 
	}

	public void setCalendars(java.util.List<Calendar> value) { 
		calendars = value; 
	}

}