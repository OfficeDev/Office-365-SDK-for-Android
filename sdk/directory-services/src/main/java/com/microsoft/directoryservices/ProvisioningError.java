/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type Provisioning Error.
*/
public class ProvisioningError {

	private String errorDetail;

	/**
	* Gets the error Detail.
	*
	* @return the String
	*/
	public String geterrorDetail() {
		return this.errorDetail; 
	}

	/**
	* Sets the error Detail.
	*
	* @param value the String
	*/
	public void seterrorDetail(String value) { 
		errorDetail = value; 
	}

	private Boolean resolved;

	/**
	* Gets the resolved.
	*
	* @return the Boolean
	*/
	public Boolean getresolved() {
		return this.resolved; 
	}

	/**
	* Sets the resolved.
	*
	* @param value the Boolean
	*/
	public void setresolved(Boolean value) { 
		resolved = value; 
	}

	private String service;

	/**
	* Gets the service.
	*
	* @return the String
	*/
	public String getservice() {
		return this.service; 
	}

	/**
	* Sets the service.
	*
	* @param value the String
	*/
	public void setservice(String value) { 
		service = value; 
	}

	private java.util.Calendar timestamp;

	/**
	* Gets the timestamp.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar gettimestamp() {
		return this.timestamp; 
	}

	/**
	* Sets the timestamp.
	*
	* @param value the java.util.Calendar
	*/
	public void settimestamp(java.util.Calendar value) { 
		timestamp = value; 
	}
}