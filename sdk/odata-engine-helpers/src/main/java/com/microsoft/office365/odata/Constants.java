/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import java.nio.charset.Charset;

/**
 * Constants used through the framework
 */
public class Constants {
	/**
	 * UTF-8 Encoding name
	 */
	public static final String UTF8_NAME = "UTF-8";
	
	/**
	 * UTF-8 Charset instance
	 */
	public static final Charset UTF8 = Charset.forName(UTF8_NAME);

    /**
     * SDK Version
     */
    public static final String SDK_VERSION = "0.9";

    /**
     * User Agent Header
     */
    public static final String USER_AGENT_HEADER = "User-Agent";

    /**
     * Telemetry Header
     */
    public static final String TELEMETRY_HEADER = "X-ClientService-ClientTag";

    /**
     * Content Type Header
     */
    public static final String CONTENT_TYPE_HEADER = "Content-Type";

    /**
     * Json Content Type
     */
    public static final String JSON_CONTENT_TYPE = "application/json";
}
