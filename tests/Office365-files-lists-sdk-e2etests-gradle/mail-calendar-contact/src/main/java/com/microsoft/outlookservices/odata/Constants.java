/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

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
}
