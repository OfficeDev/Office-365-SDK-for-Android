/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.file.services.model;

public class File extends Item {
	private String contentUrl;

	public String getcontentUrl() {
		 return contentUrl; 
	}

	public void setcontentUrl(String value) { 
		contentUrl = value; 
	}
}