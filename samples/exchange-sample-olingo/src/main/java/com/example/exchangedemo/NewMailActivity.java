package com.example.exchangedemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class NewMailActivity extends Activity {
    EditText txtTo;
    EditText txtCc;
    EditText txtSubject;
    EditText txtBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_mail);

        txtTo = (EditText) findViewById(R.id.textTo);
        txtCc = (EditText) findViewById(R.id.textCC);
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
                case R.id.send: {
                    //validateAndSendEmail();
                    sendMail();
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
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        if (allInformationCompleted()) {
            dlgAlert.setMessage("We can send the email now :)");
        } else {
            dlgAlert.setMessage("Please fill al the fields :(");
        }

        dlgAlert.setTitle("App Title");
        dlgAlert.setPositiveButton("OK", new OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub

            }
        });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    private void sendMail() {
        try {
            new SendMailTask(this).execute("Foo");
        } catch (Throwable t) {
            Log.e("Error", t.getMessage());
        }
    }

    private boolean allInformationCompleted() {
        return (!isNullOrEmpty(txtBody.getText().toString()) &&
                !isNullOrEmpty(txtCc.getText().toString()) &&
                !isNullOrEmpty(txtSubject.getText().toString()) &&
                !isNullOrEmpty(txtTo.getText().toString()));
    }

    private boolean isNullOrEmpty(String val) {
        return (val == null || val.length() == 0);
    }
}


