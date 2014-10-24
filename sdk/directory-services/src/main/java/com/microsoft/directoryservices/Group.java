/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type Group.
*/
public class Group extends DirectoryObject {

	public Group(){
		setODataType("#Microsoft.DirectoryServices.Group");
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
		this.description = value; 
	}
	private Boolean dirSyncEnabled;

	/**
	* Gets the dir Sync Enabled.
	*
	* @return the Boolean
	*/
	public Boolean getdirSyncEnabled() {
		return this.dirSyncEnabled; 
	}

	/**
	* Sets the dir Sync Enabled.
	*
	* @param value the Boolean
	*/
	public void setdirSyncEnabled(Boolean value) { 
		this.dirSyncEnabled = value; 
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
		this.displayName = value; 
	}
	private java.util.Calendar lastDirSyncTime;

	/**
	* Gets the last Dir Sync Time.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getlastDirSyncTime() {
		return this.lastDirSyncTime; 
	}

	/**
	* Sets the last Dir Sync Time.
	*
	* @param value the java.util.Calendar
	*/
	public void setlastDirSyncTime(java.util.Calendar value) { 
		this.lastDirSyncTime = value; 
	}
	private String mail;

	/**
	* Gets the mail.
	*
	* @return the String
	*/
	public String getmail() {
		return this.mail; 
	}

	/**
	* Sets the mail.
	*
	* @param value the String
	*/
	public void setmail(String value) { 
		this.mail = value; 
	}
	private String mailNickname;

	/**
	* Gets the mail Nickname.
	*
	* @return the String
	*/
	public String getmailNickname() {
		return this.mailNickname; 
	}

	/**
	* Sets the mail Nickname.
	*
	* @param value the String
	*/
	public void setmailNickname(String value) { 
		this.mailNickname = value; 
	}
	private Boolean mailEnabled;

	/**
	* Gets the mail Enabled.
	*
	* @return the Boolean
	*/
	public Boolean getmailEnabled() {
		return this.mailEnabled; 
	}

	/**
	* Sets the mail Enabled.
	*
	* @param value the Boolean
	*/
	public void setmailEnabled(Boolean value) { 
		this.mailEnabled = value; 
	}
	private String onPremisesSecurityIdentifier;

	/**
	* Gets the on Premises Security Identifier.
	*
	* @return the String
	*/
	public String getonPremisesSecurityIdentifier() {
		return this.onPremisesSecurityIdentifier; 
	}

	/**
	* Sets the on Premises Security Identifier.
	*
	* @param value the String
	*/
	public void setonPremisesSecurityIdentifier(String value) { 
		this.onPremisesSecurityIdentifier = value; 
	}
	private java.util.List<ProvisioningError> provisioningErrors;

	/**
	* Gets the provisioning Errors.
	*
	* @return the java.util.List<ProvisioningError>
	*/
	public java.util.List<ProvisioningError> getprovisioningErrors() {
		return this.provisioningErrors; 
	}

	/**
	* Sets the provisioning Errors.
	*
	* @param value the java.util.List<ProvisioningError>
	*/
	public void setprovisioningErrors(java.util.List<ProvisioningError> value) { 
		this.provisioningErrors = value; 
	}
	private java.util.List<String> proxyAddresses;

	/**
	* Gets the proxy Addresses.
	*
	* @return the java.util.List<String>
	*/
	public java.util.List<String> getproxyAddresses() {
		return this.proxyAddresses; 
	}

	/**
	* Sets the proxy Addresses.
	*
	* @param value the java.util.List<String>
	*/
	public void setproxyAddresses(java.util.List<String> value) { 
		this.proxyAddresses = value; 
	}
	private Boolean securityEnabled;

	/**
	* Gets the security Enabled.
	*
	* @return the Boolean
	*/
	public Boolean getsecurityEnabled() {
		return this.securityEnabled; 
	}

	/**
	* Sets the security Enabled.
	*
	* @param value the Boolean
	*/
	public void setsecurityEnabled(Boolean value) { 
		this.securityEnabled = value; 
	}
}