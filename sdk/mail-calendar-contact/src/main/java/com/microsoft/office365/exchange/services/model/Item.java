/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.exchange.services.model;

public class Item extends Entity {
	private String changeKey;

	public String getChangeKey() {
		 return changeKey; 
	}

	public void setChangeKey(String value) { 
		changeKey = value; 
	}
	private java.util.List<String> categories;

	public java.util.List<String> getCategories() {
		 return categories; 
	}

	public void setCategories(java.util.List<String> value) { 
		categories = value; 
	}
	private java.util.Calendar dateTimeCreated;

	public java.util.Calendar getDateTimeCreated() {
		 return dateTimeCreated; 
	}

	public void setDateTimeCreated(java.util.Calendar value) { 
		dateTimeCreated = value; 
	}
	private java.util.Calendar dateTimeLastModified;

	public java.util.Calendar getDateTimeLastModified() {
		 return dateTimeLastModified; 
	}

	public void setDateTimeLastModified(java.util.Calendar value) { 
		dateTimeLastModified = value; 
	}
}