/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type Contact.
*/
public class Contact extends Item {
	private String ParentFolderId;

	/**
	* Gets the Parent Folder Id.
	*
	* @return the String
	*/
	public String getParentFolderId() {
		return this.ParentFolderId; 
	}

	/**
	* Sets the Parent Folder Id.
	*
	* @param value the String
	*/
	public void setParentFolderId(String value) { 
		ParentFolderId = value; 
	}
	private java.util.Calendar Birthday;

	/**
	* Gets the Birthday.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getBirthday() {
		return this.Birthday; 
	}

	/**
	* Sets the Birthday.
	*
	* @param value the java.util.Calendar
	*/
	public void setBirthday(java.util.Calendar value) { 
		Birthday = value; 
	}
	private String FileAs;

	/**
	* Gets the File As.
	*
	* @return the String
	*/
	public String getFileAs() {
		return this.FileAs; 
	}

	/**
	* Sets the File As.
	*
	* @param value the String
	*/
	public void setFileAs(String value) { 
		FileAs = value; 
	}
	private String DisplayName;

	/**
	* Gets the Display Name.
	*
	* @return the String
	*/
	public String getDisplayName() {
		return this.DisplayName; 
	}

	/**
	* Sets the Display Name.
	*
	* @param value the String
	*/
	public void setDisplayName(String value) { 
		DisplayName = value; 
	}
	private String GivenName;

	/**
	* Gets the Given Name.
	*
	* @return the String
	*/
	public String getGivenName() {
		return this.GivenName; 
	}

	/**
	* Sets the Given Name.
	*
	* @param value the String
	*/
	public void setGivenName(String value) { 
		GivenName = value; 
	}
	private String Initials;

	/**
	* Gets the Initials.
	*
	* @return the String
	*/
	public String getInitials() {
		return this.Initials; 
	}

	/**
	* Sets the Initials.
	*
	* @param value the String
	*/
	public void setInitials(String value) { 
		Initials = value; 
	}
	private String MiddleName;

	/**
	* Gets the Middle Name.
	*
	* @return the String
	*/
	public String getMiddleName() {
		return this.MiddleName; 
	}

	/**
	* Sets the Middle Name.
	*
	* @param value the String
	*/
	public void setMiddleName(String value) { 
		MiddleName = value; 
	}
	private String NickName;

	/**
	* Gets the Nick Name.
	*
	* @return the String
	*/
	public String getNickName() {
		return this.NickName; 
	}

	/**
	* Sets the Nick Name.
	*
	* @param value the String
	*/
	public void setNickName(String value) { 
		NickName = value; 
	}
	private String Surname;

	/**
	* Gets the Surname.
	*
	* @return the String
	*/
	public String getSurname() {
		return this.Surname; 
	}

	/**
	* Sets the Surname.
	*
	* @param value the String
	*/
	public void setSurname(String value) { 
		Surname = value; 
	}
	private String Title;

	/**
	* Gets the Title.
	*
	* @return the String
	*/
	public String getTitle() {
		return this.Title; 
	}

	/**
	* Sets the Title.
	*
	* @param value the String
	*/
	public void setTitle(String value) { 
		Title = value; 
	}
	private String Generation;

	/**
	* Gets the Generation.
	*
	* @return the String
	*/
	public String getGeneration() {
		return this.Generation; 
	}

	/**
	* Sets the Generation.
	*
	* @param value the String
	*/
	public void setGeneration(String value) { 
		Generation = value; 
	}
	private java.util.List<EmailAddress> EmailAddresses;

	/**
	* Gets the Email Addresses.
	*
	* @return the java.util.List<EmailAddress>
	*/
	public java.util.List<EmailAddress> getEmailAddresses() {
		return this.EmailAddresses; 
	}

	/**
	* Sets the Email Addresses.
	*
	* @param value the java.util.List<EmailAddress>
	*/
	public void setEmailAddresses(java.util.List<EmailAddress> value) { 
		EmailAddresses = value; 
	}
	private java.util.List<String> ImAddresses;

	/**
	* Gets the Im Addresses.
	*
	* @return the java.util.List<String>
	*/
	public java.util.List<String> getImAddresses() {
		return this.ImAddresses; 
	}

	/**
	* Sets the Im Addresses.
	*
	* @param value the java.util.List<String>
	*/
	public void setImAddresses(java.util.List<String> value) { 
		ImAddresses = value; 
	}
	private String JobTitle;

	/**
	* Gets the Job Title.
	*
	* @return the String
	*/
	public String getJobTitle() {
		return this.JobTitle; 
	}

	/**
	* Sets the Job Title.
	*
	* @param value the String
	*/
	public void setJobTitle(String value) { 
		JobTitle = value; 
	}
	private String CompanyName;

	/**
	* Gets the Company Name.
	*
	* @return the String
	*/
	public String getCompanyName() {
		return this.CompanyName; 
	}

	/**
	* Sets the Company Name.
	*
	* @param value the String
	*/
	public void setCompanyName(String value) { 
		CompanyName = value; 
	}
	private String Department;

	/**
	* Gets the Department.
	*
	* @return the String
	*/
	public String getDepartment() {
		return this.Department; 
	}

	/**
	* Sets the Department.
	*
	* @param value the String
	*/
	public void setDepartment(String value) { 
		Department = value; 
	}
	private String OfficeLocation;

