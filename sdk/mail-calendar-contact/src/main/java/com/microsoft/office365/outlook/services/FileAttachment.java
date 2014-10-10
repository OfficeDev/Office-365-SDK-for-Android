/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

/**
 * The type FileAttachment.
*/
public class FileAttachment extends Attachment {
	private String contentId;


     /**
     * Gets the content id.		
     *
     * @return the content id
     */
	public String getContentId() {
		 return contentId; 
	}
	
     /**
     * Sets the content id.		
     *
     * @param value the value
     */
	public void setContentId(String value) { 
		contentId = value; 
	}
	private String contentLocation;


     /**
     * Gets the content location.		
     *
     * @return the content location
     */
	public String getContentLocation() {
		 return contentLocation; 
	}
	
     /**
     * Sets the content location.		
     *
     * @param value the value
     */
	public void setContentLocation(String value) { 
		contentLocation = value; 
	}
	private Boolean isContactPhoto;


     /**
     * Gets the is contact photo.		
     *
     * @return the is contact photo
     */
	public Boolean getIsContactPhoto() {
		 return isContactPhoto; 
	}
	
     /**
     * Sets the is contact photo.		
     *
     * @param value the value
     */
	public void setIsContactPhoto(Boolean value) { 
		isContactPhoto = value; 
	}
	private byte[] contentBytes;


     /**
     * Gets the content bytes.		
     *
     * @return the content bytes
     */
	public byte[] getContentBytes() {
		 return contentBytes; 
	}
	
     /**
     * Sets the content bytes.		
     *
     * @param value the value
     */
	public void setContentBytes(byte[] value) { 
		contentBytes = value; 
	}
}