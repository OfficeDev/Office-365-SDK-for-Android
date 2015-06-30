/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.sharepoint;

import java.util.Locale;

import android.os.Build;

import com.microsoft.services.sharepoint.http.FroyoHttpConnection;
import com.microsoft.services.sharepoint.http.HttpConnection;
import com.microsoft.services.sharepoint.http.JavaHttpConnection;


/**
 * Platform specific classes and operations
 */
public class Platform {
	static boolean mPlatformVerified = false;
	static boolean mIsAndroid = false;


    /**
     * Create http connection.
     *
     * @return the http connection
     */
    public static HttpConnection createHttpConnection() {
		if (isAndroid() && Build.VERSION.SDK_INT <= Build.VERSION_CODES.FROYO) {
			return new FroyoHttpConnection();
		} else {
			return new JavaHttpConnection();
		}
	}

	/**
	 * Indicates if the current platform is Android
	 */
	public static boolean isAndroid() {

		if (!mPlatformVerified) {
			String runtime = System.getProperty("java.runtime.name").toLowerCase(Locale.getDefault());

            mIsAndroid = runtime.contains("android");
			mPlatformVerified = true;
		}

		return mIsAndroid;
	}

	/**
	 * Generates the User-Agent
	 */
	public static String getUserAgent() {
		String osName;

		if (isAndroid()) {
			osName = "Android API" + Build.VERSION.SDK_INT;
		} else {
			osName = System.getProperty("os.name").toLowerCase(Locale.getDefault());
		}

        return String.format("Office (lang=Java; os=%s; version=1.0)", osName);
	}
}
