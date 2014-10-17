/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.odata.impl.http;

import com.microsoft.services.odata.interfaces.HttpVerb;
import com.microsoft.services.odata.interfaces.Request;

import java.util.HashMap;
import java.util.Map;


/**
 * Represents an HTTP Request
 */
public class RequestImpl implements Request {

	private HttpVerb mVerb = HttpVerb.GET;

	private byte[] mContent = null;

	private HashMap<String, String> mHeaders = new HashMap<String, String>();

	private String mUrl = "";

	/**
	 * Sets the request content
	 */
	public void setContent(byte[] content) {
		mContent = content;
	}

	/**
	 * Returns the request content
	 */
	public byte[] getContent() {
		return mContent;
	}

	/**
	 * Returns the request headers
	 */
	public Map<String, String> getHeaders() {
		HashMap<String, String> copy = new HashMap<String, String>();
		copy.putAll(mHeaders);

		return copy;
	}

	/**
	 * Sets the request headers
	 */
	public void setHeaders(Map<String, String> headers) {
		mHeaders = new HashMap<String, String>();

		if (headers != null) {
			mHeaders.putAll(headers);
		}
	}

	/**
	 * Adds a header to the request
	 * @param name The header name
	 * @param value The header value
	 */
	public void addHeader(String name, String value) {
		mHeaders.put(name, value);
	}

	/**
	 * Removes a header
	 * @param name The header name
	 */
	public void removeHeader(String name) {
		mHeaders.remove(name);
	}

	/**
	 * Sets the request HTTP verb
	 */
	public void setVerb(HttpVerb httpVerb) {
		mVerb = httpVerb;
	}

	/**
	 * Returns the request HTTP verb
	 */
	public HttpVerb getVerb() {
		return mVerb;
	}

	/**
	 * Sets the request URL
	 */
	public void setUrl(String url) {
		mUrl = url;
	}

	/**
	 * Returns the request URL
	 */
	public String getUrl() {
		return mUrl;
	}
}
