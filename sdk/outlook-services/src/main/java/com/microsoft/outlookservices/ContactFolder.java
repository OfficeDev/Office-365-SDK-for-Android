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
    
    private java.util.List<Contact> Contacts = new java.util.ArrayList<Contact>();
     
    /**
    * Gets the Contacts.
    *
    * @return the java.util.List<Contact>
    */
    public java.util.List<Contact> getContacts() {
        return this.Contacts; 
    }

    /**
    * Sets the Contacts.
    *
    * @param value the java.util.List<Contact>
    */
    public void setContacts(java.util.List<Contact> value) { 
        this.Contacts = value; 
    }
    
    private java.util.List<ContactFolder> ChildFolders = new java.util.ArrayList<ContactFolder>();
     
    /**
    * Gets the Child Folders.
    *
    * @return the java.util.List<ContactFolder>
    */
    public java.util.List<ContactFolder> getChildFolders() {
        return this.ChildFolders; 
    }

    /**
    * Sets the Child Folders.
    *
    * @param value the java.util.List<ContactFolder>
    */
    public void setChildFolders(java.util.List<ContactFolder> value) { 
        this.ChildFolders = value; 
    }
}

