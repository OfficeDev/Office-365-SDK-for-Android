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

	private String $$_ODataType = "#Microsoft.OutlookServices.Attachment";

	private String Name;

	/**
	* Gets the Name.
	*
	* @return the String
	*/
	public String getName() {
		return this.Name; 
	}

	/**
	* Sets the Name.
	*
	* @param value the String
	*/
	public void setName(String value) { 
		this.Name = value; 
	}
	private String ContentType;

	/**
	* Gets the Content Type.
	*
	* @return the String
	*/
	public String getContentType() {
		return this.ContentType; 
	}

	/**
	* Sets the Content Type.
	*
	* @param value the String
	*/
	public void setContentType(String value) { 
		this.ContentType = value; 
	}
	private Integer Size;

	/**
	* Gets the Size.
	*
	* @return the Integer
	*/
	public Integer getSize() {
		return this.Size; 
	}

	/**
	* Sets the Size.
	*
	* @param value the Integer
	*/
	public void setSize(Integer value) { 
		this.Size = value; 
	}
	private Boolean IsInline;

	/**
	* Gets the Is Inline.
	*
	* @return the Boolean
	*/
	public Boolean getIsInline() {
		return this.IsInline; 
	}

	/**
	* Sets the Is Inline.
	*
	* @param value the Boolean
	*/
	public void setIsInline(Boolean value) { 
		this.IsInline = value; 
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
		this.DateTimeLastModified = value; 
	}
}