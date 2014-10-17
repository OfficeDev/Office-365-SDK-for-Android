/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type User.
*/
public class User extends Entity {
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
	private String alias;

	/**
	* Gets the Alias.
	*
	* @return the String
	*/
	public String getAlias() {
		 return alias; 
	}

	/**
	* Sets the Alias.
	*
	* @param value the String
	*/
	public void setAlias(String value) { 
		alias = value; 
	}
	private java.util.UUID mailboxGuid;

	/**
	* Gets the Mailbox Guid.
	*
	* @return the java.util.UUID
	*/
	public java.util.UUID getMailboxGuid() {
		 return mailboxGuid; 
	}

	/**
	* Sets the Mailbox Guid.
	*
	* @param value the java.util.UUID
	*/
	public void setMailboxGuid(java.util.UUID value) { 
		mailboxGuid = value; 
	}
}