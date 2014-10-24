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
	
	private String $$_ODataType = "#Microsoft.OutlookServices.ResponseStatus";


	private ResponseType Response;

	/**
	* Gets the Response.
	*
	* @return the ResponseType
	*/
	public ResponseType getResponse() {
		return this.Response; 
	}

	/**
	* Sets the Response.
	*
	* @param value the ResponseType
	*/
	public void setResponse(ResponseType value) { 
		this.Response = value; 
	}

	private java.util.Calendar Time;

	/**
	* Gets the Time.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getTime() {
		return this.Time; 
	}

	/**
	* Sets the Time.
	*
	* @param value the java.util.Calendar
	*/
	public void setTime(java.util.Calendar value) { 
		this.Time = value; 
	}
}