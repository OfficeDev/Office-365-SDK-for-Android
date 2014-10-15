/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.files.services;

/**
 * The type ItemReference.
*/
public class ItemReference {

	private String driveId;

	 /**
     * Gets the drive id.		
     *
     * @return the drive id
     */
	public String getdriveId() {
		 return driveId; 
	}

	 /**
     * Sets the drive id.		
     *
     * @param value the value
     */
	public void setdriveId(String value) { 
		driveId = value; 
	}

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

	private String path;

	 /**
     * Gets the path.		
     *
     * @return the path
     */
	public String getpath() {
		 return path; 
	}

	 /**
     * Sets the path.		
     *
     * @param value the value
     */
	public void setpath(String value) { 
		path = value; 
	}
}