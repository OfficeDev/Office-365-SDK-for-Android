/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

/**
 * The type Attendee.
*/
public class Attendee extends Recipient {

	private ResponseStatus status;

	 /**
     * Gets the status.		
     *
     * @return the status
     */
	public ResponseStatus getStatus() {
		 return status; 
	}

	 /**
     * Sets the status.		
     *
     * @param value the value
     */
	public void setStatus(ResponseStatus value) { 
		status = value; 
	}

	private AttendeeType type;

	 /**
     * Gets the type.		
     *
     * @return the type
     */
	public AttendeeType getType() {
		 return type; 
	}

	 /**
     * Sets the type.		
     *
     * @param value the value
     */
	public void setType(AttendeeType value) { 
		type = value; 
	}
}