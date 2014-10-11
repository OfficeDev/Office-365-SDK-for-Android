/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.files.services;

/**
 * The type ApplicationInfo.
*/
public class ApplicationInfo {

	private String id;

	 /**
     * Gets the id.		
     *
     * @return the id
     */
	public String getid() {
		 return id; 
	}

	 /**
     * Sets the id.		
     *
     * @param value the value
     */
	public void setid(String value) { 
		id = value; 
	}

	private String displayName;

	 /**
     * Gets the display name.		
     *
     * @return the display name
     */
	public String getdisplayName() {
		 return displayName; 
	}

	 /**
     * Sets the display name.		
     *
     * @param value the value
     */
	public void setdisplayName(String value) { 
		displayName = value; 
	}
}