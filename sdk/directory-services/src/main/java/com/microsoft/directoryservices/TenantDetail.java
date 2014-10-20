/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type Tenant Detail.
*/
public class TenantDetail extends DirectoryObject {
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
	private java.util.Calendar companyLastDirSyncTime;

	/**
	* Gets the company Last Dir Sync Time.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getcompanyLastDirSyncTime() {
		return this.companyLastDirSyncTime; 
	}

	/**
	* Sets the company Last Dir Sync Time.
	*
	* @param value the java.util.Calendar
	*/
	public void setcompanyLastDirSyncTime(java.util.Calendar value) { 
		companyLastDirSyncTime = value; 
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
	private String countryLetterCode;

	/**
	* Gets the country Letter Code.
	*
	* @return the String
	*/
	public String getcountryLetterCode() {
		return this.countryLetterCode; 
	}

	/**
	* Sets the country Letter Code.
	*
	* @param value the String
	*/
	public void setcountryLetterCode(String value) { 
		countryLetterCode = value; 
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
	private java.util.List<String> marketingNotificationEmails;

	/**
	* Gets the marketing Notification Emails.
	*
	* @return the java.util.List<String>
	*/
	public java.util.List<String> getmarketingNotificationEmails() {
		return this.marketingNotificationEmails; 
	}

	/**
	* Sets the marketing Notification Emails.
	*
	* @param value the java.util.List<String>
	*/
	public void setmarketingNotificationEmails(java.util.List<String> value) { 
		marketingNotificationEmails = value; 
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
	private String street;

	/**
	* Gets the street.
	*
	* @return the String
	*/
	public String getstreet() {
		return this.street; 
	}

	/**
	* Sets the street.
	*
	* @param value the String
	*/
	public void setstreet(String value) { 
		street = value; 
	}
	private java.util.List<String> technicalNotificationMails;

	/**
	* Gets the technical Notification Mails.
	*
	* @return the java.util.List<String>
	*/
	public java.util.List<String> gettechnicalNotificationMails() {
		return this.technicalNotificationMails; 
	}

	/**
	* Sets the technical Notification Mails.
	*
	* @param value the java.util.List<String>
	*/
	public void settechnicalNotificationMails(java.util.List<String> value) { 
		technicalNotificationMails = value; 
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
	private java.util.List<VerifiedDomain> verifiedDomains;

	/**
	* Gets the verified Domains.
	*
	* @return the java.util.List<VerifiedDomain>
	*/
	public java.util.List<VerifiedDomain> getverifiedDomains() {
		return this.verifiedDomains; 
	}

	/**
	* Sets the verified Domains.
	*
	* @param value the java.util.List<VerifiedDomain>
	*/
	public void setverifiedDomains(java.util.List<VerifiedDomain> value) { 
		verifiedDomains = value; 
	}
}