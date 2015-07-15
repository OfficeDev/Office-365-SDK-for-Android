package com.microsoft.samples.onenotesample;


import android.content.Context;
import android.app.Activity;
import android.util.Log;

import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.services.msa.LiveAuthClient;
import com.microsoft.services.msa.LiveAuthException;
import com.microsoft.services.msa.LiveAuthListener;
import com.microsoft.services.msa.LiveConnectSession;
import com.microsoft.services.msa.LiveStatus;
import com.microsoft.services.orc.http.Credentials;
import com.microsoft.services.orc.http.impl.OAuthCredentials;
import com.microsoft.services.orc.log.LogLevel;
import com.microsoft.services.orc.resolvers.DefaultDependencyResolver;

import java.util.concurrent.ExecutionException;

/**
 * The type LiveSDK dependency resolver.
 */
public class MSAAuthDependencyResolver extends DefaultDependencyResolver {

    private static final String TAG = "MSAAuthDepResolver";

    private LiveAuthClient liveAuthClient;
    private Context mContext;

    /**
     * Instantiates a new dependency resolver.
     *
     * @param theAuthClient the context
     */
    public MSAAuthDependencyResolver(LiveAuthClient theAuthClient) {
        super("");
        this.liveAuthClient = theAuthClient;
    }

    public SettableFuture<Boolean> interactiveInitialize(final Activity contextActivity) throws ExecutionException, InterruptedException {
        final SettableFuture<Boolean> signal = SettableFuture.create();

        this.getLogger().log(
                "Initializing LiveAuthDependencyResolver. If cached refresh token is available it will be used.", LogLevel.INFO);

        contextActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                liveAuthClient.login(contextActivity, new LiveAuthListener() {
                    @Override
                    public void onAuthComplete(LiveStatus status, LiveConnectSession session, Object userState) {
                        if (status == LiveStatus.CONNECTED) {
                            MSAAuthDependencyResolver.this.getLogger().log(
                                    "Successfully refreshed tokens with refresh token.", LogLevel.INFO);
                            signal.set(true);
                        } else {
                            // We shouldn't get here right?
                            // Should be in onAuthError
                        }
                    }

                    @Override
                    public void onAuthError(LiveAuthException exception, Object userState) {
                        signal.setException(exception);
                    }
                });
            }
        });

        return signal;

    }

    @Override
    public Credentials getCredentials() {
        final SettableFuture<Credentials> credentialsFuture = SettableFuture.create();

        liveAuthClient.loginSilent(new LiveAuthListener() {
            @Override
            public void onAuthError(LiveAuthException exception, Object userState) {
                credentialsFuture.setException(exception);
            }

            @Override
            public void onAuthComplete(LiveStatus status, LiveConnectSession session, Object userState) {
                if (status == LiveStatus.CONNECTED) {
                    OAuthCredentials credentials = new OAuthCredentials(session.getAccessToken());
                    credentialsFuture.set(credentials);
                } else {
                    credentialsFuture.setException(new LiveAuthException("Couldn't initialize LiveAuthClient, perform UI Login."));
                }
            }
        });


        try {
            return credentialsFuture.get();
        } catch (LiveAuthException e) {
            throw e;
        } catch (Throwable t) {
            Log.e(TAG, t.getMessage());
            throw new RuntimeException(t);
        }
    }

    public void logout() {
        final SettableFuture<Boolean> logoutFuture = SettableFuture.create();

        liveAuthClient.logout(new LiveAuthListener() {
            @Override
            public void onAuthComplete(LiveStatus status, LiveConnectSession session, Object userState) {
                logoutFuture.set(true);
            }

            @Override
            public void onAuthError(LiveAuthException exception, Object userState) {
                logoutFuture.setException(exception);
            }
        });

        try {
            logoutFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
