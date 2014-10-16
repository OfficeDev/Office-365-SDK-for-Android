package com.microsoft.simple_exchange_sample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.office365.odata.impl.DefaultDependencyResolver;

public class MyActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Controller.getInstance().setDependencyResolver(new DefaultDependencyResolver());

        findViewById(R.id.button_start_email).setOnClickListener(this);
        findViewById(R.id.button_start_calendar).setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Authentication.context.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        startActivity(intent);
        final int clicked = view.getId();
        Authentication.createEncryptionKey(getApplicationContext());
        SettableFuture<Void> authenticated =
                Authentication.authenticate(
                        MyActivity.this,
                        (DefaultDependencyResolver) Controller.getInstance().getDependencyResolver()
                );
        final Context context = this;
        Futures.addCallback(authenticated, new FutureCallback<Void>() {
            public void onSuccess(Void result) {
                Intent intent = null;
                switch(clicked) {
                    case R.id.button_start_email:
                        intent = new Intent(context, MailActivity.class);
                        break;
                    case R.id.button_start_calendar:
                        intent = new Intent(context, CalendarActivity.class);
                        break;
                    default:
                        break;
                }
                if (intent != null) {
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(final Throwable t) {
                Controller.handleError(MyActivity.this, t.getMessage());
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
