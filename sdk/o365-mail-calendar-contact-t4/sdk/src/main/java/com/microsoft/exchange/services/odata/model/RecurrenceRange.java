
package com.microsoft.exchange.services.odata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.microsoft.exchange.services.odata.model.RecurrenceRangeType;


public class RecurrenceRange {


	@SerializedName("Type")
	@Expose
	private RecurrenceRangeType type;

	public RecurrenceRangeType getType() {
		 return type; 
	}

	public void setType(RecurrenceRangeType value) { 
		type = value; 
	}


	@SerializedName("StartDate")
	@Expose
	private java.util.Date startDate;

	public java.util.Date getStartDate() {
		 return startDate; 
	}

	public void setStartDate(java.util.Date value) { 
		startDate = value; 
	}


	@SerializedName("EndDate")
	@Expose
	private java.util.Date endDate;

	public java.util.Date getEndDate() {
		 return endDate; 
	}

	public void setEndDate(java.util.Date value) { 
		endDate = value; 
	}


	@SerializedName("NumberOfOccurrences")
	@Expose
	private Integer numberOfOccurrences;

	public Integer getNumberOfOccurrences() {
		 return numberOfOccurrences; 
	}

	public void setNumberOfOccurrences(Integer value) { 
		numberOfOccurrences = value; 
	}

}