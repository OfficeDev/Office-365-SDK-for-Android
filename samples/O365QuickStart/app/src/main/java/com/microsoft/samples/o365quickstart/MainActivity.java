package com.microsoft.samples.o365quickstart;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationResult;
import com.microsoft.aad.adal.PromptBehavior;

import com.microsoft.services.android.impl.ADALDependencyResolver;
import com.microsoft.outlookservices.orc.OutlookClient;
import com.microsoft.outlookservices.Message;
import com.microsoft.services.orc.interfaces.LogLevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";
    private static final String outlookBaseUrl = "https://outlook.office365.com/api/v1.0";

    private AuthenticationContext _authContext;
    private ADALDependencyResolver _resolver;
    private OutlookClient _client;

    private ListView lvMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMessages = (ListView) findViewById(R.id.lvMessages);

        Futures.addCallback(logon(), new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                _client = new OutlookClient(outlookBaseUrl, _resolver);
                getMessages();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }




    public SettableFuture<Boolean> logon() {

        final SettableFuture<Boolean> result = SettableFuture.create();

        try {
            _authContext = new AuthenticationContext(this, getResources().getString(R.string.AADAuthority), true);
        } catch (Exception e) {
            Log.e(TAG, "Failed to initialize Authentication Context with error: " + e.getMessage());
            _authContext = null;
            result.setException(e);
        }

        if (_authContext != null) {
            _authContext.acquireToken(
                    this,
                    getResources().getString(R.string.AADResourceId),
                    getResources().getString(R.string.AADClientId),
                    getResources().getString(R.string.AADRedirectUrl),
                    PromptBehavior.Auto,
                    new AuthenticationCallback<AuthenticationResult>() {

                        @Override
                        public void onSuccess(final AuthenticationResult authenticationResult) {

                            if (authenticationResult != null && authenticationResult.getStatus() == AuthenticationResult.AuthenticationStatus.Succeeded) {
                                _resolver = new ADALDependencyResolver(
                                        _authContext,
                                        getResources().getString(R.string.AADResourceId),
                                        getResources().getString(R.string.AADClientId));
                                _resolver.getLogger().setLogLevel(LogLevel.VERBOSE);
                                _resolver.getLogger().setEnabled(true);
                                result.set(true);
                            }
                        }

                        @Override
                        public void onError(Exception e) {
                            result.setException(e);
                        }
                    }
            );
        }

        return result;
    }

    public void getMessages() {
        _resolver.getLogger().log("Getting messages...", LogLevel.VERBOSE);
        Futures.addCallback(_client.getMe().getFolders().getById("Inbox").getMessages().read(), new FutureCallback<List<Message>>() {
            @Override
            public void onSuccess(final List<Message> result) {
                _resolver.getLogger().log("Preparing messages for display.", LogLevel.VERBOSE);
                List<Map<String, String>> listOfMessages = new ArrayList<Map<String, String>>();

                for (Message m : result) {
                    Map<String, String> oneMessage = new HashMap<String,String>();
                    oneMessage.put("subject", m.getSubject());
                    oneMessage.put("from", "From: " + m.getFrom().getEmailAddress().getAddress());
                    listOfMessages.add(oneMessage);
                }

                final SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, listOfMessages,
                        android.R.layout.simple_list_item_2,
                        new String[] {"subject", "from"},
                        new int[] {android.R.id.text1, android.R.id.text2});

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        lvMessages.setAdapter(adapter);
                    }
                });
            }

            @Override
            public void onFailure(final Throwable t) {
                _resolver.getLogger().log(t.getMessage(), LogLevel.ERROR);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        _authContext.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
