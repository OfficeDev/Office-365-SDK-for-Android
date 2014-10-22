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
	private String Alias;

	/**
	* Gets the Alias.
	*
	* @return the String
	*/
	public String getAlias() {
		return this.Alias; 
	}

	/**
	* Sets the Alias.
	*
	* @param value the String
	*/
	public void setAlias(String value) { 
		Alias = value; 
	}
	private java.util.UUID MailboxGuid;

	/**
	* Gets the Mailbox Guid.
	*
	* @return the java.util.UUID
	*/
	public java.util.UUID getMailboxGuid() {
		return this.MailboxGuid; 
	}

	/**
	* Sets the Mailbox Guid.
	*
	* @param value the java.util.UUID
	*/
	public void setMailboxGuid(java.util.UUID value) { 
		MailboxGuid = value; 
	}
}