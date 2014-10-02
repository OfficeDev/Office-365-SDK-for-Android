/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.exchange.services;

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
	private Integer size;

	public Integer getSize() {
		 return size; 
	}

	public void setSize(Integer value) { 
		size = value; 
	}
	private Boolean isInline;

	public Boolean getIsInline() {
		 return isInline; 
	}

	public void setIsInline(Boolean value) { 
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