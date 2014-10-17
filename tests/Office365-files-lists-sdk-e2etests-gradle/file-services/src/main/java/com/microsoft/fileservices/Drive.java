/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.fileservices;

/**
 * The type Drive.
*/
public class Drive {
	private String id;

	/**
	* Gets the id.
	*
	* @return the String
	*/
	public String getid() {
		 return id; 
	}

	/**
	* Sets the id.
	*
	* @param value the String
	*/
	public void setid(String value) { 
		id = value; 
	}
	private UserInfo owner;

	/**
	* Gets the owner.
	*
	* @return the UserInfo
	*/
	public UserInfo getowner() {
		 return owner; 
	}

	/**
	* Sets the owner.
	*
	* @param value the UserInfo
	*/
	public void setowner(UserInfo value) { 
		owner = value; 
	}
	private DriveQuota quota;

	/**
	* Gets the quota.
	*
	* @return the DriveQuota
	*/
	public DriveQuota getquota() {
		 return quota; 
	}

	/**
	* Sets the quota.
	*
	* @param value the DriveQuota
	*/
	public void setquota(DriveQuota value) { 
		quota = value; 
	}
}