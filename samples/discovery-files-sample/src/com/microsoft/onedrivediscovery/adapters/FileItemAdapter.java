/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.onedrivediscovery.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.microsoft.assetmanagement.R;
import com.microsoft.onedrivediscovery.viewmodel.FileViewItem;

// TODO: Auto-generated Javadoc
/**
 * The Class FileItemAdapter.
 */
public class FileItemAdapter extends BaseAdapter {

	/** The m activity. */
	private Activity mActivity;

	/** The m data. */
	private List<FileViewItem> mData;

	/** The inflater. */
	private static LayoutInflater inflater = null;

	/**
	 * Instantiates a new file item adapter.
	 *
	 * @param activity the activity
	 * @param data the data
	 */
	public FileItemAdapter(Activity activity, List<FileViewItem> data) {
		mActivity = activity;
		mData = data;
		inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (convertView == null)
			view = inflater.inflate(R.layout.activity_file_list_item, null);

		TextView fileName = (TextView) view.findViewById(R.id.fileName);
		TextView createdOn = (TextView)view.findViewById(R.id.fileCreatedOn);
		FileViewItem item = mData.get(position);
		fileName.setText(item.getName());
		createdOn.setText(item.getCreatedOn());
		view.findViewById(R.id.folder).setVisibility(item.getSelectable()? View.INVISIBLE :View.VISIBLE);
		
		return view;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return mData.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}
}
