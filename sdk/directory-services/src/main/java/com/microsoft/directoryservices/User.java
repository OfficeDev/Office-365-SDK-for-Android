/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type User.
*/
public class User extends DirectoryObject {
	private Boolean accountEnabled;

	/**
	* Gets the account Enabled.
	*
	* @return the Boolean
	*/
	public Boolean getaccountEnabled() {
		return this.accountEnabled; 
	}

	/**
	* Sets the account Enabled.
	*
	* @param value the Boolean
	*/
	public void setaccountEnabled(Boolean value) { 
		accountEnabled = value; 
	}
	private java.util.List<AssignedLicense> assignedLicenses;

	/**
	* Gets the assigned Licenses.
	*
	* @return the java.util.List<AssignedLicense>
	*/
	public java.util.List<AssignedLicense> getassignedLicenses() {
		return this.assignedLicenses; 
	}

	/**
	* Sets the assigned Licenses.
	*
	* @param value the java.util.List<AssignedLicense>
	*/
	public void setassignedLicenses(java.util.List<AssignedLicense> value) { 
		assignedLicenses = value; 
	}
	private java.util.List<AssignedPlan> assignedPlans;

	/**
	* Gets the assigned Plans.
	*
	* @return the java.util.List<AssignedPlan>
	*/
	public java.util.List<AssignedPlan> getassignedPlans() {
		return this.assignedPlans; 
	}

	/**
	* Sets the assigned Plans.
	*
	* @param value the java.util.List<AssignedPlan>
	*/
	public void setassignedPlans(java.util.List<AssignedPlan> value) { 
		assignedPlans = value; 
	}
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
	private String immutableId;

	/**
	* Gets the immutable Id.
	*
	* @return the String
	*/
	public String getimmutableId() {
		return this.immutableId; 
	}

	/**
	* Sets the immutable Id.
	*
	* @param value the String
	*/
	public void setimmutableId(String value) { 
		immutableId = value; 
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
	private java.util.List<String> otherMails;

	/**
	* Gets the other Mails.
	*
	* @return the java.util.List<String>
	*/
	public java.util.List<String> getotherMails() {
		return this.otherMails; 
	}

	/**
	* Sets the other Mails.
	*
	* @param value the java.util.List<String>
	*/
	public void setotherMails(java.util.List<String> value) { 
		otherMails = value; 
	}
	private String passwordPolicies;

	/**
	* Gets the password Policies.
	*
	* @return the String
	*/
	public String getpasswordPolicies() {
		return this.passwordPolicies; 
	}

	/**
	* Sets the password Policies.
	*
	* @param value the String
	*/
	public void setpasswordPolicies(String value) { 
		passwordPolicies = value; 
	}
	private PasswordProfile passwordProfile;

	/**
	* Gets the password Profile.
	*
	* @return the PasswordProfile
	*/
	public PasswordProfile getpasswordProfile() {
		return this.passwordProfile; 
	}

	/**
	* Sets the password Profile.
	*
	* @param value the PasswordProfile
	*/
	public void setpasswordProfile(PasswordProfile value) { 
		passwordProfile = value; 
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
	private String preferredLanguage;

	/**
	* Gets the preferred Language.
	*
	* @return the String
	*/
	public String getpreferredLanguage() {
		return this.preferredLanguage; 
	}

	/**
	* Sets the preferred Language.
	*
	* @param value the String
	*/
	public void setpreferredLanguage(String value) { 
		preferredLanguage = value; 
	}
	private java.util.List<ProvisionedPlan> provisionedPlans;

	/**
	* Gets the provisioned Plans.
	*
	* @return the java.util.List<ProvisionedPlan>
	*/
	public java.util.List<ProvisionedPlan> getprovisionedPlans() {
		return this.provisionedPlans; 
	}

	/**
	* Sets the provisioned Plans.
	*
	* @param value the java.util.List<ProvisionedPlan>
	*/
	public void setprovisionedPlans(java.util.List<ProvisionedPlan> value) { 
		provisionedPlans = value; 
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
	private String usageLocation;

	/**
	* Gets the usage Location.
	*
	* @return the String
	*/
	public String getusageLocation() {
		return this.usageLocation; 
	}

	/**
	* Sets the usage Location.
	*
	* @param value the String
	*/
	public void setusageLocation(String value) { 
		usageLocation = value; 
	}
	private String userPrincipalName;

	/**
	* Gets the user Principal Name.
	*
	* @return the String
	*/
	public String getuserPrincipalName() {
		return this.userPrincipalName; 
	}

	/**
	* Sets the user Principal Name.
	*
	* @param value the String
	*/
	public void setuserPrincipalName(String value) { 
		userPrincipalName = value; 
	}
	private String userType;

	/**
	* Gets the user Type.
	*
	* @return the String
	*/
	public String getuserType() {
		return this.userType; 
	}

	/**
	* Sets the user Type.
	*
	* @param value the String
	*/
	public void setuserType(String value) { 
		userType = value; 
	}
}