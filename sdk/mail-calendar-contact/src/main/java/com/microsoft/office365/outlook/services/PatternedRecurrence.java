/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

/**
 * The type PatternedRecurrence.
*/
public class PatternedRecurrence {

	private RecurrencePattern pattern;

	 /**
     * Gets the pattern.		
     *
     * @return the pattern
     */
	public RecurrencePattern getPattern() {
		 return pattern; 
	}

	 /**
     * Sets the pattern.		
     *
     * @param value the value
     */
	public void setPattern(RecurrencePattern value) { 
		pattern = value; 
	}

	private RecurrenceRange range;

	 /**
     * Gets the range.		
     *
     * @return the range
     */
	public RecurrenceRange getRange() {
		 return range; 
	}

	 /**
     * Sets the range.		
     *
     * @param value the value
     */
	public void setRange(RecurrenceRange value) { 
		range = value; 
	}
}