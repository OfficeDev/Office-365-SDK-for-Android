/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.oauth;

public class OAuthCredentials {

	private String mToken;
	
	public OAuthCredentials(String oAuthToken) {
		mToken = oAuthToken;
	}
	
	/**
	 * Returns the OAuth Token
	 */
	public String getToken() {
		return mToken;
	}

}
