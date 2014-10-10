/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

public class Attachment extends Entity {
	private String name;

	public String getName() {
		 return name; 
	}

	public void setName(String value) { 
		name = value; 
	}
	private String contentType;

	public String getContentType() {
		 return contentType; 
	}

	public void setContentType(String value) { 
		contentType = value; 
	}
	private int size;

	public int getSize() {
		 return size; 
	}

	public void setSize(int value) { 
		size = value; 
	}
	private boolean isInline;

	public boolean getIsInline() {
		 return isInline; 
	}

	public void setIsInline(boolean value) { 
		isInline = value; 
	}
	private java.util.Calendar dateTimeLastModified;

	public java.util.Calendar getDateTimeLastModified() {
		 return dateTimeLastModified; 
	}

	public void setDateTimeLastModified(java.util.Calendar value) { 
		dateTimeLastModified = value; 
	}
}