/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type Item Body.
*/
public class ItemBody {
	
	private String $$__ODataType = "#Microsoft.OutlookServices.ItemBody";


	private BodyType ContentType;

	/**
	* Gets the Content Type.
	*
	* @return the BodyType
	*/
	public BodyType getContentType() {
		return this.ContentType; 
	}

	/**
	* Sets the Content Type.
	*
	* @param value the BodyType
	*/
	public void setContentType(BodyType value) { 
		this.ContentType = value; 
	}

	private String Content;

	/**
	* Gets the Content.
	*
	* @return the String
	*/
	public String getContent() {
		return this.Content; 
	}

	/**
	* Sets the Content.
	*
	* @param value the String
	*/
	public void setContent(String value) { 
		this.Content = value; 
	}
}