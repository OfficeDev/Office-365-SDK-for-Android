
package com.microsoft.exchange.services.odata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.microsoft.exchange.services.odata.model.ResponseStatus;

import com.microsoft.exchange.services.odata.model.AttendeeType;


public class Attendee extends Recipient {


	@SerializedName("Status")
	@Expose
	private ResponseStatus status;

	public ResponseStatus getStatus() {
		 return status; 
	}

	public void setStatus(ResponseStatus value) { 
		status = value; 
	}


	@SerializedName("Type")
	@Expose
	private AttendeeType type;

	public AttendeeType getType() {
		 return type; 
	}

	public void setType(AttendeeType value) { 
		type = value; 
	}

}