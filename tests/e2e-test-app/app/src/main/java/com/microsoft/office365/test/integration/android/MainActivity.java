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

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.microsoft.office365.test.integration.ApplicationContext;
import com.microsoft.office365.test.integration.R;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestExecutionCallback;
import com.microsoft.office365.test.integration.framework.TestGroup;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.office365.test.integration.framework.TestResultsPostManager;
import com.microsoft.office365.test.integration.tests.AllTests;
import com.microsoft.office365.test.integration.tests.CalendarTests;
import com.microsoft.office365.test.integration.tests.ContactTests;
import com.microsoft.office365.test.integration.tests.FilesTests;
import com.microsoft.office365.test.integration.tests.MailTests;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity {
	private String mPostUrl = "";
	private boolean mIsAutomatedRun = false;
	private StringBuilder mLog;

	private ListView mTestCaseList;

	private Spinner mTestGroupSpinner;

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// don't restart the activity. Just process the configuration change
		super.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidTestPlatformContext testPlatformContext = new AndroidTestPlatformContext(this);
		ApplicationContext.setTestPlatformContext(testPlatformContext);

		setContentView(R.layout.activity_main);

		mTestCaseList = (ListView) findViewById(R.id.testCaseList);
		TestCaseAdapter testCaseAdapter = new TestCaseAdapter(this, R.layout.row_list_test_case);
		mTestCaseList.setAdapter(testCaseAdapter);

		mTestGroupSpinner = (Spinner) findViewById(R.id.testGroupSpinner);

		ArrayAdapter<TestGroup> testGroupAdapter = new ArrayAdapter<TestGroup>(this,
				android.R.layout.simple_spinner_item);
		mTestGroupSpinner.setAdapter(testGroupAdapter);
		mTestGroupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
				selectTestGroup(pos);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// do nothing
			}
		});

		refreshTestGroupsAndLog();

		checkForCIServer();
	}

	private void selectTestGroup(int pos) {
		TestGroup tg = (TestGroup) mTestGroupSpinner.getItemAtPosition(pos);
		List<TestCase> testCases = tg.getTestCases();

		fillTestList(testCases);
	}

	@SuppressWarnings("unchecked")
	private void refreshTestGroupsAndLog() {
		mLog = new StringBuilder();

		ArrayAdapter<TestGroup> adapter = (ArrayAdapter<TestGroup>) mTestGroupSpinner.getAdapter();
		adapter.clear();
		adapter.add(new AllTests());
		adapter.add(new MailTests());
        adapter.add(new ContactTests());
        adapter.add(new CalendarTests());
        adapter.add(new FilesTests());
		mTestGroupSpinner.setSelection(0);
		selectTestGroup(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings:
			startActivity(new Intent(this, OfficePreferenceActivity.class));
			return true;

		case R.id.menu_run_tests:
			if (ApplicationContext.getServerUrl().trim().equals("")) {
				startActivity(new Intent(this, OfficePreferenceActivity.class));
			} else {
				runTests();
			}
			return true;

		case R.id.menu_check_all:
			changeCheckAllTests(true);
			return true;

		case R.id.menu_uncheck_all:
			changeCheckAllTests(false);
			return true;

		case R.id.menu_reset:
			refreshTestGroupsAndLog();
			return true;

		case R.id.menu_view_log:
			AlertDialog.Builder logDialogBuilder = new AlertDialog.Builder(this);
			logDialogBuilder.setTitle("Log");

			final WebView webView = new WebView(this);

			String logContent = TextUtils.htmlEncode(mLog.toString()).replace("\n", "<br />");
			String logHtml = "<html><body><pre>" + logContent + "</pre></body></html>";
			webView.loadData(logHtml, "text/html", "utf-8");

			logDialogBuilder.setPositiveButton("Copy", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
					clipboardManager.setText(mLog.toString());
				}
			});

			logDialogBuilder.setView(webView);

			logDialogBuilder.create().show();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void changeCheckAllTests(boolean check) {
		TestGroup tg = (TestGroup) mTestGroupSpinner.getSelectedItem();
		List<TestCase> testCases = tg.getTestCases();

		for (TestCase testCase : testCases) {
			if(testCase.isEnabled())
				testCase.setSelected(check);
		}

		fillTestList(testCases);
	}

	private void fillTestList(List<TestCase> testCases) {
		TestCaseAdapter testCaseAdapter = (TestCaseAdapter) mTestCaseList.getAdapter();

		testCaseAdapter.clear();
		for (TestCase testCase : testCases) {
			testCaseAdapter.add(testCase);
		}
	}

	private void runTests() {
		TestGroup group = (TestGroup) mTestGroupSpinner.getSelectedItem();

		group.runTests(new TestExecutionCallback() {

			@Override
			public void onTestStart(TestCase test) {
				TestCaseAdapter adapter = (TestCaseAdapter) mTestCaseList.getAdapter();
				adapter.notifyDataSetChanged();
				log("TEST START", test.getName());
			}

			@Override
			public void onTestGroupComplete(TestGroup group, List<TestResult> results) {
				log("TEST GROUP COMPLETED", group.getName() + " - " + group.getStatus().toString());				
				logSeparator();

				if(mIsAutomatedRun){
					postResults(results);
				}					
			}

			@Override
			public void onTestComplete(TestCase test, TestResult result) {
				Throwable e = result.getException();
				String exMessage = "-";
				if (e != null) {
					StringBuilder sb = new StringBuilder();
					while (e != null) {
						sb.append(e.getClass().getSimpleName() + ": ");
						sb.append(e.getMessage());
						sb.append(" // ");
						e = e.getCause();
					}

					exMessage = sb.toString();
				}

				final TestCaseAdapter adapter = (TestCaseAdapter) mTestCaseList.getAdapter();

				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						adapter.notifyDataSetChanged();

					}

				});
				log("TEST LOG", test.getLog());
				log("TEST COMPLETED", test.getName() + " - " + result.getStatus().toString()
						+ " - Ex: " + exMessage);
				logSeparator();
			}
		}, mIsAutomatedRun);

	}

	private void logSeparator() {
		mLog.append("\n");
		mLog.append("----\n");
		mLog.append("\n");
	}

	@SuppressWarnings("unused")
	private void log(String content) {
		log("Info", content);
	}

	private void log(String title, String content) {
		String message = title + " - " + content;
		Log.d("OFFICE-ANDROID-SDK-INTEGRATION", message);

		mLog.append(message);
		mLog.append('\n');
	}

	private void postResults(List<TestResult> results){
		TestResultsPostManager manager = new TestResultsPostManager(mPostUrl);
		manager.InformResults(results);
	}

	private void checkForCIServer(){
		Bundle extras = this.getIntent ( ).getExtras ( );
		if ( extras != null){
			if( extras.containsKey( "runForCI" ) && extras.getString("runForCI", "false").equalsIgnoreCase("true"))
			{
				if(extras.getString("postUrl", null) != null)
				{
					mIsAutomatedRun = true;
					mPostUrl = extras.getString("postUrl");
					changeCheckAllTests(true);
					runTests();
				}
			}
		}
	}

	/**
	 * Creates a dialog and shows it
	 * 
	 * @param exception
	 *            The exception to show in the dialog
	 * @param title
	 *            The dialog title
	 */
	@SuppressWarnings("unused")
	private void createAndShowDialog(Exception exception, String title) {
		createAndShowDialog(exception.toString(), title);
	}

	/**
	 * Creates a dialog and shows it
	 * 
	 * @param message
	 *            The dialog message
	 * @param title
	 *            The dialog title
	 */
	private void createAndShowDialog(String message, String title) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setMessage(message);
		builder.setTitle(title);
		builder.create().show();
	}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (AndroidTestPlatformContext.context != null) {
            AndroidTestPlatformContext.context.onActivityResult(requestCode, resultCode, data);
        }
    }

}
