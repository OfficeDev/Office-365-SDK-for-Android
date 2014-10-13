package com.microsoft.simple_exchange_sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.office365.odata.impl.DefaultDependencyResolver;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Controller.getInstance().setDependencyResolver(new DefaultDependencyResolver());

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Authentication.createEncryptionKey(getApplicationContext());
                SettableFuture<Void> authenticated = Authentication.authenticate(MyActivity.this, (DefaultDependencyResolver)Controller.getInstance().getDependencyResolver());

                Futures.addCallback(authenticated, new FutureCallback<Void>() {
                    @Override
                    public void onSuccess(Void result) {
                        startMailActivity();
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

    void startMailActivity() {
        Intent intent = new Intent(this, MailActivity.class);
        startActivity(intent);
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
