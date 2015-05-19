package com.microsoft.office365.test.integration.tests;


import com.microsoft.directoryservices.Application;
import com.microsoft.fileservices.Drive;
import com.microsoft.fileservices.File;
import com.microsoft.fileservices.Folder;
import com.microsoft.fileservices.Item;
import com.microsoft.office365.test.integration.ApplicationContext;
import com.microsoft.office365.test.integration.R;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestGroup;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.office365.test.integration.framework.TestStatus;
import com.microsoft.services.odata.Constants;
import com.microsoft.fileservices.orc.SharePointClient;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.UUID;


public class FilesTests extends TestGroup {

    public FilesTests() {
        super("Files tests");

        this.addTest(canCreateLargeFileFromStream("Can create large file with content from stream", true));
        this.addTest(canGetFiles("Can get files", true));
        this.addTest(canGetFilesById("Can get file by Id", true));
        this.addTest(canCreateFile("Can create file with content", true));
        this.addTest(canCreateFileFromStream("Can create file with content from stream", true));
        this.addTest(canUpdateFile("Can update file", true));
        this.addTest(canUpdateFileContent("Can update file content", true));
        this.addTest(canGetDrive("Can get drive", false));
        this.addTest(canGetFilesTyped("Can get files typed with derived classes", true));
        this.addTest(canGetFileTyped("Can get file typed with derived class", true));
        this.addTest(canDeleteFiles("Can delete files", true));
        //Select, top
        this.addTest(canSelectFiles("Can use select in files", true));
        this.addTest(canTopFiles("Can use top in files", true));
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
                    if (files == null)
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
                    if (storedFile == null)
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
                    if (addedFile == null)
                        result.setStatus(TestStatus.Failed);

                    if (content.length == 0)
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

    private TestCase canCreateFileFromStream(String name, boolean enabled) {
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

                    InputStream stream = ApplicationContext.getResource(R.drawable.ic_launcher);
                    long size = ApplicationContext.getResourceSize(R.drawable.ic_launcher);
                    client.getfiles().getById(addedFile.getid()).asFile().putContent(stream, size).get();

                    byte[] content = client.getfiles().getById(addedFile.getid()).asFile().getContent().get();

                    //Assert
                    if (addedFile == null)
                        result.setStatus(TestStatus.Failed);

                    if (content.length == 0)
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

    private TestCase canCreateLargeFileFromStream(String name, boolean enabled) {
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

                    long newFileSize = 1 * 1024;
                    java.io.File file = ApplicationContext.createTempFile(1 * 1024);

                    InputStream stream = new FileInputStream(file);
                    long size = file.length();
                    client.getfiles().getById(addedFile.getid()).asFile().putContent(stream, size).get();

                    InputStream contentStream = client.getfiles().getById(addedFile.getid()).asFile().getStreamedContent().get();

                    //Assert
                    if (addedFile == null)
                        result.setStatus(TestStatus.Failed);

                    if (contentStream == null)
                        result.setStatus(TestStatus.Failed);

                    contentStream.close();

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
                    if (addedFile != null && strContent.equals("My other Content"))
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
                    if (addedFile == null)
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getfiles().getById(addedFile.getid()).addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();

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
                    if (drive == null || drive.getquota() == null || drive.getquota().gettotal() == 0 || drive.getid().equals(""))
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

    // Select, Top
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
                            .select("name,dateTimeCreated")
                            .read().get();

                    //Assert
                    if (files.size() > 0 && !files.get(0).getname().equals("") && files.get(0).getdateTimeLastModified() == null)
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
                    String fileName2 = "TestFile" + UUID.randomUUID().toString();
                    SharePointClient client = ApplicationContext.getFilesClient();

                    //Prepare
                    Item newFile = new Item();
                    newFile.settype("File");
                    newFile.setname(fileName + ".txt");

                    Item newFile2 = new Item();
                    newFile2.settype("File");
                    newFile2.setname(fileName2);

                    Item addedFile1 = client.getfiles().add(newFile).get();
                    Item addedFile2 = client.getfiles().add(newFile2).get();

                    //Act
                    List<Item> files = client.getfiles()
                            .top(1)
                            .read().get();

                    //Assert
                    if (files.size() == 1)
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

    private TestCase canGetFilesTyped(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    SharePointClient client = ApplicationContext.getFilesClient();

                    //Prepare
                    Item newFile = new Item();
                    newFile.settype("File");
                    newFile.setname(UUID.randomUUID().toString() + ".txt");

                    Item newFolder = new Item();
                    newFolder.settype("Folder");
                    newFolder.setname(UUID.randomUUID().toString());

                    Item addedFile = client.getfiles().add(newFile).get();
                    Item addedFolder = client.getfiles().add(newFolder).get();

                    //Act
                    List<Item> files = client.getfiles().top(15).read().get();

                    //Assert
                    boolean wellTyped = true;

                    for(Item i : files){
                        if(!(i instanceof Folder || i instanceof File)) wellTyped = false;
                    }

                    if (files == null || !wellTyped)
                        result.setStatus(TestStatus.Failed);

                    //cleanup
                    client.getfiles().getById(addedFile.getid()).addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();
                    client.getfiles().getById(addedFolder.getid()).addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();
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

    private TestCase canDeleteFiles(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    SharePointClient client = ApplicationContext.getFilesClient();

                    //Prepare
                    Item newFile = new Item();
                    newFile.settype("File");
                    newFile.setname(UUID.randomUUID().toString() + ".txt");

                    Item addedFile = client.getfiles().add(newFile).get();

                    //Act
                    client.getfiles().getById(addedFile.getid()).asFile().addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();

                    return result;
                } catch (Throwable t) {
                    return createResultFromException(new Exception(t));
                }
            }
        };

        test.setName(name);
        test.setEnabled(enabled);
        return test;
    }

    private TestCase canGetFileTyped(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    SharePointClient client = ApplicationContext.getFilesClient();

                    //Prepare
                    Item newFile = new Item();
                    newFile.settype("File");
                    newFile.setname(UUID.randomUUID().toString() + ".txt");

                    Item newFolder = new Item();
                    newFolder.settype("Folder");
                    newFolder.setname(UUID.randomUUID().toString());

                    Item addedFile = client.getfiles().add(newFile).get();
                    Item addedFolder = client.getfiles().add(newFolder).get();

                    //Act
                    Item file = client.getfiles().getById(addedFile.getid()).read().get();
                    Item folder = client.getfiles().getById(addedFolder.getid()).read().get();
                    //Assert


                    if (file instanceof File && folder instanceof Folder)
                        result.setStatus(TestStatus.Passed);

                    //cleanup
                    client.getfiles().getById(addedFile.getid()).addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();
                    client.getfiles().getById(addedFolder.getid()).addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();
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
