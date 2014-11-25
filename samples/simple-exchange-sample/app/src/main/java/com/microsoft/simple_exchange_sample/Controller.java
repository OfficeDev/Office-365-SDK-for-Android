/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.simple_exchange_sample;

import android.app.Activity;
import android.widget.Toast;

import com.microsoft.aad.adal.AuthenticationResult;
import com.microsoft.services.odata.impl.DefaultDependencyResolver;

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
    private Timer tokenRefreshTimer;

    private static Controller INSTANCE;

    ExecutorService executor;

    private Controller() {
        this.executor = Executors.newFixedThreadPool(2);
    }

    /**
     * Creates a singleton instance of the Controller
     * @return an instance of Controller class
     */
    public static synchronized Controller getInstance() {
        Controller controller = null;
        if (INSTANCE == null) {
            INSTANCE = new Controller();
        }

        return INSTANCE;
    }

    /**
     * sets the dependency resolver
     * @param resolver the resolver object
     */
    public void setDependencyResolver(DefaultDependencyResolver resolver) {
        this.dependencyResolver = resolver;
    }

    /**
     * gets the dependency resolver object
     * @return the instance of the resolver
     */
    public DefaultDependencyResolver getDependencyResolver() {
        return this.dependencyResolver;
    }

    /**
     * post an async task to the executor thread pool
     * @param callable the task to be executed
     * @param <V> the type of the task to be executed
     */
    public <V> void postASyncTask(Callable<V> callable) {
        this.executor.submit(callable);
    }

    /**
     * set the authentication result for the activity
     * @param activity the activity
     * @param authenticationResult the authentication result
     */
    public void setAuthenticationResult(Activity activity, final AuthenticationResult authenticationResult) {
        // save root activity and authentication result in order to use the refresh token later
        this.rootActivity = activity;
        this.authenticationResult = authenticationResult;

        // set a timer to refresh the authentication token
        if (this.tokenRefreshTimer != null) {
            this.tokenRefreshTimer.cancel();
        }
        this.tokenRefreshTimer = new Timer();
        this.tokenRefreshTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Authentication.acquireTokenByRefreshToken(rootActivity);
            }
        }, authenticationResult.getExpiresOn());
    }

    /**
     * gets the authentication result for the authentication process
     * @return an instance of the authentication result object
     */
    public AuthenticationResult getAuthenticationResult() {
        return this.authenticationResult;
    }

    /**
     * notifies about the exception on executing the Future
     *
     * @param msg error message to be displayed
     */
    public void handleError(final Activity activity, final String msg) {

        if (msg.contains("Authentication_ExpiredToken")) {
            Authentication.acquireTokenByRefreshToken(this.rootActivity);
        }

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}

