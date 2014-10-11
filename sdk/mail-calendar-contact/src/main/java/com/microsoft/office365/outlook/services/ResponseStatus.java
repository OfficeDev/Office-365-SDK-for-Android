/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

/**
 * The type ResponseStatus.
*/
public class ResponseStatus {

	private ResponseType response;

	 /**
     * Gets the response.		
     *
     * @return the response
     */
	public ResponseType getResponse() {
		 return response; 
	}

	 /**
     * Sets the response.		
     *
     * @param value the value
     */
	public void setResponse(ResponseType value) { 
		response = value; 
	}

	private java.util.Calendar time;

	 /**
     * Gets the time.		
     *
     * @return the time
     */
	public java.util.Calendar getTime() {
		 return time; 
	}

	 /**
     * Sets the time.		
     *
     * @param value the value
     */
	public void setTime(java.util.Calendar value) { 
		time = value; 
	}
}