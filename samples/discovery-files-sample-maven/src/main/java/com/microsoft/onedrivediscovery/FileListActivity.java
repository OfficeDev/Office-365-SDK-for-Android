/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.onedrivediscovery;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.microsoft.onedrivediscovery.R;
import com.microsoft.onedrivediscovery.adapters.FileItemAdapter;
import com.microsoft.onedrivediscovery.tasks.DownloadFileTask;
import com.microsoft.onedrivediscovery.tasks.RetrieveFilesTask;
import com.microsoft.onedrivediscovery.viewmodel.FileItem;
import com.microsoft.onedrivediscovery.viewmodel.FileViewItem;

// TODO: Auto-generated Javadoc
/**
 * The Class FileListActivity.
 */
public class FileListActivity extends FragmentActivity {

	/** The m list view. */
	private ListView mListView;

	private String resourseId;
	private String endpoint;
	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_lists);

		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			String data = bundle.getString(Constants.DATA);
			if (data != null) {
				JSONObject payload;
				try {
					payload = new JSONObject(data);
					resourseId = payload.getString(Constants.RESOURCEID);
					endpoint = payload.getString(Constants.ENDPOINT);

					new RetrieveFilesTask(FileListActivity.this).execute(resourseId, endpoint);
				} 
				catch (JSONException e) {
					Log.e("Asset", e.getMessage());
				}
			}
		}

		mListView = (ListView) findViewById(R.id.list);

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3) {
				final FileViewItem serviceItem = (FileViewItem) mListView.getItemAtPosition(position);

				if(serviceItem.getSelectable()){
					AlertDialog.Builder builder = new AlertDialog.Builder(FileListActivity.this);

					builder.setMessage("Download File?")
					.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {

							FileItem file = new FileItem();
							file.setId(serviceItem.getId());
							file.setEndpoint(endpoint);
							file.setResourceId(resourseId);
							new DownloadFileTask(FileListActivity.this).execute(file);
						}
					}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
						}
					}).show();
				}
			}
		});
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home: {
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		case R.id.menu_new_file: {
			Intent intent = new Intent(FileListActivity.this, FileItemActivity.class);
			JSONObject payload = new JSONObject();
			try {
				payload.put(Constants.RESOURCEID, resourseId);
				payload.put(Constants.ENDPOINT, endpoint);
				payload.put("uri", "");
				intent.putExtra(Constants.DATA, payload.toString());
				startActivity(intent);
			} catch (Throwable t) {
				Log.e("Asset", t.getMessage());
			}

			return true;
		}
		case R.id.menu_refresh: {
			new RetrieveFilesTask(FileListActivity.this).execute(resourseId, endpoint);
			return true;
		}
		default:
			return true;
		}
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		NavUtils.navigateUpFromSameTask(this);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.file_list_menu, menu);
		return true;
	}

	/**
	 * Sets the list adapter.
	 *
	 * @param adapter the new list adapter
	 */
	public void setListAdapter(FileItemAdapter adapter) {
		mListView.setAdapter(adapter);
	}
}
