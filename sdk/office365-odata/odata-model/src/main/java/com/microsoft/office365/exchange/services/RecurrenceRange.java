/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.exchange.services;

public class RecurrenceRange {

	private RecurrenceRangeType type;

	public RecurrenceRangeType getType() {
		 return type; 
	}

	public void setType(RecurrenceRangeType value) { 
		type = value; 
	}

	private java.util.Date startDate;

	public java.util.Date getStartDate() {
		 return startDate; 
	}

	public void setStartDate(java.util.Date value) { 
		startDate = value; 
	}

	private java.util.Date endDate;

	public java.util.Date getEndDate() {
		 return endDate; 
	}

	public void setEndDate(java.util.Date value) { 
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