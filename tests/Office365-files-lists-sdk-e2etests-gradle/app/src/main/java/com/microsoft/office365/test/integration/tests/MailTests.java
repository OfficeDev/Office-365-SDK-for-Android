package com.microsoft.office365.test.integration.tests;

import android.util.Log;


import com.microsoft.office365.exchange.services.model.Attachment;
import com.microsoft.office365.exchange.services.model.EmailAddress;
import com.microsoft.office365.exchange.services.model.FileAttachment;
import com.microsoft.office365.exchange.services.model.Folder;
import com.microsoft.office365.exchange.services.model.ItemBody;
import com.microsoft.office365.exchange.services.model.Message;
import com.microsoft.office365.exchange.services.model.Recipient;
import com.microsoft.office365.exchange.services.odata.EntityContainerClient;
import com.microsoft.office365.test.integration.ApplicationContext;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestGroup;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.office365.test.integration.framework.TestStatus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class MailTests extends TestGroup {

    public MailTests() {
        super("Mail tests");

        this.addTest(canCreateClient("Can create client", true));
        // Folders
        this.addTest(canRetrieveFolders("Can retrieve folders", true));
        this.addTest(canRetrieveFolderById("Can retrieve folder by id", true));
        this.addTest(canCreateFolder("Can create folder", true));
        this.addTest(canDeleteFolder("Can delete folder", true));
        this.addTest(canMoveFolder("Can move folder", false));
        this.addTest(canCopyFolder("Can copy folder", false));
        //update folder

        //Messages
        this.addTest(canGetMessages("Can get messages", true));
        this.addTest(canCreateMessage("Can create message in drafts", true));
        this.addTest(canCreateMessageAttachment("Can create message with attachment", false));
        this.addTest(canSendMessage("Can send message", true));
        this.addTest(canUpdateMessage("Can update message", true));
        this.addTest(canDeleteMessage("Can delete message", true));
        this.addTest(canMoveMessage("Can move message", false));
        this.addTest(canMoveMessage("Can copy message", false));
        //create message with attachment
        //createReply ->to drafts
        //reply all ->to drafts
        //forward -> to drafts
        //reply action
        //reply all
        //forward action


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
                            .getById(addedFolder.getId()).execute().get();

                    if(folder == null || !folder.getDisplayName().equals(newFolderName)){
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
                            .execute().get();

                    boolean exists = false;
                    for (Folder f : folders) {
                        if(f.getId().equals(addedFolder.getId())){
                            exists = true;
                        }
                    }
                    if(exists)
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
                            .execute().get();

                    if(movedFolder != null && movedFolder.getDisplayName().equals(newFolderName))
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
                            .execute().get();

                    if(folder!= null && folder.getDisplayName().equals(newFolderName))
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

    private TestCase canGetMessages(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();
                    List<Message> messages = client.getMe()
                            .getFolders()
                            .getById("Inbox")
                            .getMessages()
                            .top(3)
                            .execute().get();

                    if(messages == null || messages.size() == 0 || messages.size()>3)
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
                            .getById(createdMessage.getId()).execute().get();

                    if(searchedMessage == null || !searchedMessage.getSubject().equals("Test message"))
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

                    /*EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();

                    Message message = getSampleMessage("Test message", ApplicationContext.getTestMail(), "", true);

                    //Act
                    Message added = client.getMe().getMessages().add(message).get();

                    String id = added.getId();



                    client.getMe().getFolders().getById("Drafts").getMessages().getById(id).getAttachments().add(getFileAttachment());
                    //Assert
                    List<Message> messages = client.getMe().getFolders().getById("Drafts").getMessages().execute().get();

                    Message myMessage = messages.get(0);
                    if(messages.size()!= 1 || !myMessage.getSubject().equals("Test message"))
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getMe().getFolders()
                            .getById("Drafts")
                            .getMessages()
                            .getById(myMessage.getId())
                            .delete().get();*/
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
                    Message addedMessage = client.getMe().getMessages().add(message).get();
                        client
                            .getMe()
                            .getFolders()
                            .getById("Drafts")
                            .getMessages()
                            .getById(addedMessage.getId())
                            .getOperations().send().get();

                    Thread.sleep(1000);
                    //Assert
                    List<Message> sentMessages = client.getMe()
                            .getFolders()
                            .getById("SentItems")
                            .getMessages().execute().get();

                    if(sentMessages.size() != 1 || !sentMessages.get(0).getSubject().equals(mailSubject))
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
                            .getById(addedMessage.getId()).execute().get();

                    if(updatedMessage == null || !updatedMessage.getSubject().equals("New Test Update Message"))
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
                            .getMessages().execute().get();

                    boolean exists = false;
                    for(Message m : messages){
                        if(m.getId().equals(addedMessage.getId()))
                            exists = true;
                    }

                    if(exists)
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
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    String destinationFolderName = "Inbox";

                    EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();

                    String mailSubject = "Test move Message";
                    Message message = getSampleMessage(mailSubject, ApplicationContext.getTestMail(), "");

                    Message addedMessage = client.getMe().getMessages().add(message).get();
                    //Act
                    client.getMe()
                            .getFolders()
                            .getById("Drafts")
                            .getMessages()
                            .getById(addedMessage.getId())
                            .getOperations().move(destinationFolderName).get();

                    //Assert
                    Message m = client.getMe()
                            .getFolders()
                            .getById(destinationFolderName)
                            .getMessages()
                            .getById(addedMessage.getId())
                            .execute().get();

                    if(m != null)
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getMe()
                            .getFolders()
                            .getById(destinationFolderName)
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
        return getSampleMessage(subject, to, cc, false);
    }

    private Message getSampleMessage(String subject, String to, String cc, boolean withAttachment){
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

        if(withAttachment){
            //m.set
        }
        return m;
    }
    /*
    FileAttachment getFileAttachment() {

        FileAttachment a = new FileAttachment();
        a.setName("Test attachment");
        a.setContentBytes();

        return a;

    }*/
}
