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
		dirSyncEnabled = value; 
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
		lastDirSyncTime = value; 
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
		mail = value; 
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
		mailNickname = value; 
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
		mailEnabled = value; 
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
		onPremisesSecurityIdentifier = value; 
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
		provisioningErrors = value; 
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
		proxyAddresses = value; 
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
		securityEnabled = value; 
	}
}