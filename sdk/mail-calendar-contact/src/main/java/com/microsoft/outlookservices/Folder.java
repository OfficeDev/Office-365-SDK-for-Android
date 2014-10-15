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
	private String parentFolderId;

	/**
	* Gets the Parent Folder Id.
	*
	* @return the String
	*/
	public String getParentFolderId() {
		 return parentFolderId; 
	}

	/**
	* Sets the Parent Folder Id.
	*
	* @param value the String
	*/
	public void setParentFolderId(String value) { 
		parentFolderId = value; 
	}
	private String displayName;

	/**
	* Gets the Display Name.
	*
	* @return the String
	*/
	public String getDisplayName() {
		 return displayName; 
	}

	/**
	* Sets the Display Name.
	*
	* @param value the String
	*/
	public void setDisplayName(String value) { 
		displayName = value; 
	}
	private Integer childFolderCount;

	/**
	* Gets the Child Folder Count.
	*
	* @return the Integer
	*/
	public Integer getChildFolderCount() {
		 return childFolderCount; 
	}

	/**
	* Sets the Child Folder Count.
	*
	* @param value the Integer
	*/
	public void setChildFolderCount(Integer value) { 
		childFolderCount = value; 
	}
}