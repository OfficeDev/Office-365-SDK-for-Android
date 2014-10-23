/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.simple_exchange_sample;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationResult;
import com.microsoft.aad.adal.AuthenticationSettings;
import com.microsoft.aad.adal.UserInfo;
import com.microsoft.services.odata.impl.DefaultDependencyResolver;
import com.microsoft.services.odata.interfaces.Credentials;
import com.microsoft.services.odata.interfaces.CredentialsFactory;
import com.microsoft.services.odata.interfaces.Request;

import java.util.Random;


public class Authentication {

    public static AuthenticationContext context = null;
    private static String mLoggedInUser;

    public static SettableFuture<Void> authenticate(final Activity rootActivity, final DefaultDependencyResolver resolver) {

        final SettableFuture<Void> result = SettableFuture.create();

        getAuthenticationContext(rootActivity).acquireToken(rootActivity, Constants.RESOURCE_ID,
                Constants.CLIENT_ID, Constants.REDIRECT_URL, "",
                new AuthenticationCallback<AuthenticationResult>() {

                    @Override
                    public void onSuccess(final AuthenticationResult authenticationResult) {

                        if (authenticationResult != null && !TextUtils.isEmpty(authenticationResult.getAccessToken())) {

                            resolver.setCredentialsFactory(new CredentialsFactory() {
                                @Override
                                public Credentials getCredentials() {
                                    return new Credentials() {
                                        @Override
                                        public void prepareRequest(Request request) {
                                            request.addHeader("Authorization", "Bearer " + authenticationResult.getAccessToken());
                                        }
                                    };
                                }
                            });

                            storeUserId(rootActivity, authenticationResult);
                            result.set(null);
                        }
                    }

                    private void storeUserId(final Activity rootActivity,
                                             final AuthenticationResult authenticationResult) {

                        UserInfo ui = authenticationResult.getUserInfo();
                        SharedPreferences sharedPref = rootActivity.getPreferences(Context.MODE_PRIVATE);

                        if (ui != null) {
                            mLoggedInUser = ui.getUserId();
                            Editor editor = sharedPref.edit();
                            editor.putString("UserId", mLoggedInUser);
                            editor.commit();
                        } else {
                            mLoggedInUser = sharedPref.getString("UserId", "");
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
            Log.e("SampleApp", t.toString());
        }
        return context;
    }

    public static void resetToken(Activity activity) {
        getAuthenticationContext(activity).getCache().removeAll();
        android.webkit.CookieManager.getInstance().removeAllCookie();
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
}