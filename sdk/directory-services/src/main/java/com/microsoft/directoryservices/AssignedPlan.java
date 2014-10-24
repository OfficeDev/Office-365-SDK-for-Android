/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type Assigned Plan.
*/
public class AssignedPlan extends ODataBaseEntity {

	public AssignedPlan(){
		setODataType("#Microsoft.DirectoryServices.AssignedPlan");
	}


	private java.util.Calendar assignedTimestamp;

	/**
	* Gets the assigned Timestamp.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getassignedTimestamp() {
		return this.assignedTimestamp; 
	}

	/**
	* Sets the assigned Timestamp.
	*
	* @param value the java.util.Calendar
	*/
	public void setassignedTimestamp(java.util.Calendar value) { 
		this.assignedTimestamp = value; 
	}

	private String capabilityStatus;

	/**
	* Gets the capability Status.
	*
	* @return the String
	*/
	public String getcapabilityStatus() {
		return this.capabilityStatus; 
	}

	/**
	* Sets the capability Status.
	*
	* @param value the String
	*/
	public void setcapabilityStatus(String value) { 
		this.capabilityStatus = value; 
	}

	private String service;

	/**
	* Gets the service.
	*
	* @return the String
	*/
	public String getservice() {
		return this.service; 
	}

	/**
	* Sets the service.
	*
	* @param value the String
	*/
	public void setservice(String value) { 
		this.service = value; 
	}

	private java.util.UUID servicePlanId;

	/**
	* Gets the service Plan Id.
	*
	* @return the java.util.UUID
	*/
	public java.util.UUID getservicePlanId() {
		return this.servicePlanId; 
	}

	/**
	* Sets the service Plan Id.
	*
	* @param value the java.util.UUID
	*/
	public void setservicePlanId(java.util.UUID value) { 
		this.servicePlanId = value; 
	}
}