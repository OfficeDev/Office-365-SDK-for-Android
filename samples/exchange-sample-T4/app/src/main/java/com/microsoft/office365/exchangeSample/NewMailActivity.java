package com.microsoft.office365.exchangeSample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.exchange.services.odata.model.ItemBody;
import com.microsoft.exchange.services.odata.model.Message;
import com.microsoft.exchange.services.odata.model.Recipient;

import java.util.ArrayList;

public class NewMailActivity extends Activity {
    EditText txtTo;
    EditText txtSubject;
    EditText txtBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_mail);

        txtTo = (EditText) findViewById(R.id.textTo);
        txtSubject = (EditText) findViewById(R.id.textSubject);
        txtBody = (EditText) findViewById(R.id.textBody);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_mail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        try {
            switch (item.getItemId()) {
                case com.microsoft.office365.exchangeSample.R.id.send: {
                    validateAndSendEmail();
                    return true;
                }
                default:
                    return super.onOptionsItemSelected(item);
            }

        } catch (Throwable t) {
            //ErrorHandler.handleError(t, this);
        }
        return true;
    }

    private void validateAndSendEmail() {
        if (allInformationCompleted()) {

            Message message = new Message();

            Recipient recipient = new Recipient();
            recipient.setAddress(txtTo.getText().toString());
            ArrayList<Recipient> recipientList = new ArrayList<Recipient>();

            recipientList.add(recipient);
            message.setToRecipients(recipientList);
            message.setSubject(txtSubject.getText().toString());

            ItemBody itemBody = new ItemBody();
            itemBody.setContent(txtBody.getText().toString());
            message.setBody(itemBody);

            ListenableFuture<Message> future =
                    AppData.getEntityContainer().createMessage(message, "Drafts");

            Futures.addCallback(future, new FutureCallback<Message>() {
                @Override
                public void onSuccess(Message message) {
                    message.getOperations().Send();
                }

                @Override
                public void onFailure(Throwable throwable) {

                }
            });

        }
    }

    private void sendMail() {
        // TODO Auto-generated method stub

    }

    private boolean allInformationCompleted() {
        return (!isNullOrEmpty(txtBody.getText().toString()) &&
                !isNullOrEmpty(txtSubject.getText().toString()) &&
                !isNullOrEmpty(txtTo.getText().toString()));
    }

    private boolean isNullOrEmpty(String val) {
        return (val == null || val.length() == 0);
    }
}


