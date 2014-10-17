/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type Item.
*/
public class Item extends Entity {
	private String changeKey;

	/**
	* Gets the Change Key.
	*
	* @return the String
	*/
	public String getChangeKey() {
		 return changeKey; 
	}

	/**
	* Sets the Change Key.
	*
	* @param value the String
	*/
	public void setChangeKey(String value) { 
		changeKey = value; 
	}
	private java.util.List<String> categories;

	/**
	* Gets the Categories.
	*
	* @return the java.util.List<String>
	*/
	public java.util.List<String> getCategories() {
		 return categories; 
	}

	/**
	* Sets the Categories.
	*
	* @param value the java.util.List<String>
	*/
	public void setCategories(java.util.List<String> value) { 
		categories = value; 
	}
	private java.util.Calendar dateTimeCreated;

	/**
	* Gets the Date Time Created.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getDateTimeCreated() {
		 return dateTimeCreated; 
	}

	/**
	* Sets the Date Time Created.
	*
	* @param value the java.util.Calendar
	*/
	public void setDateTimeCreated(java.util.Calendar value) { 
		dateTimeCreated = value; 
	}
	private java.util.Calendar dateTimeLastModified;

	/**
	* Gets the Date Time Last Modified.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getDateTimeLastModified() {
		 return dateTimeLastModified; 
	}

	/**
	* Sets the Date Time Last Modified.
	*
	* @param value the java.util.Calendar
	*/
	public void setDateTimeLastModified(java.util.Calendar value) { 
		dateTimeLastModified = value; 
	}
}