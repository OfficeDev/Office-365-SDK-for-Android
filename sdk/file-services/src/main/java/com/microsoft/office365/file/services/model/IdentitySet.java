/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.file.services.model;

public class IdentitySet {

	private ApplicationInfo application;

	public ApplicationInfo getapplication() {
		 return application; 
	}

	public void setapplication(ApplicationInfo value) { 
		application = value; 
	}

	private UserInfo user;

	public UserInfo getuser() {
		 return user; 
	}

	public void setuser(UserInfo value) { 
		user = value; 
	}
}