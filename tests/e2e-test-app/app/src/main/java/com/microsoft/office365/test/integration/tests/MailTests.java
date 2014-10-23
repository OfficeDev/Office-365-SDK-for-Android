package com.microsoft.office365.test.integration.tests;

import android.util.Log;


import com.google.common.io.Files;

import com.microsoft.office365.test.integration.ApplicationContext;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestGroup;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.office365.test.integration.framework.TestStatus;
import com.microsoft.outlookservices.Attachment;
import com.microsoft.outlookservices.EmailAddress;
import com.microsoft.outlookservices.FileAttachment;
import com.microsoft.outlookservices.Folder;
import com.microsoft.outlookservices.ItemBody;
import com.microsoft.outlookservices.Message;
import com.microsoft.outlookservices.Recipient;
import com.microsoft.outlookservices.odata.EntityContainerClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MailTests extends TestGroup {

    public MailTests() {
        super("Mail tests");

        this.addTest(canCreateClient("Can create client", true));
        // Folders
        this.addTest(canRetrieveFolders("Can retrieve folders", true));
        this.addTest(canRetrieveFolderById("Can retrieve folder by id", true));
        this.addTest(canCreateFolder("Can create folder", true));
        this.addTest(canDeleteFolder("Can delete folder", true));
        this.addTest(canMoveFolder("Can move folder", true));
        this.addTest(canCopyFolder("Can copy folder", true));
        this.addTest(canUpdateFolder("Can update folder", true));

        //Messages
        this.addTest(canGetMessages("Can get messages", true));
        this.addTest(canCreateMessage("Can create message in drafts", true));
        this.addTest(canCreateMessageAttachment("Can create message with attachment", false));
        this.addTest(canRetrieveMessageAttachment("Can retrieve message with attachment", true));
        this.addTest(canSendMessage("Can send message", true));
        this.addTest(canUpdateMessage("Can update message", true));
        this.addTest(canDeleteMessage("Can delete message", true));
        this.addTest(canMoveMessage("Can move message", true));
        this.addTest(canCopyMessage("Can copy message", true));
        this.addTest(canCreateReplyMessage("Can create reply", true));
        this.addTest(canCreateReplyAllMessage("Can create reply all", true));
        this.addTest(canCreateForwardMessage("Can create forward", true));
        //TODO:create message with attachment
        //TODO:reply action
        //TODO:reply all
        //TODO:forward action


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

                    if (client == null)
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
                    List<Folder> folders = client.getMe().getFolders().read().get();
                    if (folders == null || folders.size() == 0)
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
                    Folder folder = client.getMe().getFolders().getById("Inbox").read().get();
                    if (folder == null || !folder.getDisplayName().equals("Inbox"))
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
                    Folder addedFolder = client.getMe()
                            .getFolders()
                            .getById(parentFolderName)
                            .getChildFolders()
                            .add(newFolder).get();

                    // Assert
                    Folder folder = client.getMe()
                            .getFolders()
                            .getById(parentFolderName)
                            .getChildFolders()
                            .getById(addedFolder.getId()).read().get();

                    if (folder == null || !folder.getDisplayName().equals(newFolderName)) {
                        result.setStatus(TestStatus.Failed);
                    }

                    //Cleanup
                    client.getMe()
                            .getFolders()
                            .getById(parentFolderName)
                            .getChildFolders()
                            .getById(folder.getId())
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
                    Folder addedFolder = client.getMe()
                            .getFolders()
                            .getById(parentFolderName)
                            .getChildFolders()
                            .add(newFolder).get();

                    // Delete folder
                    client.getMe()
                            .getFolders()
                            .getById(parentFolderName)
                            .getChildFolders()
                            .getById(addedFolder.getId())
                            .delete().get();

                    // Assert
                    List<Folder> folders = client.getMe()
                            .getFolders()
                            .getById(parentFolderName)
                            .getChildFolders()
                            .read().get();

                    boolean exists = false;
                    for (Folder f : folders) {
                        if (f.getId().equals(addedFolder.getId())) {
                            exists = true;
                        }
                    }
                    if (exists)
                        result.setStatus(TestStatus.Failed);

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
                    Folder addedFolder = client.getMe()
                            .getFolders()
                            .getById(parentFolderName)
                            .getChildFolders()
                            .add(newFolder).get();

                    //Act
                    client.getMe()
                            .getFolders()
                            .getById(parentFolderName).getChildFolders()
                            .getById(addedFolder.getId())
                            .getOperations().move(destinationFolderName).get();

                    //Assert
                    Folder movedFolder = client.getMe()
                            .getFolders()
                            .getById(destinationFolderName)
                            .getChildFolders()
                            .getById(addedFolder.getId())
                            .read().get();

                    if (movedFolder != null && movedFolder.getDisplayName().equals(newFolderName))
                        result.setStatus(TestStatus.Passed);

                    //Cleanup
                    client.getMe()
                            .getFolders()
                            .getById(destinationFolderName)
                            .getChildFolders()
                            .getById(movedFolder.getId())
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


    private TestCase canCopyFolder(String name, boolean enabled) {
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
                    Folder addedFolder = client.getMe()
                            .getFolders()
                            .getById(parentFolderName)
                            .getChildFolders()
                            .add(newFolder).get();

                    //Act
                    Folder copiedFolder = client.getMe()
                            .getFolders()
                            .getById(parentFolderName).getChildFolders()
                            .getById(addedFolder.getId())
                            .getOperations().copy(destinationFolderName).get();

                    //Assert
                    Folder folder = client.getMe()
                            .getFolders()
                            .getById(destinationFolderName)
                            .getChildFolders()
                            .getById(copiedFolder.getId())
                            .read().get();

                    if (folder != null && folder.getDisplayName().equals(newFolderName))
                        result.setStatus(TestStatus.Passed);

                    //Cleanup
                    client.getMe()
                            .getFolders()
                            .getById(destinationFolderName)
                            .getChildFolders()
                            .getById(copiedFolder.getId())
                            .delete().get();

                    client.getMe()
                            .getFolders()
                            .getById(parentFolderName)
                            .getChildFolders()
                            .getById(addedFolder.getId())
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

    private TestCase canUpdateFolder(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    String folderName = "TestFolder";
                    String updatedFolderName = "UpdatedTestFolder";
                    String parentFolderName = "Inbox";

                    //Create new folder
                    EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();
                    Folder newFolder = new Folder();
                    newFolder.setDisplayName(folderName);
                    Folder addedFolder = client.getMe()
                            .getFolders()
                            .getById(parentFolderName)
                            .getChildFolders()
                            .add(newFolder).get();

                    //Act
                    newFolder.setDisplayName(updatedFolderName);
                    client.getMe()
                            .getFolders()
                            .getById(addedFolder.getId())
                            .update(newFolder);

                    // Assert
                    Folder folder = client.getMe()
                            .getFolders()
                            .getById(addedFolder.getId()).read().get();

                    if (folder == null || !folder.getDisplayName().equals(updatedFolderName)) {
                        result.setStatus(TestStatus.Failed);
                    }

                    //Cleanup
                    client.getMe()
                            .getFolders()
                            .getById(folder.getId())
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

    // Messages tests
    private TestCase canGetMessages(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();

                    List<Message> inboxMessages = client.getMe().getFolders().getById("Inbox").getMessages().top(3).read().get();
                    if(inboxMessages.size()== 0)
                    {
                        String mailSubject = "Test get Message";
                        Message message = getSampleMessage(mailSubject, ApplicationContext.getTestMail(), "");

                        Message toSend = client.getMe().getMessages().add(message).get();
                        client.getMe().getMessages().getById(toSend.getId()).getOperations().send().get();
                        Thread.sleep(500);
                        inboxMessages = client.getMe().getFolders().getById("Inbox").getMessages().top(1).read().get();
                    }

                    if (inboxMessages == null || inboxMessages.size() == 0 || inboxMessages.size() > 3)
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

    private TestCase canCreateMessage(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();

                    Message message = getSampleMessage("Test message", ApplicationContext.getTestMail(), "");

                    //Act
                    Message createdMessage = client.getMe().getMessages().add(message).get();

                    //Assert
                    Message searchedMessage = client.getMe()
                            .getFolders()
                            .getById("Drafts")
                            .getMessages()
                            .getById(createdMessage.getId()).read().get();

                    if (searchedMessage == null || !searchedMessage.getSubject().equals("Test message"))
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getMe().getFolders()
                            .getById("Drafts")
                            .getMessages()
                            .getById(createdMessage.getId())
                            .delete().get();
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

    private TestCase canCreateMessageAttachment(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();

                    Message message = getSampleMessage("Test message", ApplicationContext.getTestMail(), "");

                    //Act
                    Message added = client.getMe().getMessages().add(message).get();
                    client.getMe().getMessages()
                            .getById(added.getId())
                            .getAttachments()
                            .add(getFileAttachment()).get();

                    //Assert
                    Message storedMessage = client.getMe().getMessages().getById(added.getId()).read().get();

                    if(!storedMessage.getHasAttachments())
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
//                    client.getMe().getFolders()
//                            .getById("Drafts")
//                            .getMessages()
//                            .getById(myMessage.getId())
//                            .delete().get();
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

    private TestCase canRetrieveMessageAttachment(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();

                    //Get mail
                    List<Message> messages = client.getMe()
                            .getFolders()
                            .getById("Drafts")
                            .getMessages()
                             .filter("HasAttachments eq true").read().get();

                    List<Attachment> attachments= null;
                    if(messages.size() > 0)
                    {
                        attachments = client.getMe().getMessages().getById(messages.get(0).getId()).getAttachments().read().get();
                        //attachments = client.getMe().getMessages().getById(messages.get(0).getId()).getAttachments().getById("").r
                    }

                    //Assert
                    if(attachments != null && attachments.size() > 0)
                        result.setStatus(TestStatus.Passed);

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

    private TestCase canSendMessage(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();

                    String mailSubject = "Test Send Message";
                    Message message = getSampleMessage(mailSubject, ApplicationContext.getTestMail(), "");

                    //Act
                        client
                        .getMe().getOperations().sendMail(message, true).get();

                    Thread.sleep(2000);
                    //Assert
                    List<Message> sentMessages = client.getMe()
                            .getFolders()
                            .getById("SentItems")
                            .getMessages().read().get();

                    if (sentMessages.size() != 1 || !sentMessages.get(0).getSubject().equals(mailSubject))
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getMe().getFolders()
                            .getById("SentItems")
                            .getMessages()
                            .getById(sentMessages.get(0).getId())
                            .delete().get();
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

    private TestCase canUpdateMessage(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();

                    String mailSubject = "Test Update Message";
                    Message message = getSampleMessage(mailSubject, ApplicationContext.getTestMail(), "");

                    //Act
                    Message addedMessage = client.getMe().getMessages().add(message).get();
                    message.setSubject("New Test Update Message");
                    client
                            .getMe()
                            .getFolders()
                            .getById("Drafts")
                            .getMessages()
                            .getById(addedMessage.getId())
                            .update(message).get();

                    Thread.sleep(1000);
                    //Assert
                    Message updatedMessage = client.getMe()
                            .getFolders()
                            .getById("Drafts")
                            .getMessages()
                            .getById(addedMessage.getId()).read().get();

                    if (updatedMessage == null || !updatedMessage.getSubject().equals("New Test Update Message"))
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getMe().getFolders()
                            .getById("Drafts")
                            .getMessages()
                            .getById(updatedMessage.getId())
                            .delete().get();
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

    private TestCase canDeleteMessage(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();

                    String mailSubject = "Test Delete Message";
                    Message message = getSampleMessage(mailSubject, ApplicationContext.getTestMail(), "");

                    Message addedMessage = client.getMe().getMessages().add(message).get();

                    //Act
                    client.getMe()
                            .getFolders()
                            .getById("Drafts")
                            .getMessages()
                            .getById(addedMessage.getId())
                            .delete().get();

                    Thread.sleep(1000);

                    //Assert
                    List<Message> messages = client.getMe()
                            .getFolders()
                            .getById("Drafts")
                            .getMessages().read().get();

                    boolean exists = false;
                    for (Message m : messages) {
                        if (m.getId().equals(addedMessage.getId()))
                            exists = true;
                    }

                    if (exists)
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

    private TestCase canMoveMessage(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    String destinationFolderName = "Inbox";

                    EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();

                    String mailSubject = "Test move Message";
                    Message message = getSampleMessage(mailSubject, ApplicationContext.getTestMail(), "");

                    Message addedMessage = client.getMe().getMessages().add(message).get();
                    //Act
                    Message movedMessage = client.getMe()
                            .getFolders()
                            .getById("Drafts")
                            .getMessages()
                            .getById(addedMessage.getId())
                            .getOperations().move(destinationFolderName).get();

                    Thread.sleep(2000);
                    //Assert
                    try {
                        Message m = client.getMe()
                                .getFolders()
                                .getById(destinationFolderName)
                                .getMessages()
                                .getById(movedMessage.getId())
                                .read().get();

                        if (m == null && !m.getId().equals(movedMessage.getId()))
                            result.setStatus(TestStatus.Failed);
                    } catch (Throwable t) {
                        result.setStatus(TestStatus.Failed);
                    }


                    //Cleanup
                    client.getMe()
                            .getMessages()
                            .getById(movedMessage.getId())
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

    private TestCase canCopyMessage(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    String destinationFolderName = "Inbox";

                    EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();

                    String mailSubject = "Test copy Message";
                    Message message = getSampleMessage(mailSubject, ApplicationContext.getTestMail(), "");

                    Message addedMessage = client.getMe().getMessages().add(message).get();

                    //Act
                    Message copiedMessage = client.getMe()
                            .getFolders()
                            .getById("Drafts")
                            .getMessages()
                            .getById(addedMessage.getId())
                            .getOperations().copy(destinationFolderName).get();

                    Thread.sleep(2000);
                    //Assert
                    try {
                        Message m = client.getMe()
                                .getFolders()
                                .getById(destinationFolderName)
                                .getMessages()
                                .getById(copiedMessage.getId())
                                .read().get();

                        if (m == null && !m.getId().equals(copiedMessage.getId()))
                            result.setStatus(TestStatus.Failed);
                    } catch (Throwable t) {
                        result.setStatus(TestStatus.Failed);
                    }


                    //Cleanup
                    client.getMe()
                            .getMessages()
                            .getById(copiedMessage.getId())
                            .delete().get();

                    client.getMe()
                            .getMessages()
                            .getById(addedMessage.getId())
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


    private TestCase canCreateReplyMessage(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();

                    List<Message> inboxMessages = client.getMe().getFolders().getById("Inbox").getMessages().top(1).read().get();
                    if(inboxMessages.size()== 0)
                    {
                        String mailSubject = "Test reply Message";
                        Message message = getSampleMessage(mailSubject, ApplicationContext.getTestMail(), "");

                        Message toSend = client.getMe().getMessages().add(message).get();
                        client.getMe().getMessages().getById(toSend.getId()).getOperations().send().get();
                        Thread.sleep(500);
                        inboxMessages = client.getMe().getFolders().getById("Inbox").getMessages().top(1).read().get();
                    }

                    Message messageToReply = inboxMessages.get(0);
                    //Act
                    Message repliedMessage = client.getMe()
                            .getFolders()
                            .getById("Drafts")
                            .getMessages()
                            .getById(messageToReply.getId())
                            .getOperations().createReply().get();

                    Thread.sleep(500);

                    //Assert
                    List<Message> messages = client.getMe()
                            .getFolders()
                            .getById("Drafts")
                            .getMessages().read().get();

                    boolean exists = false;
                    for(Message m : messages){
                        if(m.getConversationId().equals(messageToReply.getConversationId()))
                            exists = true;
                    }

                    if(!exists)
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getMe()
                            .getMessages()
                            .getById(messageToReply.getId())
                            .delete().get();

                    client.getMe()
                            .getMessages()
                            .getById(repliedMessage.getId())
                            .delete().get();
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

    private TestCase canCreateReplyAllMessage(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();

                    List<Message> inboxMessages = client.getMe().getFolders().getById("Inbox").getMessages().top(1).read().get();
                    if(inboxMessages.size()== 0)
                    {
                        String mailSubject = "Test reply all Message";
                        Message message = getSampleMessage(mailSubject, ApplicationContext.getTestMail(), "");

                        Message toSend = client.getMe().getMessages().add(message).get();
                        client.getMe().getMessages().getById(toSend.getId()).getOperations().send().get();
                        Thread.sleep(500);
                        inboxMessages = client.getMe().getFolders().getById("Inbox").getMessages().top(1).read().get();
                    }

                    Message messageToReply = inboxMessages.get(0);
                    //Act
                    Message repliedMessage = client.getMe()
                            .getFolders()
                            .getById("Drafts")
                            .getMessages()
                            .getById(messageToReply.getId())
                            .getOperations().createReplyAll().get();

                    Thread.sleep(500);

                    //Assert
                    List<Message> messages = client.getMe()
                            .getFolders()
                            .getById("Drafts")
                            .getMessages().read().get();

                    boolean exists = false;
                    for(Message m : messages){
                        if(m.getConversationId().equals(messageToReply.getConversationId()))
                            exists = true;
                    }

                    if(!exists)
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getMe()
                            .getMessages()
                            .getById(messageToReply.getId())
                            .delete().get();

                    client.getMe()
                            .getMessages()
                            .getById(repliedMessage.getId())
                            .delete().get();
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

    private TestCase canCreateForwardMessage(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();

                    List<Message> inboxMessages = client.getMe().getFolders().getById("Inbox").getMessages().top(1).read().get();
                    if(inboxMessages.size()== 0)
                    {
                        String mailSubject = "Test fw Message";
                        Message message = getSampleMessage(mailSubject, ApplicationContext.getTestMail(), "");

                        Message toSend = client.getMe().getMessages().add(message).get();
                        client.getMe().getMessages().getById(toSend.getId()).getOperations().send().get();
                        Thread.sleep(500);
                        inboxMessages = client.getMe().getFolders().getById("Inbox").getMessages().top(1).read().get();
                    }

                    Message messageToReply = inboxMessages.get(0);
                    //Act
                    Message repliedMessage = client.getMe()
                            .getFolders()
                            .getById("Drafts")
                            .getMessages()
                            .getById(messageToReply.getId())
                            .getOperations().createForward().get();

                    Thread.sleep(500);

                    //Assert
                    List<Message> messages = client.getMe()
                            .getFolders()
                            .getById("Drafts")
                            .getMessages().read().get();

                    boolean exists = false;
                    for(Message m : messages){
                        if(m.getConversationId().equals(messageToReply.getConversationId()))
                            exists = true;
                    }

                    if(!exists)
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getMe()
                            .getMessages()
                            .getById(messageToReply.getId())
                            .delete().get();

                    client.getMe()
                            .getMessages()
                            .getById(repliedMessage.getId())
                            .delete().get();
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

    /*private String getChildFolderIdByName(String folderName, String childFolder){
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

    private String getMessageId(String folder, String subject) {

        try {
            EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();
            List<Message> messages = client.getMe().getFolders().getById(folder).getMessages().execute().get();

            for (int b = 0; b < messages.size(); b++) {
                if(messages.get(b).getSubject().equals(subject))
                    return messages.get(b).getId();
            }

            return "";
        }catch (Throwable t){
            Log.e("Error", t.getMessage());
            return "";

        }
    }*/

    private Message getSampleMessage(String subject, String to, String cc){
        Message m = new Message();
        // To Recipient
        final Recipient toRecipient = new Recipient();
        EmailAddress toEmailAddress = new EmailAddress();
        toEmailAddress.setAddress(to);
        toRecipient.setEmailAddress(toEmailAddress);
        ArrayList<Recipient> toRecipients = new ArrayList<Recipient>();
        toRecipients.add(toRecipient);
        m.setToRecipients(toRecipients);

        // CC recipient
        if(!cc.isEmpty()){
            final Recipient ccRecipient = new Recipient();
            EmailAddress ccEmailAddress = new EmailAddress();
            ccEmailAddress.setAddress(cc);
            ccRecipient.setEmailAddress(ccEmailAddress);
            ArrayList<Recipient> ccRecipients = new ArrayList<Recipient>();
            ccRecipients.add(ccRecipient);
            m.setCcRecipients(ccRecipients);
        }
        //Body and subject
        m.setSubject(subject);
        ItemBody body = new ItemBody();
        body.setContent("This is the email's body");
        m.setBody(body);

        return m;
    }

    FileAttachment getFileAttachment() {
        FileAttachment att = new FileAttachment();

        att.setContentBytes("hello world".getBytes());
        att.setName("myFile.txt");

        return att;
    }
}
