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
import com.microsoft.services.odata.impl.ADALDependencyResolver;
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
                            ADALDependencyResolver adalDependencyResolver= new ADALDependencyResolver(getAuthenticationContext(rootActivity),
                                                                    ServiceConstants.RESOURCE_ID, ServiceConstants.CLIENT_ID);


                            Controller.getInstance().setDependencyResolver(adalDependencyResolver);
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
}