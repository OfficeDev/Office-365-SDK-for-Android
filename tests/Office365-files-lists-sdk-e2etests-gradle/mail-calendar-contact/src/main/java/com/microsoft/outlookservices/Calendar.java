/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type Calendar.
*/
public class Calendar extends Entity {
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
	private String changeKey;

	/**
	* Gets the Change Key.
	*
	* @return the String
	*/
	public String getChangeKey() {
		 return changeKey; 
	}

	/**
	* Sets the Change Key.
	*
	* @param value the String
	*/
	public void setChangeKey(String value) { 
		changeKey = value; 
	}
}