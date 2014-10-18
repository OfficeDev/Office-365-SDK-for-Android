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
import com.microsoft.office365.Platform;
import com.microsoft.office365.http.HttpConnection;
import com.microsoft.office365.http.Request;
import com.microsoft.office365.http.Response;


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

		Log.d("Paylod to post", payload);
		
		executeRequest("POST",payload.getBytes());
	}

	private ListenableFuture<byte[]> executeRequest(String method, byte[] payload) {
		HttpConnection connection = Platform.createHttpConnection();

		Request request = new Request(method);

		if (mHeaders != null) {
			for (String key : mHeaders.keySet()) {
				request.addHeader(key, mHeaders.get(key));
			}
		}

		request.setUrl(mPostUrl);
		request.setContent(payload);

		final SettableFuture<byte[]> result = SettableFuture.create();
		final ListenableFuture<Response> future = connection.execute(request);

		Futures.addCallback(future, new FutureCallback<Response>() {
			@Override
			public void onFailure(Throwable t) {
				result.setException(t);
			}

			@Override
			public void onSuccess(Response response) {
				try {
					int statusCode = response.getStatus();
					if (statusCode >= 200 && statusCode <= 299) {
						byte[] responseContentBytes = response.readAllBytes();
						result.set(responseContentBytes);
					} else {
						result.setException(new Exception("Invalid status code " + statusCode + ": "
								+ response.readToEnd()));
					}
				} catch (IOException e) {

				}
			}
		});
		return result;
	}
}
