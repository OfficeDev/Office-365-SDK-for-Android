package com.microsoft.office365.test.integration.tests;


import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.sharepointservices.odata.EntityContainerClient;
import com.microsoft.directoryservices.DirectoryObject;
import com.microsoft.directoryservices.odata.DirectoryDataServiceClient;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestGroup;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.office365.test.integration.framework.TestStatus;
import com.microsoft.services.odata.impl.DefaultDependencyResolver;
import com.microsoft.services.odata.impl.http.CredentialsFactoryImpl;
import com.microsoft.services.odata.interfaces.Credentials;
import com.microsoft.services.odata.interfaces.LogLevel;
import com.microsoft.services.odata.interfaces.Request;

import java.util.List;


public class DirectoryTests extends TestGroup {

    public DirectoryTests() {
        super("Directory tests");

        this.addTest(canCreateClient("Can create client - WIP", false));
    }

    private TestCase canCreateClient(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    final TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    DirectoryDataServiceClient client = getFileClient();

                    ListenableFuture<List<DirectoryObject>> future = client.getdirectoryObjects().read();

                    Futures.addCallback(future, new FutureCallback<List<DirectoryObject>>() {
                        @Override
                        public void onSuccess(List<DirectoryObject> result) {

                        }

                        @Override
                        public void onFailure(Throwable t) {
                            result.setStatus(TestStatus.Failed);

                        }
                    });

                    return result;
                } catch (Exception e) {
                    return createResultFromException(e);
                }
            }
        };

        test.setName(name);
        test.setEnabled(enabled);
        return test;
    }

    private DirectoryDataServiceClient getFileClient() {
        DefaultDependencyResolver depResolver = new DefaultDependencyResolver();
        depResolver.getLogger().setEnabled(true);
        depResolver.getLogger().setLogLevel(LogLevel.VERBOSE);
        CredentialsFactoryImpl credentialsFactory = new CredentialsFactoryImpl();

        credentialsFactory.setCredentials(new Credentials() {
            @Override
            public void prepareRequest(Request request) {
                request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IjVUa0d0S1JrZ2FpZXpFWTJFc0xDMmdPTGpBNCJ9.eyJhdWQiOiJodHRwczovL21zb3BlbnRlY2guc3BvcHBlLmNvbSIsImlzcyI6Imh0dHBzOi8vc3RzLndpbmRvd3MtcHBlLm5ldC8yYzc1YzM0NS02OWJmLTQzZTgtOWIzZi1lMmVjZGUwNDE0OGEvIiwiaWF0IjoxNDEzNDg5MDk3LCJuYmYiOjE0MTM0ODkwOTcsImV4cCI6MTQxMzQ5Mjk5NywidmVyIjoiMS4wIiwidGlkIjoiMmM3NWMzNDUtNjliZi00M2U4LTliM2YtZTJlY2RlMDQxNDhhIiwiYW1yIjpbInB3ZCJdLCJvaWQiOiI2Yjg3YjgwOS04NTIzLTQ5YTctOTExMy0xZGEwZDI2OTdmZDMiLCJ1cG4iOiJ2LWFuaG9qbkBtc29wZW50ZWNoLmNjc2N0cC5uZXQiLCJ1bmlxdWVfbmFtZSI6InYtYW5ob2puQG1zb3BlbnRlY2guY2NzY3RwLm5ldCIsInN1YiI6ImcwU0J6UFNZWmppaGJTMnhXZmIzNnNlemwxbGtwblNHcXh6RVhXTzUtOG8iLCJwdWlkIjoiMTAwMzAwMDA4Qjk3MDIxOSIsImZhbWlseV9uYW1lIjoiSG9qbmFkZWwiLCJnaXZlbl9uYW1lIjoiQW5haGkiLCJhcHBpZCI6IjEyMTZkYTY3LWM5YjktNDRmYS1iZGM0LWQ4ZTJhMDZiNGMwZiIsImFwcGlkYWNyIjoiMCIsInNjcCI6IkFsbFNpdGVzLkZ1bGxDb250cm9sIEFsbFNpdGVzLk1hbmFnZSBBbGxTaXRlcy5SZWFkIEFsbFNpdGVzLldyaXRlIE15RmlsZXMuUmVhZCBNeUZpbGVzLldyaXRlIFNlYXJjaCIsImFjciI6IjEifQ.ozoan8Nwh-ZhuOwLOgIdx1uKn10M9GHYwui-Ox7HBpVZM1iEvYxfDB-laHGtGpcw4-VzkKejqW2GcAk9WAVRnZFQJBO2RYLXh1U-_CO8kWXKWjRnqZlhYN01PaZ_Nm01r6yik6N1UEdD4LHtDLaPCr4NtGk0i3JJjvenSeS0Tg2vZ_2pyrM-Adxwe5Zt4MEvnSLaAusRY2VgJcxU9etcYEDqNZFx-RJ6J2JEcGYCiCFp3FsVX1j0wJyf6vIYE_NdgduX78OtYsS6B1DkF2a0XzdkwNTFS2Vip3COKQVRUOMf4kFby3cfhEwBKHmsVKgyZ17IfL_8oLjxpBrWt92XcQ");
                request.addHeader("Accept", "application/json");
            }
        });

        depResolver.setCredentialsFactory(credentialsFactory);

        DirectoryDataServiceClient client = new  DirectoryDataServiceClient("https://msopentech.spoppe.com/_api/v1.0", depResolver);
        return client;
    }
}
