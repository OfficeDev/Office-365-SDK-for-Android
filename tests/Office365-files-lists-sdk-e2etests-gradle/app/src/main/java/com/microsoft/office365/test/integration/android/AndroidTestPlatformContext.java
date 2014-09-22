package com.microsoft.office365.test.integration.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationResult;
import com.microsoft.aad.adal.PromptBehavior;
import com.microsoft.office365.LogLevel;
import com.microsoft.office365.Logger;
import com.microsoft.office365.files.FileClient;
import com.microsoft.office365.http.CookieCredentials;
import com.microsoft.office365.http.OAuthCredentials;
import com.microsoft.office365.http.SharepointCookieCredentials;
import com.microsoft.office365.lists.SharepointListsClient;
import com.microsoft.office365.test.integration.TestPlatformContext;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestExecutionCallback;
import com.microsoft.office365.test.integration.framework.TestResult;

public class AndroidTestPlatformContext implements TestPlatformContext {

	private static Activity mActivity;

	public AndroidTestPlatformContext(Activity activity) {
		mActivity = activity;
	}

	@Override
	public Logger getLogger() {
		return new Logger() {

			@Override
			public void log(String message, LogLevel level) {
				Log.d(Constants.TAG, level.toString() + ": " + message);
			}
		};
	}

	@Override
	public String getServerUrl() {
		return PreferenceManager.getDefaultSharedPreferences(mActivity).getString(Constants.PREFERENCE_SHAREPOINT_URL,
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
	public FileClient getFileClient() {
		FileClient result;

		if (getAuthenticationMethod().equals("AAD")) {
			result = getFileClientAAD();
		} else {
			result = getFileClientCookies();
		}

		return result;
	}

	@Override
	public SharepointListsClient getListsClient() {
		SharepointListsClient result;

		if (getAuthenticationMethod().equals("AAD")) {
			result = getListsClientAAD();
		} else {
			result = getListsClientCookies();
		}
		return result;
	}

	SharepointListsClient getListsClientCookies() {

		final SettableFuture<SharepointListsClient> clientFuture = SettableFuture.create();
		mActivity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				ListenableFuture<CookieCredentials> future = SharepointCookieCredentials.requestCredentials(
						getServerUrl(), mActivity);

				Futures.addCallback(future, new FutureCallback<CookieCredentials>() {
					@Override
					public void onFailure(Throwable t) {
						clientFuture.setException(t);
					}

					@Override
					public void onSuccess(CookieCredentials credentials) {
						SharepointListsClient client = new SharepointListsClient(getServerUrl(), getSiteRelativeUrl(),
								credentials, getLogger());
						clientFuture.set(client);
					}
				});
			}
		});

		try {
			return clientFuture.get();
		} catch (Throwable t) {
			Log.e(Constants.TAG, t.getMessage());
			return null;
		}
	}

	SharepointListsClient getListsClientAAD() {
		final SettableFuture<SharepointListsClient> future = SettableFuture.create();

		try {
			// here we get the token using ADAL Library
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
							// once succeeded we create a credentials instance
							// using the token from ADAL
							OAuthCredentials credentials = new OAuthCredentials(result.getAccessToken());

							// retrieve the OfficeClient with the credentials
							SharepointListsClient client = new SharepointListsClient(getServerUrl(),
									getSiteRelativeUrl(), credentials, getLogger());
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

	FileClient getFileClientAAD() {
		final SettableFuture<FileClient> future = SettableFuture.create();

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
							OAuthCredentials credentials = new OAuthCredentials(result.getAccessToken());

							FileClient client = new FileClient(getServerUrl(), getSiteRelativeUrl(), credentials,
									getLogger());
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

	FileClient getFileClientCookies() {

		final SettableFuture<FileClient> clientFuture = SettableFuture.create();

		mActivity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				ListenableFuture<CookieCredentials> future = SharepointCookieCredentials.requestCredentials(
						getServerUrl(), mActivity);

				Futures.addCallback(future, new FutureCallback<CookieCredentials>() {
					@Override
					public void onFailure(Throwable t) {
						clientFuture.setException(t);
					}

					@Override
					public void onSuccess(CookieCredentials credentials) {
						FileClient client = new FileClient(getServerUrl(), getSiteRelativeUrl(), credentials,
								getLogger());
						clientFuture.set(client);
					}
				});
			}
		});

		try {
			return clientFuture.get();
		} catch (Throwable t) {
			Log.e(Constants.TAG, t.getMessage());
			return null;
		}
	}
}