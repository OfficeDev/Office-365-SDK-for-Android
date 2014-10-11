/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

/**
 * The type Contact.
*/
public class Contact extends Item {
	private String parentFolderId;


     /**
     * Gets the parent folder id.		
     *
     * @return the parent folder id
     */
	public String getParentFolderId() {
		 return parentFolderId; 
	}
	
     /**
     * Sets the parent folder id.		
     *
     * @param value the value
     */
	public void setParentFolderId(String value) { 
		parentFolderId = value; 
	}
	private java.util.Calendar birthday;


     /**
     * Gets the birthday.		
     *
     * @return the birthday
     */
	public java.util.Calendar getBirthday() {
		 return birthday; 
	}
	
     /**
     * Sets the birthday.		
     *
     * @param value the value
     */
	public void setBirthday(java.util.Calendar value) { 
		birthday = value; 
	}
	private String fileAs;


     /**
     * Gets the file as.		
     *
     * @return the file as
     */
	public String getFileAs() {
		 return fileAs; 
	}
	
     /**
     * Sets the file as.		
     *
     * @param value the value
     */
	public void setFileAs(String value) { 
		fileAs = value; 
	}
	private String displayName;


     /**
     * Gets the display name.		
     *
     * @return the display name
     */
	public String getDisplayName() {
		 return displayName; 
	}
	
     /**
     * Sets the display name.		
     *
     * @param value the value
     */
	public void setDisplayName(String value) { 
		displayName = value; 
	}
	private String givenName;


     /**
     * Gets the given name.		
     *
     * @return the given name
     */
	public String getGivenName() {
		 return givenName; 
	}
	
     /**
     * Sets the given name.		
     *
     * @param value the value
     */
	public void setGivenName(String value) { 
		givenName = value; 
	}
	private String initials;


     /**
     * Gets the initials.		
     *
     * @return the initials
     */
	public String getInitials() {
		 return initials; 
	}
	
     /**
     * Sets the initials.		
     *
     * @param value the value
     */
	public void setInitials(String value) { 
		initials = value; 
	}
	private String middleName;


     /**
     * Gets the middle name.		
     *
     * @return the middle name
     */
	public String getMiddleName() {
		 return middleName; 
	}
	
     /**
     * Sets the middle name.		
     *
     * @param value the value
     */
	public void setMiddleName(String value) { 
		middleName = value; 
	}
	private String nickName;


     /**
     * Gets the nick name.		
     *
     * @return the nick name
     */
	public String getNickName() {
		 return nickName; 
	}
	
     /**
     * Sets the nick name.		
     *
     * @param value the value
     */
	public void setNickName(String value) { 
		nickName = value; 
	}
	private String surname;


     /**
     * Gets the surname.		
     *
     * @return the surname
     */
	public String getSurname() {
		 return surname; 
	}
	
     /**
     * Sets the surname.		
     *
     * @param value the value
     */
	public void setSurname(String value) { 
		surname = value; 
	}
	private String title;


     /**
     * Gets the title.		
     *
     * @return the title
     */
	public String getTitle() {
		 return title; 
	}
	
     /**
     * Sets the title.		
     *
     * @param value the value
     */
	public void setTitle(String value) { 
		title = value; 
	}
	private String generation;


     /**
     * Gets the generation.		
     *
     * @return the generation
     */
	public String getGeneration() {
		 return generation; 
	}
	
     /**
     * Sets the generation.		
     *
     * @param value the value
     */
	public void setGeneration(String value) { 
		generation = value; 
	}
	private java.util.List<EmailAddress> emailAddresses;


     /**
     * Gets the email addresses.		
     *
     * @return the email addresses
     */
	public java.util.List<EmailAddress> getEmailAddresses() {
		 return emailAddresses; 
	}
	
     /**
     * Sets the email addresses.		
     *
     * @param value the value
     */
	public void setEmailAddresses(java.util.List<EmailAddress> value) { 
		emailAddresses = value; 
	}
	private java.util.List<String> imAddresses;


     /**
     * Gets the im addresses.		
     *
     * @return the im addresses
     */
	public java.util.List<String> getImAddresses() {
		 return imAddresses; 
	}
	
     /**
     * Sets the im addresses.		
     *
     * @param value the value
     */
	public void setImAddresses(java.util.List<String> value) { 
		imAddresses = value; 
	}
	private String jobTitle;


     /**
     * Gets the job title.		
     *
     * @return the job title
     */
	public String getJobTitle() {
		 return jobTitle; 
	}
	
     /**
     * Sets the job title.		
     *
     * @param value the value
     */
	public void setJobTitle(String value) { 
		jobTitle = value; 
	}
	private String companyName;


     /**
     * Gets the company name.		
     *
     * @return the company name
     */
	public String getCompanyName() {
		 return companyName; 
	}
	
     /**
     * Sets the company name.		
     *
     * @param value the value
     */
	public void setCompanyName(String value) { 
		companyName = value; 
	}
	private String department;


     /**
     * Gets the department.		
     *
     * @return the department
     */
	public String getDepartment() {
		 return department; 
	}
	
