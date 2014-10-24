/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type Service Plan Info.
*/
public class ServicePlanInfo extends ODataBaseEntity {

	public ServicePlanInfo(){
		setODataType("#Microsoft.DirectoryServices.ServicePlanInfo");
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

	private String servicePlanName;

	/**
	* Gets the service Plan Name.
	*
	* @return the String
	*/
	public String getservicePlanName() {
		return this.servicePlanName; 
	}

	/**
	* Sets the service Plan Name.
	*
	* @param value the String
	*/
	public void setservicePlanName(String value) { 
		this.servicePlanName = value; 
	}
}