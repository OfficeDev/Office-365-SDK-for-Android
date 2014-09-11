/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.mailservice.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.microsoft.mailservice.R;
import com.microsoft.office.microsoft.exchange.services.odata.model.types.Event;
import com.microsoft.office.microsoft.exchange.services.odata.model.types.EventCollection;

public class EventItemAdapter extends BaseAdapter {

	/** The inflater. */
	private static LayoutInflater inflater = null;
	private List<Event> mEvents;
	private Activity mActivity;

	public EventItemAdapter(Activity activity, EventCollection events) {
		mEvents = Lists.newArrayList(events);
		mActivity = activity;
		inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return mEvents.size();
	}

	@Override
	public Object getItem(int position) {
		return mEvents.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;
		if (convertView == null)
			view = inflater.inflate(R.layout.activity_event_list_item, null);
		Event event = mEvents.get(position);

		((TextView) view.findViewById(R.id.event_subject)).setText(event.getSubject());
		((TextView) view.findViewById(R.id.event_start)).setText(" Start On: " + event.getStart());
		((TextView) view.findViewById(R.id.event_end)).setText(" - End: " + event.getEnd());
		// ((TextView)
		// view.findViewById(R.id.contact_list_name)).setText(event.getLocation());
		// ((TextView)
		// view.findViewById(R.id.contact_list_name)).setText(event.get);

		return view;
	}
}