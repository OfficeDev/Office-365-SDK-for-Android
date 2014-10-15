/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type Patterned Recurrence.
*/
public class PatternedRecurrence {

	private RecurrencePattern pattern;

	/**
	* Gets the Pattern.
	*
	* @return the RecurrencePattern
	*/
	public RecurrencePattern getPattern() {
		 return pattern; 
	}

	/**
	* Sets the Pattern.
	*
	* @param value the RecurrencePattern
	*/
	public void setPattern(RecurrencePattern value) { 
		pattern = value; 
	}

	private RecurrenceRange range;

	/**
	* Gets the Range.
	*
	* @return the RecurrenceRange
	*/
	public RecurrenceRange getRange() {
		 return range; 
	}

	/**
	* Sets the Range.
	*
	* @param value the RecurrenceRange
	*/
	public void setRange(RecurrenceRange value) { 
		range = value; 
	}
}