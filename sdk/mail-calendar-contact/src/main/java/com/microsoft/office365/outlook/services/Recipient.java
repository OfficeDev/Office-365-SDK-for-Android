/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

public class Recipient {

	private EmailAddress emailAddress;

	public EmailAddress getEmailAddress() {
		 return emailAddress; 
	}

	public void setEmailAddress(EmailAddress value) { 
		emailAddress = value; 
	}
}