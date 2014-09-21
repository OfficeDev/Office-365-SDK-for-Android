
package com.microsoft.exchange.services.odata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.microsoft.exchange.services.odata.model.RecurrencePatternType;

import com.system.DayOfWeek;

import com.microsoft.exchange.services.odata.model.WeekIndex;


public class RecurrencePattern {


	@SerializedName("Type")
	@Expose
	private RecurrencePatternType type;

	public RecurrencePatternType getType() {
		 return type; 
	}

	public void setType(RecurrencePatternType value) { 
		type = value; 
	}


	@SerializedName("Interval")
	@Expose
	private Integer interval;

	public Integer getInterval() {
		 return interval; 
	}

	public void setInterval(Integer value) { 
		interval = value; 
	}


	@SerializedName("DayOfMonth")
	@Expose
	private Integer dayOfMonth;

	public Integer getDayOfMonth() {
		 return dayOfMonth; 
	}

	public void setDayOfMonth(Integer value) { 
		dayOfMonth = value; 
	}


	@SerializedName("Month")
	@Expose
	private Integer month;

	public Integer getMonth() {
		 return month; 
	}

	public void setMonth(Integer value) { 
		month = value; 
	}


	@SerializedName("DaysOfWeek")
	@Expose
	private java.util.List<DayOfWeek> daysOfWeek;

	public java.util.List<DayOfWeek> getDaysOfWeek() {
		 return daysOfWeek; 
	}

	public void setDaysOfWeek(java.util.List<DayOfWeek> value) { 
		daysOfWeek = value; 
	}


	@SerializedName("FirstDayOfWeek")
	@Expose
	private DayOfWeek firstDayOfWeek;

	public DayOfWeek getFirstDayOfWeek() {
		 return firstDayOfWeek; 
	}

	public void setFirstDayOfWeek(DayOfWeek value) { 
		firstDayOfWeek = value; 
	}


	@SerializedName("Index")
	@Expose
	private WeekIndex index;

	public WeekIndex getIndex() {
		 return index; 
	}

	public void setIndex(WeekIndex value) { 
		index = value; 
	}

}