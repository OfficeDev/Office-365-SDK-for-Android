package com.microsoft.office365.test.integration;

import java.util.concurrent.Future;

import com.microsoft.office365.Logger;
import com.microsoft.office365.api.MailClient;
import com.microsoft.office365.files.FileClient;
import com.microsoft.office365.lists.SharepointListsClient;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestExecutionCallback;

public interface TestPlatformContext {

	String getServerUrl();

	String getClientId();
	
	String getRedirectUrl();
	
	String getTestListName();
	
	String getTestDocumentListName();

	String getSiteRelativeUrl();

	SharepointListsClient getListsClient();

	Future<Void> showMessage(String message);

	void executeTest(TestCase testCase, TestExecutionCallback callback);

	void sleep(int seconds) throws Exception;

	Logger getLogger();

	FileClient getFileClient();
	
	String getAuthenticationMethod();

	MailClient getMailClient();

}
