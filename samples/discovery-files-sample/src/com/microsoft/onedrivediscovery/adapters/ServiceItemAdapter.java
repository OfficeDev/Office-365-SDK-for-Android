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
import com.microsoft.onedrivediscovery.viewmodel.ServiceViewItem;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceItemAdapter.
 */
public class ServiceItemAdapter extends BaseAdapter {

	/** The m activity. */
	private Activity mActivity;

	/** The m data. */
	private List<ServiceViewItem> mData;

	/** The inflater. */
	private static LayoutInflater inflater = null;

	/**
	 * Instantiates a new car item adapter.
	 * 
	 * @param activity
	 *            the activity
	 * @param data
	 *            the data
	 */
	public ServiceItemAdapter(Activity activity, List<ServiceViewItem> data) {
		mActivity = activity;
		mData = data;
		inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (convertView == null)
			view = inflater.inflate(R.layout.activity_service_list_item, null);

		TextView serviceName = (TextView) view.findViewById(R.id.serviceName);
		TextView resourceId = (TextView) view.findViewById(R.id.resourceId);
		TextView capability = (TextView) view.findViewById(R.id.capability);

		ServiceViewItem item = mData.get(position);
		serviceName.setText(item.getName());
		resourceId.setText(item.getResourceId());
		capability.setText(item.getCapability());

		if (!item.getSelectable()) {
			view.findViewById(R.id.arrow).setVisibility(View.INVISIBLE);
		} else {
			view.findViewById(R.id.arrow).setVisibility(View.VISIBLE);
		}

		return view;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return mData.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}
}
