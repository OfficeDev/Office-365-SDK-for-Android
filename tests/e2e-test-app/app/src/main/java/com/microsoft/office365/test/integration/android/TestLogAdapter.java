/*
Copyright (c) Microsoft Open Technologies, Inc.
All Rights Reserved
Apache 2.0 License

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

See the Apache Version 2.0 License for specific language governing permissions and limitations under the License.
 */
package com.microsoft.office365.test.integration.android;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.microsoft.office365.test.integration.R;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestLog;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.office365.test.integration.framework.TestStatus;

import junit.framework.Test;

/**
 * Adapter to bind a ToDoItem List to a view
 */
public class TestLogAdapter extends ArrayAdapter<TestLog> {

	/**
	 * Adapter mAuthContext
	 */
	Context mContext;

	/**
	 * Adapter View layout
	 */
	int mLayoutResourceId;

	public TestLogAdapter(Context context, int layoutResourceId) {
		super(context, layoutResourceId);

		mContext = context;
		mLayoutResourceId = layoutResourceId;
	}

	/**
	 * Returns the view for a specific item on the list
	 */
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View row = convertView;

		final TestLog testResult = getItem(position);

		if (row == null) {
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			row = inflater.inflate(mLayoutResourceId, parent, false);
		}

		final CheckBox checkBox = (CheckBox) row.findViewById(R.id.checkTestLog);

		String text = String.format("%s - %s", testResult.getName(), testResult.getTestStatus().toString());

		if (testResult.getTestStatus() == TestStatus.Failed) {
			checkBox.setTextColor(Color.RED);
		} else if (testResult.getTestStatus() == TestStatus.Passed) {
			checkBox.setTextColor(Color.GREEN);
		} else {
			checkBox.setTextColor(Color.GRAY);
		}

		checkBox.setText(text);
		/*
		checkBox.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				testResult.getTestCase().setSelected(checkBox.isChecked());
			}
		});
*/
		final TextView textView = (TextView) row.findViewById(R.id.textLog);

        String errorMessage = testResult.getException() == null ? " - " : testResult.getException().getLocalizedMessage();
        textView.setText(errorMessage);

		return row;
	}

}
