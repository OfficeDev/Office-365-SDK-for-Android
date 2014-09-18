package com.example.exchangedemo;

import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationCancelError;
import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationResult;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";

	private static final int MENU_RESET_TOKEN = 0;

	private AuthenticationContext mAuthContext;

	private AppPreferences mAppPreferences;
	private AndroidApplication mApplication;

	TextView txtOutput;
	TextView txtTokenResult;
	ListView lvOutput;
	Button btnGetAccessToken;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtTokenResult = (TextView) findViewById(R.id.textView_Token);
		btnGetAccessToken = (Button) findViewById(R.id.button_getAccessToken);
		txtOutput = (TextView) findViewById(R.id.textView_Output);

		mApplication = (AndroidApplication) getApplication();
		mAppPreferences = (mApplication).getAppPreferences();

		btnGetAccessToken.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (mApplication.hasConfiguration()) {
					getToken();
				} else {
					Intent intent = new Intent(MainActivity.this,
							AppPreferencesActivity.class);
					startActivity(intent);
				}
			}
		});	
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		mAuthContext.onActivityResult(requestCode, resultCode, data);
	}

	protected AuthenticationContext getAuthContext() {
		if (mAuthContext == null) {
			try {
				mAuthContext = new AuthenticationContext(this,
						Constants.AUTHORITY_URL, false);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mAuthContext;
	}

	protected void getToken() {
		//TODO: revisar segundo parametro
		getAuthContext().acquireToken(this, mAppPreferences.getResourceUrl(),
				mAppPreferences.getClientId(),
				mAppPreferences.getRedirectUrl(), Constants.USER_HINT,
				new AuthenticationCallback<AuthenticationResult>() {

					@Override
					public void onError(Exception exc) {
						if(exc.getClass() == AuthenticationCancelError.class){
							txtOutput.setText("User canceled");
						}else
						{
							txtOutput.setText(exc.getMessage());						
							Log.e(TAG, exc.getMessage());	
						}
						
					}

					@Override
					public void onSuccess(AuthenticationResult result) {
						if (result != null
								&& !result.getAccessToken().isEmpty()) {
							txtOutput.setText("Got token!");
							txtTokenResult.setText(result.getAccessToken());
							Log.i(TAG, result.getAccessToken());
						}
					}
				});
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);

		menu.add(Menu.NONE, MENU_RESET_TOKEN, Menu.NONE, "Clear Token Cache");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings: {
			Intent intent = new Intent(MainActivity.this,
					AppPreferencesActivity.class);
			startActivity(intent);
			return true;
		}
		case MENU_RESET_TOKEN: {
			resetToken();
			clearCookies();
			return true;
		}
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void resetToken() {
		Log.i(TAG, "Clearing cached tokens");
		getAuthContext().getCache().removeAll();
		txtOutput.setText("");
		txtTokenResult.setText("");
	}

	public void clearCookies() {
		CookieSyncManager.createInstance(getApplicationContext());
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.removeAllCookie();
		CookieSyncManager.getInstance().sync();
	}

}
