package com.microsoft.office365.test.integration.tests;


import com.microsoft.fileservices.File;
import com.microsoft.fileservices.Item;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestGroup;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.office365.test.integration.framework.TestStatus;
import com.microsoft.coreservices.odata.EntityContainerClient;
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
                request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IjVUa0d0S1JrZ2FpZXpFWTJFc0xDMmdPTGpBNCJ9.eyJhdWQiOiJodHRwczovL21zb3BlbnRlY2guc3BvcHBlLmNvbSIsImlzcyI6Imh0dHBzOi8vc3RzLndpbmRvd3MtcHBlLm5ldC8yYzc1YzM0NS02OWJmLTQzZTgtOWIzZi1lMmVjZGUwNDE0OGEvIiwiaWF0IjoxNDEzOTI1OTE2LCJuYmYiOjE0MTM5MjU5MTYsImV4cCI6MTQxMzkyOTgxNiwidmVyIjoiMS4wIiwidGlkIjoiMmM3NWMzNDUtNjliZi00M2U4LTliM2YtZTJlY2RlMDQxNDhhIiwiYW1yIjpbInB3ZCJdLCJvaWQiOiI3OWRhZGJjMS03ZmRiLTRhOTItOGFhYy1lNjE2MWNiZTczZDQiLCJ1cG4iOiJ2LW1hcmN0b0Btc29wZW50ZWNoLmNjc2N0cC5uZXQiLCJ1bmlxdWVfbmFtZSI6InYtbWFyY3RvQG1zb3BlbnRlY2guY2NzY3RwLm5ldCIsInN1YiI6InVWS2tUOVFxUkROZkxpM2h5YmR1b1Fob1BvNGwzeF84RmNjeDhwSnpoeE0iLCJwdWlkIjoiMTAwM0JGRkQ4QjlGQTM1RCIsImZhbWlseV9uYW1lIjoiVG9ycmVzIiwiZ2l2ZW5fbmFtZSI6Ik1hcmNvcyIsImFwcGlkIjoiMTIxNmRhNjctYzliOS00NGZhLWJkYzQtZDhlMmEwNmI0YzBmIiwiYXBwaWRhY3IiOiIwIiwic2NwIjoiQWxsU2l0ZXMuRnVsbENvbnRyb2wgQWxsU2l0ZXMuTWFuYWdlIEFsbFNpdGVzLlJlYWQgQWxsU2l0ZXMuV3JpdGUgTXlGaWxlcy5SZWFkIE15RmlsZXMuV3JpdGUgU2VhcmNoIiwiYWNyIjoiMSJ9.cjLV2OPPSmzeUTHsciHsyQfc6xx4UmYelvjK0sChb_erGT08TWspnPJZKWgpKrsNrv1CNL2FpLhl2R7hdmZ-rbar3hPnFILYy2xH2sxALbQj8Ec250WzCp_deIE_Mo8hjlKzQoUboVhgMCUwkt-ZX4bSiulKMretYo0KLxrAY3ufJEt00p9QnCTfBMCX9nOzl5voxe0jRWhjAWza89logGIP31LSLwXI8Pclp7qzghv6FxRZ-4q8NAuvC1cQRVne3oEZKqit5vZXvtB2G-WLw-Re6Cx-XTs8KZR2J2Z3mqaCJn80cgQaBGTWX5GRtclO8r6e8lzCS7lg7oSm4q__IQ");
                request.addHeader("Accept", "application/json");
            }
        });

        depResolver.setCredentialsFactory(credentialsFactory);

        EntityContainerClient client = new  EntityContainerClient("https://msopentech.spoppe.com/_api/v1.0", depResolver);
        return client;
    }
}
