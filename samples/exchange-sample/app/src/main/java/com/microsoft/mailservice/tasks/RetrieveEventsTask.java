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

import com.microsoft.mailservice.CalendarEventsActivity;
import com.microsoft.mailservice.ExchangeAPIApplication;
import com.microsoft.mailservice.R;
import com.microsoft.mailservice.adapters.EventItemAdapter;
import com.microsoft.office365.exchange.services.Event;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class RetrieveEventsTask.
 */
public class RetrieveEventsTask extends AsyncTask<String, Void, List<Event>> {

	private ProgressDialog mDialog;

	/** The m context. */
	private Context mContext;

	/** The m activity. */
	private CalendarEventsActivity mActivity;

	/** The m stored rotation. */
	private int mStoredRotation;

	private ExchangeAPIApplication mApplication;

	public RetrieveEventsTask(CalendarEventsActivity activity) {
		mActivity = activity;
		mContext = activity;
		mApplication = (ExchangeAPIApplication) mActivity.getApplication();
		mDialog = new ProgressDialog(mContext);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	protected void onPreExecute() {

		mStoredRotation = mActivity.getRequestedOrientation();
		mActivity
				.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

		mDialog.setTitle("Retrieving Events...");
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
	protected void onPostExecute(List<Event> events) {
		if (mDialog.isShowing()) {
			mDialog.dismiss();
			mActivity.setRequestedOrientation(mStoredRotation);
		}

		if (events != null) {

			EventItemAdapter adapter = new EventItemAdapter(mActivity, events);
			((ListView) mActivity.findViewById(R.id.event_list))
					.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			Toast.makeText(mContext, "Finished loading events",
					Toast.LENGTH_LONG).show();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	protected List<Event> doInBackground(final String... args) {
		List<Event> events = new ArrayList<Event>();
		return events;
	}
}
