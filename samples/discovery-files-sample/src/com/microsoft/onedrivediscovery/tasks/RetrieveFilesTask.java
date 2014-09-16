/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.onedrivediscovery.tasks;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import com.microsoft.onedrivediscovery.DiscoveryAPIApplication;
import com.microsoft.onedrivediscovery.FileListActivity;
import com.microsoft.onedrivediscovery.adapters.FileItemAdapter;
import com.microsoft.onedrivediscovery.datasource.ListItemsDataSource;
import com.microsoft.onedrivediscovery.viewmodel.FileViewItem;

// TODO: Auto-generated Javadoc
/**
 * The Class RetrieveFilesTask.
 */
public class RetrieveFilesTask extends AsyncTask<String, Void, ArrayList<FileViewItem>> {

	/** The m dialog. */
	private ProgressDialog mDialog;
	
	/** The m context. */
	private Context mContext;
	
	/** The m activity. */
	private FileListActivity mActivity;
	
	/** The m source. */
	private ListItemsDataSource mSource;
	
	/** The m application. */
	private DiscoveryAPIApplication mApplication;
	
	/** The m throwable. */
	private Throwable mThrowable;
	
	/** The m stored rotation. */
	private int mStoredRotation;

	/**
	 * Instantiates a new retrieve files task.
	 *
	 * @param activity the activity
	 */
	public RetrieveFilesTask(FileListActivity activity) {
		mActivity = activity;
		mContext = activity;
		mDialog = new ProgressDialog(mContext);
		mApplication = (DiscoveryAPIApplication) activity.getApplication();
		mSource = new ListItemsDataSource(mApplication);
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	protected void onPreExecute() {

		mStoredRotation = mActivity.getRequestedOrientation();
		mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

		mDialog.setTitle("Retrieving Files...");
		mDialog.setMessage("Please wait.");
		mDialog.setCancelable(false);
		mDialog.setIndeterminate(true);
		mDialog.show();
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(final ArrayList<FileViewItem> fileItems) {
		if (mDialog.isShowing()) {
			mDialog.dismiss();
			mActivity.setRequestedOrientation(mStoredRotation);
		}
		
		if (fileItems != null) {
			FileItemAdapter adapter = new FileItemAdapter(mActivity, fileItems);
			mActivity.setListAdapter(adapter);
			adapter.notifyDataSetChanged();
			Toast.makeText(mContext, "Finished loading files", Toast.LENGTH_LONG).show();
		} else {
			mApplication.handleError(mThrowable);
		}
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	protected ArrayList<FileViewItem> doInBackground(final String... args) {
		try {
			return mSource.getFiles(args[0],args[1]);
		} catch (Exception e) {
			mThrowable = e;
			return null;
		}
	}
}
