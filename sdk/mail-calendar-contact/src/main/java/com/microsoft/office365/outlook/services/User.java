/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

/**
 * The type User.
*/
public class User extends Entity {
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
	private String alias;


     /**
     * Gets the alias.		
     *
     * @return the alias
     */
	public String getAlias() {
		 return alias; 
	}
	
     /**
     * Sets the alias.		
     *
     * @param value the value
     */
	public void setAlias(String value) { 
		alias = value; 
	}
	private java.util.UUID mailboxGuid;


     /**
     * Gets the mailbox guid.		
     *
     * @return the mailbox guid
     */
	public java.util.UUID getMailboxGuid() {
		 return mailboxGuid; 
	}
	
     /**
     * Sets the mailbox guid.		
     *
     * @param value the value
     */
	public void setMailboxGuid(java.util.UUID value) { 
		mailboxGuid = value; 
	}
}