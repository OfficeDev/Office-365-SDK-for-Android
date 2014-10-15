/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.files.services;

/**
 * The type IdentitySet.
*/
public class IdentitySet {

	private ApplicationInfo application;

	 /**
     * Gets the application.		
     *
     * @return the application
     */
	public ApplicationInfo getapplication() {
		 return application; 
	}

	 /**
     * Sets the application.		
     *
     * @param value the value
     */
	public void setapplication(ApplicationInfo value) { 
		application = value; 
	}

	private UserInfo user;

	 /**
     * Gets the user.		
     *
     * @return the user
     */
	public UserInfo getuser() {
		 return user; 
	}

	 /**
     * Sets the user.		
     *
     * @param value the value
     */
	public void setuser(UserInfo value) { 
		user = value; 
	}
}