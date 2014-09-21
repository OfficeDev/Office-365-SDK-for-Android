
package com.microsoft.exchange.services.odata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.microsoft.exchange.services.odata.model.BodyType;


public class ItemBody {


	@SerializedName("ContentType")
	@Expose
	private BodyType contentType;

	public BodyType getContentType() {
		 return contentType; 
	}

	public void setContentType(BodyType value) { 
		contentType = value; 
	}


	@SerializedName("Content")
	@Expose
	private String content;

	public String getContent() {
		 return content; 
	}

	public void setContent(String value) { 
		content = value; 
	}

}