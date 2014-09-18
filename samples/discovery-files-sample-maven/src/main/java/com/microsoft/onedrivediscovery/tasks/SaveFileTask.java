/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.onedrivediscovery.tasks;

import org.json.JSONObject;

import com.microsoft.onedrivediscovery.Constants;
import com.microsoft.onedrivediscovery.DiscoveryAPIApplication;
import com.microsoft.onedrivediscovery.FileListActivity;
import com.microsoft.onedrivediscovery.datasource.ListItemsDataSource;
import com.microsoft.onedrivediscovery.viewmodel.FileItem;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.widget.Toast;

// TODO: Auto-generated Javadoc
/**
 * The Class SaveFileTask.
 */
public class SaveFileTask extends AsyncTask<FileItem, Void, Void> {

	/** The m source. */
	private ListItemsDataSource mSource;

	/** The m activity. */
	private Activity mActivity;

	/** The m dialog. */
	private ProgressDialog mDialog;

	/** The m application. */
	private DiscoveryAPIApplication mApplication;

	/** The m throwable. */
	private Throwable mThrowable;

	private String mResourceId;

	private String mEndpoint;

	/**
	 * Instantiates a SaveFileTask task
	 *
	 * @param activity
	 *            the activity
	 */
	public SaveFileTask(Activity activity) {
		mActivity = activity;
		mDialog = new ProgressDialog(mActivity);
		mApplication = (DiscoveryAPIApplication) activity.getApplication();
		mSource = new ListItemsDataSource(mApplication);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	protected void onPreExecute() {
		mDialog.setTitle("Uploading file...");
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
	protected void onPostExecute(Void result) {
		if (mDialog.isShowing()) {
			mDialog.dismiss();
		}

		if (mThrowable == null) {
			Toast.makeText(mActivity, "File Uploaded.", Toast.LENGTH_SHORT)
					.show();

		} else {
			mApplication.handleError(mThrowable);
		}

		Intent intent = new Intent(mActivity, FileListActivity.class);
		JSONObject payload = new JSONObject();
		try {
			payload.put(mApplication.getAppPreferences().getRedirectUrl(),
					mResourceId);
			payload.put(Constants.ENDPOINT, mEndpoint);
			intent.putExtra(Constants.DATA, payload.toString());
			NavUtils.navigateUpTo(mActivity, intent);
		} catch (Throwable t) {
			Log.e("Asset", t.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected Void doInBackground(FileItem... params) {
		FileItem viewItem = params[0];
		if (viewItem != null) {
			try {
				mResourceId = viewItem.getResourceId();
				mEndpoint = viewItem.getEndpoint();
				mSource.saveFile(viewItem);
			} catch (Throwable t) {
				mThrowable = t;
			}
		} else {
			mThrowable = new IllegalArgumentException(
					"params argument must contain at least a FileItem");
		}
		return null;
	}
}
