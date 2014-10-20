package com.microsoft.office365.test.integration.framework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.util.Log;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.Gson;


public class TestResultsPostManager {
	private String mPostUrl;
	private Map<String, String> mHeaders;
	
	public TestResultsPostManager(String postUrl){
		mPostUrl = postUrl;
		mHeaders = null;
	}
	
	public TestResultsPostManager(String postUrl,Map<String, String> headers ){
		mPostUrl = postUrl;
		mHeaders = headers;
	}

	public void InformResults(List<TestResult> testResults){
		
		List<TestResultInfo> testResultInfos = new ArrayList<TestResultInfo>();
		for (TestResult testResult : testResults) {
			testResultInfos.add(new TestResultInfo(testResult));
		}
		
		String payload = new Gson().toJson(testResultInfos);

        // TODO: implement this
		//Log.d("Paylod to post", payload);
	}
}
