/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type Recurrence Pattern.
*/
public class RecurrencePattern {

	private RecurrencePatternType type;

	/**
	* Gets the Type.
	*
	* @return the RecurrencePatternType
	*/
	public RecurrencePatternType getType() {
		 return type; 
	}

	/**
	* Sets the Type.
	*
	* @param value the RecurrencePatternType
	*/
	public void setType(RecurrencePatternType value) { 
		type = value; 
	}

	private Integer interval;

	/**
	* Gets the Interval.
	*
	* @return the Integer
	*/
	public Integer getInterval() {
		 return interval; 
	}

	/**
	* Sets the Interval.
	*
	* @param value the Integer
	*/
	public void setInterval(Integer value) { 
		interval = value; 
	}

	private Integer dayOfMonth;

	/**
	* Gets the Day Of Month.
	*
	* @return the Integer
	*/
	public Integer getDayOfMonth() {
		 return dayOfMonth; 
	}

	/**
	* Sets the Day Of Month.
	*
	* @param value the Integer
	*/
	public void setDayOfMonth(Integer value) { 
		dayOfMonth = value; 
	}

	private Integer month;

	/**
	* Gets the Month.
	*
	* @return the Integer
	*/
	public Integer getMonth() {
		 return month; 
	}

	/**
	* Sets the Month.
	*
	* @param value the Integer
	*/
	public void setMonth(Integer value) { 
		month = value; 
	}

	private java.util.List<DayOfWeek> daysOfWeek;

	/**
	* Gets the Days Of Week.
	*
	* @return the java.util.List<DayOfWeek>
	*/
	public java.util.List<DayOfWeek> getDaysOfWeek() {
		 return daysOfWeek; 
	}

	/**
	* Sets the Days Of Week.
	*
	* @param value the java.util.List<DayOfWeek>
	*/
	public void setDaysOfWeek(java.util.List<DayOfWeek> value) { 
		daysOfWeek = value; 
	}

	private DayOfWeek firstDayOfWeek;

	/**
	* Gets the First Day Of Week.
	*
	* @return the DayOfWeek
	*/
	public DayOfWeek getFirstDayOfWeek() {
		 return firstDayOfWeek; 
	}

	/**
	* Sets the First Day Of Week.
	*
	* @param value the DayOfWeek
	*/
	public void setFirstDayOfWeek(DayOfWeek value) { 
		firstDayOfWeek = value; 
	}

	private WeekIndex index;

	/**
	* Gets the Index.
	*
	* @return the WeekIndex
	*/
	public WeekIndex getIndex() {
		 return index; 
	}

	/**
	* Sets the Index.
	*
	* @param value the WeekIndex
	*/
	public void setIndex(WeekIndex value) { 
		index = value; 
	}
}