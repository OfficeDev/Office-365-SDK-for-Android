/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

/**
 * The type Attachment.
*/
public class Attachment extends Entity {
	private String name;


     /**
     * Gets the name.		
     *
     * @return the name
     */
	public String getName() {
		 return name; 
	}
	
     /**
     * Sets the name.		
     *
     * @param value the value
     */
	public void setName(String value) { 
		name = value; 
	}
	private String contentType;


     /**
     * Gets the content type.		
     *
     * @return the content type
     */
	public String getContentType() {
		 return contentType; 
	}
	
     /**
     * Sets the content type.		
     *
     * @param value the value
     */
	public void setContentType(String value) { 
		contentType = value; 
	}
	private Integer size;


     /**
     * Gets the size.		
     *
     * @return the size
     */
	public Integer getSize() {
		 return size; 
	}
	
     /**
     * Sets the size.		
     *
     * @param value the value
     */
	public void setSize(Integer value) { 
		size = value; 
	}
	private Boolean isInline;


     /**
     * Gets the is inline.		
     *
     * @return the is inline
     */
	public Boolean getIsInline() {
		 return isInline; 
	}
	
     /**
     * Sets the is inline.		
     *
     * @param value the value
     */
	public void setIsInline(Boolean value) { 
		isInline = value; 
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