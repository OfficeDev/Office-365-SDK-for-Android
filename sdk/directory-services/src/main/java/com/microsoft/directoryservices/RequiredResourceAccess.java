/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type Required Resource Access.
*/
public class RequiredResourceAccess {

	private String resourceAppId;

	/**
	* Gets the resource App Id.
	*
	* @return the String
	*/
	public String getresourceAppId() {
		return this.resourceAppId; 
	}

	/**
	* Sets the resource App Id.
	*
	* @param value the String
	*/
	public void setresourceAppId(String value) { 
		resourceAppId = value; 
	}

	private java.util.List<ResourceAccess> resourceAccess;

	/**
	* Gets the resource Access.
	*
	* @return the java.util.List<ResourceAccess>
	*/
	public java.util.List<ResourceAccess> getresourceAccess() {
		return this.resourceAccess; 
	}

	/**
	* Sets the resource Access.
	*
	* @param value the java.util.List<ResourceAccess>
	*/
	public void setresourceAccess(java.util.List<ResourceAccess> value) { 
		resourceAccess = value; 
	}
}