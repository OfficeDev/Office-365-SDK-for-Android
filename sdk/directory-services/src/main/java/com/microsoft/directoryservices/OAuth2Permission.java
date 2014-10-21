/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type OAuth2Permission.
*/
public class OAuth2Permission {

	private String adminConsentDescription;

	/**
	* Gets the admin Consent Description.
	*
	* @return the String
	*/
	public String getadminConsentDescription() {
		return this.adminConsentDescription; 
	}

	/**
	* Sets the admin Consent Description.
	*
	* @param value the String
	*/
	public void setadminConsentDescription(String value) { 
		adminConsentDescription = value; 
	}

	private String adminConsentDisplayName;

	/**
	* Gets the admin Consent Display Name.
	*
	* @return the String
	*/
	public String getadminConsentDisplayName() {
		return this.adminConsentDisplayName; 
	}

	/**
	* Sets the admin Consent Display Name.
	*
	* @param value the String
	*/
	public void setadminConsentDisplayName(String value) { 
		adminConsentDisplayName = value; 
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

	private String type;

	/**
	* Gets the type.
	*
	* @return the String
	*/
	public String gettype() {
		return this.type; 
	}

	/**
	* Sets the type.
	*
	* @param value the String
	*/
	public void settype(String value) { 
		type = value; 
	}

	private String userConsentDescription;

	/**
	* Gets the user Consent Description.
	*
	* @return the String
	*/
	public String getuserConsentDescription() {
		return this.userConsentDescription; 
	}

	/**
	* Sets the user Consent Description.
	*
	* @param value the String
	*/
	public void setuserConsentDescription(String value) { 
		userConsentDescription = value; 
	}

	private String userConsentDisplayName;

	/**
	* Gets the user Consent Display Name.
	*
	* @return the String
	*/
	public String getuserConsentDisplayName() {
		return this.userConsentDisplayName; 
	}

	/**
	* Sets the user Consent Display Name.
	*
	* @param value the String
	*/
	public void setuserConsentDisplayName(String value) { 
		userConsentDisplayName = value; 
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