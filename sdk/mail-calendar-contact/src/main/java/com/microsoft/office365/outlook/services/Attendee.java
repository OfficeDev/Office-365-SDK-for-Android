/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

public class Attendee extends Recipient {

	private ResponseStatus status;

	public ResponseStatus getStatus() {
		 return status; 
	}

	public void setStatus(ResponseStatus value) { 
		status = value; 
	}

	private AttendeeType type;

	public AttendeeType getType() {
		 return type; 
	}

	public void setType(AttendeeType value) { 
		type = value; 
	}
}