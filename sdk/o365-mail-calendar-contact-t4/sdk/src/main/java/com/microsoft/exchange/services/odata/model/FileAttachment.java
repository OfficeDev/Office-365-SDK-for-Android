
package com.microsoft.exchange.services.odata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class FileAttachment extends Attachment {

	@SerializedName("ContentId")
	@Expose
	private String contentId;

	public String getContentId() {
		 return contentId; 
	}

	public void setContentId(String value) { 
		contentId = value; 
	}

	@SerializedName("ContentLocation")
	@Expose
	private String contentLocation;

	public String getContentLocation() {
		 return contentLocation; 
	}

	public void setContentLocation(String value) { 
		contentLocation = value; 
	}

	@SerializedName("IsContactPhoto")
	@Expose
	private Boolean isContactPhoto;

	public Boolean getIsContactPhoto() {
		 return isContactPhoto; 
	}

	public void setIsContactPhoto(Boolean value) { 
		isContactPhoto = value; 
	}

	@SerializedName("ContentBytes")
	@Expose
	private Byte[] contentBytes;

	public Byte[] getContentBytes() {
		 return contentBytes; 
	}

	public void setContentBytes(Byte[] value) { 
		contentBytes = value; 
	}

}