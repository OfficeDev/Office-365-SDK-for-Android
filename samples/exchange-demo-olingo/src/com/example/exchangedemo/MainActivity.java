package com.example.exchangedemo;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.NoSuchPaddingException;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationCancelError;
import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationResult;
import com.microsoft.office365.Credentials;
import com.microsoft.office365.http.OAuthCredentials;
import com.microsoft.office365.http.Request;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {

	SimpleExchangeAdapter mailAdapter;
	OAuthCredentials mCredentials;
	AuthenticationContext mAuthContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		authenticate();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.new_mail:{
			startActivity(new Intent(this, NewMailActivity.class));
			return true;
		}
		case R.id.load_mails:{
			getDataForListView();
			return true;
		}
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void getDataForListView()
	{	
		MailClient client = new MailClient(mCredentials, "https://outlook.office365.com/EWS/OData");
		List<Message> messages = new ArrayList<Message>();
		try {
			messages = client.getMessages("Inbox").get();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		fillList(messages);			
	}

	private void authenticate(){
		getAuthContext().acquireToken(this, "https://outlook.office365.com/",
				"778a099e-ed6e-49a2-9f15-92c01366ad7d",
				"https://lagash.com/oauth", "",
				new AuthenticationCallback<AuthenticationResult>() {

			@Override
			public void onError(Exception exc) {
				if(exc.getClass() == AuthenticationCancelError.class){
					// User canceled
				}else
				{
					// Do something with the error
				}
			}

			@Override
			public void onSuccess(AuthenticationResult result) {
				if (result != null
						&& !result.getAccessToken().isEmpty()) {
					OAuthCredentials credentials = new OAuthCredentials(result
							.getAccessToken());

					mCredentials = credentials;		
					getDataForListView();
				}
			}
		});
	}

	private AuthenticationContext getAuthContext(){
		mAuthContext = null;
		try {
			mAuthContext = new AuthenticationContext(this,
					"https://login.windows.net/common", false);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mAuthContext;
	}

	private void fillList(final List<Message> messages){
		runOnUiThread(new Runnable() {
		     @Override
		     public void run() {
		    	 mailAdapter = new SimpleExchangeAdapter(MainActivity.this,messages);
		 		ListView mailListView = (ListView)findViewById(R.id.mail_list);
		 		mailListView.setAdapter(mailAdapter);

		    }
		});
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		mAuthContext.onActivityResult(requestCode, resultCode, data);
	}
}
