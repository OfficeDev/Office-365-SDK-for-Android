
package com.microsoft.exchange.services.odata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Attachment extends Entity {

	@SerializedName("Name")
	@Expose
	private String name;

	public String getName() {
		 return name; 
	}

	public void setName(String value) { 
		name = value; 
	}

	@SerializedName("ContentType")
	@Expose
	private String contentType;

	public String getContentType() {
		 return contentType; 
	}

	public void setContentType(String value) { 
		contentType = value; 
	}

	@SerializedName("Size")
	@Expose
	private Integer size;

	public Integer getSize() {
		 return size; 
	}

	public void setSize(Integer value) { 
		size = value; 
	}

	@SerializedName("IsInline")
	@Expose
	private Boolean isInline;

	public Boolean getIsInline() {
		 return isInline; 
	}

	public void setIsInline(Boolean value) { 
		isInline = value; 
	}

	@SerializedName("LastModifiedTime")
	@Expose
	private java.util.Date lastModifiedTime;

	public java.util.Date getLastModifiedTime() {
		 return lastModifiedTime; 
	}

	public void setLastModifiedTime(java.util.Date value) { 
		lastModifiedTime = value; 
	}

}