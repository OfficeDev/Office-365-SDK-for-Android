/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type Provisioned Plan.
*/
public class ProvisionedPlan {

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
		capabilityStatus = value; 
	}

	private String provisioningStatus;

	/**
	* Gets the provisioning Status.
	*
	* @return the String
	*/
	public String getprovisioningStatus() {
		return this.provisioningStatus; 
	}

	/**
	* Sets the provisioning Status.
	*
	* @param value the String
	*/
	public void setprovisioningStatus(String value) { 
		provisioningStatus = value; 
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
		service = value; 
	}
}