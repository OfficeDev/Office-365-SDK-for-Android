package com.microsoft.office365.test.integration.framework;

public class TestResultInfo {
	private String mTestStatus;
	private String mException;
	private String mTestCaseName;
	private String mTestCaseDescription;

	public TestResultInfo(TestResult testResult){
		mTestStatus = testResult.getStatus().name();
		mException = testResult.getException()== null ? "" : testResult.getException().getMessage();
		mTestCaseName = testResult.getTestCase().getName();
		mTestCaseDescription = testResult.getTestCase().getDescription();
	}
	
	public String getTestStatus(){
		return mTestStatus;		
	}
	
	public String getTestException(){
		return mException;		
	}
	
	public String getTestCaseName(){
		return mTestCaseName;		
	}
	
	public String getmTestCaseDescription(){
		return mTestCaseDescription;		
	}
	
}
