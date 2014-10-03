package com.microsoft.simple_exchange_sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.office365.exchange.services.Message;
import com.microsoft.office365.odata.EntityContainerClient;
import com.microsoft.office365.odata.impl.DefaultDependencyResolver;

import java.util.List;


public class MyActivity extends Activity {

    DefaultDependencyResolver mResolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mResolver = new DefaultDependencyResolver();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Authentication.createEncryptionKey(getApplicationContext());
                SettableFuture<Void> authenticated = Authentication.authenticate(MyActivity.this, mResolver);

                Futures.addCallback(authenticated, new FutureCallback<Void>() {
                    @Override
                    public void onSuccess(Void result) {
                        retrieveMails();
                    }

                    @Override
                    public void onFailure(final Throwable t) {
                        handleError(t);
                    }
                });

            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Authentication.context.onActivityResult(requestCode, resultCode, data);
    }

    private void retrieveMails() {
        EntityContainerClient client = new EntityContainerClient(Constants.ENDPOINT_ID, mResolver);

        ListenableFuture<List<Message>> messagesFuture = client
                .getMe()
                .getFolders()
                .getById("Inbox")
                .getMessages()
                .execute();

        Futures.addCallback(messagesFuture, new FutureCallback<List<Message>>() {
            @Override
            public void onSuccess(List<Message> result) {
                showMessages(result);

            }

            @Override
            public void onFailure(final Throwable t) {
                handleError(t);
            }
        });
    }

    private void showMessages(List<Message> result) {
        String message;
        if (result.size() == 0) {
            message = "You have no messages";
        } else {
            StringBuilder sb = new StringBuilder();

            sb.append("Your messages are:");
            sb.append("\n");
            for (Message m : result) {
                sb.append(m.getSubject());
                sb.append("\n");
            }
            message = sb.toString();
        }

        final String finalMessage = message;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MyActivity.this, finalMessage, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void handleError(final Throwable t) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MyActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
