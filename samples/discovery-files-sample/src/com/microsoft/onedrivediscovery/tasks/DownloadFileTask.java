/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.onedrivediscovery.tasks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.microsoft.onedrivediscovery.DiscoveryAPIApplication;
import com.microsoft.onedrivediscovery.datasource.ListItemsDataSource;
import com.microsoft.onedrivediscovery.viewmodel.FileItem;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

// TODO: Auto-generated Javadoc
/**
 * The Class DownloadFileTask.
 */
public class DownloadFileTask extends AsyncTask<FileItem, Void, FileItem> {

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

	/**
	 * Instantiates a download file task.
	 * 
	 * @param activity
	 *            the activity
	 */
	public DownloadFileTask(Activity activity) {
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
		mDialog.setTitle("Downloading file...");
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
	protected void onPostExecute(FileItem result) {
		if (mDialog.isShowing()) {
			mDialog.dismiss();
		}

		if (mThrowable == null) {

			File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			File file = new File(path, result.getId());

			if (file.exists()) {
				file.delete();
			}

			try {
				file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);

				fos.write(result.getContent());
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			MimeTypeMap myMime = MimeTypeMap.getSingleton();
			Uri uri = Uri.parse("file://" + path + "/" + result.getId());
			Intent newIntent = new Intent(Intent.ACTION_VIEW);

			String mimeType = myMime.getMimeTypeFromExtension(fileExt(uri.toString()).substring(1));
			newIntent.setDataAndType(Uri.fromFile(file), mimeType);
			newIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			try {
				Intent intent = Intent.createChooser(newIntent, "Open File");
				mActivity.startActivity(intent);
			} catch (android.content.ActivityNotFoundException e) {
				Toast.makeText(mActivity, "No handler for this type of file.", Toast.LENGTH_LONG).show();
			}
		} else {
			mApplication.handleError(mThrowable);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected FileItem doInBackground(FileItem... params) {
		FileItem viewItem = params[0];

		if (viewItem != null) {
			try {
				viewItem.setContent(mSource.getFile(viewItem).get());
			} catch (Throwable t) {
				mThrowable = t;
			}
		} else {
			mThrowable = new IllegalArgumentException("params argument must contain at least a FileItem");
		}
		return viewItem;
	}

	private String fileExt(String url) {
		if (url.indexOf("?") > -1) {
			url = url.substring(0, url.indexOf("?"));
		}
		if (url.lastIndexOf(".") == -1) {
			return null;
		} else {
			String ext = url.substring(url.lastIndexOf("."));
			if (ext.indexOf("%") > -1) {
				ext = ext.substring(0, ext.indexOf("%"));
			}
			if (ext.indexOf("/") > -1) {
				ext = ext.substring(0, ext.indexOf("/"));
			}
			return ext.toLowerCase();
		}
	}
}
