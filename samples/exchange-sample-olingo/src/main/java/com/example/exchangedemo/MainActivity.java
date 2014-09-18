package com.example.exchangedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.ListView;
import android.widget.Toast;

import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationCancelError;
import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationResult;
import com.microsoft.office365.api.MailClient;
import com.microsoft.office365.microsoft.exchange.services.odata.model.types.FolderCollection;
import com.microsoft.office365.microsoft.exchange.services.odata.model.types.Message;
import com.microsoft.office365.microsoft.exchange.services.odata.model.types.MessageCollection;
import com.microsoft.office365.oauth.OAuthCredentials;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.NoSuchPaddingException;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    private static final int MENU_RESET_TOKEN = 0;
    SimpleExchangeAdapter mailAdapter;
    String mToken;
    private AuthenticationContext mAuthContext;
    private AppPreferences mAppPreferences;
    private AndroidApplication mApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApplication = (AndroidApplication) getApplication();
        mAppPreferences = (mApplication).getAppPreferences();

        if (mApplication.hasConfiguration()) {
            authenticate();
        } else {
            Intent intent = new Intent(MainActivity.this, AppPreferencesActivity.class);
            startActivity(intent);
        }
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
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            }
        }
        return mAuthContext;
    }

    protected void authenticate() {
        getAuthContext().acquireToken(this, Constants.RESOURCE_ID,
                mAppPreferences.getClientId(),
                mAppPreferences.getRedirectUrl(), Constants.USER_HINT,
                new AuthenticationCallback<AuthenticationResult>() {

                    @Override
                    public void onError(Exception exc) {
                        if (exc.getClass() == AuthenticationCancelError.class) {
                            Toast.makeText(getApplicationContext(), "User canceled",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), exc.getMessage(),
                                    Toast.LENGTH_LONG).show();
                            Log.e(TAG, exc.getMessage());
                        }
                    }

                    @Override
                    public void onSuccess(AuthenticationResult result) {
                        if (result != null
                                && !result.getAccessToken().isEmpty()) {

                            Toast.makeText(getApplicationContext(), "Got token!",
                                    Toast.LENGTH_LONG).show();
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

            case R.id.new_mail: {
                startActivity(new Intent(this, NewMailActivity.class));
                return true;
            }

            case R.id.load_mails: {
                getDataForListView();
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

    private void getDataForListView() {

        MailClient client = new MailClient.Builder()
                .setCredentials(new OAuthCredentials(mToken))
                .setResourceId(Constants.RESOURCE_ID)
                .setODataEndpoint(Constants.ODATA_ENDPOINT)
                .setMaxDefaultResults(10)
                .build();

        FolderCollection folderCollection = client.

        MessageCollection c = client.getMessages("Inbox");
        Message m = c.iterator().next();
        String s = m.getId();


       /*                                   .build();
        List<Message> messages = new ArrayList<Message>();
        try {
            messages = client.getMessages("Inbox").get();
        } catch (Exception e) {
            // TODO: handle exception
        }
        fillList(messages);
        */

    }

    private void fillList(final List<MessageViewModel> messages) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mailAdapter = new SimpleExchangeAdapter(MainActivity.this, messages);
                ListView mailListView = (ListView) findViewById(R.id.mail_list);
                mailListView.setAdapter(mailAdapter);

            }
        });
    }

    public void resetToken() {
        Log.i(TAG, "Clearing cached tokens");
        getAuthContext().getCache().removeAll();
    }

    public void clearCookies() {
        CookieSyncManager.createInstance(getApplicationContext());
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }
}
