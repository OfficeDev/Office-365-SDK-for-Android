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

	private String $$__ODataType = "#Microsoft.OutlookServices.Calendar";

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
		this.Name = value; 
	}
	private String ChangeKey;

	/**
	* Gets the Change Key.
	*
	* @return the String
	*/
	public String getChangeKey() {
		return this.ChangeKey; 
	}

	/**
	* Sets the Change Key.
	*
	* @param value the String
	*/
	public void setChangeKey(String value) { 
		this.ChangeKey = value; 
	}
}