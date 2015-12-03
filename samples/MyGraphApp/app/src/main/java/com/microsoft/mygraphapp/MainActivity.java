package com.microsoft.mygraphapp;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ToggleButton;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationResult;
import com.microsoft.aad.adal.PromptBehavior;
import com.microsoft.services.graph.Message;
import com.microsoft.services.graph.fetchers.GraphServiceClient;
import com.microsoft.services.orc.auth.AuthenticationCredentials;
import com.microsoft.services.orc.core.DependencyResolver;
import com.microsoft.services.orc.http.Credentials;
import com.microsoft.services.orc.http.impl.OAuthCredentials;
import com.microsoft.services.orc.http.impl.OkHttpTransport;
import com.microsoft.services.orc.serialization.impl.GsonSerializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.NoSuchPaddingException;

public class MainActivity extends AppCompatActivity {
    private AuthenticationContext mAuthContext;
    private DependencyResolver mResolver;
    private GraphServiceClient mClient;
    private ListView mMessagesList;
    private List<MessageDisplayItem> mReturnedMessages;

    static Logger logger = LoggerFactory.getLogger(MainActivity.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final ToggleButton button = (ToggleButton) findViewById(R.id.buttonCheckAll);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mReturnedMessages != null) {

                    for (MessageDisplayItem m : mReturnedMessages) {
                        m.setIsSelected(button.isChecked());
                    }

                    refreshListView();
                }
            }
        });

        final Button buttonMarkRead = (Button) findViewById(R.id.buttonMarkRead);
        buttonMarkRead.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean isRead = true;
                updateMessages(isRead);
            }
        });

        final Button buttonMarkUnRead = (Button) findViewById(R.id.buttonMarkUnRead);
        buttonMarkUnRead.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean isRead = false;
                updateMessages(isRead);
            }
        });


        loginProcess();
    }

    protected void loginProcess() {
        try {
            Futures.addCallback(logon(), new FutureCallback<Boolean>() {
                @Override
                public void onSuccess(Boolean result) {
                    mClient = new GraphServiceClient(getString(R.string.EndpointUrl), mResolver);
                    loadInformation();
                }

                @Override
                public void onFailure(Throwable t) {
                    logger.error("logon", t.getMessage());
                }
            });

        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    protected void updateMessages(boolean read) {
        for (final MessageDisplayItem mItem : mReturnedMessages) {
            if (mItem.getIsSelected()) {
                mItem.setStatus("Updating");
                Message msg = mItem.getMessage();
                msg.setIsRead(read);
                Futures.addCallback(
                        mClient.getMe().getMailFolder("Inbox").getMessages().getById(msg.getId()).update(msg), new FutureCallback<Message>() {
                            @Override
                            public void onSuccess(final Message result) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mItem.setStatus("");
                                        mItem.setMessage(result);
                                        refreshListView();
                                    }
                                });
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                mItem.setStatus("Error");
                                logger.error("MyGraphApp", "Error updating message" + t.getMessage());
                            }
                        });
            }
        }
    }

    protected void refreshListView() {
        MessageAdapter adapter = (MessageAdapter) mMessagesList.getAdapter();

        adapter.clear();
        adapter.addAll(mReturnedMessages);
    }

    protected SettableFuture<Boolean> logon() throws NoSuchPaddingException, NoSuchAlgorithmException {
        final SettableFuture<Boolean> result = SettableFuture.create();

        try {
            mAuthContext = new AuthenticationContext(this, getString(R.string.AADAuthority), true);
            mAuthContext.acquireToken(
                    this,
                    getString(R.string.AADResourceId),
                    getString(R.string.AADClientId),
                    getString(R.string.AADRedirectUrl),
                    PromptBehavior.Auto,
                    new AuthenticationCallback<AuthenticationResult>() {

                        @Override
                        public void onSuccess(final AuthenticationResult authenticationResult) {
                            if (authenticationResult != null
                                    && authenticationResult.getStatus() == AuthenticationResult.AuthenticationStatus.Succeeded) {
                                mResolver = new DependencyResolver.Builder(
                                        new OkHttpTransport(), new GsonSerializer(),
                                        new AuthenticationCredentials() {
                                            @Override
                                            public Credentials getCredentials() {
                                                return new OAuthCredentials(authenticationResult.getAccessToken());
                                            }
                                        }).build();
                                result.set(true);
                            }
                        }

                        @Override
                        public void onError(Exception e) {
                            result.setException(e);
                        }

                    });
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
            result.setException(e);
        }
        return result;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mAuthContext.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.events:
                startActivity(new Intent(this, EventsActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void loadInformation() {
        loadProfilePhoto();
        loadMessages();
    }

    protected void loadProfilePhoto() {
        Futures.addCallback(
                mClient.getMe()
                        .getPhoto()
                        .getStreamedContent()
                ,
                new FutureCallback<InputStream>() {
                    @Override
                    public void onSuccess(final InputStream result) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Do something with image stream
                            }
                        });
                    }

                    @Override
                    public void onFailure(final Throwable t) {
                        logger.error("MyGraphApp", t.getMessage());
                    }
                });
    }

    protected void loadMessages() {
        Futures.addCallback(
                mClient.getMe()
                        .getMailFolders()
                        .getById("Inbox")
                        .getMessages()
                        .top(20)
                        .read(),
                new FutureCallback<List<Message>>() {
                    @Override
                    public void onSuccess(final List<Message> result) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mReturnedMessages = new ArrayList<MessageDisplayItem>();
                                mMessagesList = (ListView) findViewById(R.id.testCaseList);
                                MessageAdapter messageAdapter = new MessageAdapter(MainActivity.this, R.layout.row_list_messages);
                                mMessagesList.setAdapter(messageAdapter);

                                for (Message m : result) {
                                    mReturnedMessages.add(new MessageDisplayItem(m));

                                }

                                refreshListView();
                            }
                        });
                    }

                    @Override
                    public void onFailure(final Throwable t) {
                        logger.error("getMessages", t.getMessage());
                    }
                });
    }

}
