/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.simple_exchange_sample;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.Toast;

import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationResult;
import com.microsoft.services.odata.impl.DefaultDependencyResolver;
import com.microsoft.services.odata.interfaces.Credentials;
import com.microsoft.services.odata.interfaces.CredentialsFactory;
import com.microsoft.services.odata.interfaces.Request;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * holds the context of execution across activities
 */
public class Controller {

    private DefaultDependencyResolver dependencyResolver;
    private AuthenticationResult authenticationResult;
    private Activity rootActivity;
    private Timer timer;

    private static Controller INSTANCE;

    ExecutorService executor;

    private Controller() {
        this.executor = Executors.newFixedThreadPool(2);
    }

    public static synchronized Controller getInstance() {
        Controller controller = null;
        if (INSTANCE == null) {
            INSTANCE = new Controller();
        }

        return INSTANCE;
    }

    public void setDependencyResolver(DefaultDependencyResolver resolver) {
        this.dependencyResolver = resolver;
    }

    public DefaultDependencyResolver getDependencyResolver() {
        return this.dependencyResolver;
    }

    public <V> void postASyncTask(Callable<V> callable) {
        this.executor.submit(callable);
    }

    public AuthenticationResult getAuthenticationResult() {
        return this.authenticationResult;
    }

    public void setAuthenticationResult(Activity activity, final AuthenticationResult authenticationResult) {
        // save root activity and authentication result in order to use the refresh token later
        this.rootActivity = activity;
        this.authenticationResult = authenticationResult;

        // set a timer to refresh the authentication token
        if (this.timer != null) {
            this.timer.cancel();
        }
        this.timer = new Timer();
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                acquireTokenByRefreshToken();
            }
        }, authenticationResult.getExpiresOn());
    }

    /**
     * notifies about the exception on executing the Future
     *
     * @param msg error message to be displayed
     */
    public void handleError(final Activity activity, final String msg) {

        if (msg.contains("Authentication_ExpiredToken")) {
            acquireTokenByRefreshToken();
        }

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void acquireTokenByRefreshToken() {
        Authentication.getAuthenticationContext(this.rootActivity).acquireTokenByRefreshToken(
                this.authenticationResult.getRefreshToken(),
                ServiceConstants.CLIENT_ID,
                ServiceConstants.RESOURCE_ID,
                new AuthenticationCallback<AuthenticationResult>() {

                    @Override
                    public void onSuccess(final AuthenticationResult authenticationResult) {

                        if (authenticationResult != null && !TextUtils.isEmpty(authenticationResult.getAccessToken())) {

                            dependencyResolver.setCredentialsFactory(new CredentialsFactory() {

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

                            setAuthenticationResult(rootActivity, authenticationResult);
                        }
                    }

                    @Override
                    public void onError(Exception t) {
                        Authentication.resetToken(rootActivity);
                        handleError(rootActivity, t.getMessage());
                    }
                }
        );
    }
}

