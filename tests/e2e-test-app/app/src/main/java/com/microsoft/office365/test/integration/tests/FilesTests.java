package com.microsoft.office365.test.integration.tests;


import com.microsoft.services.files.*;
import com.microsoft.office365.test.integration.ApplicationContext;
import com.microsoft.office365.test.integration.R;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestGroup;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.office365.test.integration.framework.TestStatus;
import com.microsoft.services.files.fetchers.FilesClient;
import com.microsoft.services.orc.core.Constants;

import java.io.FileInputStream;
import java.io.InputStream;
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

                    FilesClient client = ApplicationContext.getFilesClient();

                    List<Item> files = client.getFiles().read().get();

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

                    FilesClient client = ApplicationContext.getFilesClient();

                    Item newFile = new Item();
                    newFile.setType("File");
                    newFile.setName(UUID.randomUUID().toString() + ".txt");

                    Item addedFile = client.getFiles().add(newFile).get();

                    File storedFile = client.getFiles().getById(addedFile.getId()).asFile().read().get();

                    //Assert
                    if (storedFile == null)
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getFiles().getById(addedFile.getId()).asFile().addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();

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

                    FilesClient client = ApplicationContext.getFilesClient();

                    Item newFile = new Item();
                    newFile.setType("File");
                    newFile.setName(UUID.randomUUID().toString() + ".txt");

                    Item addedFile = client.getFiles().add(newFile).get();
                    client.getFiles().getById(addedFile.getId()).asFile().putContent("My Content".getBytes()).get();

                    byte[] content = client.getFiles().getById(addedFile.getId()).asFile().getContent().get();

                    //Assert
                    if (addedFile == null)
                        result.setStatus(TestStatus.Failed);

                    if (content.length == 0)
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getFiles().getById(addedFile.getId()).asFile().addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();

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

                    FilesClient client = ApplicationContext.getFilesClient();

                    Item newFile = new Item();
                    newFile.setType("File");
                    newFile.setName(UUID.randomUUID().toString() + ".txt");

                    Item addedFile = client.getFiles().add(newFile).get();

                    InputStream stream = ApplicationContext.getResource(R.drawable.ic_launcher);
                    long size = ApplicationContext.getResourceSize(R.drawable.ic_launcher);
                    client.getFiles().getById(addedFile.getId()).asFile().putContent(stream, size).get();

                    byte[] content = client.getFiles().getById(addedFile.getId()).asFile().getContent().get();

                    //Assert
                    if (addedFile == null)
                        result.setStatus(TestStatus.Failed);

                    if (content.length == 0)
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getFiles().getById(addedFile.getId()).asFile().addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();

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

                    FilesClient client = ApplicationContext.getFilesClient();

                    Item newFile = new Item();
                    newFile.setType("File");
                    newFile.setName(UUID.randomUUID().toString() + ".txt");

                    Item addedFile = client.getFiles().add(newFile).get();

                    long newFileSize = 1 * 1024;
                    java.io.File file = ApplicationContext.createTempFile(1 * 1024);

                    InputStream stream = new FileInputStream(file);
                    long size = file.length();
                    client.getFiles().getById(addedFile.getId()).asFile().putContent(stream, size).get();

                    InputStream contentStream = client.getFiles().getById(addedFile.getId()).asFile().getStreamedContent().get();

                    //Assert
                    if (addedFile == null)
                        result.setStatus(TestStatus.Failed);

                    if (contentStream == null)
                        result.setStatus(TestStatus.Failed);

                    contentStream.close();

                    //Cleanup
                    client.getFiles().getById(addedFile.getId()).asFile().addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();

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

                    FilesClient client = ApplicationContext.getFilesClient();

                    Item newFile = new Item();
                    newFile.setType("File");
                    newFile.setName(UUID.randomUUID().toString() + ".txt");

                    Item addedFile = client.getFiles().add(newFile).get();
                    //Prepare
                    client.getFiles().getById(addedFile.getId()).asFile().putContent("My Content".getBytes()).get();

                    //Act
                    client.getFiles().getById(addedFile.getId()).asFile().putContent("My other Content".getBytes()).get();

                    //Assert
                    byte[] content = client.getFiles().getById(addedFile.getId()).asFile().getContent().get();
                    String strContent = new String(content);
                    if (addedFile != null && strContent.equals("My other Content"))
                        result.setStatus(TestStatus.Passed);

                    //Cleanup
                    client.getFiles().getById(addedFile.getId()).asFile().addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();

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

                    FilesClient client = ApplicationContext.getFilesClient();

                    Item newFile = new Item();
                    newFile.setType("File");
                    newFile.setName(UUID.randomUUID().toString() + ".txt");

                    Item addedFile = client.getFiles().add(newFile).get();
                    client.getFiles().getById(addedFile.getId()).asFile().putContent("My Content".getBytes()).get();

                    String newFileName = "Updated" + newFile.getName();
                    newFile.setName(newFileName);
                    client.getFiles().getById(addedFile.getId()).update(newFile);

                    //Assert
                    if (addedFile == null)
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getFiles().getById(addedFile.getId()).addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();

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

                    FilesClient client = ApplicationContext.getFilesClient();

                    Drive drive = client.getDrive().read().get();

                    //Assert
                    if (drive == null || drive.getQuota() == null || drive.getQuota().getTotal() == 0 || drive.getId().equals(""))
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

                    FilesClient client = ApplicationContext.getFilesClient();

                    //Prepare
                    Item newFile = new Item();
                    newFile.setType("File");
                    newFile.setName(fileName + ".txt");

                    Item addedFile = client.getFiles().add(newFile).get();

                    //Act
                    List<Item> files = client.getFiles()
                            .select("name,dateTimeCreated")
                            .read().get();

                    //Assert
                    if (files.size() > 0 && !files.get(0).getName().equals("") && files.get(0).getDateTimeLastModified() == null)
                        result.setStatus(TestStatus.Passed);

                    //Cleanup
                    client.getFiles().getById(addedFile.getId()).asFile().addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();

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
                    FilesClient client = ApplicationContext.getFilesClient();

                    //Prepare
                    Item newFile = new Item();
                    newFile.setType("File");
                    newFile.setName(fileName + ".txt");

                    Item newFile2 = new Item();
                    newFile2.setType("File");
                    newFile2.setName(fileName2);

                    Item addedFile1 = client.getFiles().add(newFile).get();
                    Item addedFile2 = client.getFiles().add(newFile2).get();

                    //Act
                    List<Item> files = client.getFiles()
                            .top(1)
                            .read().get();

                    //Assert
                    if (files.size() == 1)
                        result.setStatus(TestStatus.Passed);

                    //Cleanup
                    client.getFiles().getById(addedFile1.getId()).asFile().addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();
                    client.getFiles().getById(addedFile2.getId()).asFile().addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();

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

                    FilesClient client = ApplicationContext.getFilesClient();

                    //Prepare
                    Item newFile = new Item();
                    newFile.setType("File");
                    newFile.setName(UUID.randomUUID().toString() + ".txt");

                    Item newFolder = new Item();
                    newFolder.setType("Folder");
                    newFolder.setName(UUID.randomUUID().toString());

                    Item addedFile = client.getFiles().add(newFile).get();
                    Item addedFolder = client.getFiles().add(newFolder).get();

                    //Act
                    List<Item> files = client.getFiles().top(15).read().get();

                    //Assert
                    boolean wellTyped = true;

                    for(Item i : files){
                        if(!(i instanceof Folder || i instanceof File)) wellTyped = false;
                    }

                    if (files == null || !wellTyped)
                        result.setStatus(TestStatus.Failed);

                    //cleanup
                    client.getFiles().getById(addedFile.getId()).addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();
                    client.getFiles().getById(addedFolder.getId()).addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();
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

                    FilesClient client = ApplicationContext.getFilesClient();

                    //Prepare
                    Item newFile = new Item();
                    newFile.setType("File");
                    newFile.setName(UUID.randomUUID().toString() + ".txt");

                    Item addedFile = client.getFiles().add(newFile).get();

                    //Act
                    client.getFiles().getById(addedFile.getId()).asFile().addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();

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

                    FilesClient client = ApplicationContext.getFilesClient();

                    //Prepare
                    Item newFile = new Item();
                    newFile.setType("File");
                    newFile.setName(UUID.randomUUID().toString() + ".txt");

                    Item newFolder = new Item();
                    newFolder.setType("Folder");
                    newFolder.setName(UUID.randomUUID().toString());

                    Item addedFile = client.getFiles().add(newFile).get();
                    Item addedFolder = client.getFiles().add(newFolder).get();

                    //Act
                    Item file = client.getFiles().getById(addedFile.getId()).read().get();
                    Item folder = client.getFiles().getById(addedFolder.getId()).read().get();
                    //Assert


                    if (file instanceof File && folder instanceof Folder)
                        result.setStatus(TestStatus.Passed);

                    //cleanup
                    client.getFiles().getById(addedFile.getId()).addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();
                    client.getFiles().getById(addedFolder.getId()).addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();
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
