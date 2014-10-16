package com.microsoft.simple_exchange_sample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.office365.exchange.services.EmailAddress;
import com.microsoft.office365.exchange.services.ItemBody;
import com.microsoft.office365.exchange.services.Message;
import com.microsoft.office365.exchange.services.Recipient;
import com.microsoft.office365.odata.EntityContainerClient;
import com.microsoft.office365.odata.impl.DefaultDependencyResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  Displays email messages in a ListView
 */
public class MailActivity extends Activity implements View.OnClickListener {

    /**
     * Adapter for the ListView
     */
    private class MessagesAdapter extends ArrayAdapter<Message> {

        /**
         * Caches one item in the list view
         */
        private class ViewHolder {
            TextView from;
            TextView subject;
        }

        /**
         *
         * @param context   the activity Context
         * @param users     the Collection that holds the messages
         */
        public MessagesAdapter(Context context, ArrayList<Message> messages) {
            super(context, R.layout.item_message, messages);
        }

        /**
         * Overrides the ArrayAdapter getView method to retrieve the view for one item
         * @param position      the position of the item in the underlying collection
         * @param convertView   the view of the item
         * @param parent        the containing widget
         * @return              the View of one item of the containing ListView
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Message m = getItem(position);

            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.item_message, parent, false);
                viewHolder.from = (TextView) convertView.findViewById(R.id.tv_from);
                viewHolder.subject = (TextView) convertView.findViewById(R.id.tv_subject);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.from.setText("From: " + m.getFrom().getEmailAddress().getName());
            viewHolder.subject.setText("Subject: " + m.getSubject());

            return convertView;
        }
    }

    MessagesAdapter adapter;
    int messageCounter = 0;
    ExecutorService executor = Executors.newFixedThreadPool(2);

    /**
     * creates the view for this activity and initializes all state
     * @param savedInstanceState the saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        ListView listView = (ListView)findViewById(R.id.listView);
        MessagesAdapter adapter = new MessagesAdapter(this, new ArrayList<Message>());
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        Button checkEmailButton = (Button)findViewById(R.id.button_check_email);
        checkEmailButton.setOnClickListener(this);
        Button sendToSelfButton = (Button)findViewById(R.id.button_send_to_self);
        sendToSelfButton.setOnClickListener(this);
        Button clearAllButton = (Button)findViewById(R.id.button_clear_all);
        clearAllButton.setOnClickListener(this);

        this.adapter = adapter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            final EditText input = new EditText(this);
            input.setText(
                    PreferenceManager
                            .getDefaultSharedPreferences(this)
                            .getString(Constants.EMAIL_TARGET_KEY, Constants.EMAIL_TARGET)
            );

            new AlertDialog.Builder(this)
                .setTitle("Enter your email")
                .setMessage("Enter the email address for your Inbox")
                .setCancelable(true)
                .setView(input)
                .setPositiveButton("Enter", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dlg, int which)
                    {
                        setEmail(input.getText().toString());
                        dlg.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dlg, int which)
                    {
                        dlg.cancel();
                    }
                }).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * handles actions for buttons on the view
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_check_email:
            {
                this.executor.submit(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        return retrieveMails();
                    }
                });
                break;
            }
            case R.id.button_send_to_self:
            {
                this.executor.submit(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        return sendToSelf();
                    }
                });
                break;
            }
            case R.id.button_clear_all:
            {
                this.executor.submit(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        return clearAll();
                    }
                });
                break;
            }
            default:
                break;
        }

    }

    /**
     * retrieves Inbox folder contents
     * @return nothing
     */
    private Void retrieveMails() {

        //create a client object
        EntityContainerClient client = new EntityContainerClient(Constants.ENDPOINT_ID, (DefaultDependencyResolver)Controller.getInstance().getDependencyResolver());

        // retrieve Inbox folder content asynchronously
        ListenableFuture<List<Message>> messages = client   .getMe()
                                                            .getFolders()
                                                            .getById("Inbox")
                                                            .getMessages()
                                                            .execute();

        // handle success and failure cases
        final MessagesAdapter adapter = this.adapter;
        Futures.addCallback(messages, new FutureCallback<List<Message>>() {

            @Override
            public void onSuccess(final List<Message> result) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MailActivity.this, String.valueOf(result.size()) + " messages received", Toast.LENGTH_SHORT).show();
                        adapter.clear();
                        adapter.addAll(result);
                    }
                });

            }

            @Override
            public void onFailure(final Throwable t) {
                Controller.handleError(MailActivity.this, t.getMessage());
            }
        });

        return null;
    }

    /**
     * send one email to the account specified in the preferences
     * @return nothing
     */
    private Void sendToSelf() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        final String email = preferences.getString(Constants.EMAIL_TARGET_KEY, Constants.EMAIL_TARGET);

        if (email.isEmpty()) {
            Controller.handleError(MailActivity.this, "Please set email address in Settings or edit Constants");
            return null;
        }

        try {

            // create a client object
            EntityContainerClient client =
                    new EntityContainerClient(
                            Constants.ENDPOINT_ID,
                            (DefaultDependencyResolver) Controller.getInstance().getDependencyResolver()
                    );

            // craft a message
            final String messageCounter = String.valueOf(++this.messageCounter);

            EmailAddress toEmailAddress = new EmailAddress();
            toEmailAddress.setAddress(email);

            Recipient toRecipient = new Recipient();
            toRecipient.setEmailAddress(toEmailAddress);

            ArrayList<Recipient> toRecipients = new ArrayList<Recipient>();
            toRecipients.add(toRecipient);

            ItemBody body = new ItemBody();
            body.setContent("This is the body of email with subject #" + messageCounter);

            Message m = new Message();
            m.setToRecipients(toRecipients);
            m.setSubject("This is email #" + messageCounter);
            m.setBody(body);

            // prepare message for sending, adding the message to the Drafts folder
            // this operation is synchronous
            Message addedMessage = client.getMe().getMessages().add(m).get();

            // send message asynchronously
            ListenableFuture<Integer> sent = client .getMe()
                                                    .getMessages()
                                                    .getById(addedMessage.getId())
                                                    .getOperations().send();

            // handle success and failure cases
            Futures.addCallback(sent, new FutureCallback<Integer>() {
                @Override
                public void onSuccess(final Integer result) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MailActivity.this, "Message sent", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                @Override
                public void onFailure(final Throwable t) {
                    Controller.handleError(MailActivity.this, t.getMessage());
                }
            });

        } catch (final Throwable t) {
            Controller.handleError(MailActivity.this, t.getMessage());
        }

        return null;
    }

    /**
     * clears all messages in Inbox folder
     * @return nothng
     */
    private Void clearAll() {

        // create one client object
        EntityContainerClient client = new EntityContainerClient(Constants.ENDPOINT_ID, (DefaultDependencyResolver)Controller.getInstance().getDependencyResolver());

        try {

            // get all messages in inbox
            ListenableFuture<List<Message>> fetcher = client    .getMe()
                                                                .getFolders()
                                                                .getById("Inbox")
                                                                .getMessages()
                                                                .execute();

            List<Message> messages = fetcher.get();

            // delete all messages
            for (Message m : messages) {
                client  .getMe()
                        .getFolders()
                        .getById("Inbox")
                        .getMessages()
                        .getById(m.getId())
                        .delete();
            }
        }
        catch (Throwable t) {
            Controller.handleError(MailActivity.this, t.getMessage());
        }

        return null;
    }

    /**
     * store email address for the target Inbox
     * @param email the email address
     */
    private void setEmail(String email) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.EMAIL_TARGET_KEY, email);
        editor.commit();
    }
}
