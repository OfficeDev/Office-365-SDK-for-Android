/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.mailservice.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.widget.ListView;

import com.microsoft.mailservice.ErrorHandler;
import com.microsoft.mailservice.ExchangeAPIApplication;
import com.microsoft.mailservice.MainActivity;
import com.microsoft.mailservice.R;
import com.microsoft.mailservice.adapters.MessageItemAdapter;
import com.microsoft.office365.microsoft.exchange.services.odata.model.types.MessageCollection;
import com.microsoft.office365.api.MailClient;

/**
 * The Class RetrieveMessagesTask.
 */
public class RetrieveMessagesTask extends AsyncTask<String, Void, MessageCollection> {

    private ExchangeAPIApplication mApplication;

    /**
     * The m activity.
     */
    private MainActivity mActivity;

    /**
     * The m dialog.
     */
    private ProgressDialog mDialog;

    /**
     * The m context.
     */
    private Context mContext;

    String mFolderId;

    public RetrieveMessagesTask(MainActivity activity) {
        mActivity = activity;
        mContext = activity;
        mDialog = new ProgressDialog(mContext);
        mApplication = (ExchangeAPIApplication) mActivity.getApplication();
    }

    /*
     * (non-Javadoc)
     *
     * @see android.os.AsyncTask#onPreExecute()
     */
    protected void onPreExecute() {

        mDialog.setTitle("Retrieving messages...");
        mDialog.setMessage("Please wait.");
        mDialog.setCancelable(false);
        mDialog.setIndeterminate(true);
        mDialog.show();

        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
     */
    @Override
    protected void onPostExecute(MessageCollection messages) {

        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }

        if (messages != null) {
            mActivity.setMessages(mFolderId, messages);
            MessageItemAdapter adapter = new MessageItemAdapter(mActivity, messages);
            ((ListView) mActivity.findViewById(R.id.mail_list)).setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see android.os.AsyncTask#doInBackground(Params[])
     */
    protected MessageCollection doInBackground(final String... args) {
        MessageCollection messages = null;
        mFolderId = args[0];
        try {
            MailClient mailClient = mApplication.getMailClient();
            messages = mailClient.getMessages(mFolderId,
                                 "Id,From,Sender,Subject,BodyPreview,DateTimeSent,LastModifiedTime",
                                 "From,Sender", 0, 0);
        } catch (Exception e) {
            ErrorHandler.handleError(e, mActivity);
        }
        return messages;
    }
}
