/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

/**
 * The type Item.
*/
public class Item extends Entity {
	private String changeKey;


     /**
     * Gets the change key.		
     *
     * @return the change key
     */
	public String getChangeKey() {
		 return changeKey; 
	}
	
     /**
     * Sets the change key.		
     *
     * @param value the value
     */
	public void setChangeKey(String value) { 
		changeKey = value; 
	}
	private java.util.List<String> categories;


     /**
     * Gets the categories.		
     *
     * @return the categories
     */
	public java.util.List<String> getCategories() {
		 return categories; 
	}
	
     /**
     * Sets the categories.		
     *
     * @param value the value
     */
	public void setCategories(java.util.List<String> value) { 
		categories = value; 
	}
	private java.util.Calendar dateTimeCreated;


     /**
     * Gets the date time created.		
     *
     * @return the date time created
     */
	public java.util.Calendar getDateTimeCreated() {
		 return dateTimeCreated; 
	}
	
     /**
     * Sets the date time created.		
     *
     * @param value the value
     */
	public void setDateTimeCreated(java.util.Calendar value) { 
		dateTimeCreated = value; 
	}
	private java.util.Calendar dateTimeLastModified;


     /**
     * Gets the date time last modified.		
     *
     * @return the date time last modified
     */
	public java.util.Calendar getDateTimeLastModified() {
		 return dateTimeLastModified; 
	}
	
     /**
     * Sets the date time last modified.		
     *
     * @param value the value
     */
	public void setDateTimeLastModified(java.util.Calendar value) { 
		dateTimeLastModified = value; 
	}
}