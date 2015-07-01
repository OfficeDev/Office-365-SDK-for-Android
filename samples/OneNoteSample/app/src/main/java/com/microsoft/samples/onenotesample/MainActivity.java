package com.microsoft.samples.onenotesample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.microsoft.live.LiveAuthClient;

import com.microsoft.services.onenote.*;
import com.microsoft.services.onenote.fetchers.OneNoteApiClient;
import com.microsoft.services.orc.log.LogLevel;
import com.microsoft.services.orc.resolvers.LiveAuthDependencyResolver;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = "OneNoteSampleActivity";

    private ListView lvNotebooks;
    private Button btnNotebooks;
    private OneNoteApiClient oneNoteClient;
    private LiveAuthDependencyResolver dependencyResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvNotebooks = (ListView) findViewById(R.id.listView1);
        btnNotebooks = (Button) findViewById(R.id.button1);
        btnNotebooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNotebooks();
            }
        });

        logon();
    }


    protected OneNoteApiClient getOneNoteClient() {
        if (oneNoteClient == null) {
            oneNoteClient = new OneNoteApiClient(Constants.ONENOTE_API_ROOT, getDependencyResolver());
        }
        return oneNoteClient;
    }

    protected LiveAuthDependencyResolver getDependencyResolver() {

        if (dependencyResolver == null) {
            LiveAuthClient theAuthClient = new LiveAuthClient(getApplicationContext(), Constants.CLIENT_ID,
                    Arrays.asList(Constants.SCOPES));

            dependencyResolver = new LiveAuthDependencyResolver(theAuthClient);

            dependencyResolver.getLogger().setEnabled(true);
            dependencyResolver.getLogger().setLogLevel(LogLevel.VERBOSE);
        }

        return dependencyResolver;
    }


    private void logon() {

        try {
            Futures.addCallback(this.getDependencyResolver().interactiveInitialize(this), new FutureCallback<Boolean>() {
                @Override
                public void onSuccess(Boolean result) {
                    btnNotebooks.setEnabled(true);
                }

                @Override
                public void onFailure(Throwable t) {
                    getDependencyResolver().getLogger().log(t.getMessage(), LogLevel.ERROR);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private void getNotebooks() {

        Futures.addCallback(getOneNoteClient().getMe().getNote().getNotebooks().read(), new FutureCallback<List<Notebook>>() {
            @Override
            public void onSuccess(List<Notebook> notebooks) {
                List<String> nbNames = new ArrayList<String>();
                for (Notebook nb : notebooks) {
                    nbNames.add(nb.getName());
                }
                final ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1, nbNames);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        lvNotebooks.setAdapter(adapter);
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });

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

        if (id == R.id.action_logout) {
            getDependencyResolver().logout();
            btnNotebooks.setEnabled(false);
            lvNotebooks.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, new String[0]));
        }

        if (id == R.id.action_login) {
            logon();
        }

        return super.onOptionsItemSelected(item);
    }
}
