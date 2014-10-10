/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

public class ItemBody {

	private BodyType contentType;

	public BodyType getContentType() {
		 return contentType; 
	}

	public void setContentType(BodyType value) { 
		contentType = value; 
	}

	private String content;

	public String getContent() {
		 return content; 
	}

	public void setContent(String value) { 
		content = value; 
	}
}