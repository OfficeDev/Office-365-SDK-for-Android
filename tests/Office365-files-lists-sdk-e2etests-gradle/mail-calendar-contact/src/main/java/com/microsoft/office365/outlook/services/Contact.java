/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

public class Contact extends Item {
	private String parentFolderId;

	public String getParentFolderId() {
		 return parentFolderId; 
	}

	public void setParentFolderId(String value) { 
		parentFolderId = value; 
	}
	private java.util.Calendar birthday;

	public java.util.Calendar getBirthday() {
		 return birthday; 
	}

	public void setBirthday(java.util.Calendar value) { 
		birthday = value; 
	}
	private String fileAs;

	public String getFileAs() {
		 return fileAs; 
	}

	public void setFileAs(String value) { 
		fileAs = value; 
	}
	private String displayName;

	public String getDisplayName() {
		 return displayName; 
	}

	public void setDisplayName(String value) { 
		displayName = value; 
	}
	private String givenName;

	public String getGivenName() {
		 return givenName; 
	}

	public void setGivenName(String value) { 
		givenName = value; 
	}
	private String initials;

	public String getInitials() {
		 return initials; 
	}

	public void setInitials(String value) { 
		initials = value; 
	}
	private String middleName;

	public String getMiddleName() {
		 return middleName; 
	}

	public void setMiddleName(String value) { 
		middleName = value; 
	}
	private String nickName;

	public String getNickName() {
		 return nickName; 
	}

	public void setNickName(String value) { 
		nickName = value; 
	}
	private String surname;

	public String getSurname() {
		 return surname; 
	}

	public void setSurname(String value) { 
		surname = value; 
	}
	private String title;

	public String getTitle() {
		 return title; 
	}

	public void setTitle(String value) { 
		title = value; 
	}
	private String generation;

	public String getGeneration() {
		 return generation; 
	}

	public void setGeneration(String value) { 
		generation = value; 
	}
	private java.util.List<EmailAddress> emailAddresses;

	public java.util.List<EmailAddress> getEmailAddresses() {
		 return emailAddresses; 
	}

	public void setEmailAddresses(java.util.List<EmailAddress> value) { 
		emailAddresses = value; 
	}
	private java.util.List<String> imAddresses;

	public java.util.List<String> getImAddresses() {
		 return imAddresses; 
	}

	public void setImAddresses(java.util.List<String> value) { 
		imAddresses = value; 
	}
	private String jobTitle;

	public String getJobTitle() {
		 return jobTitle; 
	}

	public void setJobTitle(String value) { 
		jobTitle = value; 
	}
	private String companyName;

	public String getCompanyName() {
		 return companyName; 
	}

	public void setCompanyName(String value) { 
		companyName = value; 
	}
	private String department;

	public String getDepartment() {
		 return department; 
	}

	public void setDepartment(String value) { 
		department = value; 
	}
	private String officeLocation;

	public String getOfficeLocation() {
		 return officeLocation; 
	}

	public void setOfficeLocation(String value) { 
		officeLocation = value; 
	}
	private String profession;

	public String getProfession() {
		 return profession; 
	}

	public void setProfession(String value) { 
		profession = value; 
	}
	private String businessHomePage;

	public String getBusinessHomePage() {
		 return businessHomePage; 
	}

	public void setBusinessHomePage(String value) { 
		businessHomePage = value; 
	}
	private String assistantName;

	public String getAssistantName() {
		 return assistantName; 
	}

	public void setAssistantName(String value) { 
		assistantName = value; 
	}
	private String manager;

	public String getManager() {
		 return manager; 
	}

	public void setManager(String value) { 
		manager = value; 
	}
	private java.util.List<String> homePhones;

	public java.util.List<String> getHomePhones() {
		 return homePhones; 
	}

	public void setHomePhones(java.util.List<String> value) { 
		homePhones = value; 
	}
	private java.util.List<String> businessPhones;

	public java.util.List<String> getBusinessPhones() {
		 return businessPhones; 
	}

	public void setBusinessPhones(java.util.List<String> value) { 
		businessPhones = value; 
	}
	private String mobilePhone1;

	public String getMobilePhone1() {
		 return mobilePhone1; 
	}

	public void setMobilePhone1(String value) { 
		mobilePhone1 = value; 
	}
	private PhysicalAddress homeAddress;

	public PhysicalAddress getHomeAddress() {
		 return homeAddress; 
	}

	public void setHomeAddress(PhysicalAddress value) { 
		homeAddress = value; 
	}
	private PhysicalAddress businessAddress;

	public PhysicalAddress getBusinessAddress() {
		 return businessAddress; 
	}

	public void setBusinessAddress(PhysicalAddress value) { 
		businessAddress = value; 
	}
	private PhysicalAddress otherAddress;

	public PhysicalAddress getOtherAddress() {
		 return otherAddress; 
	}

	public void setOtherAddress(PhysicalAddress value) { 
		otherAddress = value; 
	}
	private String yomiCompanyName;

	public String getYomiCompanyName() {
		 return yomiCompanyName; 
	}

	public void setYomiCompanyName(String value) { 
		yomiCompanyName = value; 
	}
	private String yomiGivenName;

	public String getYomiGivenName() {
		 return yomiGivenName; 
	}

	public void setYomiGivenName(String value) { 
		yomiGivenName = value; 
	}
	private String yomiSurname;

	public String getYomiSurname() {
		 return yomiSurname; 
	}

	public void setYomiSurname(String value) { 
		yomiSurname = value; 
	}
}