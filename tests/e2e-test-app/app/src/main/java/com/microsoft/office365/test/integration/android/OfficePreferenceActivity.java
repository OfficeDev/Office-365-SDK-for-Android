/*
Copyright (c) Microsoft Open Technologies, Inc.
All Rights Reserved
Apache 2.0 License
 
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
     http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 
See the Apache Version 2.0 License for specific language governing permissions and limitations under the License.
 */
package com.microsoft.office365.test.integration.android;

import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Toast;

import com.microsoft.office365.test.integration.R;

/**
 * A {@link PreferenceActivity} that presents a set of application settings.
 */
public class OfficePreferenceActivity extends PreferenceActivity {
	@SuppressWarnings("deprecation")
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.pref_general);
		//addPreferencesFromResource(R.xml.aad_settings);
		
		Preference myPref = (Preference) findPreference("prefEraseSettings");
		myPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			public boolean onPreferenceClick(Preference preference) {

				Context context = preference.getContext();

				if (context != null) {
					CookieSyncManager syncManager = CookieSyncManager.createInstance(context);
					if (syncManager != null) {
						CookieManager cookieManager = CookieManager.getInstance();
						cookieManager.removeAllCookie();
						Toast.makeText(context, "Cookies cleared!", Toast.LENGTH_SHORT).show();
					}
				}
				return true;
			}
		});
//		
//		Preference myPref = (Preference) findPreference("prefEraseSettings");
//
//		myPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
//			public boolean onPreferenceClick(Preference preference) {
//
//				Context mAuthContext = preference.getContext();
//
//				if (mAuthContext != null) {
//					CookieSyncManager syncManager = CookieSyncManager.createInstance(mAuthContext);
//					if (syncManager != null) {
//						CookieManager cookieManager = CookieManager.getInstance();
//						cookieManager.removeAllCookie();
//						Toast.makeText(mAuthContext, "Cookies cleared!", Toast.LENGTH_SHORT).show();
//					}
//				}
//				return true;
//			}
//		});
	}

	/** {@inheritDoc} */
	@Override
	public boolean onIsMultiPane() {
		return false;
	}
}
