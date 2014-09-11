/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.mailservice;

public class Constants {

	public static final String AUTHORITY_URL = "https://login.windows.net/common";
	//public static final String RESOURCE_ID = "https://sdfpilot.outlook.com/";
	public static final String RESOURCE_ID = "https://outlook.office365.com/";
	public static final String ODATA_ENDPOINT = "ews/odata";
	public static final String ENCRYPTION_KEY = "EncryptionKey";
	public static final String[] MAIL_FIELDS_TO_SELECT = new String[] { "Id", "Subject", "Sender", "ToRecipients",
			"CcRecipients", "DateTimeSent", "ToRecipients", "LastModifiedTime", "ParentFolderId" };

	public static final String[] CONTACT_FIELDS_TO_SELECT = new String[] { "Id", "DisplayName", "Surname",
			"EmailAddress1", "JobTitle", "Department", "CompanyName", "MiddleName", "GivenName", "NickName" };

	public static final int TOP_VALUE = 40;

}