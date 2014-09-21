
package com.microsoft.exchange.services.odata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



import com.microsoft.exchange.services.odata.model.Contact;



import com.microsoft.exchange.services.odata.model.ContactFolder;



public class ContactFolder extends Entity {

	@SerializedName("ParentFolderId")
	@Expose
	private String parentFolderId;

	public String getParentFolderId() {
		 return parentFolderId; 
	}

	public void setParentFolderId(String value) { 
		parentFolderId = value; 
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

	@SerializedName("Contacts")
	@Expose
	private java.util.List<Contact> contacts;

	public java.util.List<Contact> getContacts() {
		 return contacts; 
	}

	public void setContacts(java.util.List<Contact> value) { 
		contacts = value; 
	}

	@SerializedName("ChildFolders")
	@Expose
	private java.util.List<ContactFolder> childFolders;

	public java.util.List<ContactFolder> getChildFolders() {
		 return childFolders; 
	}

	public void setChildFolders(java.util.List<ContactFolder> value) { 
		childFolders = value; 
	}

}