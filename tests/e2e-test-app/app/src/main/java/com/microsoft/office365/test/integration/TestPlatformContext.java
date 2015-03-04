package com.microsoft.office365.test.integration;

import com.microsoft.directoryservices.odata.DirectoryClient;
import com.microsoft.discoveryservices.odata.DiscoveryClient;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestExecutionCallback;
import com.microsoft.outlookservices.odata.OutlookClient;
import com.microsoft.sharepointservices.ListClient;
import com.microsoft.sharepointservices.odata.SharePointClient;

import java.util.concurrent.Future;

public interface TestPlatformContext {

    String getExchangeServerUrl();

    String getFileServerUrl();

    String getClientId();

    String getRedirectUrl();

    String getExchangeEndpointUrl();

    String getFilesEndpointUrl();

    String getDiscoveryEndpointUrl();

    String getDirectoryEndpointUrl();

    String getTestMail();

    String getSharepointServerUrl();

    String getTestListName();

    String getTestDocumentListName();

    String getSiteRelativeUrl();

    String getDiscoveryServerUrl();

    String getDirectoryServerUrl();

    Future<Void> showMessage(String message);

    void executeTest(TestCase testCase, TestExecutionCallback callback);

    void sleep(int seconds) throws Exception;

    String getAuthenticationMethod();

    OutlookClient getMailCalendarContactClient();

    SharePointClient getFilesClient();

    ListClient getSharePointListClient();

    DiscoveryClient getDiscoveryClient();

    DirectoryClient getDirectoryClient();
}
