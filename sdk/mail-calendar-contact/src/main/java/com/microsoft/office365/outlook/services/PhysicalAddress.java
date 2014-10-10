/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

/**
 * The type PhysicalAddress.
*/
public class PhysicalAddress {

	private String street;

	 /**
     * Gets the street.		
     *
     * @return the street
     */
	public String getStreet() {
		 return street; 
	}

	 /**
     * Sets the street.		
     *
     * @param value the value
     */
	public void setStreet(String value) { 
		street = value; 
	}

	private String city;

	 /**
     * Gets the city.		
     *
     * @return the city
     */
	public String getCity() {
		 return city; 
	}

	 /**
     * Sets the city.		
     *
     * @param value the value
     */
	public void setCity(String value) { 
		city = value; 
	}

	private String state;

	 /**
     * Gets the state.		
     *
     * @return the state
     */
	public String getState() {
		 return state; 
	}

	 /**
     * Sets the state.		
     *
     * @param value the value
     */
	public void setState(String value) { 
		state = value; 
	}

	private String countryOrRegion;

	 /**
     * Gets the country or region.		
     *
     * @return the country or region
     */
	public String getCountryOrRegion() {
		 return countryOrRegion; 
	}

	 /**
     * Sets the country or region.		
     *
     * @param value the value
     */
	public void setCountryOrRegion(String value) { 
		countryOrRegion = value; 
	}

	private String postalCode;

	 /**
     * Gets the postal code.		
     *
     * @return the postal code
     */
	public String getPostalCode() {
		 return postalCode; 
	}

	 /**
     * Sets the postal code.		
     *
     * @param value the value
     */
	public void setPostalCode(String value) { 
		postalCode = value; 
	}
}