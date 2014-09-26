/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.infrastructure.http;

import com.infrastructure.HttpConnection;

import java.util.Locale;

/**
 * Platform specific classes and operations
 */
public class Platform {
    static boolean mPlatformVerified = false;
    static boolean mIsAndroid = false;

    /**
     * Creates an adequate HttpConnection for the current platform
     * @return An HttpConnection
     */
    public static HttpConnection createHttpConnection() {

        return new JavaHttpConnection();
    }

    /**
     * Indicates if the current platform is Android
     */
    public static boolean isAndroid() {

        if (!mPlatformVerified) {
            String runtime = System.getProperty("java.runtime.name").toLowerCase(Locale.getDefault());

            if (runtime.contains("android")) {
                mIsAndroid = true;
            } else {
                mIsAndroid = false;
            }
            mPlatformVerified = true;
        }

        return mIsAndroid;
    }

    /**
     * Generates the User-Agent
     */
    public static String getUserAgent() {
        String osName;

        //TODO:Android specific code. Extract/Inject
        //if (isAndroid()) {
            //osName = "Android API" + Build.VERSION.SDK_INT;
        //} else {
            osName = System.getProperty("os.name").toLowerCase(Locale.getDefault());
        //}
        String userAgent = String.format("Office (lang=Java; os=%s; version=1.0)", osName);

        return userAgent;
    }
}
