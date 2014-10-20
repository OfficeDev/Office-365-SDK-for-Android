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

	private String Name;

	/**
	* Gets the Name.
	*
	* @return the String
	*/
	public String getName() {
		return this.Name; 
	}

	/**
	* Sets the Name.
	*
	* @param value the String
	*/
	public void setName(String value) { 
		Name = value; 
	}

	private String Address;

	/**
	* Gets the Address.
	*
	* @return the String
	*/
	public String getAddress() {
		return this.Address; 
	}

	/**
	* Sets the Address.
	*
	* @param value the String
	*/
	public void setAddress(String value) { 
		Address = value; 
	}
}