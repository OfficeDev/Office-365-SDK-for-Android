package com.microsoft.office365.test.integration.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationResult;
import com.microsoft.aad.adal.PromptBehavior;
import com.microsoft.directoryservices.odata.DirectoryClient;
import com.microsoft.discoveryservices.odata.DiscoveryClient;
import com.microsoft.listservices.SharepointListsClient;
import com.microsoft.office365.test.integration.TestPlatformContext;
import com.microsoft.office365.test.integration.framework.OAuthCredentials;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestExecutionCallback;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.outlookservices.odata.OutlookClient;
import com.microsoft.services.odata.impl.DefaultDependencyResolver;
import com.microsoft.services.odata.impl.http.CredentialsFactoryImpl;
import com.microsoft.services.odata.interfaces.DependencyResolver;
import com.microsoft.services.odata.interfaces.LogLevel;
import com.microsoft.sharepointservices.odata.SharePointClient;

public class AndroidTestPlatformContext implements TestPlatformContext {

    private static Activity mActivity;

    public AndroidTestPlatformContext(Activity activity) {
        mActivity = activity;
    }

    @Override
    public String getExchangeServerUrl() {
        return PreferenceManager.getDefaultSharedPreferences(mActivity).getString(Constants.PREFERENCE_EXCHANGE_RESOURCE_URL,
                "");
    }

    @Override
    public String getFileServerUrl() {
        return PreferenceManager.getDefaultSharedPreferences(mActivity).getString(Constants.PREFERENCE_FILES_RESOURCE_URL,
                "");
    }

    @Override
    public String getSharepointServerUrl() {
        return PreferenceManager.getDefaultSharedPreferences(mActivity).getString(Constants.PREFERENCE_SHAREPOINT_URL,
                "");
    }

    @Override
    public String getDiscoveryServerUrl(){
        return PreferenceManager.getDefaultSharedPreferences(mActivity).getString(Constants.PREFERENCE_DISCOVERY_RESOURCE_URL,
                "");
    }

    @Override
    public String getDirectoryServerUrl() {
        return PreferenceManager.getDefaultSharedPreferences(mActivity).getString(Constants.PREFERENCE_DIRECTORY_RESOURCE_URL,
                "");
    }

    @Override
    public String getTestListName() {
        return PreferenceManager.getDefaultSharedPreferences(mActivity).getString(Constants.PREFERENCE_LIST_NAME, "");
    }

    @Override
    public String getTestDocumentListName() {
        return PreferenceManager.getDefaultSharedPreferences(mActivity).getString(Constants.PREFERENCE_DOCUMENT_LIST_NAME, "");
    }

    @Override
    public String getSiteRelativeUrl() {
        return PreferenceManager.getDefaultSharedPreferences(mActivity).getString(Constants.PREFERENCE_SITE_URL, "");
    }

    @Override
    public String getClientId() {
        return PreferenceManager.getDefaultSharedPreferences(mActivity).getString(Constants.PREFERENCE_AAD_CLIENT_ID,
                "");
    }

    @Override
    public String getRedirectUrl() {
        return PreferenceManager.getDefaultSharedPreferences(mActivity).getString(
                Constants.PREFERENCE_AAD_Redirect_URL, "");
    }

    @Override
    public String getAuthenticationMethod() {
        return PreferenceManager.getDefaultSharedPreferences(mActivity).getString(
                Constants.PREFERENCE_AUTHENTICATION_METHOD, "");
    }

    @Override
    public String getExchangeEndpointUrl() {
        return PreferenceManager.getDefaultSharedPreferences(mActivity).getString(
                Constants.PREFERENCE_EXCHANGE_ENDPOINT_URL, "");
    }

    @Override
    public String getFilesEndpointUrl() {
        return PreferenceManager.getDefaultSharedPreferences(mActivity).getString(
                Constants.PREFERENCE_FILES_ENDPOINT_URL, "");
    }

    @Override
    public String getDiscoveryEndpointUrl() {
        return PreferenceManager.getDefaultSharedPreferences(mActivity).getString(
                Constants.PREFERENCE_DISCOVERY_ENDPOINT_URL, "");
    }

