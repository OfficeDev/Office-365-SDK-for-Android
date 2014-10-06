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
package com.microsoft.office365.test.integration.framework;

import android.util.Log;

import com.microsoft.office365.test.integration.ApplicationContext;
import com.microsoft.office365.test.integration.android.Constants;

import java.util.Date;

public abstract class TestCase {
	private String mName;

	private String mDescription;

	private Class<?> mExpectedExceptionClass;

	private boolean mEnabled;

	private boolean mSelected;
	
	private TestStatus mStatus;

	private StringBuilder mTestLog;
	
	private boolean mCanRunAuto;

    private String mFileName;

    private Date mStartTime;

    private Date mEndTime;

	public TestCase(String name) {
		this(name, true);
	}
	
	public TestCase(String name, boolean canRunAuto){
		mEnabled = false;
		mStatus = TestStatus.NotRun;
		mTestLog = new StringBuilder();
		mName = name;
		mCanRunAuto = canRunAuto;
	}

	public TestCase() {
		this(null);
	}

	public void log(String log) {
		mTestLog.append(log);
		mTestLog.append("\n");
	}

	public String getLog() {
		return mTestLog.toString();
	}

	public void clearLog() {
		mTestLog = new StringBuilder();
	}

	public TestStatus getStatus() {
		return mEnabled ? mStatus : TestStatus.Disabled;
	}

	public void setStatus(TestStatus status) {
		mStatus = status;
	}

	public boolean isEnabled() {
		return mEnabled;
	}

	public void setEnabled(boolean enabled) {
		mEnabled = enabled;
	}

	public boolean isSelected() {
		return mSelected;
	}

	public void setSelected(boolean selected) {
		mSelected = selected;
	}
	
	public void run(TestExecutionCallback callback) {
		try {
			if (callback != null)
				callback.onTestStart(this);
		} catch (Exception e) {
			// do nothing
		}
		mStatus = TestStatus.Running;
		try {
			ApplicationContext.executeTest(this, callback);
		} catch (Exception e) {
			StackTraceElement[] stackTrace = e.getStackTrace();
			for (int i = 0; i < stackTrace.length; i++) {
				log("  " + stackTrace[i].toString());
			}

			TestResult result;
			if (e.getClass() != this.getExpectedExceptionClass()) {
				result = createResultFromException(e);
				mStatus = result.getStatus();
			} else {
				result = new TestResult();
				result.setException(e);
				result.setStatus(TestStatus.Passed);
				result.setTestCase(this);
				mStatus = result.getStatus();
			}

			if (callback != null)
				callback.onTestComplete(this, result);
		}
	}

	public abstract TestResult executeTest();

	protected TestResult createResultFromException(Exception e) {
		return createResultFromException(new TestResult(), e);
	}

	protected TestResult createResultFromException(TestResult result, Exception e) {
		result.setException(e);
		result.setTestCase(this);

		result.setStatus(TestStatus.Failed);
		Log.e(Constants.TAG, e.getMessage());
		return result;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public String getDescription() {
		return mDescription;
	}

	public void setDescription(String description) {
		mDescription = description;
	}

	public void setExpectedExceptionClass(Class<?> expectedExceptionClass) {
		mExpectedExceptionClass = expectedExceptionClass;
	}

	public Class<?> getExpectedExceptionClass() {
		return mExpectedExceptionClass;
	}
	
	public boolean CanRunAutomatically(){
		return mCanRunAuto;
	}
	
	public void setRunsAutomatically(boolean canRun){
		mCanRunAuto = canRun;
	}

    public String getFileName() {
        return mFileName;
    }

    public void setFileName(String fileName) {
        this.mFileName = fileName;
    }

    public Date getStartTime() {
        return mStartTime;
    }

    public void setStartTime(Date mStartTime) {
        this.mStartTime = mStartTime;
    }

    public Date getEndTime() {
        return mEndTime;
    }

    public void setEndTime(Date endTime) {
        this.mEndTime = endTime;
    }
}
