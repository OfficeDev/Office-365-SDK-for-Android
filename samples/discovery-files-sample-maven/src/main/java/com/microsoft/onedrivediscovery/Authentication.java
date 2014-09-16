package com.microsoft.onedrivediscovery;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationResult;
import com.microsoft.aad.adal.AuthenticationSettings;
import com.microsoft.office365.Credentials;
import com.microsoft.office365.http.OAuthCredentials;

public class Authentication {

	/** The m credentials. */
	private static Map<String, Credentials> mCredentials = new HashMap<String, Credentials>();

	/**
	 * Gets the credentials.
	 * 
	 * @return the credentials
	 */
	public static Credentials getCredentials(String resourceId) {
		return mCredentials.get(resourceId);
	}

	/**
	 * Sets the credentials.
	 * 
	 * @param credentials
	 *            the new credentials
	 */
	public static void setCredentials(Map<String, Credentials> credentials) {
		mCredentials = credentials;
	}

	/**
	 * Authenticate.
	 * 
	 * @param activity
	 *            the activity
	 * @return the office future
	 */
	public static ListenableFuture<Map<String, Credentials>> authenticate(
			Activity activity, final String resourceId,
			AppPreferences preferences) {
		final SettableFuture<Map<String, Credentials>> result = SettableFuture
				.create();

		getAuthenticationContext(activity).acquireToken(activity, resourceId,
				preferences.getClientId(), preferences.getRedirectUrl(), "",
				new AuthenticationCallback<AuthenticationResult>() {

					@Override
					public void onSuccess(
							AuthenticationResult authenticationResult) {
						// once succeeded we create a credentials instance
						// using
						// the token from ADAL
						mCredentials.put(resourceId, new OAuthCredentials(
								authenticationResult.getAccessToken()));
						result.set(mCredentials);
					}

					@Override
					public void onError(Exception exc) {
						result.setException(exc);
					}
				});
		return result;
	}

	public static AuthenticationContext context = null;

	/**
	 * Gets AuthenticationContext for AAD.
	 * 
	 * @return authenticationContext, if successful
	 */
	public static AuthenticationContext getAuthenticationContext(
			Activity activity) {

		try {
			context = new AuthenticationContext(activity,
					Constants.AUTHORITY_URL, false);
		} catch (Throwable t) {
			Log.e("Asset", t.getMessage());
		}
		return context;
	}

	public static void ResetToken(Activity activity) {
		getAuthenticationContext(activity).getCache().removeAll();

	}

	static void createEncryptionKey(Context applicationContext) {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(applicationContext);

		if (!preferences.contains(Constants.ENCRYPTION_KEY)) {
			// generate a random key
			Random r = new Random();
			byte[] bytes = new byte[32];
			r.nextBytes(bytes);

			String key = Base64.encodeToString(bytes, Base64.DEFAULT);

			Editor editor = preferences.edit();
			editor.putString(Constants.ENCRYPTION_KEY, key);
			editor.commit();
		}

		AuthenticationSettings.INSTANCE
				.setSecretKey(getEncryptionKey(applicationContext));
	}

	static byte[] getEncryptionKey(Context applicationContext) {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(applicationContext);
		String key = preferences.getString(Constants.ENCRYPTION_KEY, null);

		if (key != null) {
			byte[] bytes = Base64.decode(key, Base64.DEFAULT);
			return bytes;
		} else {
			return new byte[32];
		}
	}
}
