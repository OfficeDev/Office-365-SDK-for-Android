/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.mailservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import com.microsoft.mailservice.adapters.FolderItemAdapter;
import com.microsoft.mailservice.adapters.MessageItemAdapter;
import com.microsoft.mailservice.tasks.RefreshMessageTask;
import com.microsoft.mailservice.tasks.RetrieveFoldersTask;
import com.microsoft.mailservice.tasks.RetrieveMessagesTask;
import com.microsoft.mailservice.tasks.RetrieveMoreMessageTask;
import com.microsoft.office365.microsoft.exchange.services.odata.model.types.Folder;
import com.microsoft.office365.microsoft.exchange.services.odata.model.types.Message;
import com.microsoft.office365.microsoft.exchange.services.odata.model.types.MessageCollection;

/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {

	public static ListView mMailListView;
	ListView mListPrimaryFolderView;
	ListView mListSecondaryFolderView;
	TextView mFolderTextView;
	TextView mContacts;
	TextView mCalendar;

	SwipeRefreshLayout mSwipeRefreshLayout;
	AppPreferences mAppPreferences;
	ExchangeAPIApplication mApplication;

	// TODO: review this and do in a better way
	static Map<String, MessageCollection> mMessages = new HashMap<String, MessageCollection>();
	static Map<String, List<Folder>> mFolders;
	static Folder mLastSelectedFolder;

	DrawerLayout mDrawerLayout;
	ActionBarDrawerToggle mDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mApplication = (ExchangeAPIApplication) getApplication();
		mAppPreferences = mApplication.getAppPreferences();
		initialize();
		load();
	}

	private void load() {
		if (mApplication.hasConfiguration()) {
			Authentication.createEncryptionKey(getApplicationContext());
			startAuthentication();
		} else {
			startActivity(new Intent(this, AppPreferencesActivity.class));
		}
	}

	private void startAuthentication() {
		ListenableFuture<Void> authFuture = Authentication.authenticate(this);
		Futures.addCallback(authFuture, new FutureCallback<Void>() {

			@Override
			public void onFailure(final Throwable t) {
				runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(MainActivity.this, "Error while getting credentials: " + t.getMessage(),
								Toast.LENGTH_LONG).show();
					}
				});
			}

			@Override
			public void onSuccess(Void v) {
				onAuthenticationSuccessfull();
			}
		});
	}

	protected void onAuthenticationSuccessfull() {

		if (mFolders == null) {
			new RetrieveFoldersTask(MainActivity.this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} else {
			mListPrimaryFolderView.setAdapter(new FolderItemAdapter(this, mFolders.get("Primary")));
			mListSecondaryFolderView.setAdapter(new FolderItemAdapter(this, mFolders.get("Secondary")));
		}

		mListPrimaryFolderView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
				mLastSelectedFolder = (Folder) mListPrimaryFolderView.getItemAtPosition(position);
				retrieveMesages(mLastSelectedFolder.getId());
			}
		});

		mListSecondaryFolderView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				mLastSelectedFolder = (Folder) mListSecondaryFolderView.getItemAtPosition(position);
				retrieveMesages(mLastSelectedFolder.getId());
			}
		});
	}

	private void initialize() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		mListPrimaryFolderView = (ListView) findViewById(R.id.list_primary_foders);
		mListSecondaryFolderView = (ListView) findViewById(R.id.list_secondary_foders);

		mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.layout_to_refresh);
		mSwipeRefreshLayout.setOnRefreshListener(this);
		mSwipeRefreshLayout.setColorScheme(android.R.color.holo_blue_light, android.R.color.white,
				android.R.color.holo_blue_light, android.R.color.white);

		mMailListView = (ListView) findViewById(R.id.mail_list);
		mMailListView.setOnItemClickListener(mailListOnItemClick());
		mMailListView.setOnScrollListener(new StateScrollListener());

		mContacts = (TextView) findViewById(R.id.contacts);
		mContacts.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
				startActivity(intent);
			}
		});

		mCalendar = (TextView) findViewById(R.id.calendar);
		mCalendar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this, CalendarEventsActivity.class);
				startActivity(intent);
			}
		});

		setDrawerIconEvent();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		if (mDrawerToggle != null) {
			mDrawerToggle.syncState();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Authentication.context.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		try {
			switch (item.getItemId()) {
			case R.id.menu_clear_credentials:
				((ExchangeAPIApplication) getApplication()).clearPreferences(this);
				startAuthentication();
				break;
			case R.id.menu_settings: {
				startActivity(new Intent(this, AppPreferencesActivity.class));
				return true;
			}
			case R.id.menu_get_mails: {
				load();
				return true;
			}

			default:
				if (mDrawerToggle.onOptionsItemSelected(item))
					return true;

				return super.onOptionsItemSelected(item);
			}

		} catch (Throwable t) {
			ErrorHandler.handleError(t, this);
		}
		return true;
	}

	@Override
	public void onRefresh() {

		try {
			Message message = (Message) mMailListView.getItemAtPosition(0);

			String lastDate;
			if (message != null) {
				lastDate = message.getLastModifiedTime().toString();
			} else {
				lastDate = null;
			}

			MessageItemAdapter adapter = (MessageItemAdapter) mMailListView.getAdapter();
			Folder selectedFolder = mLastSelectedFolder;
			if (selectedFolder != null) {
				new RefreshMessageTask(MainActivity.this, adapter, mSwipeRefreshLayout).execute(selectedFolder.getId(),
						lastDate);
			}
		} catch (Throwable t) {
			ErrorHandler.handleError(t, this);
		}
	}

	OnItemClickListener mailListOnItemClick() {
		return new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3) {

				if (((Message) MainActivity.mMailListView.getItemAtPosition(position)).getId() != null) {
					Intent intent = new Intent(MainActivity.this, MailActivity.class);
					JSONObject payload = new JSONObject();
					try {
						payload.put("position", position);
						intent.putExtra("data", payload.toString());
						startActivity(intent);
					} catch (Throwable t) {
						ErrorHandler.handleError(t, MainActivity.this);
					}
				}
			}
		};
	}

	public void setMessages(String folderId, MessageCollection messages) {

		mMessages.put(folderId, messages);
	}

	public void setFolders(Map<String, List<Folder>> folders) {
		mFolders = folders;

		if (folders.get("Primary") != null)
			mLastSelectedFolder = (Folder) folders.get("Primary").get(0);
	}

	public void retrieveMesages(String folder) {

		if (!mMessages.containsKey(folder)) {
			new RetrieveMessagesTask(MainActivity.this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, folder);
		} else {
			mMailListView.setAdapter(new MessageItemAdapter(this, mMessages.get(folder)));
		}

		if (mDrawerToggle != null) {
			mDrawerLayout.closeDrawers();
			mDrawerToggle.syncState();
		}
	}

	private void setDrawerIconEvent() {

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				invalidateOptionsMenu();

				if (mLastSelectedFolder != null)
					getActionBar().setTitle(mLastSelectedFolder.getDisplayName());

			}

			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				invalidateOptionsMenu();
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	private class StateScrollListener implements AbsListView.OnScrollListener {

		private int mCurrentVisibleItemCount;
		private int mCurrentFirstVisibleItem;
		private int mCurrentTotalItemCount;
		private int mScrollState;

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

			mCurrentFirstVisibleItem = firstVisibleItem;
			mCurrentVisibleItemCount = visibleItemCount;
			mCurrentTotalItemCount = totalItemCount;

		}

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			mScrollState = scrollState;

			if (mCurrentTotalItemCount > 0
					&& ((mCurrentFirstVisibleItem + mCurrentVisibleItemCount) >= mCurrentTotalItemCount)
					&& (mCurrentFirstVisibleItem != 0) && mScrollState == SCROLL_STATE_IDLE) {

				new RetrieveMoreMessageTask(MainActivity.this, (MessageItemAdapter) mMailListView.getAdapter())
						.execute(Integer.toString(mCurrentTotalItemCount), mLastSelectedFolder.getId());
			}
		}

	}
}
