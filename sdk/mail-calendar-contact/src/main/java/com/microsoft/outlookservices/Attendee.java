/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type Attendee.
*/
public class Attendee extends Recipient {

	private ResponseStatus status;

	/**
	* Gets the Status.
	*
	* @return the ResponseStatus
	*/
	public ResponseStatus getStatus() {
		 return status; 
	}

	/**
	* Sets the Status.
	*
	* @param value the ResponseStatus
	*/
	public void setStatus(ResponseStatus value) { 
		status = value; 
	}

	private AttendeeType type;

	/**
	* Gets the Type.
	*
	* @return the AttendeeType
	*/
	public AttendeeType getType() {
		 return type; 
	}

	/**
	* Sets the Type.
	*
	* @param value the AttendeeType
	*/
	public void setType(AttendeeType value) { 
		type = value; 
	}
}