	/**
	* Gets the Office Location.
	*
	* @return the String
	*/
	public String getOfficeLocation() {
		return this.OfficeLocation; 
	}

	/**
	* Sets the Office Location.
	*
	* @param value the String
	*/
	public void setOfficeLocation(String value) { 
		OfficeLocation = value; 
	}
	private String Profession;

	/**
	* Gets the Profession.
	*
	* @return the String
	*/
	public String getProfession() {
		return this.Profession; 
	}

	/**
	* Sets the Profession.
	*
	* @param value the String
	*/
	public void setProfession(String value) { 
		Profession = value; 
	}
	private String BusinessHomePage;

	/**
	* Gets the Business Home Page.
	*
	* @return the String
	*/
	public String getBusinessHomePage() {
		return this.BusinessHomePage; 
	}

	/**
	* Sets the Business Home Page.
	*
	* @param value the String
	*/
	public void setBusinessHomePage(String value) { 
		BusinessHomePage = value; 
	}
	private String AssistantName;

	/**
	* Gets the Assistant Name.
	*
	* @return the String
	*/
	public String getAssistantName() {
		return this.AssistantName; 
	}

	/**
	* Sets the Assistant Name.
	*
	* @param value the String
	*/
	public void setAssistantName(String value) { 
		AssistantName = value; 
	}
	private String Manager;

	/**
	* Gets the Manager.
	*
	* @return the String
	*/
	public String getManager() {
		return this.Manager; 
	}

	/**
	* Sets the Manager.
	*
	* @param value the String
	*/
	public void setManager(String value) { 
		Manager = value; 
	}
	private java.util.List<String> HomePhones;

	/**
	* Gets the Home Phones.
	*
	* @return the java.util.List<String>
	*/
	public java.util.List<String> getHomePhones() {
		return this.HomePhones; 
	}

	/**
	* Sets the Home Phones.
	*
	* @param value the java.util.List<String>
	*/
	public void setHomePhones(java.util.List<String> value) { 
		HomePhones = value; 
	}
	private java.util.List<String> BusinessPhones;

	/**
	* Gets the Business Phones.
	*
	* @return the java.util.List<String>
	*/
	public java.util.List<String> getBusinessPhones() {
		return this.BusinessPhones; 
	}

	/**
	* Sets the Business Phones.
	*
	* @param value the java.util.List<String>
	*/
	public void setBusinessPhones(java.util.List<String> value) { 
		BusinessPhones = value; 
	}
	private String MobilePhone1;

	/**
	* Gets the Mobile Phone1.
	*
	* @return the String
	*/
	public String getMobilePhone1() {
		return this.MobilePhone1; 
	}

	/**
	* Sets the Mobile Phone1.
	*
	* @param value the String
	*/
	public void setMobilePhone1(String value) { 
		MobilePhone1 = value; 
	}
	private PhysicalAddress HomeAddress;

	/**
	* Gets the Home Address.
	*
	* @return the PhysicalAddress
	*/
	public PhysicalAddress getHomeAddress() {
		return this.HomeAddress; 
	}

	/**
	* Sets the Home Address.
	*
	* @param value the PhysicalAddress
	*/
	public void setHomeAddress(PhysicalAddress value) { 
		HomeAddress = value; 
	}
	private PhysicalAddress BusinessAddress;

	/**
	* Gets the Business Address.
	*
	* @return the PhysicalAddress
	*/
	public PhysicalAddress getBusinessAddress() {
		return this.BusinessAddress; 
	}

	/**
	* Sets the Business Address.
	*
	* @param value the PhysicalAddress
	*/
	public void setBusinessAddress(PhysicalAddress value) { 
		BusinessAddress = value; 
	}
	private PhysicalAddress OtherAddress;

	/**
	* Gets the Other Address.
	*
	* @return the PhysicalAddress
	*/
	public PhysicalAddress getOtherAddress() {
		return this.OtherAddress; 
	}

	/**
	* Sets the Other Address.
	*
	* @param value the PhysicalAddress
	*/
	public void setOtherAddress(PhysicalAddress value) { 
		OtherAddress = value; 
	}
	private String YomiCompanyName;

	/**
	* Gets the Yomi Company Name.
	*
	* @return the String
	*/
	public String getYomiCompanyName() {
		return this.YomiCompanyName; 
	}

	/**
	* Sets the Yomi Company Name.
	*
	* @param value the String
	*/
	public void setYomiCompanyName(String value) { 
		YomiCompanyName = value; 
	}
	private String YomiGivenName;

	/**
	* Gets the Yomi Given Name.
	*
	* @return the String
	*/
	public String getYomiGivenName() {
		return this.YomiGivenName; 
	}

	/**
	* Sets the Yomi Given Name.
	*
	* @param value the String
	*/
	public void setYomiGivenName(String value) { 
		YomiGivenName = value; 
	}
	private String YomiSurname;

	/**
	* Gets the Yomi Surname.
	*
	* @return the String
	*/
	public String getYomiSurname() {
		return this.YomiSurname; 
	}

	/**
	* Sets the Yomi Surname.
	*
	* @param value the String
	*/
	public void setYomiSurname(String value) { 
		YomiSurname = value; 
	}
}