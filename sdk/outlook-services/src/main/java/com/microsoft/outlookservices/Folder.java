/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type Folder.
*/
public class Folder extends Entity {

	private String $$__ODataType = "#Microsoft.OutlookServices.Folder";

	private String ParentFolderId;

	/**
	* Gets the Parent Folder Id.
	*
	* @return the String
	*/
	public String getParentFolderId() {
		return this.ParentFolderId; 
	}

	/**
	* Sets the Parent Folder Id.
	*
	* @param value the String
	*/
	public void setParentFolderId(String value) { 
		this.ParentFolderId = value; 
	}
	private String DisplayName;

	/**
	* Gets the Display Name.
	*
	* @return the String
	*/
	public String getDisplayName() {
		return this.DisplayName; 
	}

	/**
	* Sets the Display Name.
	*
	* @param value the String
	*/
	public void setDisplayName(String value) { 
		this.DisplayName = value; 
	}
	private Integer ChildFolderCount;

	/**
	* Gets the Child Folder Count.
	*
	* @return the Integer
	*/
	public Integer getChildFolderCount() {
		return this.ChildFolderCount; 
	}

	/**
	* Sets the Child Folder Count.
	*
	* @param value the Integer
	*/
	public void setChildFolderCount(Integer value) { 
		this.ChildFolderCount = value; 
	}
}