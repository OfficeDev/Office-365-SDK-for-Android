/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.file.services.model;

public class Folder extends Item {
	private Integer childCount;

	public Integer getchildCount() {
		 return childCount; 
	}

	public void setchildCount(Integer value) { 
		childCount = value; 
	}
}