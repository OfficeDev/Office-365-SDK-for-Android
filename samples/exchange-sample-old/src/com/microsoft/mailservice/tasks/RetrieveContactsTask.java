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
import android.widget.Toast;

import com.microsoft.mailservice.ContactsActivity;
import com.microsoft.mailservice.ExchangeAPIApplication;
import com.microsoft.mailservice.R;
import com.microsoft.mailservice.adapters.ContactItemAdapter;
import com.microsoft.office.microsoft.exchange.services.odata.model.types.ContactCollection;
import com.microsoft.office365.api.ContactClient;

// TODO: Auto-generated Javadoc
/**
 * The Class RetrieveContactsTask.
 */
public class RetrieveContactsTask extends AsyncTask<String, Void, ContactCollection> {

	/** The m dialog. */
	private ProgressDialog mDialog;

	/** The m context. */
	private Context mContext;

	/** The m activity. */
	private ContactsActivity mActivity;
	
	private ExchangeAPIApplication mApplication;
	
	private ContactClient mContactClient;

	public RetrieveContactsTask(ContactsActivity activity) {
		mActivity = activity;
		mContext = activity;
		mApplication = (ExchangeAPIApplication)activity.getApplication();
		mContactClient = mApplication.getContactClient();
		mDialog = new ProgressDialog(mContext);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	protected void onPreExecute() {

		mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

		mDialog.setTitle("Retrieving Contacts...");
		mDialog.setMessage("Please wait.");
		mDialog.setCancelable(false);
		mDialog.setIndeterminate(true);
		mDialog.show();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(ContactCollection contacts) {

		if (mDialog.isShowing()) {
			mDialog.dismiss();
		}

		if (contacts != null) {
		
			ListView contactListView = (ListView) mActivity.findViewById(R.id.contact_list);
			ContactItemAdapter adapter = new ContactItemAdapter(mActivity, contacts);
			contactListView.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			Toast.makeText(mContext, "Finished loading contacts", Toast.LENGTH_LONG).show();

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	protected ContactCollection doInBackground(final String... args) {
		ContactCollection contacts = mContactClient.getContacts();
		return contacts;
	}
}
