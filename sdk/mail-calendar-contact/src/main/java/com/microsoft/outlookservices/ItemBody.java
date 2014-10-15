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

	private BodyType contentType;

	/**
	* Gets the Content Type.
	*
	* @return the BodyType
	*/
	public BodyType getContentType() {
		 return contentType; 
	}

	/**
	* Sets the Content Type.
	*
	* @param value the BodyType
	*/
	public void setContentType(BodyType value) { 
		contentType = value; 
	}

	private String content;

	/**
	* Gets the Content.
	*
	* @return the String
	*/
	public String getContent() {
		 return content; 
	}

	/**
	* Sets the Content.
	*
	* @param value the String
	*/
	public void setContent(String value) { 
		content = value; 
	}
}