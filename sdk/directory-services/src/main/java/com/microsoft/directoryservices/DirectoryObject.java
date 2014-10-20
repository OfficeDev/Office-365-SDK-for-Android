/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type Directory Object.
*/
public class DirectoryObject {
	private String objectType;

	/**
	* Gets the object Type.
	*
	* @return the String
	*/
	public String getobjectType() {
		return this.objectType; 
	}

	/**
	* Sets the object Type.
	*
	* @param value the String
	*/
	public void setobjectType(String value) { 
		objectType = value; 
	}
	private String objectId;

	/**
	* Gets the object Id.
	*
	* @return the String
	*/
	public String getobjectId() {
		return this.objectId; 
	}

	/**
	* Sets the object Id.
	*
	* @param value the String
	*/
	public void setobjectId(String value) { 
		objectId = value; 
	}
	private java.util.Calendar softDeletionTimestamp;

	/**
	* Gets the soft Deletion Timestamp.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getsoftDeletionTimestamp() {
		return this.softDeletionTimestamp; 
	}

	/**
	* Sets the soft Deletion Timestamp.
	*
	* @param value the java.util.Calendar
	*/
	public void setsoftDeletionTimestamp(java.util.Calendar value) { 
		softDeletionTimestamp = value; 
	}
}