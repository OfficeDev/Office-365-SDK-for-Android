/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type Directory Role.
*/
public class DirectoryRole extends DirectoryObject {
	private String description;

	/**
	* Gets the description.
	*
	* @return the String
	*/
	public String getdescription() {
		return this.description; 
	}

	/**
	* Sets the description.
	*
	* @param value the String
	*/
	public void setdescription(String value) { 
		description = value; 
	}
	private String displayName;

	/**
	* Gets the display Name.
	*
	* @return the String
	*/
	public String getdisplayName() {
		return this.displayName; 
	}

	/**
	* Sets the display Name.
	*
	* @param value the String
	*/
	public void setdisplayName(String value) { 
		displayName = value; 
	}
	private Boolean isSystem;

	/**
	* Gets the is System.
	*
	* @return the Boolean
	*/
	public Boolean getisSystem() {
		return this.isSystem; 
	}

	/**
	* Sets the is System.
	*
	* @param value the Boolean
	*/
	public void setisSystem(Boolean value) { 
		isSystem = value; 
	}
	private Boolean roleDisabled;

	/**
	* Gets the role Disabled.
	*
	* @return the Boolean
	*/
	public Boolean getroleDisabled() {
		return this.roleDisabled; 
	}

	/**
	* Sets the role Disabled.
	*
	* @param value the Boolean
	*/
	public void setroleDisabled(Boolean value) { 
		roleDisabled = value; 
	}
	private String roleTemplateId;

	/**
	* Gets the role Template Id.
	*
	* @return the String
	*/
	public String getroleTemplateId() {
		return this.roleTemplateId; 
	}

	/**
	* Sets the role Template Id.
	*
	* @param value the String
	*/
	public void setroleTemplateId(String value) { 
		roleTemplateId = value; 
	}
}