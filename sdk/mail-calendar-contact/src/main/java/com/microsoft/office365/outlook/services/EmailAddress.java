/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

/**
 * The type EmailAddress.
*/
public class EmailAddress {

	private String name;

	 /**
     * Gets the name.		
     *
     * @return the name
     */
	public String getName() {
		 return name; 
	}

	 /**
     * Sets the name.		
     *
     * @param value the value
     */
	public void setName(String value) { 
		name = value; 
	}

	private String address;

	 /**
     * Gets the address.		
     *
     * @return the address
     */
	public String getAddress() {
		 return address; 
	}

	 /**
     * Sets the address.		
     *
     * @param value the value
     */
	public void setAddress(String value) { 
		address = value; 
	}
}