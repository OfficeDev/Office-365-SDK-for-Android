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
import com.microsoft.services.odata.impl.DefaultDependencyResolver;

import java.util.Random;


public class Authentication {

    public static AuthenticationContext context = null;

    public static SettableFuture<Void> acquireToken(final Activity rootActivity) {

        final SettableFuture<Void> result = SettableFuture.create();

        getAuthenticationContext(rootActivity).acquireToken(
                rootActivity,
                ServiceConstants.RESOURCE_ID,
                ServiceConstants.CLIENT_ID,
                ServiceConstants.REDIRECT_URL, "",
                new AuthenticationCallback<AuthenticationResult>() {

                    @Override
                    public void onSuccess(final AuthenticationResult authenticationResult) {

                        if (authenticationResult != null && !TextUtils.isEmpty(authenticationResult.getAccessToken())) {
                            DefaultDependencyResolver r = new DefaultDependencyResolver(authenticationResult.getAccessToken());
                            Controller.getInstance().setDependencyResolver(r);
                            Controller.getInstance().setAuthenticationResult( rootActivity, authenticationResult );
                            result.set(null);
                        }
                    }

                    @Override
                    public void onError(Exception t) {
                        result.setException(t);
                    }
                });

        return result;
    }


    /**
     * acquires the authentication token using the refresh token
     */
    public static void acquireTokenByRefreshToken(final Activity rootActivity) {
        Authentication.getAuthenticationContext(rootActivity).acquireTokenByRefreshToken(
                Controller.getInstance().getAuthenticationResult().getRefreshToken(),
                ServiceConstants.CLIENT_ID,
                ServiceConstants.RESOURCE_ID,
                new AuthenticationCallback<AuthenticationResult>() {

                    @Override
                    public void onSuccess(final AuthenticationResult authenticationResult) {

                        if (authenticationResult != null && !TextUtils.isEmpty(authenticationResult.getAccessToken())) {
                            DefaultDependencyResolver r = new DefaultDependencyResolver(authenticationResult.getAccessToken());
                            Controller.getInstance().setDependencyResolver(r);
                            Controller.getInstance().setAuthenticationResult(rootActivity, authenticationResult);
                        }
                    }

                    @Override
                    public void onError(Exception t) {
                        Authentication.resetToken(rootActivity);
                        Controller.getInstance().handleError(rootActivity, t.getMessage());
                    }
                }
        );
    }

    /**
     * Gets AuthenticationContext for AAD.
     *
     * @return authenticationContext, if successful
     */
    public static AuthenticationContext getAuthenticationContext(Activity activity) {

        try {
            context = new AuthenticationContext(activity, ServiceConstants.AUTHORITY_URL, false);
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

        if (!preferences.contains(ServiceConstants.ENCRYPTION_KEY)) {
            Random r = new Random();
            byte[] bytes = new byte[32];
            r.nextBytes(bytes);

            String key = Base64.encodeToString(bytes, Base64.DEFAULT);

            Editor editor = preferences.edit();
            editor.putString(ServiceConstants.ENCRYPTION_KEY, key);
            editor.commit();
        }

        AuthenticationSettings.INSTANCE.setSecretKey(getEncryptionKey(applicationContext));
    }

    static byte[] getEncryptionKey(Context applicationContext) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        String key = preferences.getString(ServiceConstants.ENCRYPTION_KEY, null);

        if (key != null) {
            byte[] bytes = Base64.decode(key, Base64.DEFAULT);
            return bytes;
        } else {
            return new byte[32];
        }
    }
}