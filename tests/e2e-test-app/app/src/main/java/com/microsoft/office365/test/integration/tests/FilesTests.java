package com.microsoft.office365.test.integration.tests;


import com.microsoft.fileservices.File;
import com.microsoft.fileservices.Item;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestGroup;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.office365.test.integration.framework.TestStatus;
import com.microsoft.sharepointservices.odata.EntityContainerClient;
import com.microsoft.services.odata.impl.DefaultDependencyResolver;
import com.microsoft.services.odata.impl.http.CredentialsFactoryImpl;
import com.microsoft.services.odata.interfaces.Credentials;
import com.microsoft.services.odata.interfaces.LogLevel;
import com.microsoft.services.odata.interfaces.Request;


import java.util.List;
import java.util.UUID;


public class FilesTests extends TestGroup {

    public FilesTests() {
        super("Files tests");

        this.addTest(canGetFiles("Can get files - WIP", false));
        this.addTest(canCreateFile("Can create file - WIP", true));
    }

    private TestCase canGetFiles(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    EntityContainerClient client = getFileClient();

                    List<Item> files = client.getme().getfiles().read().get();

                    //Assert
                    if(files == null)
                        result.setStatus(TestStatus.Failed);

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

    private TestCase canCreateFile(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    EntityContainerClient client = getFileClient();

                    File newFile = new File();
                    newFile.settype("File");
                    newFile.setname(UUID.randomUUID().toString() + ".txt");

                    Item addedFile = client.getme().getfiles().add(newFile).get();
                    client.getme().getfiles().getById(addedFile.getid()).asFile().putContent("My Content".getBytes()).get();

                    client.getme().getfiles().getById("somefile.txt").asFile().putContent("somecontent".getBytes()).get();


                    byte[] content = client.getme().getfiles().getById(addedFile.getid()).asFile().getContent().get();

                    //Item addedFile = client.getme().getfiles().getOperations().add("myFile.txt", "myFile.txt", "File", "Hello World".getBytes()).get();


                    //Assert
                    if(addedFile == null)
                        result.setStatus(TestStatus.Failed);

                    if(content.length == 0)
                        result.setStatus(TestStatus.Failed);

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

    private EntityContainerClient getFileClient() {
        DefaultDependencyResolver depResolver = new DefaultDependencyResolver();
        depResolver.getLogger().setEnabled(true);
        depResolver.getLogger().setLogLevel(LogLevel.VERBOSE);
        CredentialsFactoryImpl credentialsFactory = new CredentialsFactoryImpl();
        credentialsFactory.setCredentials(new Credentials() {
            @Override
            public void prepareRequest(Request request) {
                request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IjVUa0d0S1JrZ2FpZXpFWTJFc0xDMmdPTGpBNCJ9.eyJhdWQiOiJodHRwczovL21zb3BlbnRlY2guc3BvcHBlLmNvbSIsImlzcyI6Imh0dHBzOi8vc3RzLndpbmRvd3MtcHBlLm5ldC8yYzc1YzM0NS02OWJmLTQzZTgtOWIzZi1lMmVjZGUwNDE0OGEvIiwiaWF0IjoxNDE0MDEwOTE1LCJuYmYiOjE0MTQwMTA5MTUsImV4cCI6MTQxNDAxNDgxNSwidmVyIjoiMS4wIiwidGlkIjoiMmM3NWMzNDUtNjliZi00M2U4LTliM2YtZTJlY2RlMDQxNDhhIiwiYW1yIjpbInB3ZCJdLCJvaWQiOiI3OWRhZGJjMS03ZmRiLTRhOTItOGFhYy1lNjE2MWNiZTczZDQiLCJ1cG4iOiJ2LW1hcmN0b0Btc29wZW50ZWNoLmNjc2N0cC5uZXQiLCJ1bmlxdWVfbmFtZSI6InYtbWFyY3RvQG1zb3BlbnRlY2guY2NzY3RwLm5ldCIsInN1YiI6InVWS2tUOVFxUkROZkxpM2h5YmR1b1Fob1BvNGwzeF84RmNjeDhwSnpoeE0iLCJwdWlkIjoiMTAwM0JGRkQ4QjlGQTM1RCIsImZhbWlseV9uYW1lIjoiVG9ycmVzIiwiZ2l2ZW5fbmFtZSI6Ik1hcmNvcyIsImFwcGlkIjoiMTIxNmRhNjctYzliOS00NGZhLWJkYzQtZDhlMmEwNmI0YzBmIiwiYXBwaWRhY3IiOiIwIiwic2NwIjoiQWxsU2l0ZXMuRnVsbENvbnRyb2wgQWxsU2l0ZXMuTWFuYWdlIEFsbFNpdGVzLlJlYWQgQWxsU2l0ZXMuV3JpdGUgTXlGaWxlcy5SZWFkIE15RmlsZXMuV3JpdGUgU2VhcmNoIiwiYWNyIjoiMSJ9.mu8P3GSz0kn8yGwF9cQ9elhR7BNL2_KNF91laOMvzZIbM_OePQEcQmhsUuEuyhDLV5eGnaHE_n0G5yoSm8zn0ocsWRpSQvl_7wAhCzFVjGm6bZmP4pcUBCN00bdf9ZGxDT2SD6ZjBd2zeYjGAmjllOhX9H-hE2MUZ74vdA-ESsKDkGbEi0G-EcI93JyMyBi5QJweHoO8svk0S3mkKl8R0iSfJrdJC-2p5XGS6TYmBwnwodV5QH70L27fu0dyf1_uxNABftkctC6EeyC2hgw8W5WtAi4L_fja-tzEU2owglLstr4gixy1gzstfRFJunM4osrDWEtEB5BpssSwBjae7g");
                request.addHeader("Accept", "application/json");
            }
        });

        depResolver.setCredentialsFactory(credentialsFactory);

        EntityContainerClient client = new  EntityContainerClient("https://msopentech.spoppe.com/_api/v1.0", depResolver);
        return client;
    }
}
