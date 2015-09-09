package com.microsoft.office365.test.integration.android;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.microsoft.office365.test.integration.R;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestGroup;
import com.microsoft.office365.test.integration.framework.TestLog;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.office365.test.integration.framework.TestStatus;
import com.microsoft.services.orc.serialization.impl.GsonSerializer;

import junit.framework.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LogActivity extends Activity {

    private ListView mTestLogList;
    private Spinner mTestLogSpinner;
    private List<TestLog> mTestResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("TestResults");

            Gson serializer = new Gson();
            Type listType = new TypeToken<ArrayList<TestLog>>() {
            }.getType();
            mTestResults = serializer.fromJson(value, listType);

        }

        mTestLogList = (ListView) findViewById(R.id.testLogList);
        TestLogAdapter testLogAdapter = new TestLogAdapter(this, R.layout.row_list_test_log);
        mTestLogList.setAdapter(testLogAdapter);



        mTestLogSpinner = (Spinner) findViewById(R.id.testResultSpinner);

        ArrayAdapter<String> testGroupAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item);
        mTestLogSpinner.setAdapter(testGroupAdapter);
        mTestLogSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                selectLogResults(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // do nothing
            }
        });
        refreshTestGroupsAndLog();
    }

    private void selectLogResults(int pos) {
        //fillTestResultList(mTestResults, false);
        if(mTestLogSpinner != null) {
            String tr = (String) mTestLogSpinner.getItemAtPosition(pos);
            if (tr.equals("All")) {
                fillTestResultList(mTestResults, false);
            } else {
                fillTestResultList(mTestResults, true);
            }
        }
    }

    private void fillTestResultList(List<TestLog> testResults, boolean showOnlyFailed) {
        if (testResults != null) {
            TestLogAdapter testLogAdapter = (TestLogAdapter) mTestLogList.getAdapter();

            testLogAdapter.clear();
            for (TestLog testResult : testResults) {
                if (!showOnlyFailed || (showOnlyFailed && testResult.getTestStatus().equals(TestStatus.Failed))) {
                    testLogAdapter.add(testResult);
                }
            }
        }
    }

    private void refreshTestGroupsAndLog() {

        ArrayAdapter<String> adapter = (ArrayAdapter<String>) mTestLogSpinner.getAdapter();
        adapter.clear();
        adapter.add("All");
        adapter.add("Only failed");
        mTestLogSpinner.setSelection(0);
        selectLogResults(0);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
