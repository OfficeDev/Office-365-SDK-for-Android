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
import com.microsoft.office.microsoft.exchange.services.odata.model.types.EventCollection;
import com.microsoft.office365.api.CalendarClient;

// TODO: Auto-generated Javadoc
/**
 * The Class RetrieveEventsTask.
 */
public class RetrieveEventsTask extends AsyncTask<String, Void, EventCollection> {

	private ProgressDialog mDialog;

	/** The m context. */
	private Context mContext;

	/** The m activity. */
	private CalendarEventsActivity mActivity;

	/** The m stored rotation. */
	private int mStoredRotation;

	private ExchangeAPIApplication mApplication;
	private CalendarClient mCalendarClient;

	public RetrieveEventsTask(CalendarEventsActivity activity) {
		mActivity = activity;
		mContext = activity;
		mApplication = (ExchangeAPIApplication) mActivity.getApplication();
		mCalendarClient = mApplication.getCalendarClient();
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
	protected void onPostExecute(EventCollection events) {
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
	protected EventCollection doInBackground(final String... args) {
		EventCollection events = mCalendarClient.getEvents();
		return events;
	}
}
