/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.exchange.services.model;

public class PhysicalAddress {

	private String street;

	public String getStreet() {
		 return street; 
	}

	public void setStreet(String value) { 
		street = value; 
	}

	private String city;

	public String getCity() {
		 return city; 
	}

	public void setCity(String value) { 
		city = value; 
	}

	private String state;

	public String getState() {
		 return state; 
	}

	public void setState(String value) { 
		state = value; 
	}

	private String countryOrRegion;

	public String getCountryOrRegion() {
		 return countryOrRegion; 
	}

	public void setCountryOrRegion(String value) { 
		countryOrRegion = value; 
	}

	private String postalCode;

	public String getPostalCode() {
		 return postalCode; 
	}

	public void setPostalCode(String value) { 
		postalCode = value; 
	}
}