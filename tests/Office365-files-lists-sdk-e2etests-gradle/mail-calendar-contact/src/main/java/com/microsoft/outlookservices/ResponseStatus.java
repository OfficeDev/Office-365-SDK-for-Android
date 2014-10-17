/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type Response Status.
*/
public class ResponseStatus {

	private ResponseType response;

	/**
	* Gets the Response.
	*
	* @return the ResponseType
	*/
	public ResponseType getResponse() {
		 return response; 
	}

	/**
	* Sets the Response.
	*
	* @param value the ResponseType
	*/
	public void setResponse(ResponseType value) { 
		response = value; 
	}

	private java.util.Calendar time;

	/**
	* Gets the Time.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getTime() {
		 return time; 
	}

	/**
	* Sets the Time.
	*
	* @param value the java.util.Calendar
	*/
	public void setTime(java.util.Calendar value) { 
		time = value; 
	}
}