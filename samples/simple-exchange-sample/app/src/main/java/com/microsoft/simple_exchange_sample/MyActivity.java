package com.microsoft.simple_exchange_sample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        final Button email = (Button)findViewById(R.id.button_start_email);
        email.setOnClickListener(this);
        email.setEnabled(false);
        final Button calendar = (Button)findViewById(R.id.button_start_calendar);
        calendar.setOnClickListener(this);
        calendar.setEnabled(false);

        // check that the sample has been properly customized
        if (ServiceConstants.AUTHORITY_URL.isEmpty() ||
            ServiceConstants.RESOURCE_ID.isEmpty()   ||
            ServiceConstants.REDIRECT_URL.isEmpty()  ||
            ServiceConstants.CLIENT_ID.isEmpty()) {
            Controller.getInstance().handleError(MyActivity.this, "Please edit the " +
                    "ServiceConstants.java file according to you application and subscription, " +
                    "and re-deploy the application");

            // do not move forward
            return;
        }

        //
        // run authentication
        //
        Authentication.createEncryptionKey(getApplicationContext());
        SettableFuture<Void> authenticated =
                Authentication.acquireToken(
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

                                // enable scenarios
                                email.setEnabled(true);
                                calendar.setEnabled(true);
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
}
