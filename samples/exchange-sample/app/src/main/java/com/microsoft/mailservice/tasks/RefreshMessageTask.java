/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.mailservice.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;

import com.microsoft.mailservice.ErrorHandler;
import com.microsoft.mailservice.ExchangeAPIApplication;
import com.microsoft.mailservice.adapters.MessageItemAdapter;
import com.microsoft.office365.exchange.services.Message;

import java.util.List;

//TODO:Review. This looks so familiar to retrieve messages
public class RefreshMessageTask extends AsyncTask<String, Void, List<Message>> {

	private Activity mActivity;
	private MessageItemAdapter mAdapter;
	private ExchangeAPIApplication mApplication;

	SwipeRefreshLayout mSwipeRefreshLayout;

	/** The m dialog. */
	private ProgressDialog mDialog;

	/** The m context. */
	private Context mContext;

	public RefreshMessageTask(Activity activity, MessageItemAdapter adpater, SwipeRefreshLayout swipeRefreshLayout) {
		mActivity = activity;
		mContext = mActivity;
		mAdapter = adpater;
		mSwipeRefreshLayout = swipeRefreshLayout;
		mApplication = (ExchangeAPIApplication) activity.getApplication();
		mDialog = new ProgressDialog(mContext);
	}

	@Override
	protected void onPostExecute(List<Message> result) {
		super.onPostExecute(result);

		if (mDialog.isShowing()) {
			mDialog.dismiss();
		}

		if (mAdapter != null) {
			mAdapter.clear();
			mAdapter.addMoreItems(result);
			mAdapter.notifyDataSetChanged();
		}

		mSwipeRefreshLayout.setRefreshing(false);
		mSwipeRefreshLayout.setEnabled(true);
	}

	@Override
	protected void onPreExecute() {

		mDialog.setTitle("Retrieving information...");
		mDialog.setMessage("Please wait.");
		mDialog.setCancelable(false);
		mDialog.setIndeterminate(true);
		mDialog.show();
		
		mSwipeRefreshLayout.setEnabled(false);
		super.onPreExecute();
	}

	@Override
	protected List<Message> doInBackground(String... args) {
		List<Message> messages = null;
		String folderId = args[0];

		try {
			//MailClient mailClient = mApplication.getMailClient();
			//messages = mailClient.getMessages(folderId);
		} catch (Exception e) {
			ErrorHandler.handleError(e, mActivity);
		}
		return messages;
	}
}