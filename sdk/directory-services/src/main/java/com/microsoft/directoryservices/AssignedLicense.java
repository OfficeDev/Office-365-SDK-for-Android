/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type Assigned License.
*/
public class AssignedLicense {

	private java.util.List<java.util.UUID> disabledPlans;

	/**
	* Gets the disabled Plans.
	*
	* @return the java.util.List<java.util.UUID>
	*/
	public java.util.List<java.util.UUID> getdisabledPlans() {
		return this.disabledPlans; 
	}

	/**
	* Sets the disabled Plans.
	*
	* @param value the java.util.List<java.util.UUID>
	*/
	public void setdisabledPlans(java.util.List<java.util.UUID> value) { 
		disabledPlans = value; 
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
}