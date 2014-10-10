/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

public class EmailAddress {

	private String name;

	public String getName() {
		 return name; 
	}

	public void setName(String value) { 
		name = value; 
	}

	private String address;

	public String getAddress() {
		 return address; 
	}

	public void setAddress(String value) { 
		address = value; 
	}
}