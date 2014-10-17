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
	private String parentFolderId;

	/**
	* Gets the Parent Folder Id.
	*
	* @return the String
	*/
	public String getParentFolderId() {
		 return parentFolderId; 
	}

	/**
	* Sets the Parent Folder Id.
	*
	* @param value the String
	*/
	public void setParentFolderId(String value) { 
		parentFolderId = value; 
	}
	private java.util.Calendar birthday;

	/**
	* Gets the Birthday.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getBirthday() {
		 return birthday; 
	}

	/**
	* Sets the Birthday.
	*
	* @param value the java.util.Calendar
	*/
	public void setBirthday(java.util.Calendar value) { 
		birthday = value; 
	}
	private String fileAs;

	/**
	* Gets the File As.
	*
	* @return the String
	*/
	public String getFileAs() {
		 return fileAs; 
	}

	/**
	* Sets the File As.
	*
	* @param value the String
	*/
	public void setFileAs(String value) { 
		fileAs = value; 
	}
	private String displayName;

	/**
	* Gets the Display Name.
	*
	* @return the String
	*/
	public String getDisplayName() {
		 return displayName; 
	}

	/**
	* Sets the Display Name.
	*
	* @param value the String
	*/
	public void setDisplayName(String value) { 
		displayName = value; 
	}
	private String givenName;

	/**
	* Gets the Given Name.
	*
	* @return the String
	*/
	public String getGivenName() {
		 return givenName; 
	}

	/**
	* Sets the Given Name.
	*
	* @param value the String
	*/
	public void setGivenName(String value) { 
		givenName = value; 
	}
	private String initials;

	/**
	* Gets the Initials.
	*
	* @return the String
	*/
	public String getInitials() {
		 return initials; 
	}

	/**
	* Sets the Initials.
	*
	* @param value the String
	*/
	public void setInitials(String value) { 
		initials = value; 
	}
	private String middleName;

	/**
	* Gets the Middle Name.
	*
	* @return the String
	*/
	public String getMiddleName() {
		 return middleName; 
	}

	/**
	* Sets the Middle Name.
	*
	* @param value the String
	*/
	public void setMiddleName(String value) { 
		middleName = value; 
	}
	private String nickName;

	/**
	* Gets the Nick Name.
	*
	* @return the String
	*/
	public String getNickName() {
		 return nickName; 
	}

	/**
	* Sets the Nick Name.
	*
	* @param value the String
	*/
	public void setNickName(String value) { 
		nickName = value; 
	}
	private String surname;

	/**
	* Gets the Surname.
	*
	* @return the String
	*/
	public String getSurname() {
		 return surname; 
	}

	/**
	* Sets the Surname.
	*
	* @param value the String
	*/
	public void setSurname(String value) { 
		surname = value; 
	}
	private String title;

	/**
	* Gets the Title.
	*
	* @return the String
	*/
	public String getTitle() {
		 return title; 
	}

	/**
	* Sets the Title.
	*
	* @param value the String
	*/
	public void setTitle(String value) { 
		title = value; 
	}
	private String generation;

	/**
	* Gets the Generation.
	*
	* @return the String
	*/
	public String getGeneration() {
		 return generation; 
	}

	/**
	* Sets the Generation.
	*
	* @param value the String
	*/
	public void setGeneration(String value) { 
		generation = value; 
	}
	private java.util.List<EmailAddress> emailAddresses;

	/**
	* Gets the Email Addresses.
	*
	* @return the java.util.List<EmailAddress>
	*/
	public java.util.List<EmailAddress> getEmailAddresses() {
		 return emailAddresses; 
	}

	/**
	* Sets the Email Addresses.
	*
	* @param value the java.util.List<EmailAddress>
	*/
	public void setEmailAddresses(java.util.List<EmailAddress> value) { 
		emailAddresses = value; 
	}
	private java.util.List<String> imAddresses;

	/**
	* Gets the Im Addresses.
	*
	* @return the java.util.List<String>
	*/
	public java.util.List<String> getImAddresses() {
		 return imAddresses; 
	}

	/**
	* Sets the Im Addresses.
	*
	* @param value the java.util.List<String>
	*/
	public void setImAddresses(java.util.List<String> value) { 
		imAddresses = value; 
	}
	private String jobTitle;

	/**
	* Gets the Job Title.
	*
	* @return the String
	*/
	public String getJobTitle() {
		 return jobTitle; 
	}

	/**
	* Sets the Job Title.
	*
	* @param value the String
	*/
	public void setJobTitle(String value) { 
		jobTitle = value; 
	}
	private String companyName;

	/**
	* Gets the Company Name.
	*
	* @return the String
	*/
	public String getCompanyName() {
		 return companyName; 
	}

	/**
	* Sets the Company Name.
	*
	* @param value the String
	*/
	public void setCompanyName(String value) { 
		companyName = value; 
	}
	private String department;

	/**
	* Gets the Department.
	*
	* @return the String
	*/
	public String getDepartment() {
		 return department; 
	}

	/**
	* Sets the Department.
	*
	* @param value the String
	*/
	public void setDepartment(String value) { 
		department = value; 
	}
	private String officeLocation;

	/**
	* Gets the Office Location.
	*
	* @return the String
	*/
	public String getOfficeLocation() {
		 return officeLocation; 
	}

	/**
	* Sets the Office Location.
	*
	* @param value the String
	*/
	public void setOfficeLocation(String value) { 
		officeLocation = value; 
	}
	private String profession;

	/**
	* Gets the Profession.
	*
	* @return the String
	*/
	public String getProfession() {
		 return profession; 
	}

	/**
	* Sets the Profession.
	*
	* @param value the String
	*/
	public void setProfession(String value) { 
		profession = value; 
	}
	private String businessHomePage;

	/**
	* Gets the Business Home Page.
	*
	* @return the String
	*/
	public String getBusinessHomePage() {
		 return businessHomePage; 
	}

	/**
	* Sets the Business Home Page.
	*
	* @param value the String
	*/
	public void setBusinessHomePage(String value) { 
		businessHomePage = value; 
	}
	private String assistantName;

	/**
	* Gets the Assistant Name.
	*
	* @return the String
	*/
	public String getAssistantName() {
		 return assistantName; 
	}

	/**
	* Sets the Assistant Name.
	*
	* @param value the String
	*/
	public void setAssistantName(String value) { 
		assistantName = value; 
	}
	private String manager;

	/**
	* Gets the Manager.
	*
	* @return the String
	*/
	public String getManager() {
		 return manager; 
	}

	/**
	* Sets the Manager.
	*
	* @param value the String
	*/
	public void setManager(String value) { 
		manager = value; 
	}
	private java.util.List<String> homePhones;

	/**
	* Gets the Home Phones.
	*
	* @return the java.util.List<String>
	*/
	public java.util.List<String> getHomePhones() {
		 return homePhones; 
	}

	/**
	* Sets the Home Phones.
	*
	* @param value the java.util.List<String>
	*/
	public void setHomePhones(java.util.List<String> value) { 
		homePhones = value; 
	}
	private java.util.List<String> businessPhones;

	/**
	* Gets the Business Phones.
	*
	* @return the java.util.List<String>
	*/
	public java.util.List<String> getBusinessPhones() {
		 return businessPhones; 
	}

	/**
	* Sets the Business Phones.
	*
	* @param value the java.util.List<String>
	*/
	public void setBusinessPhones(java.util.List<String> value) { 
		businessPhones = value; 
	}
	private String mobilePhone1;

	/**
	* Gets the Mobile Phone1.
	*
	* @return the String
	*/
	public String getMobilePhone1() {
		 return mobilePhone1; 
	}

	/**
	* Sets the Mobile Phone1.
	*
	* @param value the String
	*/
	public void setMobilePhone1(String value) { 
		mobilePhone1 = value; 
	}
	private PhysicalAddress homeAddress;

	/**
	* Gets the Home Address.
	*
	* @return the PhysicalAddress
	*/
	public PhysicalAddress getHomeAddress() {
		 return homeAddress; 
	}

	/**
	* Sets the Home Address.
	*
	* @param value the PhysicalAddress
	*/
	public void setHomeAddress(PhysicalAddress value) { 
		homeAddress = value; 
	}
	private PhysicalAddress businessAddress;

	/**
	* Gets the Business Address.
	*
	* @return the PhysicalAddress
	*/
	public PhysicalAddress getBusinessAddress() {
		 return businessAddress; 
	}

	/**
	* Sets the Business Address.
	*
	* @param value the PhysicalAddress
	*/
	public void setBusinessAddress(PhysicalAddress value) { 
		businessAddress = value; 
	}
	private PhysicalAddress otherAddress;

	/**
	* Gets the Other Address.
	*
	* @return the PhysicalAddress
	*/
	public PhysicalAddress getOtherAddress() {
		 return otherAddress; 
	}

	/**
	* Sets the Other Address.
	*
	* @param value the PhysicalAddress
	*/
	public void setOtherAddress(PhysicalAddress value) { 
		otherAddress = value; 
	}
	private String yomiCompanyName;

	/**
	* Gets the Yomi Company Name.
	*
	* @return the String
	*/
	public String getYomiCompanyName() {
		 return yomiCompanyName; 
	}

	/**
	* Sets the Yomi Company Name.
	*
	* @param value the String
	*/
	public void setYomiCompanyName(String value) { 
		yomiCompanyName = value; 
	}
	private String yomiGivenName;

	/**
	* Gets the Yomi Given Name.
	*
	* @return the String
	*/
	public String getYomiGivenName() {
		 return yomiGivenName; 
	}

	/**
	* Sets the Yomi Given Name.
	*
	* @param value the String
	*/
	public void setYomiGivenName(String value) { 
		yomiGivenName = value; 
	}
	private String yomiSurname;

	/**
	* Gets the Yomi Surname.
	*
	* @return the String
	*/
	public String getYomiSurname() {
		 return yomiSurname; 
	}

	/**
	* Sets the Yomi Surname.
	*
	* @param value the String
	*/
	public void setYomiSurname(String value) { 
		yomiSurname = value; 
	}
}