package com.microsoft.office365.test.integration;

import java.util.concurrent.Future;



import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestExecutionCallback;
import com.microsoft.outlookservices.odata.EntityContainerClient;


public interface TestPlatformContext {

	String getExchangeServerUrl();

    String getFileServerUrl();

	String getClientId();
	
	String getRedirectUrl();

    String getExchangeEndpointUrl();

    String getFilesEndpointUrl();

    String getTestMail();

    String getBasicAuthToken();

	Future<Void> showMessage(String message);

	void executeTest(TestCase testCase, TestExecutionCallback callback);

	void sleep(int seconds) throws Exception;

	String getAuthenticationMethod();

    EntityContainerClient getMailCalendarContactClient();

    com.microsoft.sharepointservices.odata.EntityContainerClient getFilesClient();

}
