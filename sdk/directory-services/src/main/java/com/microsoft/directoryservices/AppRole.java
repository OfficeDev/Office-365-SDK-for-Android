/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type App Role.
*/
public class AppRole {

	private java.util.List<String> allowedMemberTypes;

	/**
	* Gets the allowed Member Types.
	*
	* @return the java.util.List<String>
	*/
	public java.util.List<String> getallowedMemberTypes() {
		return this.allowedMemberTypes; 
	}

	/**
	* Sets the allowed Member Types.
	*
	* @param value the java.util.List<String>
	*/
	public void setallowedMemberTypes(java.util.List<String> value) { 
		allowedMemberTypes = value; 
	}

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

	private java.util.UUID id;

	/**
	* Gets the id.
	*
	* @return the java.util.UUID
	*/
	public java.util.UUID getid() {
		return this.id; 
	}

	/**
	* Sets the id.
	*
	* @param value the java.util.UUID
	*/
	public void setid(java.util.UUID value) { 
		id = value; 
	}

	private Boolean isEnabled;

	/**
	* Gets the is Enabled.
	*
	* @return the Boolean
	*/
	public Boolean getisEnabled() {
		return this.isEnabled; 
	}

	/**
	* Sets the is Enabled.
	*
	* @param value the Boolean
	*/
	public void setisEnabled(Boolean value) { 
		isEnabled = value; 
	}

	private String value;

	/**
	* Gets the value.
	*
	* @return the String
	*/
	public String getvalue() {
		return this.value; 
	}

	/**
	* Sets the value.
	*
	* @param value the String
	*/
	public void setvalue(String value) { 
		value = value; 
	}
}