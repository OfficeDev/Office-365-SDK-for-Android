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

import com.microsoft.mailservice.tasks.RetrieveContactsTask;

public class ContactsActivity extends Activity {

	ListView mContactListView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts);
		mContactListView = (ListView) findViewById(R.id.contact_list);

		new RetrieveContactsTask(ContactsActivity.this).execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
}