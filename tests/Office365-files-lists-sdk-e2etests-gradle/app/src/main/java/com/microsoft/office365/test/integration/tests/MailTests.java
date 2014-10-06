package com.microsoft.office365.test.integration.tests;

import android.util.Log;


import com.microsoft.office365.exchange.services.model.Folder;
import com.microsoft.office365.exchange.services.odata.EntityContainerClient;
import com.microsoft.office365.test.integration.ApplicationContext;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestGroup;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.office365.test.integration.framework.TestStatus;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

public class MailTests extends TestGroup {

    public MailTests() {
        super("Mail tests");

        this.addTest(canCreateClient("Can create client", true));
        this.addTest(canRetrieveFolders("Can retrieve folders", true));
        this.addTest(canRetrieveFolderById("Can retrieve folder by id", true));
        this.addTest(canCreateFolder("Can create folder", true));
        this.addTest(canDeleteFolder("Can delete folder", true));
        //this.addTest(canMoveFolder("Can move folder", true));
    }

    private TestCase canCreateClient(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();

                    if(client == null)
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

    private TestCase canRetrieveFolders(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();
                    List<Folder> folders = client.getMe().getFolders().execute().get();
                    if(folders == null || folders.size() == 0)
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

    private TestCase canRetrieveFolderById(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();
                    Folder folder = client.getMe().getFolders().getById("Inbox").execute().get();
                    if(folder == null || !folder.getDisplayName().equals("Inbox"))
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

    private TestCase canCreateFolder(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    String newFolderName = "TestFolder";
                    String parentFolderName = "Inbox";

                    //Create new folder
                    EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();
                    Folder newFolder = new Folder();
                    newFolder.setDisplayName(newFolderName);
                    client.getMe().getFolders().getById(parentFolderName).getChildFolders().add(newFolder).get();

                    // Assert
                    Folder folder = client.getMe()
                            .getFolders()
                            .getById(parentFolderName)
                            .getChildFolders()
                            .getById(getChildFolderIdByName(parentFolderName, newFolderName)).execute().get();

                    if(folder == null || !folder.getDisplayName().equals(newFolderName)){
                        result.setStatus(TestStatus.Failed);
                    }

                    //Cleanup
                    client.getMe()
                            .getFolders()
                            .getById(parentFolderName)
                            .getChildFolders()
                            .getById(getChildFolderIdByName(parentFolderName, newFolderName))
                            .delete().get();

                    return result;
                } catch (Throwable e) {
                    StringWriter writer = new StringWriter();
                    e.printStackTrace(new PrintWriter(writer));

                    String stackTrace = e.toString();
                    Log.e("SDK-Error", stackTrace);
                    //return createResultFromException(e);

                    return createResultFromException(new Exception("Error in test execution", e));
                }
            }
        };

        test.setName(name);
        test.setEnabled(enabled);
        return test;
    }

    private TestCase canDeleteFolder(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    String newFolderName = "TestFolder";
                    String parentFolderName = "Inbox";

                    //Prepare for test
                    EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();
                    Folder newFolder = new Folder();
                    newFolder.setDisplayName(newFolderName);
                    client.getMe().getFolders().getById(parentFolderName).getChildFolders().add(newFolder).get();

                    // Delete folder
                    client.getMe()
                            .getFolders()
                            .getById(parentFolderName)
                            .getChildFolders()
                            .getById(getChildFolderIdByName(parentFolderName, newFolderName))
                            .delete().get();

                    // Assert
                    List<Folder> folders = client.getMe()
                            .getFolders()
                            .getById(parentFolderName)
                            .getChildFolders()
                            .execute().get();

                    for ( Folder f : folders ) {
                        if(f.getDisplayName().equals(newFolderName)){
                            result.setStatus(TestStatus.Failed);
                            break;
                        }
                    }

                    return result;
                } catch (Throwable e) {
                    StringWriter writer = new StringWriter();
                    e.printStackTrace(new PrintWriter(writer));

                    String stackTrace = e.toString();
                    Log.e("SDK-Error", stackTrace);
                    //return createResultFromException(e);

                    return createResultFromException(new Exception("Error in test execution", e));
                }
            }
        };

        test.setName(name);
        test.setEnabled(enabled);
        return test;
    }

    private TestCase canMoveFolder(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    String newFolderName = "TestFolder";
                    String parentFolderName = "Inbox";
                    String destinationFolderName = "Drafts";

                    //Create new folder
                    EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();
                    Folder newFolder = new Folder();
                    newFolder.setDisplayName(newFolderName);
                    client.getMe().getFolders().getById(parentFolderName).getChildFolders().add(newFolder).get();

                    //Act
                    client.getMe()
                            .getFolders()
                            .getById(parentFolderName).getChildFolders()
                            .getById(getChildFolderIdByName(parentFolderName, newFolderName))
                            .getOperations().move(destinationFolderName).get();

                    //Assert
                    List<Folder> folders = client.getMe()
                            .getFolders()
                            .getById(destinationFolderName)
                            .getChildFolders()
                            .execute().get();

                    for ( Folder f : folders ) {
                        if(f.getDisplayName().equals(newFolderName)){
                            result.setStatus(TestStatus.Passed);
                            break;
                        }
                    }

                    //Cleanup
                    client.getMe()
                            .getFolders()
                            .getById(destinationFolderName)
                            .getChildFolders()
                            .getById(getChildFolderIdByName(destinationFolderName, newFolderName))
                            .delete().get();

                    return result;
                } catch (Throwable e) {
                    StringWriter writer = new StringWriter();
                    e.printStackTrace(new PrintWriter(writer));

                    String stackTrace = e.toString();
                    Log.e("SDK-Error", stackTrace);
                    //return createResultFromException(e);

                    return createResultFromException(new Exception("Error in test execution", e));
                }
            }
        };

        test.setName(name);
        test.setEnabled(enabled);
        return test;
    }

    private String getChildFolderIdByName(String folderName, String childFolder){
        try {
            EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();

            List<Folder> folders = client.getMe()
                    .getFolders()
                    .getById(folderName)
                    .getChildFolders().execute().get();

            for (int b = 0; b < folders.size(); b++) {
                if(folders.get(b).getDisplayName().equals(childFolder))
                    return folders.get(b).getId();
            }

            return "";
        }catch (Throwable t){
            Log.e("Error", t.getMessage());
            return "";

        }
    }
}
