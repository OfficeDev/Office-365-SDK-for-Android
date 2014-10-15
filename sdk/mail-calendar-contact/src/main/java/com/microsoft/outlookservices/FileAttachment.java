/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type File Attachment.
*/
public class FileAttachment extends Attachment {
	private String contentId;

	/**
	* Gets the Content Id.
	*
	* @return the String
	*/
	public String getContentId() {
		 return contentId; 
	}

	/**
	* Sets the Content Id.
	*
	* @param value the String
	*/
	public void setContentId(String value) { 
		contentId = value; 
	}
	private String contentLocation;

	/**
	* Gets the Content Location.
	*
	* @return the String
	*/
	public String getContentLocation() {
		 return contentLocation; 
	}

	/**
	* Sets the Content Location.
	*
	* @param value the String
	*/
	public void setContentLocation(String value) { 
		contentLocation = value; 
	}
	private Boolean isContactPhoto;

	/**
	* Gets the Is Contact Photo.
	*
	* @return the Boolean
	*/
	public Boolean getIsContactPhoto() {
		 return isContactPhoto; 
	}

	/**
	* Sets the Is Contact Photo.
	*
	* @param value the Boolean
	*/
	public void setIsContactPhoto(Boolean value) { 
		isContactPhoto = value; 
	}
	private byte[] contentBytes;

	/**
	* Gets the Content Bytes.
	*
	* @return the byte[]
	*/
	public byte[] getContentBytes() {
		 return contentBytes; 
	}

	/**
	* Sets the Content Bytes.
	*
	* @param value the byte[]
	*/
	public void setContentBytes(byte[] value) { 
		contentBytes = value; 
	}
}