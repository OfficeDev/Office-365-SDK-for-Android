package com.microsoft.simple_exchange_sample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.services.odata.impl.DefaultDependencyResolver;

import java.util.concurrent.Callable;

public class MyActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Controller.getInstance().setDependencyResolver(new DefaultDependencyResolver());

        findViewById(R.id.button_start_email).setOnClickListener(this);
        findViewById(R.id.button_start_calendar).setOnClickListener(this);

        // run authentication
        Authentication.createEncryptionKey(getApplicationContext());
        SettableFuture<Void> authenticated =
                Authentication.authenticate(
                        MyActivity.this,
                        (DefaultDependencyResolver) Controller.getInstance().getDependencyResolver()
                );

        final Context context = this;
        Futures.addCallback(authenticated, new FutureCallback<Void>() {

            @Override
            public void onSuccess(Void result) {
                Controller.getInstance().postASyncTask( new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(
                                        MyActivity.this,
                                        "Authentication successful",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                        return null;
                    }
                });
            }

            @Override
            public void onFailure(final Throwable t) {
                Controller.getInstance().handleError(MyActivity.this, t.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Authentication.context.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch(view.getId()) {
            case R.id.button_start_email:
                intent = new Intent(this, MailActivity.class);
                break;
            case R.id.button_start_calendar:
                intent = new Intent(this, CalendarActivity.class);
                break;
            default:
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
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
