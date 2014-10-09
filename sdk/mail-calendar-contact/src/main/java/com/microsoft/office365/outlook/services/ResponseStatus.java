/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

public class ResponseStatus {

	private ResponseType response;

	public ResponseType getResponse() {
		 return response; 
	}

	public void setResponse(ResponseType value) { 
		response = value; 
	}

	private java.util.Calendar time;

	public java.util.Calendar getTime() {
		 return time; 
	}

	public void setTime(java.util.Calendar value) { 
		time = value; 
	}
}