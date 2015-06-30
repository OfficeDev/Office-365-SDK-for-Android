/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.sharepoint.http;

import com.microsoft.services.sharepoint.Credentials;

import java.util.HashMap;
import java.util.Map;


/**
 * Represents credentials based on cookie values
 */
public class CookieCredentials implements Credentials {

	private Map<String, String> mCookieValues;
	
	/**
	 * Creates a new instance
	 */
	public CookieCredentials() {
		mCookieValues = new HashMap<String, String>();
	}
	
	public CookieCredentials(String cookie) {
		mCookieValues = new HashMap<String, String>();
		
		if (cookie != null) {
			cookie = cookie.trim();
			
			if (!cookie.trim().equals("")) {
				String[] keyValues = cookie.split(";");
				for (int i = 0; i < keyValues.length; i++) {
				    String cookieString = keyValues[i].trim();
					String[] parts = keyValues[i].split("=");
					String cookieName = parts[0].trim();
					String cookieValue = cookieString.substring(cookieName.length() + 1).trim();
					addCookie(cookieName, cookieValue);
				}
			}
		}
	}
	
	/**
	 * Adds a cookie to the credential
	 * @param name The cookie name
	 * @param value The cookie value
	 */
	public void addCookie(String name, String value) {
		mCookieValues.put(name, value);
	}
	
	/**
	 * Removes a cookie from the credential
	 * @param name The cookie name
	 */
	public void removeCookie(String name) {
		mCookieValues.remove(name);
	}
	
	@Override
	public void prepareRequest(Request request) {
		if (mCookieValues.size() > 0) {
			StringBuilder currentCookies = new StringBuilder();
			if (request.getHeaders().containsKey("Cookie")) {
				currentCookies.append(request.getHeaders().get("Cookie"));

				currentCookies.append("; ");
			}
			
			currentCookies.append(this.toString());
			
			request.removeHeader("Cookie");
			request.addHeader("Cookie", currentCookies.toString());
		}		
	}
	
	@Override
	public String toString() {
		if (mCookieValues.size() > 0) {
			StringBuilder sb = new StringBuilder();
						
			for (String key : mCookieValues.keySet()) {
				sb.append(key);
				sb.append("=");
				sb.append(mCookieValues.get(key));
				sb.append(";");
			}
			
			return sb.toString();
		} else {
			return "";
		}
	}
}
