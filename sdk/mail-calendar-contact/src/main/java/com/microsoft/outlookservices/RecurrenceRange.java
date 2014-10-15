/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type Recurrence Range.
*/
public class RecurrenceRange {

	private RecurrenceRangeType type;

	/**
	* Gets the Type.
	*
	* @return the RecurrenceRangeType
	*/
	public RecurrenceRangeType getType() {
		 return type; 
	}

	/**
	* Sets the Type.
	*
	* @param value the RecurrenceRangeType
	*/
	public void setType(RecurrenceRangeType value) { 
		type = value; 
	}

	private java.util.Calendar startDate;

	/**
	* Gets the Start Date.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getStartDate() {
		 return startDate; 
	}

	/**
	* Sets the Start Date.
	*
	* @param value the java.util.Calendar
	*/
	public void setStartDate(java.util.Calendar value) { 
		startDate = value; 
	}

	private java.util.Calendar endDate;

	/**
	* Gets the End Date.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getEndDate() {
		 return endDate; 
	}

	/**
	* Sets the End Date.
	*
	* @param value the java.util.Calendar
	*/
	public void setEndDate(java.util.Calendar value) { 
		endDate = value; 
	}

	private Integer numberOfOccurrences;

	/**
	* Gets the Number Of Occurrences.
	*
	* @return the Integer
	*/
	public Integer getNumberOfOccurrences() {
		 return numberOfOccurrences; 
	}

	/**
	* Sets the Number Of Occurrences.
	*
	* @param value the Integer
	*/
	public void setNumberOfOccurrences(Integer value) { 
		numberOfOccurrences = value; 
	}
}