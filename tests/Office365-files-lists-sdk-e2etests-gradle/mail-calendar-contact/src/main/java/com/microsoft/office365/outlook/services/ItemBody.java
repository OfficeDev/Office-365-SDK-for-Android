/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

/**
 * The type ItemBody.
*/
public class ItemBody {

	private BodyType contentType;

	 /**
     * Gets the content type.		
     *
     * @return the content type
     */
	public BodyType getContentType() {
		 return contentType; 
	}

	 /**
     * Sets the content type.		
     *
     * @param value the value
     */
	public void setContentType(BodyType value) { 
		contentType = value; 
	}

	private String content;

	 /**
     * Gets the content.		
     *
     * @return the content
     */
	public String getContent() {
		 return content; 
	}

	 /**
     * Sets the content.		
     *
     * @param value the value
     */
	public void setContent(String value) { 
		content = value; 
	}
}