/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.file.services.model;

public class Drive {
	private java.util.UUID id;

	public java.util.UUID getid() {
		 return id; 
	}

	public void setid(java.util.UUID value) { 
		id = value; 
	}
	private UserInfo owner;

	public UserInfo getowner() {
		 return owner; 
	}

	public void setowner(UserInfo value) { 
		owner = value; 
	}
	private DriveQuota quota;

	public DriveQuota getquota() {
		 return quota; 
	}

	public void setquota(DriveQuota value) { 
		quota = value; 
	}
}