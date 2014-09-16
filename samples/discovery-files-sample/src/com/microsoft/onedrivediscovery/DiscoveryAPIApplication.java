/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.onedrivediscovery;

import android.app.Application;
import android.preference.PreferenceManager;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Toast;

import com.microsoft.office365.Credentials;
import com.microsoft.office365.LogLevel;
import com.microsoft.office365.Logger;
import com.microsoft.office365.OfficeClient;
import com.microsoft.office365.files.FileClient;

// TODO: Auto-generated Javadoc
/**
 * The Class AssetApplication.
 */
public class DiscoveryAPIApplication extends Application {
	private AppPreferences mPreferences;

	/*
	 * (non-Javadoc)
	 * 
	 * /* (non-Javadoc)
	 * 
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {

		super.onCreate();
		mPreferences = new AppPreferences(
				PreferenceManager.getDefaultSharedPreferences(this));
	}

	public AppPreferences getAppPreferences() {
		return mPreferences;
	}

	public boolean hasConfiguration() {

		if (isNullOrEmpty(mPreferences.getClientId()))
			return false;

		if (isNullOrEmpty(mPreferences.getRedirectUrl()))
			return false;

		return true;
	}

	private boolean isNullOrEmpty(String value) {
		return value == null || value.length() == 0;
	}

	/**
	 * Handle error.
	 * 
	 * @param throwable
	 *            the throwable
	 */
	public void handleError(Throwable throwable) {
		Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_LONG).show();
		Log.e("Asset", throwable.toString());
	}

	/**
	 * Clear preferences.
	 */
	public void clearPreferences() {
		// mPreferences.clear();
		CookieSyncManager syncManager = CookieSyncManager.createInstance(this);
		if (syncManager != null) {
			CookieManager cookieManager = CookieManager.getInstance();
			cookieManager.removeAllCookie();
		}
	}

	/**
	 * Gets the current list client.
	 * 
	 * @return the current list client
	 */
	public FileClient getCurrentFileClient(String resourceId, String endpoint) {
		Credentials credentials = Authentication.getCredentials(resourceId);

		return new FileClient(endpoint, "/", credentials, new Logger() {

			@Override
			public void log(String message, LogLevel level) {
				Log.d("Asset", message);
			}
		});
	}

	public OfficeClient getOfficeClient(String resourceId) {
		return new OfficeClient(Authentication.getCredentials(resourceId));
	}
}
