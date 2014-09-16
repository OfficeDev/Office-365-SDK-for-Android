/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.onedrivediscovery;

import java.util.Map;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.assetmanagement.R;
import com.microsoft.office365.Credentials;
import com.microsoft.onedrivediscovery.adapters.ServiceItemAdapter;
import com.microsoft.onedrivediscovery.tasks.RetrieveServicesTask;
import com.microsoft.onedrivediscovery.viewmodel.ServiceViewItem;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceListActivity.
 */
public class ServiceListActivity extends FragmentActivity {

	/** The m list view. */
	private ListView mListView;

	/** The m application. */
	private DiscoveryAPIApplication mApplication;
	private AppPreferences mPreferences;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_lists);

		mApplication = (DiscoveryAPIApplication) getApplication();
		mPreferences = mApplication.getAppPreferences();
		mListView = (ListView) findViewById(R.id.list);

		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1,
					int position, long arg3) {

				final ServiceViewItem serviceItem = (ServiceViewItem) mListView
						.getItemAtPosition(position);

				if (serviceItem.getSelectable()) {
					try {

						ListenableFuture<Map<String, Credentials>> future = Authentication
								.authenticate(ServiceListActivity.this,
										((ServiceViewItem) mListView
												.getItemAtPosition(position))
												.getResourceId(), mPreferences);

						Futures.addCallback(future,
								new FutureCallback<Map<String, Credentials>>() {
									@Override
									public void onFailure(Throwable t) {
										Log.e("Asset", t.getMessage());
									}

									@Override
									public void onSuccess(
											Map<String, Credentials> credentials) {
										openSelectedService(serviceItem);
									}
								});

					} catch (Throwable t) {
						Log.e("Asset", t.getMessage());
					}
				}
			}
		});
		
		new RetrieveServicesTask(ServiceListActivity.this).execute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home: {
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		case R.id.menu_refresh: {
			new RetrieveServicesTask(ServiceListActivity.this).execute();
			return true;
		}
		default:
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		NavUtils.navigateUpFromSameTask(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.service_list_menu, menu);
		return true;
	}

	/**
	 * Sets the list adapter.
	 * 
	 * @param adapter
	 *            the new list adapter
	 */
	public void setListAdapter(ServiceItemAdapter adapter) {
		mListView.setAdapter(adapter);
	}

	/**
	 * Open selected car.
	 * 
	 * @param serviceItem
	 *            the ServiceViewItem
	 */
	public void openSelectedService(ServiceViewItem serviceItem) {

		Intent intent = new Intent(mApplication, FileListActivity.class);

		JSONObject payload = new JSONObject();
		try {
			payload.put(Constants.RESOURCEID, serviceItem.getResourceId());
			payload.put(Constants.ENDPOINT, serviceItem.getEndpointUri());

			intent.putExtra("data", payload.toString());
			startActivity(intent);
		} catch (Throwable t) {
			mApplication.handleError(t);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		Authentication.context.onActivityResult(requestCode, resultCode, data);
	}
}
