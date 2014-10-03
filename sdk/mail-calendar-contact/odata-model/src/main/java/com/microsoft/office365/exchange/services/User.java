/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.exchange.services;

public class User extends Entity {
	private String displayName;

	public String getDisplayName() {
		 return displayName; 
	}

	public void setDisplayName(String value) { 
		displayName = value; 
	}
	private String alias;

	public String getAlias() {
		 return alias; 
	}

	public void setAlias(String value) { 
		alias = value; 
	}
	private java.util.UUID mailboxGuid;

	public java.util.UUID getMailboxGuid() {
		 return mailboxGuid; 
	}

	public void setMailboxGuid(java.util.UUID value) { 
		mailboxGuid = value; 
	}
}