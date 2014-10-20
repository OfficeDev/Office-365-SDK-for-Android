/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type Subscribed Sku.
*/
public class SubscribedSku {
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
	private Integer consumedUnits;

	/**
	* Gets the consumed Units.
	*
	* @return the Integer
	*/
	public Integer getconsumedUnits() {
		return this.consumedUnits; 
	}

	/**
	* Sets the consumed Units.
	*
	* @param value the Integer
	*/
	public void setconsumedUnits(Integer value) { 
		consumedUnits = value; 
	}
	private String objectId;

	/**
	* Gets the object Id.
	*
	* @return the String
	*/
	public String getobjectId() {
		return this.objectId; 
	}

	/**
	* Sets the object Id.
	*
	* @param value the String
	*/
	public void setobjectId(String value) { 
		objectId = value; 
	}
	private LicenseUnitsDetail prepaidUnits;

	/**
	* Gets the prepaid Units.
	*
	* @return the LicenseUnitsDetail
	*/
	public LicenseUnitsDetail getprepaidUnits() {
		return this.prepaidUnits; 
	}

	/**
	* Sets the prepaid Units.
	*
	* @param value the LicenseUnitsDetail
	*/
	public void setprepaidUnits(LicenseUnitsDetail value) { 
		prepaidUnits = value; 
	}
	private java.util.List<ServicePlanInfo> servicePlans;

	/**
	* Gets the service Plans.
	*
	* @return the java.util.List<ServicePlanInfo>
	*/
	public java.util.List<ServicePlanInfo> getservicePlans() {
		return this.servicePlans; 
	}

	/**
	* Sets the service Plans.
	*
	* @param value the java.util.List<ServicePlanInfo>
	*/
	public void setservicePlans(java.util.List<ServicePlanInfo> value) { 
		servicePlans = value; 
	}
	private java.util.UUID skuId;

	/**
	* Gets the sku Id.
	*
	* @return the java.util.UUID
	*/
	public java.util.UUID getskuId() {
		return this.skuId; 
	}

	/**
	* Sets the sku Id.
	*
	* @param value the java.util.UUID
	*/
	public void setskuId(java.util.UUID value) { 
		skuId = value; 
	}
	private String skuPartNumber;

	/**
	* Gets the sku Part Number.
	*
	* @return the String
	*/
	public String getskuPartNumber() {
		return this.skuPartNumber; 
	}

	/**
	* Sets the sku Part Number.
	*
	* @param value the String
	*/
	public void setskuPartNumber(String value) { 
		skuPartNumber = value; 
	}
}