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


import com.microsoft.office365.test.integration.TestPlatformContext;
import com.microsoft.office365.test.integration.framework.OAuthCredentials;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestExecutionCallback;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.outlookservices.odata.EntityContainerClient;
import com.microsoft.services.odata.impl.DefaultDependencyResolver;
import com.microsoft.services.odata.impl.http.CredentialsFactoryImpl;
import com.microsoft.services.odata.interfaces.Credentials;
import com.microsoft.services.odata.interfaces.CredentialsFactory;
import com.microsoft.services.odata.interfaces.DependencyResolver;
import com.microsoft.services.odata.interfaces.LogLevel;
import com.microsoft.services.odata.interfaces.Request;

public class AndroidTestPlatformContext implements TestPlatformContext {

	private static Activity mActivity;

	public AndroidTestPlatformContext(Activity activity) {
		mActivity = activity;
	}

	@Override
	public String getServerUrl() {
		return PreferenceManager.getDefaultSharedPreferences(mActivity).getString(Constants.PREFERENCE_RESOURCE_URL,
				"");
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
    public String getEndpointUrl() {
        return PreferenceManager.getDefaultSharedPreferences(mActivity).getString(
                Constants.PREFERENCE_ENDPOINT_URL, "");
    }

    @Override
    public String getTestMail() {
        return PreferenceManager.getDefaultSharedPreferences(mActivity).getString(
                Constants.PREFERENCE_TEST_MAIL, "");
    }

    @Override
    public String getBasicAuthToken() {
        return PreferenceManager.getDefaultSharedPreferences(mActivity).getString(
                Constants.PREFERENCE_BASIC_TOKEN, "");
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
	public EntityContainerClient getMailCalendarContactClient() {
        EntityContainerClient result;

		if (getAuthenticationMethod().equals("AAD")) {
			result = getEntityContainerClientAAD();
		} else {
			result = getEntityContainerClientBasic();
		}

		return result;
	}

    EntityContainerClient getEntityContainerClientAAD() {
		final SettableFuture<EntityContainerClient> future = SettableFuture.create();

		try {
			getAuthenticationContext().acquireToken(
					mActivity, getServerUrl(),
					getClientId(),getRedirectUrl(), PromptBehavior.Auto,
					new AuthenticationCallback<AuthenticationResult>() {

						@Override
						public void onError(Exception exc) {
							future.setException(exc);
						}

						@Override
						public void onSuccess(AuthenticationResult result) {
							EntityContainerClient client = new EntityContainerClient(getEndpointUrl(),getDependencyResolver(result.getAccessToken()));
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


    EntityContainerClient getEntityContainerClientBasic() {
        final String token = this.getBasicAuthToken();

        DefaultDependencyResolver dependencyResolver = new DefaultDependencyResolver();
        dependencyResolver.setCredentialsFactory(new CredentialsFactory() {
            @Override
            public Credentials getCredentials() {
                return new Credentials() {
                    @Override
                    public void prepareRequest(Request request) {
                        request.addHeader("Authorization", "Basic " + token);
                    }
                };
            }
        });
        dependencyResolver.getLogger().setLogLevel(LogLevel.VERBOSE);
        EntityContainerClient client = new EntityContainerClient(getEndpointUrl(),dependencyResolver);
        return client;
    }

    private DependencyResolver getDependencyResolver(final String token) {
        OAuthCredentials credentials = new OAuthCredentials(token);
        CredentialsFactoryImpl credFactory = new CredentialsFactoryImpl();
        credFactory.setCredentials(credentials);

        DefaultDependencyResolver dependencyResolver = new DefaultDependencyResolver();
        dependencyResolver.setCredentialsFactory(credFactory);

        return dependencyResolver;
    }
}