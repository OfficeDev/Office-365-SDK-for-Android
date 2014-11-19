/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type Contact Folder.
*/
public class ContactFolder extends Entity {

    public ContactFolder(){
        setODataType("#Microsoft.OutlookServices.ContactFolder");
    }

    
    private java.util.List<Contact> Contacts = new java.util.ArrayList<Contact>();

    /**
    * Gets the Contacts.
    *
    * @return the Contacts
    */
    public java.util.List<Contact> getContacts() {
        return this.Contacts; 
    }
        
    private java.util.List<ContactFolder> ChildFolders = new java.util.ArrayList<ContactFolder>();

    /**
    * Gets the Child Folders.
    *
    * @return the ChildFolders
    */
    public java.util.List<ContactFolder> getChildFolders() {
        return this.ChildFolders; 
    }
            
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
        this.ParentFolderId = value; 
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
        this.DisplayName = value; 
    }
}