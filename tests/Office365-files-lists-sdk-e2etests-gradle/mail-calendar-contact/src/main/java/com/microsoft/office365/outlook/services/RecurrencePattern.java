/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

/**
 * The type RecurrencePattern.
*/
public class RecurrencePattern {

	private RecurrencePatternType type;

	 /**
     * Gets the type.		
     *
     * @return the type
     */
	public RecurrencePatternType getType() {
		 return type; 
	}

	 /**
     * Sets the type.		
     *
     * @param value the value
     */
	public void setType(RecurrencePatternType value) { 
		type = value; 
	}

	private Integer interval;

	 /**
     * Gets the interval.		
     *
     * @return the interval
     */
	public Integer getInterval() {
		 return interval; 
	}

	 /**
     * Sets the interval.		
     *
     * @param value the value
     */
	public void setInterval(Integer value) { 
		interval = value; 
	}

	private Integer dayOfMonth;

	 /**
     * Gets the day of month.		
     *
     * @return the day of month
     */
	public Integer getDayOfMonth() {
		 return dayOfMonth; 
	}

	 /**
     * Sets the day of month.		
     *
     * @param value the value
     */
	public void setDayOfMonth(Integer value) { 
		dayOfMonth = value; 
	}

	private Integer month;

	 /**
     * Gets the month.		
     *
     * @return the month
     */
	public Integer getMonth() {
		 return month; 
	}

	 /**
     * Sets the month.		
     *
     * @param value the value
     */
	public void setMonth(Integer value) { 
		month = value; 
	}

	private java.util.List<DayOfWeek> daysOfWeek;

	 /**
     * Gets the days of week.		
     *
     * @return the days of week
     */
	public java.util.List<DayOfWeek> getDaysOfWeek() {
		 return daysOfWeek; 
	}

	 /**
     * Sets the days of week.		
     *
     * @param value the value
     */
	public void setDaysOfWeek(java.util.List<DayOfWeek> value) { 
		daysOfWeek = value; 
	}

	private DayOfWeek firstDayOfWeek;

	 /**
     * Gets the first day of week.		
     *
     * @return the first day of week
     */
	public DayOfWeek getFirstDayOfWeek() {
		 return firstDayOfWeek; 
	}

	 /**
     * Sets the first day of week.		
     *
     * @param value the value
     */
	public void setFirstDayOfWeek(DayOfWeek value) { 
		firstDayOfWeek = value; 
	}

	private WeekIndex index;

	 /**
     * Gets the index.		
     *
     * @return the index
     */
	public WeekIndex getIndex() {
		 return index; 
	}

	 /**
     * Sets the index.		
     *
     * @param value the value
     */
	public void setIndex(WeekIndex value) { 
		index = value; 
	}
}