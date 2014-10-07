package com.microsoft.office365.test.integration;

import java.util.concurrent.Future;


import com.microsoft.office365.exchange.services.odata.EntityContainerClient;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestExecutionCallback;

public interface TestPlatformContext {

	String getServerUrl();

	String getClientId();
	
	String getRedirectUrl();

    String getEndpointUrl();

    String getTestMail();

	Future<Void> showMessage(String message);

	void executeTest(TestCase testCase, TestExecutionCallback callback);

	void sleep(int seconds) throws Exception;

	String getAuthenticationMethod();

    EntityContainerClient getMailCalendarContactClient();

}
