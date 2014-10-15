/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.fileservices;

/**
 * The type File.
*/
public class File extends Item {
	private String contentUrl;

	/**
	* Gets the content Url.
	*
	* @return the String
	*/
	public String getcontentUrl() {
		 return contentUrl; 
	}

	/**
	* Sets the content Url.
	*
	* @param value the String
	*/
	public void setcontentUrl(String value) { 
		contentUrl = value; 
	}
}