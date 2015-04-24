/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.odata;

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
     * The constant SDK_VERSION.
     */
    public static final String SDK_VERSION = "0.12.1";

    /**
     * The constant USER_AGENT_HEADER.
     */
    public static final String USER_AGENT_HEADER = "User-Agent";

    /**
     * The constant TELEMETRY_HEADER.
     */
    public static final String TELEMETRY_HEADER = "X-ClientService-ClientTag";

    /**
     * The constant CONTENT_TYPE_HEADER.
     */
    public static final String CONTENT_TYPE_HEADER = "Content-Type";

    /**
     * The constant JSON_CONTENT_TYPE.
     */
    public static final String JSON_CONTENT_TYPE = "application/json";

    /**
     * The constant MULTIPART_BOUNDARY_NAME.
     */
    public static final String MULTIPART_BOUNDARY_NAME = "MultiPartBoundary";

    /**
     * The constant HTTP_NEW_LINE.
     */
    public static final String HTTP_NEW_LINE = "\r\n";

    /**
     * The constant MULTIPART_CONTENT_TYPE.
     */
    public static final String MULTIPART_CONTENT_TYPE = "multipart/form-data; boundary=" + MULTIPART_BOUNDARY_NAME;

    /**
     * The constant ACCEPT_HEADER.
     */
    public static final String ACCEPT_HEADER = "Accept";

    /**
     * The constant IF_MATCH_HEADER.
     */
    public static final String IF_MATCH_HEADER = "If-Match";

    /**
     * The constant ODATA_VERSION_HEADER.
     */
    public static final String ODATA_VERSION_HEADER = "OData-Version";

    /**
     * The constant ODATA_VERSION.
     */
    public static final String ODATA_VERSION = "4.0";

    /**
     * The constant ODATA_MAXVERSION_HEADER.
     */
    public static final String ODATA_MAXVERSION_HEADER = "OData-MaxVersion";

    /**
     * The constant ODATA_MAXVERSION.
     */
    public static final String ODATA_MAXVERSION = "4.0";

    /**
     * The constant ODATA_DATA_TYPE_JSON_PROPERTY
     */
    public static final String ODATA_TYPE_JSON_PROPERTY = "@odata.type";

    /**
     * The constant ODATA_TYPE_PROPERTY_NAME
     */
    public static final String ODATA_TYPE_PROPERTY_NAME = "$$__ODataType";

    /**
     * The constant PROPERTY_NAME_RESERVED_PREFIX
     */
    public static final String PROPERTY_NAME_RESERVED_PREFIX = "$$__$$";

    /**
     * The constant ODATA_ENTITY_BASE_CLASS_NAME
     */
    public static final String ODATA_ENTITY_BASE_CLASS_NAME = "ODataBaseEntity";
}