    @Override
    public String getDirectoryEndpointUrl() {
        return PreferenceManager.getDefaultSharedPreferences(mActivity).getString(
                Constants.PREFERENCE_DIRECTORY_ENDPOINT_URL, "");
    }

    @Override
    public String getTestMail() {
        return PreferenceManager.getDefaultSharedPreferences(mActivity).getString(
                Constants.PREFERENCE_TEST_MAIL, "");
    }

    public static AuthenticationContext context = null;

    public AuthenticationContext getAuthenticationContext() {

        try {
            context = new AuthenticationContext(mActivity, Constants.AUTHORITY_URL, false);
        } catch (Exception e) {
        }

        return context;
    }

    @Override
    public ListenableFuture<Void> showMessage(final String message) {
        final SettableFuture<Void> result = SettableFuture.create();

        mActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);

                builder.setTitle("Message");
                builder.setMessage(message);
                builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.set(null);
                    }
                });

                builder.create().show();
            }
        });

        return result;
    }

    @Override
    public void executeTest(final TestCase testCase, final TestExecutionCallback callback) {
        AsyncTask<Void, Void, TestResult> task = new AsyncTask<Void, Void, TestResult>() {

            @Override
            protected TestResult doInBackground(Void... params) {
                return testCase.executeTest();
            }

            @Override
            protected void onPostExecute(TestResult result) {
                callback.onTestComplete(testCase, result);
            }
        };

        task.execute();
    }

    @Override
    public void sleep(int seconds) throws Exception {
        Thread.sleep(seconds * 1000);
    }

    @Override
    public OutlookClient getMailCalendarContactClient() {
        return  getExchangeEntityContainerClientAAD();
    }

    @Override
    public SharePointClient getFilesClient() {
        return getFilesEntityContainerClientAAD();
    }

    @Override
    public SharepointListsClient getSharePointListClient() {
        return getSharePointListClientAAD();
    }

    @Override
    public DiscoveryClient getDiscoveryClient() { return getDiscoveryClientAAD(); }

    @Override
    public DirectoryClient getDirectoryClient() {
        return getDirectoryClientAAD();
    }

    private DirectoryClient getDirectoryClientAAD() {
        final SettableFuture<DirectoryClient> future = SettableFuture.create();
        final String refreshTokenValue = "directoryRefreshToken";

        try {
            String refreshToken = PreferenceManager.getDefaultSharedPreferences(mActivity).getString(
                    refreshTokenValue, "");

            if (!refreshToken.isEmpty()) {
                getAuthenticationContext().acquireTokenByRefreshToken(refreshToken, getClientId(), getDirectoryServerUrl(),
                        new AuthenticationCallback<AuthenticationResult>() {

                            @Override
                            public void onError(Exception exc) {
                                future.setException(exc);
                            }

                            @Override
                            public void onSuccess(AuthenticationResult result) {
                                UpdateSharedPreferences(refreshTokenValue, result.getRefreshToken());
                                DirectoryClient client = new DirectoryClient(getDirectoryEndpointUrl(), getDependencyResolver(result.getAccessToken()));
                                future.set(client);
                            }
                        });
            } else {
                getAuthenticationContext().acquireToken(
                        mActivity, getDirectoryServerUrl(),
                        getClientId(),getRedirectUrl(), PromptBehavior.Auto,
                        new AuthenticationCallback<AuthenticationResult>() {

                            @Override
                            public void onError(Exception exc) {
                                future.setException(exc);
                            }

                            @Override
                            public void onSuccess(AuthenticationResult result) {

                                UpdateSharedPreferences(refreshTokenValue, result.getRefreshToken());
                                DirectoryClient client = new DirectoryClient(getDirectoryEndpointUrl(), getDependencyResolver(result.getAccessToken()));
                                future.set(client);
                            }
                        });
            }
        } catch (Throwable t) {
            future.setException(t);
        }
        try {
            return future.get();
        } catch (Throwable t) {
            Log.e(Constants.TAG, t.getMessage());
            return null;
        }
    }

    private DiscoveryClient getDiscoveryClientAAD() {
        final SettableFuture<DiscoveryClient> future = SettableFuture.create();
        final String refreshTokenValue="discoveryRefreshToken";
        try {
            String refreshToken = PreferenceManager.getDefaultSharedPreferences(mActivity).getString(
                    refreshTokenValue, "");
            if (!refreshToken.isEmpty()) {
                getAuthenticationContext().acquireTokenByRefreshToken(refreshToken, getClientId(), getDiscoveryServerUrl(),
                        new AuthenticationCallback<AuthenticationResult>() {

                            @Override
                            public void onError(Exception exc) {
                                future.setException(exc);
                            }

                            @Override
                            public void onSuccess(AuthenticationResult result) {
                                UpdateSharedPreferences(refreshTokenValue, result.getRefreshToken());
                                DiscoveryClient client = new DiscoveryClient(getDiscoveryEndpointUrl(), getDependencyResolver(result.getAccessToken()));
                                future.set(client);
                            }
                        });
            } else {
                getAuthenticationContext().acquireToken(
                        mActivity, getDiscoveryServerUrl(),
                        getClientId(),getRedirectUrl(), PromptBehavior.Auto,
                        new AuthenticationCallback<AuthenticationResult>() {

                            @Override
                            public void onError(Exception exc) {
                                future.setException(exc);
                            }

                            @Override
                            public void onSuccess(AuthenticationResult result) {
                                UpdateSharedPreferences(refreshTokenValue, result.getRefreshToken());
                                DiscoveryClient client = new DiscoveryClient(getDiscoveryEndpointUrl(), getDependencyResolver(result.getAccessToken()));
                                future.set(client);
                            }
                        });
            }
        } catch (Throwable t) {
            future.setException(t);
        }
        try {
            return future.get();
        } catch (Throwable t) {
            Log.e(Constants.TAG, t.getMessage());
            return null;
        }
    }

    private SharepointListsClient getSharePointListClientAAD(){
        final SettableFuture<SharepointListsClient> future = SettableFuture.create();
        final String refreshTokenValue = "listsRefreshToken";
        try {
            String refreshToken = PreferenceManager.getDefaultSharedPreferences(mActivity).getString(
                    refreshTokenValue, "");
            if (!refreshToken.isEmpty()) {
                getAuthenticationContext().acquireTokenByRefreshToken(refreshToken, getClientId(), getSharepointServerUrl(),
                        new AuthenticationCallback<AuthenticationResult>() {

                            @Override
                            public void onError(Exception exc) {
                                future.setException(exc);
                            }

                            @Override
                            public void onSuccess(AuthenticationResult result) {
                                UpdateSharedPreferences(refreshTokenValue, result.getRefreshToken());
                                com.microsoft.listservices.http.OAuthCredentials credentials = new com.microsoft.listservices.http.OAuthCredentials(result.getAccessToken());
                                SharepointListsClient client = new SharepointListsClient(getSharepointServerUrl(), getSiteRelativeUrl(),credentials);
                                future.set(client);
                            }
                        });
            } else {
                getAuthenticationContext().acquireToken(
                        mActivity, getSharepointServerUrl(),
                        getClientId(),getRedirectUrl(), PromptBehavior.Auto,
                        new AuthenticationCallback<AuthenticationResult>() {

                            @Override
                            public void onError(Exception exc) {
                                future.setException(exc);
                            }

                            @Override
                            public void onSuccess(AuthenticationResult result) {
                                UpdateSharedPreferences(refreshTokenValue, result.getRefreshToken());
                                com.microsoft.listservices.http.OAuthCredentials credentials = new com.microsoft.listservices.http.OAuthCredentials(result.getAccessToken());
                                SharepointListsClient client = new SharepointListsClient(getSharepointServerUrl(), getSiteRelativeUrl(), credentials);
                                future.set(client);
                            }
                        });
            }
        } catch (Throwable t) {
            future.setException(t);
        }
        try {
            return future.get();
        } catch (Throwable t) {
            Log.e(Constants.TAG, t.getMessage());
            return null;
        }
    }

    private OutlookClient getExchangeEntityContainerClientAAD() {
        final String refreshTokenValue ="exchangeRefreshToken";
        final SettableFuture<OutlookClient> future = SettableFuture.create();

        try {
            String refreshToken = PreferenceManager.getDefaultSharedPreferences(mActivity).getString(
                    refreshTokenValue, "");

            if (!refreshToken.isEmpty()) {
                getAuthenticationContext().acquireTokenByRefreshToken(refreshToken, getClientId(), getExchangeServerUrl(),
                        new AuthenticationCallback<AuthenticationResult>() {

                            @Override
                            public void onError(Exception exc) {
                                future.setException(exc);
                            }

                            @Override
                            public void onSuccess(AuthenticationResult result) {

                               UpdateSharedPreferences(refreshTokenValue, result.getRefreshToken());

                               OutlookClient client = new OutlookClient(getExchangeEndpointUrl(), getDependencyResolver(result.getAccessToken()));
                               future.set(client);
                            }
                        });
            } else {
                getAuthenticationContext().acquireToken(
                        mActivity, getExchangeServerUrl(),
                        getClientId(),getRedirectUrl(), PromptBehavior.Auto,
                        new AuthenticationCallback<AuthenticationResult>() {

                            @Override
                            public void onError(Exception exc) {
                                future.setException(exc);
                            }

                            @Override
                            public void onSuccess(AuthenticationResult result) {
                                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(mActivity).edit();
                                editor.putString(refreshTokenValue, result.getRefreshToken());
                                editor.commit();
                                OutlookClient client = new OutlookClient(getExchangeEndpointUrl(),getDependencyResolver(result.getAccessToken()));
                                future.set(client);
                            }
                        });
            }
        } catch (Throwable t) {
            future.setException(t);
        }
        try {
            return future.get();
        } catch (Throwable t) {
            Log.e(Constants.TAG, t.getMessage());
            return null;
        }
    }

    private DependencyResolver getDependencyResolver(final String token) {
        OAuthCredentials credentials = new OAuthCredentials(token);
        CredentialsFactoryImpl credFactory = new CredentialsFactoryImpl();
        credFactory.setCredentials(credentials);

        DefaultDependencyResolver dependencyResolver = new DefaultDependencyResolver();
        dependencyResolver.setCredentialsFactory(credFactory);

        dependencyResolver.getLogger().setEnabled(true);
        dependencyResolver.getLogger().setLogLevel(LogLevel.VERBOSE);
        return dependencyResolver;
    }

    public SharePointClient getFilesEntityContainerClientAAD() {
        final SettableFuture<SharePointClient> future = SettableFuture.create();
        final String refreshTokenValue ="filesRefreshToken";

        try {
            String refreshToken = PreferenceManager.getDefaultSharedPreferences(mActivity).getString(
                    refreshTokenValue, "");
            if (!refreshToken.isEmpty()) {
                getAuthenticationContext().acquireTokenByRefreshToken(refreshToken, getClientId(), getFileServerUrl(),
                        new AuthenticationCallback<AuthenticationResult>() {

                            @Override
                            public void onError(Exception exc) {
                                future.setException(exc);
                            }

                            @Override
                            public void onSuccess(AuthenticationResult result) {
                                UpdateSharedPreferences(refreshTokenValue, result.getRefreshToken());
                                SharePointClient client = new SharePointClient(getFilesEndpointUrl(), getDependencyResolver(result.getAccessToken()));
                                future.set(client);
                            }
                        });
            } else {
                getAuthenticationContext().acquireToken(
                        mActivity, getFileServerUrl(),
                        getClientId(),getRedirectUrl(), PromptBehavior.Auto,
                        new AuthenticationCallback<AuthenticationResult>() {

                            @Override
                            public void onError(Exception exc) {
                                future.setException(exc);
                            }

                            @Override
                            public void onSuccess(AuthenticationResult result) {
                                UpdateSharedPreferences(refreshTokenValue, result.getRefreshToken());
                                SharePointClient client = new SharePointClient(getFilesEndpointUrl(),getDependencyResolver(result.getAccessToken()));
                                future.set(client);
                            }
                        });
            }
        } catch (Throwable t) {
            future.setException(t);
        }
        try {
            return future.get();
        } catch (Throwable t) {
            Log.e(Constants.TAG, t.getMessage());
            return null;
        }
    }

    public void UpdateSharedPreferences(String key, String value){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(mActivity).edit();
        editor.putString(key, value);
        editor.commit();
    }
}