
package com.microsoft.exchange.services.odata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Contact extends Item {

	@SerializedName("ParentFolderId")
	@Expose
	private String parentFolderId;

	public String getParentFolderId() {
		 return parentFolderId; 
	}

	public void setParentFolderId(String value) { 
		parentFolderId = value; 
	}

	@SerializedName("Birthday")
	@Expose
	private java.util.Date birthday;

	public java.util.Date getBirthday() {
		 return birthday; 
	}

	public void setBirthday(java.util.Date value) { 
		birthday = value; 
	}

	@SerializedName("FileAs")
	@Expose
	private String fileAs;

	public String getFileAs() {
		 return fileAs; 
	}

	public void setFileAs(String value) { 
		fileAs = value; 
	}

	@SerializedName("DisplayName")
	@Expose
	private String displayName;

	public String getDisplayName() {
		 return displayName; 
	}

	public void setDisplayName(String value) { 
		displayName = value; 
	}

	@SerializedName("GivenName")
	@Expose
	private String givenName;

	public String getGivenName() {
		 return givenName; 
	}

	public void setGivenName(String value) { 
		givenName = value; 
	}

	@SerializedName("Initials")
	@Expose
	private String initials;

	public String getInitials() {
		 return initials; 
	}

	public void setInitials(String value) { 
		initials = value; 
	}

	@SerializedName("MiddleName")
	@Expose
	private String middleName;

	public String getMiddleName() {
		 return middleName; 
	}

	public void setMiddleName(String value) { 
		middleName = value; 
	}

	@SerializedName("NickName")
	@Expose
	private String nickName;

	public String getNickName() {
		 return nickName; 
	}

	public void setNickName(String value) { 
		nickName = value; 
	}

	@SerializedName("Surname")
	@Expose
	private String surname;

	public String getSurname() {
		 return surname; 
	}

	public void setSurname(String value) { 
		surname = value; 
	}

	@SerializedName("Title")
	@Expose
	private String title;

	public String getTitle() {
		 return title; 
	}

	public void setTitle(String value) { 
		title = value; 
	}

	@SerializedName("Generation")
	@Expose
	private String generation;

	public String getGeneration() {
		 return generation; 
	}

	public void setGeneration(String value) { 
		generation = value; 
	}

	@SerializedName("EmailAddress1")
	@Expose
	private String emailAddress1;

	public String getEmailAddress1() {
		 return emailAddress1; 
	}

	public void setEmailAddress1(String value) { 
		emailAddress1 = value; 
	}

	@SerializedName("EmailAddress2")
	@Expose
	private String emailAddress2;

	public String getEmailAddress2() {
		 return emailAddress2; 
	}

	public void setEmailAddress2(String value) { 
		emailAddress2 = value; 
	}

	@SerializedName("EmailAddress3")
	@Expose
	private String emailAddress3;

	public String getEmailAddress3() {
		 return emailAddress3; 
	}

	public void setEmailAddress3(String value) { 
		emailAddress3 = value; 
	}

	@SerializedName("ImAddress1")
	@Expose
	private String imAddress1;

	public String getImAddress1() {
		 return imAddress1; 
	}

	public void setImAddress1(String value) { 
		imAddress1 = value; 
	}

	@SerializedName("ImAddress2")
	@Expose
	private String imAddress2;

	public String getImAddress2() {
		 return imAddress2; 
	}

	public void setImAddress2(String value) { 
		imAddress2 = value; 
	}

	@SerializedName("ImAddress3")
	@Expose
	private String imAddress3;

	public String getImAddress3() {
		 return imAddress3; 
	}

	public void setImAddress3(String value) { 
		imAddress3 = value; 
	}

	@SerializedName("JobTitle")
	@Expose
	private String jobTitle;

	public String getJobTitle() {
		 return jobTitle; 
	}

	public void setJobTitle(String value) { 
		jobTitle = value; 
	}

	@SerializedName("CompanyName")
	@Expose
	private String companyName;

	public String getCompanyName() {
		 return companyName; 
	}

	public void setCompanyName(String value) { 
		companyName = value; 
	}

	@SerializedName("Department")
	@Expose
	private String department;

	public String getDepartment() {
		 return department; 
	}

	public void setDepartment(String value) { 
		department = value; 
	}

	@SerializedName("OfficeLocation")
	@Expose
	private String officeLocation;

	public String getOfficeLocation() {
		 return officeLocation; 
	}

	public void setOfficeLocation(String value) { 
		officeLocation = value; 
	}

	@SerializedName("Profession")
	@Expose
	private String profession;

	public String getProfession() {
		 return profession; 
	}

	public void setProfession(String value) { 
		profession = value; 
	}

	@SerializedName("BusinessHomePage")
	@Expose
	private String businessHomePage;

	public String getBusinessHomePage() {
		 return businessHomePage; 
	}

	public void setBusinessHomePage(String value) { 
		businessHomePage = value; 
	}

	@SerializedName("AssistantName")
	@Expose
	private String assistantName;

	public String getAssistantName() {
		 return assistantName; 
	}

	public void setAssistantName(String value) { 
		assistantName = value; 
	}

	@SerializedName("Manager")
	@Expose
	private String manager;

	public String getManager() {
		 return manager; 
	}

	public void setManager(String value) { 
		manager = value; 
	}

	@SerializedName("HomePhone1")
	@Expose
	private String homePhone1;

	public String getHomePhone1() {
		 return homePhone1; 
	}

	public void setHomePhone1(String value) { 
		homePhone1 = value; 
	}

	@SerializedName("HomePhone2")
	@Expose
	private String homePhone2;

	public String getHomePhone2() {
		 return homePhone2; 
	}

	public void setHomePhone2(String value) { 
		homePhone2 = value; 
	}

	@SerializedName("BusinessPhone1")
	@Expose
	private String businessPhone1;

	public String getBusinessPhone1() {
		 return businessPhone1; 
	}

	public void setBusinessPhone1(String value) { 
		businessPhone1 = value; 
	}

	@SerializedName("BusinessPhone2")
	@Expose
	private String businessPhone2;

	public String getBusinessPhone2() {
		 return businessPhone2; 
	}

	public void setBusinessPhone2(String value) { 
		businessPhone2 = value; 
	}

	@SerializedName("MobilePhone1")
	@Expose
	private String mobilePhone1;

	public String getMobilePhone1() {
		 return mobilePhone1; 
	}

	public void setMobilePhone1(String value) { 
		mobilePhone1 = value; 
	}

	@SerializedName("OtherPhone")
	@Expose
	private String otherPhone;

	public String getOtherPhone() {
		 return otherPhone; 
	}

	public void setOtherPhone(String value) { 
		otherPhone = value; 
	}

	@SerializedName("DateTimeCreated")
	@Expose
	private java.util.Date dateTimeCreated;

	public java.util.Date getDateTimeCreated() {
		 return dateTimeCreated; 
	}

	public void setDateTimeCreated(java.util.Date value) { 
		dateTimeCreated = value; 
	}

	@SerializedName("LastModifiedTime")
	@Expose
	private java.util.Date lastModifiedTime;

	public java.util.Date getLastModifiedTime() {
		 return lastModifiedTime; 
	}

	public void setLastModifiedTime(java.util.Date value) { 
		lastModifiedTime = value; 
	}

}