package com.microsoft.office365.test.integration.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.microsoft.office365.test.integration.TestPlatformContext;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestExecutionCallback;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.outlookservices.odata.OutlookClient;
import com.microsoft.services.odata.impl.DefaultDependencyResolver;
import com.microsoft.services.odata.interfaces.DependencyResolver;
import com.microsoft.services.odata.interfaces.LogLevel;
import com.microsoft.sharepointservices.ListClient;
import com.microsoft.fileservices.odata.SharePointClient;


public class AndroidTestPlatformContext implements TestPlatformContext {

    private static Activity mActivity;

    public AndroidTestPlatformContext(Activity activity) {
        mActivity = activity;
        try {
            context = new AuthenticationContext(mActivity, Constants.AUTHORITY_URL, true);
        } catch (Throwable e) {
            Log.e("E2ETestApp", "Error creating AuthenticationContext: " + e.getMessage());
        }
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
    public String getDiscoveryServerUrl() {
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
        return getExchangeEntityContainerClientAAD();
    }

    @Override
    public SharePointClient getFilesClient() {
        return getFilesEntityContainerClientAAD();
    }

    @Override
    public ListClient getSharePointListClient() {
        return getSharePointListClientAAD();
    }

    @Override
    public DiscoveryClient getDiscoveryClient() {
        return getDiscoveryClientAAD();
    }

    @Override
    public DirectoryClient getDirectoryClient() {
        return getDirectoryClientAAD();
    }

    private DirectoryClient getDirectoryClientAAD() {
        return getTClientAAD(getDirectoryServerUrl(), getDirectoryEndpointUrl(), DirectoryClient.class);
    }

    private DiscoveryClient getDiscoveryClientAAD() {
        return getTClientAAD(getDiscoveryServerUrl(), getDiscoveryEndpointUrl(), DiscoveryClient.class);
    }

    private OutlookClient getExchangeEntityContainerClientAAD() {
        return getTClientAAD(getExchangeServerUrl(), getExchangeEndpointUrl(), OutlookClient.class);
    }

    public SharePointClient getFilesEntityContainerClientAAD() {
        return getTClientAAD(getFileServerUrl(), getFilesEndpointUrl(), SharePointClient.class);
    }

    private <TClient> TClient getTClientAAD(String serverUrl, final String endpointUrl, final Class<TClient> clientClass) {
        final SettableFuture<TClient> future = SettableFuture.create();

        try {
            getAuthenticationContext().acquireToken(
                    mActivity, serverUrl,
                    getClientId(), getRedirectUrl(), PromptBehavior.Auto,
                    new AuthenticationCallback<AuthenticationResult>() {

                        @Override
                        public void onError(Exception exc) {
                            future.setException(exc);
                        }

                        @Override
                        public void onSuccess(AuthenticationResult result) {
                            TClient client = null;
                            try {
                                client = clientClass.getDeclaredConstructor(String.class, DependencyResolver.class)
                                                    .newInstance(endpointUrl, getDependencyResolver(result.getAccessToken()));
                                future.set(client);
                            } catch (Throwable t) {
                                onError(new Exception(t));
                            }
                        }
                    });


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

    private ListClient getSharePointListClientAAD() {
        final SettableFuture<ListClient> future = SettableFuture.create();
        try {
            getAuthenticationContext().acquireToken(
                    mActivity, getSharepointServerUrl(),
                    getClientId(), getRedirectUrl(), PromptBehavior.Auto,
                    new AuthenticationCallback<AuthenticationResult>() {

                        @Override
                        public void onError(Exception exc) {
                            future.setException(exc);
                        }

                        @Override
                        public void onSuccess(AuthenticationResult result) {
                            com.microsoft.sharepointservices.http.OAuthCredentials credentials = new com.microsoft.sharepointservices.http.OAuthCredentials(result.getAccessToken());
                            ListClient client = new ListClient(getSharepointServerUrl(), getSiteRelativeUrl(), credentials);
                            future.set(client);
                        }
                    });
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

        DefaultDependencyResolver dependencyResolver = new DefaultDependencyResolver(token);

        dependencyResolver.getLogger().setEnabled(true);
        dependencyResolver.getLogger().setLogLevel(LogLevel.VERBOSE);
        return dependencyResolver;
    }
}