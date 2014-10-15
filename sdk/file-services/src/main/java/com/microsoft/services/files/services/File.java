/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.files.services;

/**
 * The type File.
*/
public class File extends Item {
	private String contentUrl;


     /**
     * Gets the content url.		
     *
     * @return the content url
     */
	public String getcontentUrl() {
		 return contentUrl; 
	}
	
     /**
     * Sets the content url.		
     *
     * @param value the value
     */
	public void setcontentUrl(String value) { 
		contentUrl = value; 
	}
}