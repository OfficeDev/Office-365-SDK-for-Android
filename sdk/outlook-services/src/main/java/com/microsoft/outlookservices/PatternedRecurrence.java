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

	private RecurrencePattern Pattern;

	/**
	* Gets the Pattern.
	*
	* @return the RecurrencePattern
	*/
	public RecurrencePattern getPattern() {
		return this.Pattern; 
	}

	/**
	* Sets the Pattern.
	*
	* @param value the RecurrencePattern
	*/
	public void setPattern(RecurrencePattern value) { 
		Pattern = value; 
	}

	private RecurrenceRange Range;

	/**
	* Gets the Range.
	*
	* @return the RecurrenceRange
	*/
	public RecurrenceRange getRange() {
		return this.Range; 
	}

	/**
	* Sets the Range.
	*
	* @param value the RecurrenceRange
	*/
	public void setRange(RecurrenceRange value) { 
		Range = value; 
	}
}