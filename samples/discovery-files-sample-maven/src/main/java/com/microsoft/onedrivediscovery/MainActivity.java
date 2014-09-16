/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.onedrivediscovery;

import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.onedrivediscovery.R;
import com.microsoft.office365.Credentials;

// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity {

	private AppPreferences mAppPreferences;
	private DiscoveryAPIApplication mApplication;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Authentication.createEncryptionKey(getApplicationContext());
		mApplication = (DiscoveryAPIApplication) getApplication();
		mAppPreferences = (mApplication).getAppPreferences();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		try {
			switch (item.getItemId()) {
			case R.id.menu_clear_credentials:
				ClearCredentials();
				break;
			case R.id.menu_show_my_services:
				if (mApplication.hasConfiguration()) {
					StartServiceListActivity();
				}else{
					startActivity(new Intent(MainActivity.this,
							AppPreferencesActivity.class));
				}
				break;
			case R.id.menu_preferences:
				startActivity(new Intent(MainActivity.this,
						AppPreferencesActivity.class));
				break;
			default:
				return super.onOptionsItemSelected(item);
			}

		} catch (Throwable t) {
			Log.e("Asset", t.getMessage());
		}
		return true;
	}

	private void ClearCredentials() {
		CookieSyncManager syncManager = CookieSyncManager
				.createInstance(getApplicationContext());
		;
		if (syncManager != null) {
			CookieManager cookieManager = CookieManager.getInstance();
			cookieManager.removeAllCookie();
			CookieSyncManager.getInstance().sync();
			Authentication.ResetToken(this);
		}
	}

	void StartServiceListActivity() {

		ListenableFuture<Map<String, Credentials>> future = Authentication
				.authenticate(this, Constants.DISCOVERY_RESOURCE_ID, mAppPreferences);

		Futures.addCallback(future,
				new FutureCallback<Map<String, Credentials>>() {
					@Override
					public void onFailure(Throwable t) {
						Log.e("Asset", t.getMessage());
					}

					@Override
					public void onSuccess(Map<String, Credentials> credentials) {

						startActivity(new Intent(MainActivity.this,
								ServiceListActivity.class));
					}
				});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		Authentication.context.onActivityResult(requestCode, resultCode, data);
	}
}
