/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

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
	private boolean isContactPhoto;

	public boolean getIsContactPhoto() {
		 return isContactPhoto; 
	}

	public void setIsContactPhoto(boolean value) { 
		isContactPhoto = value; 
	}
	private byte[] contentBytes;

	public byte[] getContentBytes() {
		 return contentBytes; 
	}

	public void setContentBytes(byte[] value) { 
		contentBytes = value; 
	}
}