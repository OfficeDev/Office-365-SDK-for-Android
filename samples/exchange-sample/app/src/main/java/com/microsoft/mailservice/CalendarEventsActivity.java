/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.mailservice;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.microsoft.mailservice.tasks.RetrieveEventsTask;

public class CalendarEventsActivity extends Activity {

	ListView mCalendarEvents;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar_events);

		mCalendarEvents = (ListView) findViewById(R.id.event_list);
		new RetrieveEventsTask(CalendarEventsActivity.this).execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
}