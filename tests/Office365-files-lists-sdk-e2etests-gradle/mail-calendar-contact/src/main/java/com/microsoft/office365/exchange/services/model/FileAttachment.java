/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.exchange.services.model;

public class FileAttachment extends Attachment {
	private String contentId;

	public String getContentId() {
		 return contentId; 
	}

	public void setContentId(String value) { 
		contentId = value; 
	}
	private String contentLocation;

	public String getContentLocation() {
		 return contentLocation; 
	}

	public void setContentLocation(String value) { 
		contentLocation = value; 
	}
	private Boolean isContactPhoto;

	public Boolean getIsContactPhoto() {
		 return isContactPhoto; 
	}

	public void setIsContactPhoto(Boolean value) { 
		isContactPhoto = value; 
	}
	private Byte[] contentBytes;

	public Byte[] getContentBytes() {
		 return contentBytes; 
	}

	public void setContentBytes(Byte[] value) { 
		contentBytes = value; 
	}
}