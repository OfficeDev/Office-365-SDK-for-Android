/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.fileservices;

/**
 * The type Item Reference.
*/
public class ItemReference {

	private String driveId;

	/**
	* Gets the drive Id.
	*
	* @return the String
	*/
	public String getdriveId() {
		 return driveId; 
	}

	/**
	* Sets the drive Id.
	*
	* @param value the String
	*/
	public void setdriveId(String value) { 
		driveId = value; 
	}

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

	private String path;

	/**
	* Gets the path.
	*
	* @return the String
	*/
	public String getpath() {
		 return path; 
	}

	/**
	* Sets the path.
	*
	* @param value the String
	*/
	public void setpath(String value) { 
		path = value; 
	}
}