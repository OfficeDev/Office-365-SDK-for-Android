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

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
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

        //Select, top, filter
        this.addTest(canFilterFiles("Can use filter in files", false));
        this.addTest(canSelectFiles("Can use select in files", false));
        this.addTest(canTopFiles("Can use top in files", false));
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

    // Filter, Select, Top, Skip
    private TestCase canFilterFiles(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    String fileName = "TestFile" + UUID.randomUUID().toString();


                    SharePointClient client = ApplicationContext.getFilesClient();

                    //Prepare
                    Item newFile = new Item();
                    newFile.settype("File");
                    newFile.setname(fileName + ".txt");

                    Item addedFile = client.getfiles().add(newFile).get();

                    //Act
                    java.util.Calendar dateGt = addedFile.getdateTimeCreated();
                    dateGt.add(java.util.Calendar.SECOND, -2);

                    //format date properly
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSSSSSS'Z'", Locale.getDefault());
                    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                    String formatted = dateFormat.format(dateGt.getTime());

                    List<Item> files = client.getfiles()
                            .filter("name eq '" + addedFile.getname() + "' and dateTimeCreated gt " + formatted)
                            .read().get();

                    //Assert
                    if(files.size() == 1)
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

    private TestCase canSelectFiles(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    String fileName = "TestFile" + UUID.randomUUID().toString();

                    SharePointClient client = ApplicationContext.getFilesClient();

                    //Prepare
                    Item newFile = new Item();
                    newFile.settype("File");
                    newFile.setname(fileName + ".txt");

                    Item addedFile = client.getfiles().add(newFile).get();

                    //Act
                    List<Item> files = client.getfiles()
                            .filter("name eq '" + fileName + "'")
                            .select("name,dateTimeCreated")
                            .read().get();

                    //Assert
                    if(files.size() > 0 && !files.get(0).getname().equals("") && files.get(0).getdateTimeLastModified() == null)
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


    private TestCase canTopFiles(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    String fileName = "TestFile" + UUID.randomUUID().toString();

                    SharePointClient client = ApplicationContext.getFilesClient();

                    //Prepare
                    Item newFile = new Item();
                    newFile.settype("File");
                    newFile.setname(fileName + ".txt");

                    Item addedFile1 = client.getfiles().add(newFile).get();
                    Item addedFile2 = client.getfiles().add(newFile).get();

                    //Act
                    List<Item> files = client.getfiles()
                            .filter("name eq '" + fileName + "'")
                            .top(1)
                            .read().get();

                    //Assert
                    if(files.size() == 1 && files.get(0).getname().equals(fileName))
                        result.setStatus(TestStatus.Passed);

                    //Cleanup
                    client.getfiles().getById(addedFile1.getid()).asFile().addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();
                    client.getfiles().getById(addedFile2.getid()).asFile().addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();

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
