/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type Directory Role Template.
*/
public class DirectoryRoleTemplate extends DirectoryObject {
	private String description;

	/**
	* Gets the description.
	*
	* @return the String
	*/
	public String getdescription() {
		return this.description; 
	}

	/**
	* Sets the description.
	*
	* @param value the String
	*/
	public void setdescription(String value) { 
		description = value; 
	}
	private String displayName;

	/**
	* Gets the display Name.
	*
	* @return the String
	*/
	public String getdisplayName() {
		return this.displayName; 
	}

	/**
	* Sets the display Name.
	*
	* @param value the String
	*/
	public void setdisplayName(String value) { 
		displayName = value; 
	}
}