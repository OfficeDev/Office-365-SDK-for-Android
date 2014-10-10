/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

/**
 * The type ContactFolder.
*/
public class ContactFolder extends Entity {
	private String parentFolderId;


     /**
     * Gets the parent folder id.		
     *
     * @return the parent folder id
     */
	public String getParentFolderId() {
		 return parentFolderId; 
	}
	
     /**
     * Sets the parent folder id.		
     *
     * @param value the value
     */
	public void setParentFolderId(String value) { 
		parentFolderId = value; 
	}
	private String displayName;


     /**
     * Gets the display name.		
     *
     * @return the display name
     */
	public String getDisplayName() {
		 return displayName; 
	}
	
     /**
     * Sets the display name.		
     *
     * @param value the value
     */
	public void setDisplayName(String value) { 
		displayName = value; 
	}
}