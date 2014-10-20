/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type Password Profile.
*/
public class PasswordProfile {

	private String password;

	/**
	* Gets the password.
	*
	* @return the String
	*/
	public String getpassword() {
		return this.password; 
	}

	/**
	* Sets the password.
	*
	* @param value the String
	*/
	public void setpassword(String value) { 
		password = value; 
	}

	private Boolean forceChangePasswordNextLogin;

	/**
	* Gets the force Change Password Next Login.
	*
	* @return the Boolean
	*/
	public Boolean getforceChangePasswordNextLogin() {
		return this.forceChangePasswordNextLogin; 
	}

	/**
	* Sets the force Change Password Next Login.
	*
	* @param value the Boolean
	*/
	public void setforceChangePasswordNextLogin(Boolean value) { 
		forceChangePasswordNextLogin = value; 
	}
}