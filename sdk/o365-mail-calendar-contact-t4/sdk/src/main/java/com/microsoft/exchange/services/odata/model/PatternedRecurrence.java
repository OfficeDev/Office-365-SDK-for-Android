
package com.microsoft.exchange.services.odata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.microsoft.exchange.services.odata.model.RecurrencePattern;

import com.microsoft.exchange.services.odata.model.RecurrenceRange;


public class PatternedRecurrence {


	@SerializedName("Pattern")
	@Expose
	private RecurrencePattern pattern;

	public RecurrencePattern getPattern() {
		 return pattern; 
	}

	public void setPattern(RecurrencePattern value) { 
		pattern = value; 
	}


	@SerializedName("Range")
	@Expose
	private RecurrenceRange range;

	public RecurrenceRange getRange() {
		 return range; 
	}

	public void setRange(RecurrenceRange value) { 
		range = value; 
	}

}