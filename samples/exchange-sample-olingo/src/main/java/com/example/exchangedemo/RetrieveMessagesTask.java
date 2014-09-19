/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.example.exchangedemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.microsoft.office365.microsoft.exchange.services.odata.model.types.Message;
import com.microsoft.office365.microsoft.exchange.services.odata.model.types.MessageCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class RetrieveMessagesTask.
 */
public class RetrieveMessagesTask extends AsyncTask<String, Void, ArrayList<MessageViewModel>> {

    private AndroidApplication mApplication;
    private MainActivity mActivity;
    private ProgressDialog mDialog;
    private Context mContext;
    String mFolderId;

    List<MessageViewModel> viewModels = new ArrayList<MessageViewModel>();


    public RetrieveMessagesTask(MainActivity activity) {
        mActivity = activity;
        mContext = activity;
        mDialog = new ProgressDialog(mContext);
        mApplication = (AndroidApplication) mActivity.getApplication();
    }

    protected void onPreExecute() {

        mDialog.setTitle("Retrieving messages...");
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

            MessageCollection messages = mApplication.getEntityContainer()
                    .getMe().getFolders().getByKey(folderId).getMessages()
                    .select("Id,From,Sender,Subject,DateTimeSent")
                    .expand("From,Sender")
                    .top(50)
                    .execute();

            for (Message message : messages) {
                MessageViewModel vm = new MessageViewModel(
                        message.getFrom().getName(),
                        message.getSubject(),
                        message.getDateTimeSent());
                viewModels.add(vm);
            }

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return viewModels;
    }
}
