/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.files.services;

/**
 * The type Folder.
*/
public class Folder extends Item {
	private Integer childCount;


     /**
     * Gets the child count.		
     *
     * @return the child count
     */
	public Integer getchildCount() {
		 return childCount; 
	}
	
     /**
     * Sets the child count.		
     *
     * @param value the value
     */
	public void setchildCount(Integer value) { 
		childCount = value; 
	}
}