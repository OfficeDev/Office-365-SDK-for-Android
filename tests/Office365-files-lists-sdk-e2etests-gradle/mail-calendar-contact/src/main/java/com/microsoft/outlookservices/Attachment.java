/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type Attachment.
*/
public class Attachment extends Entity {
	private String name;

	/**
	* Gets the Name.
	*
	* @return the String
	*/
	public String getName() {
		 return name; 
	}

	/**
	* Sets the Name.
	*
	* @param value the String
	*/
	public void setName(String value) { 
		name = value; 
	}
	private String contentType;

	/**
	* Gets the Content Type.
	*
	* @return the String
	*/
	public String getContentType() {
		 return contentType; 
	}

	/**
	* Sets the Content Type.
	*
	* @param value the String
	*/
	public void setContentType(String value) { 
		contentType = value; 
	}
	private Integer size;

	/**
	* Gets the Size.
	*
	* @return the Integer
	*/
	public Integer getSize() {
		 return size; 
	}

	/**
	* Sets the Size.
	*
	* @param value the Integer
	*/
	public void setSize(Integer value) { 
		size = value; 
	}
	private Boolean isInline;

	/**
	* Gets the Is Inline.
	*
	* @return the Boolean
	*/
	public Boolean getIsInline() {
		 return isInline; 
	}

	/**
	* Sets the Is Inline.
	*
	* @param value the Boolean
	*/
	public void setIsInline(Boolean value) { 
		isInline = value; 
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