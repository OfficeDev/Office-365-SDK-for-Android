/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type License Units Detail.
*/
public class LicenseUnitsDetail extends ODataBaseEntity {

	public LicenseUnitsDetail(){
		setODataType("#Microsoft.DirectoryServices.LicenseUnitsDetail");
	}


	private Integer enabled;

	/**
	* Gets the enabled.
	*
	* @return the Integer
	*/
	public Integer getenabled() {
		return this.enabled; 
	}

	/**
	* Sets the enabled.
	*
	* @param value the Integer
	*/
	public void setenabled(Integer value) { 
		this.enabled = value; 
	}

	private Integer suspended;

	/**
	* Gets the suspended.
	*
	* @return the Integer
	*/
	public Integer getsuspended() {
		return this.suspended; 
	}

	/**
	* Sets the suspended.
	*
	* @param value the Integer
	*/
	public void setsuspended(Integer value) { 
		this.suspended = value; 
	}

	private Integer warning;

	/**
	* Gets the warning.
	*
	* @return the Integer
	*/
	public Integer getwarning() {
		return this.warning; 
	}

	/**
	* Sets the warning.
	*
	* @param value the Integer
	*/
	public void setwarning(Integer value) { 
		this.warning = value; 
	}
}