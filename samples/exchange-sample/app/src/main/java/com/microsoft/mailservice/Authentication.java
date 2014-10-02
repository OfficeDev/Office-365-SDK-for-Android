/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.mailservice;

import java.util.Random;

import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationResult;
import com.microsoft.aad.adal.AuthenticationSettings;
import com.microsoft.aad.adal.UserInfo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Base64;


public class Authentication {

	public static AuthenticationContext context = null;
	private static String mLogedInUser;

	public static SettableFuture<Void> authenticate(final Activity rootActivity) {

		final SettableFuture<Void> result = SettableFuture.create();
		final ExchangeAPIApplication application = (ExchangeAPIApplication) rootActivity.getApplication();
		final AppPreferences preferences = application.getAppPreferences();

		getAuthenticationContext(rootActivity).acquireToken(rootActivity, Constants.RESOURCE_ID,
				preferences.getClientId(), preferences.getRedirectUrl(), "",
				new AuthenticationCallback<AuthenticationResult>() {

					@Override
					public void onSuccess(final AuthenticationResult authenticationResult) {

						if (authenticationResult != null && !TextUtils.isEmpty(authenticationResult.getAccessToken())) {



							storeUserId(rootActivity, authenticationResult);
							result.set(null);
						}
					}

					private void storeUserId(final Activity rootActivity,
							final AuthenticationResult authenticationResult) {

						UserInfo ui = authenticationResult.getUserInfo();
						SharedPreferences sharedPref = rootActivity.getPreferences(Context.MODE_PRIVATE);

						if (ui != null) {
							mLogedInUser = ui.getUserId();
							Editor editor = sharedPref.edit();
							editor.putString("UserId", mLogedInUser);
							editor.commit();
						} else {
							mLogedInUser = sharedPref.getString("UserId", "");
						}
					}

					@Override
					public void onError(Exception exc) {
						result.setException(exc);
					}
				});
		return result;
	}

	/**
	 * Gets AuthenticationContext for AAD.
	 * 
	 * @return authenticationContext, if successful
	 */
	public static AuthenticationContext getAuthenticationContext(Activity activity) {

		try {
			context = new AuthenticationContext(activity, Constants.AUTHORITY_URL, false);
		} catch (Throwable t) {
			ErrorHandler.handleError(t, activity);
		}
		return context;
	}

	public static void resetToken(Activity activity) {
		getAuthenticationContext(activity).getCache().removeAll();
	}

	static void createEncryptionKey(Context applicationContext) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);

		if (!preferences.contains(Constants.ENCRYPTION_KEY)) {
			Random r = new Random();
			byte[] bytes = new byte[32];
			r.nextBytes(bytes);

			String key = Base64.encodeToString(bytes, Base64.DEFAULT);

			Editor editor = preferences.edit();
			editor.putString(Constants.ENCRYPTION_KEY, key);
			editor.commit();
		}

		AuthenticationSettings.INSTANCE.setSecretKey(getEncryptionKey(applicationContext));
	}

	static byte[] getEncryptionKey(Context applicationContext) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
		String key = preferences.getString(Constants.ENCRYPTION_KEY, null);

		if (key != null) {
			byte[] bytes = Base64.decode(key, Base64.DEFAULT);
			return bytes;
		} else {
			return new byte[32];
		}
	}

	public static String getLogedUser() {
		return mLogedInUser;
	}

}