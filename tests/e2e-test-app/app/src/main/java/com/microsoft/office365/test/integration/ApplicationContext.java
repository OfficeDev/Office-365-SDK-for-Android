package com.microsoft.office365.test.integration;

import com.microsoft.directoryservices.odata.DirectoryClient;
import com.microsoft.discoveryservices.odata.DiscoveryClient;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestExecutionCallback;
import com.microsoft.outlookservices.odata.OutlookClient;
import com.microsoft.sharepointservices.ListClient;
import com.microsoft.fileservices.odata.SharePointClient;

import java.util.concurrent.Future;

public class ApplicationContext {
	
    private static TestPlatformContext mTestPlatformContext;
    
    public static void setTestPlatformContext(TestPlatformContext testPlatformContext) {
        mTestPlatformContext = testPlatformContext;
    }
    
	public static void sleep() throws Exception {
	    mTestPlatformContext.sleep(3);
	}
	
	public static void sleep(int seconds) throws Exception {
        mTestPlatformContext.sleep(seconds);
    }
	
	public static Future<Void> showMessage(String message) {
        return mTestPlatformContext.showMessage(message);
    }
    
    public static void executeTest(TestCase testCase, TestExecutionCallback callback) {
        mTestPlatformContext.executeTest(testCase, callback);
    }
    
    public static String getServerUrl() {
        return mTestPlatformContext.getExchangeServerUrl();
    }

    public static OutlookClient getMailCalendarContactClient(){
        return mTestPlatformContext.getMailCalendarContactClient();
    }

    public static SharePointClient getFilesClient(){
        return mTestPlatformContext.getFilesClient();
    }

    public static String getTestMail() { return mTestPlatformContext.getTestMail();}

    public static ListClient getSharePointListClient(){
        return mTestPlatformContext.getSharePointListClient();
    }

    public static String getTestListName(){
        return mTestPlatformContext.getTestListName();
    }

    public static DiscoveryClient getDiscoveryClient(){
        return mTestPlatformContext.getDiscoveryClient();
    }

    public static DirectoryClient getDirectoryClient(){
        return mTestPlatformContext.getDirectoryClient();
    }
}
