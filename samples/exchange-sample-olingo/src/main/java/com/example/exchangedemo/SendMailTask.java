/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.example.exchangedemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.microsoft.office365.microsoft.exchange.services.odata.model.types.BodyType;
import com.microsoft.office365.microsoft.exchange.services.odata.model.types.ItemBody;
import com.microsoft.office365.microsoft.exchange.services.odata.model.types.Message;
import com.microsoft.office365.microsoft.exchange.services.odata.model.types.Recipient;
import com.microsoft.office365.microsoft.exchange.services.odata.model.types.RecipientCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class RetrieveMessagesTask.
 */
public class SendMailTask extends AsyncTask<String, Void, ArrayList<MessageViewModel>> {

    private AndroidApplication mApplication;
    private Activity mActivity;
    private ProgressDialog mDialog;
    private Context mContext;
    String mFolderId;

    List<MessageViewModel> viewModels = new ArrayList<MessageViewModel>();


    public SendMailTask(Activity activity) {
        mActivity = activity;
        mContext = activity;
        mDialog = new ProgressDialog(mContext);
        mApplication = (AndroidApplication) mActivity.getApplication();
    }

    protected void onPreExecute() {

        mDialog.setTitle("Sending e-mail...");
        mDialog.setMessage("Please wait.");
        mDialog.setCancelable(false);
        mDialog.setIndeterminate(true);
        mDialog.show();

        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
    }

    @Override
    protected void onPostExecute(ArrayList<MessageViewModel> messages) {

        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }

        if (messages != null) {
            SimpleExchangeAdapter adapter = new SimpleExchangeAdapter(mActivity, messages);
            ((ListView) mActivity.findViewById(R.id.mail_list)).setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see android.os.AsyncTask#doInBackground(Params[])
     */
    protected ArrayList<MessageViewModel> doInBackground(final String... args) {
        ArrayList<MessageViewModel> viewModels = new ArrayList<MessageViewModel>();
        String folderId = args[0];

        try {

            Message message = mApplication.getEntityContainer().newEntityInstance(Message.class);
            Recipient recipient = mApplication.getEntityContainer().newComplexInstance(Recipient.class);

            //recipient.setAddress(txtTo.getText().toString());
            recipient.setAddress("marcost@lagash.com");
            //recipient.setName(txtTo.getText().toString());
            recipient.setName("Marcote");

            RecipientCollection recipients = mApplication.getEntityContainer()
                    .newComplexCollection(RecipientCollection.class);
            recipients.add(recipient);

            /*
            // CC Recipient
            Recipient recipientCC = mApplication.getEntityContainer().newComplexInstance(Recipient.class);
            recipientCC.setAddress("v-marcto@microsoft.com");
            recipientCC.setName("MarcoTTe");

            RecipientCollection recipientsCC = mApplication.getEntityContainer()
                    .newComplexCollection(RecipientCollection.class);
            recipientsCC.add(recipientCC);

            */

            ItemBody itemBody = mApplication.getEntityContainer().newComplexInstance(ItemBody.class);
            itemBody.setContent("some body message");
            itemBody.setContentType(BodyType.Text);

            message.setSubject("some subject");
            message.setToRecipients(recipients);
            // message.setCcRecipients(recipientsCC);
            message.setBody(itemBody);
            message.operations().send().execute();

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return viewModels;
    }
}
