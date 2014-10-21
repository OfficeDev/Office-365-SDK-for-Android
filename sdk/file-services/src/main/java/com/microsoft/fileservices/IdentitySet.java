/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.fileservices;

/**
 * The type Identity Set.
*/
public class IdentitySet {

	private Identity application;

	/**
	* Gets the application.
	*
	* @return the Identity
	*/
	public Identity getapplication() {
		return this.application; 
	}

	/**
	* Sets the application.
	*
	* @param value the Identity
	*/
	public void setapplication(Identity value) { 
		application = value; 
	}

	private Identity user;

	/**
	* Gets the user.
	*
	* @return the Identity
	*/
	public Identity getuser() {
		return this.user; 
	}

	/**
	* Sets the user.
	*
	* @param value the Identity
	*/
	public void setuser(Identity value) { 
		user = value; 
	}
}