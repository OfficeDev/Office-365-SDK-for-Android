
package com.microsoft.exchange.services.odata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.microsoft.exchange.services.odata.model.ResponseType;


public class ResponseStatus extends Recipient {


	@SerializedName("Response")
	@Expose
	private ResponseType response;

	public ResponseType getResponse() {
		 return response; 
	}

	public void setResponse(ResponseType value) { 
		response = value; 
	}


	@SerializedName("Time")
	@Expose
	private java.util.Date time;

	public java.util.Date getTime() {
		 return time; 
	}

	public void setTime(java.util.Date value) { 
		time = value; 
	}

}