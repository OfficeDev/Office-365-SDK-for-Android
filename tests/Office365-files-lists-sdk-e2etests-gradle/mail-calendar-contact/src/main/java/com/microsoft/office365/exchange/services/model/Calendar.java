/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.exchange.services.model;

public class Calendar extends Entity {
	private String name;

	public String getName() {
		 return name; 
	}

	public void setName(String value) { 
		name = value; 
	}
	private String changeKey;

	public String getChangeKey() {
		 return changeKey; 
	}

	public void setChangeKey(String value) { 
		changeKey = value; 
	}
}