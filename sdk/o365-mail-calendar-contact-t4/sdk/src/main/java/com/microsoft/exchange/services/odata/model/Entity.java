
package com.microsoft.exchange.services.odata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Entity {

	@SerializedName("Id")
	@Expose
	private String id;

	public String getId() {
		 return id; 
	}

	public void setId(String value) { 
		id = value; 
	}

}