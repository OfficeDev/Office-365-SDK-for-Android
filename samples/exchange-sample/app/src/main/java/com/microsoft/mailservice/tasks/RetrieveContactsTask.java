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
import com.microsoft.office365.exchange.services.Contact;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class RetrieveContactsTask.
 */
public class RetrieveContactsTask extends AsyncTask<String, Void, List<Contact>> {

	/** The m dialog. */
	private ProgressDialog mDialog;

	/** The m context. */
	private Context mContext;

	/** The m activity. */
	private ContactsActivity mActivity;
	private ExchangeAPIApplication mApplication;
	
	public RetrieveContactsTask(ContactsActivity activity) {
		mActivity = activity;
		mContext = activity;
		mApplication = (ExchangeAPIApplication)activity.getApplication();
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
	protected void onPostExecute(List<Contact> contacts) {

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
	protected List<Contact> doInBackground(final String... args) {
		List<Contact> contacts = new ArrayList<Contact>();
		return contacts;
	}
}