     /**
     * Sets the department.		
     *
     * @param value the value
     */
	public void setDepartment(String value) { 
		department = value; 
	}
	private String officeLocation;


     /**
     * Gets the office location.		
     *
     * @return the office location
     */
	public String getOfficeLocation() {
		 return officeLocation; 
	}
	
     /**
     * Sets the office location.		
     *
     * @param value the value
     */
	public void setOfficeLocation(String value) { 
		officeLocation = value; 
	}
	private String profession;


     /**
     * Gets the profession.		
     *
     * @return the profession
     */
	public String getProfession() {
		 return profession; 
	}
	
     /**
     * Sets the profession.		
     *
     * @param value the value
     */
	public void setProfession(String value) { 
		profession = value; 
	}
	private String businessHomePage;


     /**
     * Gets the business home page.		
     *
     * @return the business home page
     */
	public String getBusinessHomePage() {
		 return businessHomePage; 
	}
	
     /**
     * Sets the business home page.		
     *
     * @param value the value
     */
	public void setBusinessHomePage(String value) { 
		businessHomePage = value; 
	}
	private String assistantName;


     /**
     * Gets the assistant name.		
     *
     * @return the assistant name
     */
	public String getAssistantName() {
		 return assistantName; 
	}
	
     /**
     * Sets the assistant name.		
     *
     * @param value the value
     */
	public void setAssistantName(String value) { 
		assistantName = value; 
	}
	private String manager;


     /**
     * Gets the manager.		
     *
     * @return the manager
     */
	public String getManager() {
		 return manager; 
	}
	
     /**
     * Sets the manager.		
     *
     * @param value the value
     */
	public void setManager(String value) { 
		manager = value; 
	}
	private java.util.List<String> homePhones;


     /**
     * Gets the home phones.		
     *
     * @return the home phones
     */
	public java.util.List<String> getHomePhones() {
		 return homePhones; 
	}
	
     /**
     * Sets the home phones.		
     *
     * @param value the value
     */
	public void setHomePhones(java.util.List<String> value) { 
		homePhones = value; 
	}
	private java.util.List<String> businessPhones;


     /**
     * Gets the business phones.		
     *
     * @return the business phones
     */
	public java.util.List<String> getBusinessPhones() {
		 return businessPhones; 
	}
	
     /**
     * Sets the business phones.		
     *
     * @param value the value
     */
	public void setBusinessPhones(java.util.List<String> value) { 
		businessPhones = value; 
	}
	private String mobilePhone1;


     /**
     * Gets the mobile phone1.		
     *
     * @return the mobile phone1
     */
	public String getMobilePhone1() {
		 return mobilePhone1; 
	}
	
     /**
     * Sets the mobile phone1.		
     *
     * @param value the value
     */
	public void setMobilePhone1(String value) { 
		mobilePhone1 = value; 
	}
	private PhysicalAddress homeAddress;


     /**
     * Gets the home address.		
     *
     * @return the home address
     */
	public PhysicalAddress getHomeAddress() {
		 return homeAddress; 
	}
	
     /**
     * Sets the home address.		
     *
     * @param value the value
     */
	public void setHomeAddress(PhysicalAddress value) { 
		homeAddress = value; 
	}
	private PhysicalAddress businessAddress;


     /**
     * Gets the business address.		
     *
     * @return the business address
     */
	public PhysicalAddress getBusinessAddress() {
		 return businessAddress; 
	}
	
     /**
     * Sets the business address.		
     *
     * @param value the value
     */
	public void setBusinessAddress(PhysicalAddress value) { 
		businessAddress = value; 
	}
	private PhysicalAddress otherAddress;


     /**
     * Gets the other address.		
     *
     * @return the other address
     */
	public PhysicalAddress getOtherAddress() {
		 return otherAddress; 
	}
	
     /**
     * Sets the other address.		
     *
     * @param value the value
     */
	public void setOtherAddress(PhysicalAddress value) { 
		otherAddress = value; 
	}
	private String yomiCompanyName;


     /**
     * Gets the yomi company name.		
     *
     * @return the yomi company name
     */
	public String getYomiCompanyName() {
		 return yomiCompanyName; 
	}
	
     /**
     * Sets the yomi company name.		
     *
     * @param value the value
     */
	public void setYomiCompanyName(String value) { 
		yomiCompanyName = value; 
	}
	private String yomiGivenName;


     /**
     * Gets the yomi given name.		
     *
     * @return the yomi given name
     */
	public String getYomiGivenName() {
		 return yomiGivenName; 
	}
	
     /**
     * Sets the yomi given name.		
     *
     * @param value the value
     */
	public void setYomiGivenName(String value) { 
		yomiGivenName = value; 
	}
	private String yomiSurname;


     /**
     * Gets the yomi surname.		
     *
     * @return the yomi surname
     */
	public String getYomiSurname() {
		 return yomiSurname; 
	}
	
     /**
     * Sets the yomi surname.		
     *
     * @param value the value
     */
	public void setYomiSurname(String value) { 
		yomiSurname = value; 
	}
}