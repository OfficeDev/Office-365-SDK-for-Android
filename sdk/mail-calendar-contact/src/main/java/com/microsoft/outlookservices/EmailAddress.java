/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type Email Address.
*/
public class EmailAddress {

	private String name;

	/**
	* Gets the Name.
	*
	* @return the String
	*/
	public String getName() {
		 return name; 
	}

	/**
	* Sets the Name.
	*
	* @param value the String
	*/
	public void setName(String value) { 
		name = value; 
	}

	private String address;

	/**
	* Gets the Address.
	*
	* @return the String
	*/
	public String getAddress() {
		 return address; 
	}

	/**
	* Sets the Address.
	*
	* @param value the String
	*/
	public void setAddress(String value) { 
		address = value; 
	}
}