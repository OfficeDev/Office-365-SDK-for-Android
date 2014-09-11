/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.mailservice.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.microsoft.mailservice.MainActivity;
import com.microsoft.mailservice.R;
import com.microsoft.office.microsoft.exchange.services.odata.model.types.Folder;

public class FolderItemAdapter extends BaseAdapter {

	/** The inflater. */
	private static LayoutInflater inflater = null;
	private List<Folder> mFolder;
	private MainActivity mActivity;

	public FolderItemAdapter(MainActivity activity, List<Folder> folders) {
		mFolder = folders;
		mActivity = activity;
		inflater = (LayoutInflater) mActivity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return mFolder.size();
	}

	@Override
	public Object getItem(int position) {
		return mFolder.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;
		if (convertView == null)
			view = inflater.inflate(R.layout.drawer_list_item, null);
		Folder folder = mFolder.get(position);
		String count = "";

		TextView tv = (TextView) view.findViewById(R.id.folder_name);
		TextView tc = (TextView) view.findViewById(R.id.folder_item_count);
		ImageView iv = (ImageView) view.findViewById(R.id.folder_icons);

		if (folder.getDisplayName().equals("Inbox")) {
			iv.setBackgroundResource(R.color.soft_red);
		} else if (folder.getDisplayName().equals("Drafts")) {
			iv.setBackgroundResource(R.color.soft_orange);
		} else if (folder.getDisplayName().equals("Deleted Items")) {
			iv.setBackgroundResource(R.color.soft_green);
		} else if (folder.getDisplayName().equals("Sent Items")) {
			iv.setBackgroundResource(R.color.soft_violet);
		} else {
			iv.setVisibility(8);
		}

		
		if (folder.getDisplayName().equals("Inbox")) {
			count = " (" + folder.getUnreadItemCount() + ")";
		} else if (folder.getDisplayName().equals("Deleted Items"))
			count = " (" + folder.getUnreadItemCount() + ")";
		else
			count = " (" + folder.getTotalCount() + ")";

		tv.setText(folder.getDisplayName());
		tc.setText(count);
		return view;
	}
}