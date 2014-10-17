/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type Contact Folder.
*/
public class ContactFolder extends Entity {
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
}