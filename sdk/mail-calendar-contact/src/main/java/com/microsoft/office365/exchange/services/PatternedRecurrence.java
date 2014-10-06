/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.exchange.services;

public class PatternedRecurrence {

	private RecurrencePattern pattern;

	public RecurrencePattern getPattern() {
		 return pattern; 
	}

	public void setPattern(RecurrencePattern value) { 
		pattern = value; 
	}

	private RecurrenceRange range;

	public RecurrenceRange getRange() {
		 return range; 
	}

	public void setRange(RecurrenceRange value) { 
		range = value; 
	}
}