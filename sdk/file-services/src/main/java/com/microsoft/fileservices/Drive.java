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
		return this.id; 
	}

	/**
	* Sets the id.
	*
	* @param value the String
	*/
	public void setid(String value) { 
		id = value; 
	}
	private Identity owner;

	/**
	* Gets the owner.
	*
	* @return the Identity
	*/
	public Identity getowner() {
		return this.owner; 
	}

	/**
	* Sets the owner.
	*
	* @param value the Identity
	*/
	public void setowner(Identity value) { 
		owner = value; 
	}
	private DriveQuota quota;

	/**
	* Gets the quota.
	*
	* @return the DriveQuota
	*/
	public DriveQuota getquota() {
		return this.quota; 
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