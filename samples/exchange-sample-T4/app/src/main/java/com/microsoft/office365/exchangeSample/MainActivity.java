package com.microsoft.office365.exchangeSample;

import java.util.ArrayList;
import java.util.List;

import com.core.EntityContainer;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationCancelError;
import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationResult;
import com.microsoft.aad.adal.AuthenticationSettings;
import com.microsoft.exchange.services.odata.model.Message;
import com.microsoft.office365.http.OAuthCredentials;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    SimpleExchangeAdapter mailAdapter;
    AuthenticationContext mAuthContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authenticate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(com.microsoft.office365.exchangeSample.R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case com.microsoft.office365.exchangeSample.R.id.new_mail: {
                startActivity(new Intent(this, NewMailActivity.class));
                return true;
            }
            case com.microsoft.office365.exchangeSample.R.id.load_mails: {
                getDataForListView();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getDataForListView() {
        EntityContainer container = AppData.getEntityContainer();

        ListenableFuture<List<Message>> future = container.getMessages("Inbox");

        Futures.addCallback(future, new FutureCallback<List<Message>>() {
            @Override
            public void onSuccess(List<Message> messages) {
                List<MessageViewModel> messageViewModelList = new ArrayList<MessageViewModel>();

                for (Message m : messages) {
                    MessageViewModel mvm = new MessageViewModel(m.getSender().getName(), m.getSubject());
                    messageViewModelList.add(mvm);
                }

                fillList(messageViewModelList);

            }

            @Override
            public void onFailure(final Throwable throwable) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Error: " + throwable.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }

    private void authenticate() {
        AuthenticationSettings.INSTANCE.setSecretKey(new byte[32]);

        getAuthContext().acquireToken(this, "https://outlook.office365.com/",
                "778a099e-ed6e-49a2-9f15-92c01366ad7d",
                "https://lagash.com/oauth", "",
                new AuthenticationCallback<AuthenticationResult>() {

                    @Override
                    public void onError(Exception exc) {
                        if (exc.getClass() == AuthenticationCancelError.class) {
                            // User canceled
                        } else {
                            // Do something with the error
                        }
                    }

                    @Override
                    public void onSuccess(AuthenticationResult result) {
                        if (result != null
                                && !result.getAccessToken().isEmpty()) {
                            OAuthCredentials credentials = new OAuthCredentials(result
                                    .getAccessToken());

                            AppData.setCredentials(credentials);
                        }
                    }
                });
    }

    private AuthenticationContext getAuthContext() {
        mAuthContext = null;
        try {
            mAuthContext = new AuthenticationContext(this,
                    "https://login.windows.net/common", false);
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return mAuthContext;
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mAuthContext.onActivityResult(requestCode, resultCode, data);
    }
}
