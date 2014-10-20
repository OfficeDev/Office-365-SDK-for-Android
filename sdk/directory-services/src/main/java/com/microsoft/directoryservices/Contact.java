/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type Contact.
*/
public class Contact extends DirectoryObject {
	private String city;

	/**
	* Gets the city.
	*
	* @return the String
	*/
	public String getcity() {
		return this.city; 
	}

	/**
	* Sets the city.
	*
	* @param value the String
	*/
	public void setcity(String value) { 
		city = value; 
	}
	private String country;

	/**
	* Gets the country.
	*
	* @return the String
	*/
	public String getcountry() {
		return this.country; 
	}

	/**
	* Sets the country.
	*
	* @param value the String
	*/
	public void setcountry(String value) { 
		country = value; 
	}
	private String department;

	/**
	* Gets the department.
	*
	* @return the String
	*/
	public String getdepartment() {
		return this.department; 
	}

	/**
	* Sets the department.
	*
	* @param value the String
	*/
	public void setdepartment(String value) { 
		department = value; 
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
	private String facsimileTelephoneNumber;

	/**
	* Gets the facsimile Telephone Number.
	*
	* @return the String
	*/
	public String getfacsimileTelephoneNumber() {
		return this.facsimileTelephoneNumber; 
	}

	/**
	* Sets the facsimile Telephone Number.
	*
	* @param value the String
	*/
	public void setfacsimileTelephoneNumber(String value) { 
		facsimileTelephoneNumber = value; 
	}
	private String givenName;

	/**
	* Gets the given Name.
	*
	* @return the String
	*/
	public String getgivenName() {
		return this.givenName; 
	}

	/**
	* Sets the given Name.
	*
	* @param value the String
	*/
	public void setgivenName(String value) { 
		givenName = value; 
	}
	private String jobTitle;

	/**
	* Gets the job Title.
	*
	* @return the String
	*/
	public String getjobTitle() {
		return this.jobTitle; 
	}

	/**
	* Sets the job Title.
	*
	* @param value the String
	*/
	public void setjobTitle(String value) { 
		jobTitle = value; 
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
	private String mobile;

	/**
	* Gets the mobile.
	*
	* @return the String
	*/
	public String getmobile() {
		return this.mobile; 
	}

	/**
	* Sets the mobile.
	*
	* @param value the String
	*/
	public void setmobile(String value) { 
		mobile = value; 
	}
	private String physicalDeliveryOfficeName;

	/**
	* Gets the physical Delivery Office Name.
	*
	* @return the String
	*/
	public String getphysicalDeliveryOfficeName() {
		return this.physicalDeliveryOfficeName; 
	}

	/**
	* Sets the physical Delivery Office Name.
	*
	* @param value the String
	*/
	public void setphysicalDeliveryOfficeName(String value) { 
		physicalDeliveryOfficeName = value; 
	}
	private String postalCode;

	/**
	* Gets the postal Code.
	*
	* @return the String
	*/
	public String getpostalCode() {
		return this.postalCode; 
	}

	/**
	* Sets the postal Code.
	*
	* @param value the String
	*/
	public void setpostalCode(String value) { 
		postalCode = value; 
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
	private String sipProxyAddress;

	/**
	* Gets the sip Proxy Address.
	*
	* @return the String
	*/
	public String getsipProxyAddress() {
		return this.sipProxyAddress; 
	}

	/**
	* Sets the sip Proxy Address.
	*
	* @param value the String
	*/
	public void setsipProxyAddress(String value) { 
		sipProxyAddress = value; 
	}
	private String state;

	/**
	* Gets the state.
	*
	* @return the String
	*/
	public String getstate() {
		return this.state; 
	}

	/**
	* Sets the state.
	*
	* @param value the String
	*/
	public void setstate(String value) { 
		state = value; 
	}
	private String streetAddress;

	/**
	* Gets the street Address.
	*
	* @return the String
	*/
	public String getstreetAddress() {
		return this.streetAddress; 
	}

	/**
	* Sets the street Address.
	*
	* @param value the String
	*/
	public void setstreetAddress(String value) { 
		streetAddress = value; 
	}
	private String surname;

	/**
	* Gets the surname.
	*
	* @return the String
	*/
	public String getsurname() {
		return this.surname; 
	}

	/**
	* Sets the surname.
	*
	* @param value the String
	*/
	public void setsurname(String value) { 
		surname = value; 
	}
	private String telephoneNumber;

	/**
	* Gets the telephone Number.
	*
	* @return the String
	*/
	public String gettelephoneNumber() {
		return this.telephoneNumber; 
	}

	/**
	* Sets the telephone Number.
	*
	* @param value the String
	*/
	public void settelephoneNumber(String value) { 
		telephoneNumber = value; 
	}
	private byte[] thumbnailPhoto;

	/**
	* Gets the thumbnail Photo.
	*
	* @return the byte[]
	*/
	public byte[] getthumbnailPhoto() {
		return this.thumbnailPhoto; 
	}

	/**
	* Sets the thumbnail Photo.
	*
	* @param value the byte[]
	*/
	public void setthumbnailPhoto(byte[] value) { 
		thumbnailPhoto = value; 
	}
}