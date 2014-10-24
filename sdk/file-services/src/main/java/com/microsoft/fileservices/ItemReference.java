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
	
	private String $$_ODataType = "#Microsoft.FileServices.ItemReference";


	private String driveId;

	/**
	* Gets the drive Id.
	*
	* @return the String
	*/
	public String getdriveId() {
		return this.driveId; 
	}

	/**
	* Sets the drive Id.
	*
	* @param value the String
	*/
	public void setdriveId(String value) { 
		this.driveId = value; 
	}

	private String id;

	/**
	* Gets the id.
	*
	* @return the String
	*/
	public String getid() {
		return this.id; 
	}

	/**
	* Sets the id.
	*
	* @param value the String
	*/
	public void setid(String value) { 
		this.id = value; 
	}

	private String path;

	/**
	* Gets the path.
	*
	* @return the String
	*/
	public String getpath() {
		return this.path; 
	}

	/**
	* Sets the path.
	*
	* @param value the String
	*/
	public void setpath(String value) { 
		this.path = value; 
	}
}