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

	private ApplicationInfo application;

	/**
	* Gets the application.
	*
	* @return the ApplicationInfo
	*/
	public ApplicationInfo getapplication() {
		 return application; 
	}

	/**
	* Sets the application.
	*
	* @param value the ApplicationInfo
	*/
	public void setapplication(ApplicationInfo value) { 
		application = value; 
	}

	private UserInfo user;

	/**
	* Gets the user.
	*
	* @return the UserInfo
	*/
	public UserInfo getuser() {
		 return user; 
	}

	/**
	* Sets the user.
	*
	* @param value the UserInfo
	*/
	public void setuser(UserInfo value) { 
		user = value; 
	}
}