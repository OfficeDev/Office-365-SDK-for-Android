/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

/**
 * The type Recipient.
*/
public class Recipient {

	private EmailAddress emailAddress;

	 /**
     * Gets the email address.		
     *
     * @return the email address
     */
	public EmailAddress getEmailAddress() {
		 return emailAddress; 
	}

	 /**
     * Sets the email address.		
     *
     * @param value the value
     */
	public void setEmailAddress(EmailAddress value) { 
		emailAddress = value; 
	}
}