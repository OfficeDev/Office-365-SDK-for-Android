/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

/**
 * The type RecurrenceRange.
*/
public class RecurrenceRange {

	private RecurrenceRangeType type;

	 /**
     * Gets the type.		
     *
     * @return the type
     */
	public RecurrenceRangeType getType() {
		 return type; 
	}

	 /**
     * Sets the type.		
     *
     * @param value the value
     */
	public void setType(RecurrenceRangeType value) { 
		type = value; 
	}

	private java.util.Calendar startDate;

	 /**
     * Gets the start date.		
     *
     * @return the start date
     */
	public java.util.Calendar getStartDate() {
		 return startDate; 
	}

	 /**
     * Sets the start date.		
     *
     * @param value the value
     */
	public void setStartDate(java.util.Calendar value) { 
		startDate = value; 
	}

	private java.util.Calendar endDate;

	 /**
     * Gets the end date.		
     *
     * @return the end date
     */
	public java.util.Calendar getEndDate() {
		 return endDate; 
	}

	 /**
     * Sets the end date.		
     *
     * @param value the value
     */
	public void setEndDate(java.util.Calendar value) { 
		endDate = value; 
	}

	private Integer numberOfOccurrences;

	 /**
     * Gets the number of occurrences.		
     *
     * @return the number of occurrences
     */
	public Integer getNumberOfOccurrences() {
		 return numberOfOccurrences; 
	}

	 /**
     * Sets the number of occurrences.		
     *
     * @param value the value
     */
	public void setNumberOfOccurrences(Integer value) { 
		numberOfOccurrences = value; 
	}
}