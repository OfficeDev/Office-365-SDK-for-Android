/*
Copyright (c) Microsoft Open Technologies, Inc.
All Rights Reserved
Apache 2.0 License
 
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
     http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 
See the Apache Version 2.0 License for specific language governing permissions and limitations under the License.
 */
package com.microsoft.office365.test.integration.android;

public class Constants {
	public static final String PREFERENCE_SHAREPOINT_URL = "prefSharepointUrl";
	public static final String PREFERENCE_SITE_URL = "prefSiteRelativeUrl";
	public static final String PREFERENCE_LIST_NAME = "prefListName";
	public static final String PREFERENCE_DOCUMENT_LIST_NAME = "prefDocumentListName";
	public static final String PREFERENCE_LOG_POST_URL = "pref_log_post_url";
	public static final String TAG = "Sharepoint-SDK Integration-Test";
	public static final String PREFERENCE_AUTHENTICATION_METHOD = "prefAuthenticationMethod";
//	public static final String AUTHORITY_URL = "https://login.windows.net/common";
	public static final String PREFERENCE_AAD_CLIENT_ID = "prefAADClientId";
	public static final String PREFERENCE_AAD_Redirect_URL ="prefAADRedirectUrl";
//	public static final String ODATA_ENDPOINT = "ews/odata";
//	public static final String RESOURCE_ID = "https://outlook.office365.com/";
//	public static final int TOP_VALUE = 10;
	
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
