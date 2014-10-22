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
	private String ChangeKey;

	/**
	* Gets the Change Key.
	*
	* @return the String
	*/
	public String getChangeKey() {
		return this.ChangeKey; 
	}

	/**
	* Sets the Change Key.
	*
	* @param value the String
	*/
	public void setChangeKey(String value) { 
		ChangeKey = value; 
	}
	private java.util.List<String> Categories;

	/**
	* Gets the Categories.
	*
	* @return the java.util.List<String>
	*/
	public java.util.List<String> getCategories() {
		return this.Categories; 
	}

	/**
	* Sets the Categories.
	*
	* @param value the java.util.List<String>
	*/
	public void setCategories(java.util.List<String> value) { 
		Categories = value; 
	}
	private java.util.Calendar DateTimeCreated;

	/**
	* Gets the Date Time Created.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getDateTimeCreated() {
		return this.DateTimeCreated; 
	}

	/**
	* Sets the Date Time Created.
	*
	* @param value the java.util.Calendar
	*/
	public void setDateTimeCreated(java.util.Calendar value) { 
		DateTimeCreated = value; 
	}
	private java.util.Calendar DateTimeLastModified;

	/**
	* Gets the Date Time Last Modified.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getDateTimeLastModified() {
		return this.DateTimeLastModified; 
	}

	/**
	* Sets the Date Time Last Modified.
	*
	* @param value the java.util.Calendar
	*/
	public void setDateTimeLastModified(java.util.Calendar value) { 
		DateTimeLastModified = value; 
	}
}