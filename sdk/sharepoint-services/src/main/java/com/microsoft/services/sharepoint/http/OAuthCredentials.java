/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.sharepoint.http;


import com.microsoft.services.sharepoint.Credentials;

public class OAuthCredentials implements Credentials {

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

	@Override
	public void prepareRequest(Request request) {
		request.addHeader("Authorization", "Bearer " + getToken());
	}
	
}
