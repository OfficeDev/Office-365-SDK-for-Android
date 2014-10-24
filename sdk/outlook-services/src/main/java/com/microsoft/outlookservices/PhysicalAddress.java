/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type Physical Address.
*/
public class PhysicalAddress {
	
	private String $$__ODataType = "#Microsoft.OutlookServices.PhysicalAddress";


	private String Street;

	/**
	* Gets the Street.
	*
	* @return the String
	*/
	public String getStreet() {
		return this.Street; 
	}

	/**
	* Sets the Street.
	*
	* @param value the String
	*/
	public void setStreet(String value) { 
		this.Street = value; 
	}

	private String City;

	/**
	* Gets the City.
	*
	* @return the String
	*/
	public String getCity() {
		return this.City; 
	}

	/**
	* Sets the City.
	*
	* @param value the String
	*/
	public void setCity(String value) { 
		this.City = value; 
	}

	private String State;

	/**
	* Gets the State.
	*
	* @return the String
	*/
	public String getState() {
		return this.State; 
	}

	/**
	* Sets the State.
	*
	* @param value the String
	*/
	public void setState(String value) { 
		this.State = value; 
	}

	private String CountryOrRegion;

	/**
	* Gets the Country Or Region.
	*
	* @return the String
	*/
	public String getCountryOrRegion() {
		return this.CountryOrRegion; 
	}

	/**
	* Sets the Country Or Region.
	*
	* @param value the String
	*/
	public void setCountryOrRegion(String value) { 
		this.CountryOrRegion = value; 
	}

	private String PostalCode;

	/**
	* Gets the Postal Code.
	*
	* @return the String
	*/
	public String getPostalCode() {
		return this.PostalCode; 
	}

	/**
	* Sets the Postal Code.
	*
	* @param value the String
	*/
	public void setPostalCode(String value) { 
		this.PostalCode = value; 
	}
}