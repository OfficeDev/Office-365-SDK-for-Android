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

	private String street;

	/**
	* Gets the Street.
	*
	* @return the String
	*/
	public String getStreet() {
		 return street; 
	}

	/**
	* Sets the Street.
	*
	* @param value the String
	*/
	public void setStreet(String value) { 
		street = value; 
	}

	private String city;

	/**
	* Gets the City.
	*
	* @return the String
	*/
	public String getCity() {
		 return city; 
	}

	/**
	* Sets the City.
	*
	* @param value the String
	*/
	public void setCity(String value) { 
		city = value; 
	}

	private String state;

	/**
	* Gets the State.
	*
	* @return the String
	*/
	public String getState() {
		 return state; 
	}

	/**
	* Sets the State.
	*
	* @param value the String
	*/
	public void setState(String value) { 
		state = value; 
	}

	private String countryOrRegion;

	/**
	* Gets the Country Or Region.
	*
	* @return the String
	*/
	public String getCountryOrRegion() {
		 return countryOrRegion; 
	}

	/**
	* Sets the Country Or Region.
	*
	* @param value the String
	*/
	public void setCountryOrRegion(String value) { 
		countryOrRegion = value; 
	}

	private String postalCode;

	/**
	* Gets the Postal Code.
	*
	* @return the String
	*/
	public String getPostalCode() {
		 return postalCode; 
	}

	/**
	* Sets the Postal Code.
	*
	* @param value the String
	*/
	public void setPostalCode(String value) { 
		postalCode = value; 
	}
}