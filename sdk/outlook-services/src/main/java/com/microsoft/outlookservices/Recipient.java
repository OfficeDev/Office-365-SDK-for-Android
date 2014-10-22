/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type Recipient.
*/
public class Recipient {

	private EmailAddress EmailAddress;

	/**
	* Gets the Email Address.
	*
	* @return the EmailAddress
	*/
	public EmailAddress getEmailAddress() {
		return this.EmailAddress; 
	}

	/**
	* Sets the Email Address.
	*
	* @param value the EmailAddress
	*/
	public void setEmailAddress(EmailAddress value) { 
		EmailAddress = value; 
	}
}