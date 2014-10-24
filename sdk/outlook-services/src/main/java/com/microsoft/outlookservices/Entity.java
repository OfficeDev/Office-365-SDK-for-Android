/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type Entity.
*/
public class Entity {

	private String $$__ODataType = "#Microsoft.OutlookServices.Entity";

	private String Id;

	/**
	* Gets the Id.
	*
	* @return the String
	*/
	public String getId() {
		return this.Id; 
	}

	/**
	* Sets the Id.
	*
	* @param value the String
	*/
	public void setId(String value) { 
		this.Id = value; 
	}
}