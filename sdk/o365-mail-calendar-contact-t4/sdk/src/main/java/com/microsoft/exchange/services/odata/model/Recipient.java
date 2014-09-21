
package com.microsoft.exchange.services.odata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Recipient {


	@SerializedName("Name")
	@Expose
	private String name;

	public String getName() {
		 return name; 
	}

	public void setName(String value) { 
		name = value; 
	}


	@SerializedName("Address")
	@Expose
	private String address;

	public String getAddress() {
		 return address; 
	}

	public void setAddress(String value) { 
		address = value; 
	}

}