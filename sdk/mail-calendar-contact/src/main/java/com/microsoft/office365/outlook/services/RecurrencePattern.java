/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

public class RecurrencePattern {

	private RecurrencePatternType type;

	public RecurrencePatternType getType() {
		 return type; 
	}

	public void setType(RecurrencePatternType value) { 
		type = value; 
	}

	private int interval;

	public int getInterval() {
		 return interval; 
	}

	public void setInterval(int value) { 
		interval = value; 
	}

	private int dayOfMonth;

	public int getDayOfMonth() {
		 return dayOfMonth; 
	}

	public void setDayOfMonth(int value) { 
		dayOfMonth = value; 
	}

	private int month;

	public int getMonth() {
		 return month; 
	}

	public void setMonth(int value) { 
		month = value; 
	}

	private java.util.List<DayOfWeek> daysOfWeek;

	public java.util.List<DayOfWeek> getDaysOfWeek() {
		 return daysOfWeek; 
	}

	public void setDaysOfWeek(java.util.List<DayOfWeek> value) { 
		daysOfWeek = value; 
	}

	private DayOfWeek firstDayOfWeek;

	public DayOfWeek getFirstDayOfWeek() {
		 return firstDayOfWeek; 
	}

	public void setFirstDayOfWeek(DayOfWeek value) { 
		firstDayOfWeek = value; 
	}

	private WeekIndex index;

	public WeekIndex getIndex() {
		 return index; 
	}

	public void setIndex(WeekIndex value) { 
		index = value; 
	}
}