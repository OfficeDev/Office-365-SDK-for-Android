/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

public class RecurrenceRange {

	private RecurrenceRangeType type;

	public RecurrenceRangeType getType() {
		 return type; 
	}

	public void setType(RecurrenceRangeType value) { 
		type = value; 
	}

	private java.util.Calendar startDate;

	public java.util.Calendar getStartDate() {
		 return startDate; 
	}

	public void setStartDate(java.util.Calendar value) { 
		startDate = value; 
	}

	private java.util.Calendar endDate;

	public java.util.Calendar getEndDate() {
		 return endDate; 
	}

	public void setEndDate(java.util.Calendar value) { 
		endDate = value; 
	}

	private Integer numberOfOccurrences;

	public Integer getNumberOfOccurrences() {
		 return numberOfOccurrences; 
	}

	public void setNumberOfOccurrences(Integer value) { 
		numberOfOccurrences = value; 
	}
}