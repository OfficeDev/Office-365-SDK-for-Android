/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.fileservices;

/**
 * The type Application Info.
*/
public class ApplicationInfo {

	private String id;

	/**
	* Gets the id.
	*
	* @return the String
	*/
	public String getid() {
		 return id; 
	}

	/**
	* Sets the id.
	*
	* @param value the String
	*/
	public void setid(String value) { 
		id = value; 
	}

	private String displayName;

	/**
	* Gets the display Name.
	*
	* @return the String
	*/
	public String getdisplayName() {
		 return displayName; 
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