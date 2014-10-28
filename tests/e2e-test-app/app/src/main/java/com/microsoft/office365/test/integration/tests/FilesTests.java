package com.microsoft.office365.test.integration.tests;


import com.microsoft.fileservices.Drive;
import com.microsoft.fileservices.File;
import com.microsoft.fileservices.Item;
import com.microsoft.office365.test.integration.ApplicationContext;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestGroup;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.office365.test.integration.framework.TestStatus;
import com.microsoft.services.odata.Constants;
import com.microsoft.sharepointservices.odata.SharePointClient;

import java.util.List;
import java.util.UUID;


public class FilesTests extends TestGroup {

    public FilesTests() {
        super("Files tests");

        this.addTest(canGetFiles("Can get files", true));
        this.addTest(canGetFilesById("Can get file by Id", true));
        this.addTest(canCreateFile("Can create file with content", true));
        this.addTest(canUpdateFile("Can update file", true));
        this.addTest(canUpdateFileContent("Can update file content", true));
        this.addTest(canGetDrive("Can get drive", false));
    }

    private TestCase canGetFiles(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    SharePointClient client = ApplicationContext.getFilesClient();

                    List<Item> files = client.getfiles().read().get();

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

    private TestCase canGetFilesById(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    SharePointClient client = ApplicationContext.getFilesClient();

                    Item newFile = new Item();
                    newFile.settype("File");
                    newFile.setname(UUID.randomUUID().toString() + ".txt");

                    Item addedFile = client.getfiles().add(newFile).get();

                    File storedFile = client.getfiles().getById(addedFile.getid()).asFile().read().get();

                    //Assert
                    if(storedFile == null)
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getfiles().getById(addedFile.getid()).asFile().addHeader(Constants.IF_MATCH_HEADER,"*").delete().get();

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

                    SharePointClient client = ApplicationContext.getFilesClient();

                    Item newFile = new Item();
                    newFile.settype("File");
                    newFile.setname(UUID.randomUUID().toString() + ".txt");

                    Item addedFile = client.getfiles().add(newFile).get();
                    client.getfiles().getById(addedFile.getid()).asFile().putContent("My Content".getBytes()).get();

                    byte[] content = client.getfiles().getById(addedFile.getid()).asFile().getContent().get();

                    //Assert
                    if(addedFile == null)
                        result.setStatus(TestStatus.Failed);

                    if(content.length == 0)
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getfiles().getById(addedFile.getid()).asFile().addHeader(Constants.IF_MATCH_HEADER,"*").delete().get();

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

    private TestCase canUpdateFileContent(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    SharePointClient client = ApplicationContext.getFilesClient();

                    Item newFile = new Item();
                    newFile.settype("File");
                    newFile.setname(UUID.randomUUID().toString() + ".txt");

                    Item addedFile = client.getfiles().add(newFile).get();
                    //Prepare
                    client.getfiles().getById(addedFile.getid()).asFile().putContent("My Content".getBytes()).get();

                    //Act
                    client.getfiles().getById(addedFile.getid()).asFile().putContent("My other Content".getBytes()).get();

                    //Assert
                    byte[] content = client.getfiles().getById(addedFile.getid()).asFile().getContent().get();
                    String strContent = new String(content);
                    if(addedFile != null && strContent.equals("My other Content"))
                        result.setStatus(TestStatus.Passed);

                    //Cleanup
                    client.getfiles().getById(addedFile.getid()).asFile().addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();

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

    private TestCase canUpdateFile(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    SharePointClient client = ApplicationContext.getFilesClient();

                    Item newFile = new Item();
                    newFile.settype("File");
                    newFile.setname(UUID.randomUUID().toString() + ".txt");

                    Item addedFile = client.getfiles().add(newFile).get();
                    client.getfiles().getById(addedFile.getid()).asFile().putContent("My Content".getBytes()).get();

                    String newFileName = "Updated" + newFile.getname();
                    newFile.setname(newFileName);
                    client.getfiles().getById(addedFile.getid()).update(newFile);

                    //Assert
                    if(addedFile == null)
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getfiles().getById(addedFile.getid()).asFile().addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();

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

    private TestCase canGetDrive(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    SharePointClient client = ApplicationContext.getFilesClient();

                    Drive drive = client.getdrive().read().get();

                    //Assert
                    if(drive == null || drive.getquota() == null || drive.getquota().gettotal() == 0 || drive.getid().equals(""))
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
}
