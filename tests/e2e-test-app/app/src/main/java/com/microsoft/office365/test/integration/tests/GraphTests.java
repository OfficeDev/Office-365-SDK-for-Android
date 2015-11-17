package com.microsoft.office365.test.integration.tests;


import android.util.Log;

import com.microsoft.services.graph.*;
import com.microsoft.services.graph.fetchers.GraphServiceClient;
import com.microsoft.office365.test.integration.ApplicationContext;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestGroup;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.office365.test.integration.framework.TestStatus;
import com.microsoft.services.orc.core.Constants;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class GraphTests extends TestGroup {

    public GraphTests() {
        super("Graph tests");

        this.addTest(canGetContacts("Can get contacts", true));
        this.addTest(canGetDevices("Can get devices", true));
        this.addTest(canGetGroups("Can get groups", true));
        this.addTest(canGetDirectoryRoles("Can get directoryRoles", true));
        this.addTest(canGetDirectoryRoleTemplates("Can get directoryRoleTemplates", true));
        this.addTest(canGetUsers("Can get users", true));
        this.addTest(canGetSubscribedSkus("Can get SubscribedSkus", true));

        //Get User's information
        this.addTest(canGetUserOwnedDevices("Can get user's ownedDevices", true));
        this.addTest(canGetUserRegisteredDevices("Can get user's registeredDevices", true));
        this.addTest(canGetUserDirectReports("Can get user's directReports", true));
        this.addTest(canGetUserMemberOf("Can get user's memberOf", true));
        this.addTest(canGetUserMemberOfById("Can get user's memberOf by id", true));
        this.addTest(canGetUserCreatedObjects("Can get user's createdObjects", true));
        this.addTest(canGetUserOwnedObjects("Can get user's ownedObjects", true));
        this.addTest(canGetUserOwnedObjectsById("Can get user's ownedObject by id", true));

        this.addTest(canGetUserMessages("Can get user's Messages", true));
        this.addTest(canGetUserMessagesById("Can get user's Message by id", true));
        this.addTest(canSendMessage("Can send message", true));
        this.addTest(canCreateMessageAttachment("Can create message with attachment", true));
        this.addTest(canGetMessageAttachments("Can get message attachment", true));
        this.addTest(canSendWithMessageOperations("Can send with message operations", true));
        this.addTest(canSendHtmlMessage("Can send html message", true));
        this.addTest(canReplyHtmlMessage("Can reply html message", true));
        this.addTest(canUpdateMessage("Can update message", true));
        this.addTest(canDeleteMessage("Can delete message", true));
        this.addTest(canMoveMessage("Can move message", true));
        this.addTest(canCopyMessage("Can copy message", true));
        this.addTest(canCreateReplyMessage("Can create reply", true));
        this.addTest(canCreateReplyAllMessage("Can create reply all", true));
        this.addTest(canCreateForwardMessage("Can create forward", true));
        this.addTest(canUpdateSendingOnlyUpdatedValues("Can update sending only updated values", true));
        
        
        this.addTest(canGetUserCalendars("Can get user's Calendars", true));
        this.addTest(canGetUserCalendarsById("Can get user's Calendar by id", true));
        this.addTest(canGetUserCalendar("Can get user's default Calendar", true));
        this.addTest(canGetUserCalendarGroups("Can get user's CalendarGroups", true));
        this.addTest(canGetUserCalendarGroupsById("Can get user's CalendarGroup by id", true));

        this.addTest(canGetUserEvents("Can get user's Events", true));
        this.addTest(canGetUserEventsById("Can get user's Event by id", true));
        this.addTest(canCreateUserEvent("Can create user's Event", true));
        this.addTest(canUpdateUserEvent("Can update user's Event", true));
        this.addTest(canDeleteUserEvent("Can delete user's Event", true));
        this.addTest(canGetUserCalendarView("Can get user's CalendarView", true));

        this.addTest(canGetUserUserPhoto("Can get user's UserPhoto", true));
        this.addTest(canGetUserDrive("Can get user's drive", true));
        this.addTest(canGetUserFilesById("Can get user's file by id", true));
        this.addTest(canCreateUserFiles("Can create user's files", true));
        this.addTest(canUpdateUserFiles("Can update user's files", true));
        this.addTest(canDeleteUserFiles("Can delete user's files", true));

        this.addTest(canRetrieveFolders("Can retrieve folders", true));
        this.addTest(canRetrieveFolderById("Can retrieve folder by id", true));
        this.addTest(canRetrieveFolder("Can Retrieve Folder (overload)", true));
        this.addTest(canCreateFolder("Can create folder", true));
        this.addTest(canDeleteFolder("Can delete folder", true));
        this.addTest(canMoveFolder("Can move folder", true));
        this.addTest(canCopyFolder("Can copy folder", true));
        this.addTest(canUpdateFolder("Can update folder", true));
    }

    private TestCase canGetContacts(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Act
                    List<Contact> contacts = client.getMe().getContacts().read().get();

                    //Assert
                    if (contacts != null)
                        result.setStatus(TestStatus.Passed);

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


    private TestCase canGetDevices(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Act
                    List<Device> devices = client.getDevices().read().get();

                    //Assert
                    if (devices != null)
                        result.setStatus(TestStatus.Passed);

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

    private TestCase canGetGroups(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Act
                    List<Group> groups = client.getGroups().read().get();

                    //Assert
                    if (groups != null)
                        result.setStatus(TestStatus.Passed);

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

    private TestCase canGetDirectoryRoles(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Act
                    List<DirectoryRole> directoryRoles = client.getDirectoryRoles().read().get();

                    //Assert
                    if (directoryRoles != null)
                        result.setStatus(TestStatus.Passed);

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

    private TestCase canGetDirectoryRoleTemplates(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Act
                    List<DirectoryRoleTemplate> directoryRoleTemplates = client.getDirectoryRoleTemplates().read().get();

                    //Assert
                    if (directoryRoleTemplates != null)
                        result.setStatus(TestStatus.Passed);

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


    private TestCase canGetUsers(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Act
                    List<User> users = client.getUsers().read().get();

                    //Assert
                    if (users != null)
                        result.setStatus(TestStatus.Passed);

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

    private TestCase canGetSubscribedSkus(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Act
                    List<SubscribedSku> SubscribedSkus = client.getSubscribedSkus().read().get();

                    //Assert
                    if (SubscribedSkus != null)
                        result.setStatus(TestStatus.Passed);

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


    private TestCase canGetUserOwnedDevices(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Act
                    List<DirectoryObject> ownedDevices = client.getUsers().getById(ApplicationContext.getTestMail()).getOwnedDevices().read().get();

                    //Assert
                    if (ownedDevices != null)
                        result.setStatus(TestStatus.Passed);

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

    private TestCase canGetUserRegisteredDevices(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Act
                    List<DirectoryObject> registeredDevices = client.getUsers().getById(ApplicationContext.getTestMail()).getRegisteredDevices().read().get();

                    //Assert
                    if (registeredDevices != null)
                        result.setStatus(TestStatus.Passed);

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

    private TestCase canGetUserDirectReports(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Act
                    List<DirectoryObject> directReports = client.getUsers().getById(ApplicationContext.getTestMail()).getDirectReports().read().get();

                    //Assert
                    if (directReports != null)
                        result.setStatus(TestStatus.Passed);

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

    private TestCase canGetUserDirectReportsById(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Prepare
                    List<DirectoryObject> directReports = client.getUsers().getById(ApplicationContext.getTestMail()).getDirectReports().top(1).read().get();
                    String directReportId;
                    if (directReports != null && directReports.size() > 0) {
                        directReportId = directReports.get(0).getId().toString();
                        //Act
                        DirectoryObject directReport = client.getUsers().getById(ApplicationContext.getTestMail())
                                .getDirectReport(directReportId).read().get();
                        //Assert
                        if (directReport != null)
                            result.setStatus(TestStatus.Passed);
                    } else {
                        result.setException(new Exception("No available DirectReports"));
                    }
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

    private TestCase canGetUserMemberOf(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Act
                    List<DirectoryObject> memberOf = client.getUsers().getById(ApplicationContext.getTestMail()).getMemberOf().read().get();

                    //Assert
                    if (memberOf != null)
                        result.setStatus(TestStatus.Passed);

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

    private TestCase canGetUserMemberOfById(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

//Prepare
                    List<DirectoryObject> memberOf = client.getUsers().getById(ApplicationContext.getTestMail()).getMemberOf().top(1).read().get();
                    String memberOfId;
                    if (memberOf != null && memberOf.size() > 0) {
                        memberOfId = memberOf.get(0).getId().toString();
                        //Act
                        DirectoryObject memberOfItem = client.getUsers().getById(ApplicationContext.getTestMail())
                                .getMemberOf(memberOfId).read().get();
                        //Assert
                        if (memberOfItem != null)
                            result.setStatus(TestStatus.Passed);
                    } else {
                        result.setException(new Exception("No available MemberOf"));
                    }
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

    private TestCase canGetUserCreatedObjects(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Act
                    List<DirectoryObject> createdObjects = client.getUsers().getById(ApplicationContext.getTestMail()).getCreatedObjects().read().get();

                    //Assert
                    if (createdObjects != null)
                        result.setStatus(TestStatus.Passed);

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

    private TestCase canGetUserOwnedObjects(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Act
                    List<DirectoryObject> ownedObjects = client.getUsers().getById(ApplicationContext.getTestMail()).getOwnedObjects().read().get();

                    //Assert
                    if (ownedObjects != null)
                        result.setStatus(TestStatus.Passed);

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

    private TestCase canGetUserOwnedObjectsById(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Prepare
                    List<DirectoryObject> ownedObjects = client.getUsers().getById(ApplicationContext.getTestMail()).getOwnedObjects().top(1).read().get();
                    String ownedObjectId;
                    if (ownedObjects != null && ownedObjects.size() > 0) {
                        ownedObjectId = ownedObjects.get(0).getId().toString();
                        //Act
                        DirectoryObject ownedObject = client.getUsers().getById(ApplicationContext.getTestMail())
                                .getOwnedObject(ownedObjectId).read().get();
                        //Assert
                        if (ownedObject != null)
                            result.setStatus(TestStatus.Passed);
                    } else {
                        result.setException(new Exception("No available OwnedObjects"));
                    }
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

    private TestCase canGetUserMessages(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Act
                    List<Message> Messages = client.getUsers().getById(ApplicationContext.getTestMail()).getMessages().read().get();

                    //Assert
                    if (Messages != null)
                        result.setStatus(TestStatus.Passed);

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

    private TestCase canGetUserMessagesById(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Prepare
                    List<Message> Messages = client.getUsers().getById(ApplicationContext.getTestMail()).getMessages().top(1).read().get();
                    String messageId;
                    if (Messages != null && Messages.size() > 0) {
                        messageId = Messages.get(0).getId();
                        //Act
                        Message message = client.getUsers().getById(ApplicationContext.getTestMail())
                                .getMessage(messageId).read().get();
                        //Assert
                        if (message != null)
                            result.setStatus(TestStatus.Passed);
                    } else {
                        result.setException(new Exception("No available Messages"));
                    }
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
    
    private TestCase canCreateMessageAttachment(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    Message message = getSampleMessage("Test message", ApplicationContext.getTestMail(), "");

                    //Act
                    Message added = client.getMe().getMessages().add(message).get();
                    client.getMe().getMessages()
                            .getById(added.getId())
                            .getAttachments()
                            .add(getFileAttachment()).get();

                    //Assert
                    Message storedMessage = client.getMe().getMessages().getById(added.getId()).read().get();

                    if (!storedMessage.getHasAttachments())
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getMe().getMessage(added.getId()).delete().get();

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

    private TestCase canGetMessageAttachments(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    Message message = getSampleMessage("Test message", ApplicationContext.getTestMail(), "");

                    //Prepare
                    Message added = client.getMe().getMessages().add(message).get();
                    client.getMe().getMessages()
                            .getById(added.getId())
                            .getAttachments()
                            .add(getFileAttachment()).get();

                    //Act
                    List<Attachment> attachments = client.getMe().getMessage(added.getId()).getAttachments().read().get();

                    //Assert
                    if (attachments != null && attachments.size() > 0)
                        result.setStatus(TestStatus.Passed);


                    //Cleanup
                    client.getMe().getMessage(added.getId()).delete().get();
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

    private TestCase canSendMessage(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    String mailSubject = "Test Send Message";
                    Message message = getSampleMessage(mailSubject, ApplicationContext.getTestMail(), "");

                    //Act
                    client
                            .getMe().getOperations().sendMail(message, true).get();

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

    private TestCase canSendWithMessageOperations(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    String mailSubject = "Test Send Message";
                    Message message = getSampleMessage(mailSubject, ApplicationContext.getTestMail(), "");

                    //Act
                    Message addedMessage = client.getMe().getMessages().add(message).get();
                    client.getMe().getMessage(addedMessage.getId()).getOperations().send();

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

    private TestCase canSendHtmlMessage(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    String mailSubject = "Test Send Message" + UUID.randomUUID().toString();
                    Message message = getSampleMessage(mailSubject, ApplicationContext.getTestMail(), "");
                    ItemBody itemBody = message.getBody();
                    itemBody.setContentType(BodyType.html);
                    itemBody.setContent("<h1>This is an Html body.</h1><a href='#'>With Link!</a>");
                    message.setBody(itemBody);

                    //Act
                    client.getMe().getOperations().sendMail(message, true).get();

                    //Assert
                    List<MailFolder> sentFolder = client.getMe().getMailFolders().filter("DisplayName eq 'Sent Items'").read().get();
                    List<Message> messages = client.getMe()
                            .getMailFolder(sentFolder.get(0).getId())
                            .getMessages()
                            .filter("Subject eq '" + mailSubject + "'")
                            .read().get();

                    if (messages.size() > 0 && messages.get(0).getBody().getContentType() == BodyType.html)
                        result.setStatus(TestStatus.Passed);

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

    private TestCase canReplyHtmlMessage(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    String mailSubject = "Test Send Message" + UUID.randomUUID().toString();
                    Message message = getSampleMessage(mailSubject, ApplicationContext.getTestMail(), "");
                    ItemBody itemBody = message.getBody();
                    itemBody.setContentType(BodyType.html);
                    itemBody.setContent("<h1>This is an Html body.</h1><a href='#'>With Link!</a>");
                    message.setBody(itemBody);

                    //Prepare
                    client.getMe().getOperations().sendMail(message, true).get();

                    List<MailFolder> sentFolder = client.getMe().getMailFolders().filter("DisplayName eq 'Sent Items'").read().get();
                    List<Message> messages = client.getMe()
                            .getMailFolder(sentFolder.get(0).getId())
                            .getMessages()
                            .filter("Subject eq '" + mailSubject + "'")
                            .read().get();

                    //Act
                    Message messageToReply = client.getMe().getMailFolder(sentFolder.get(0).getId())
                            .getMessage(messages.get(0).getId()).getOperations().createReply().get();

                    //Assert
                    if (messageToReply.getBody().getContentType() == BodyType.html)
                        result.setStatus(TestStatus.Passed);

                    //Cleanup
                    client.getMe().getMessage(messageToReply.getId()).delete().get();
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

    private TestCase canUpdateMessage(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    String mailSubject = "Test Update Message";
                    Message message = getSampleMessage(mailSubject, ApplicationContext.getTestMail(), "");

                    //Act
                    Message addedMessage = client.getMe().getMessages().add(message).get();
                    message.setSubject("New Test Update Message");
                    client
                            .getMe()
                            .getMailFolders()
                            .getById("Drafts")
                            .getMessages()
                            .getById(addedMessage.getId())
                            .update(message).get();

                    Thread.sleep(1000);
                    //Assert
                    Message updatedMessage = client.getMe()
                            .getMailFolders()
                            .getById("Drafts")
                            .getMessages()
                            .getById(addedMessage.getId()).read().get();

                    if (updatedMessage == null || !updatedMessage.getSubject().equals("New Test Update Message"))
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getMe().getMailFolders()
                            .getById("Drafts")
                            .getMessages()
                            .getById(updatedMessage.getId())
                            .delete().get();
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

    private TestCase canDeleteMessage(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    String mailSubject = "Test Delete Message";
                    Message message = getSampleMessage(mailSubject, ApplicationContext.getTestMail(), "");

                    Message addedMessage = client.getMe().getMessages().add(message).get();

                    //Act
                    client.getMe()
                            .getMailFolders()
                            .getById("Drafts")
                            .getMessages()
                            .getById(addedMessage.getId())
                            .delete().get();

                    Thread.sleep(1000);

                    //Assert
                    List<Message> messages = client.getMe()
                            .getMailFolders()
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
                } catch (Throwable t) {
                    return createResultFromException(new Exception(t));
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

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    String mailSubject = "Test move Message";
                    Message message = getSampleMessage(mailSubject, ApplicationContext.getTestMail(), "");

                    Message addedMessage = client.getMe().getMessages().add(message).get();
                    //Act
                    Message movedMessage = client.getMe()
                            .getMailFolders()
                            .getById("Drafts")
                            .getMessages()
                            .getById(addedMessage.getId())
                            .getOperations().move(destinationFolderName).get();

                    Thread.sleep(2000);
                    //Assert
                    try {
                        Message m = client.getMe()
                                .getMailFolders()
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
                } catch (Throwable t) {
                    return createResultFromException(new Exception(t));
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

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    String mailSubject = "Test copy Message";
                    Message message = getSampleMessage(mailSubject, ApplicationContext.getTestMail(), "");

                    Message addedMessage = client.getMe().getMessages().add(message).get();

                    //Act
                    Message copiedMessage = client.getMe()
                            .getMailFolders()
                            .getById("Drafts")
                            .getMessages()
                            .getById(addedMessage.getId())
                            .getOperations().copy(destinationFolderName).get();

                    Thread.sleep(2000);
                    //Assert
                    try {
                        Message m = client.getMe()
                                .getMailFolders()
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
                } catch (Throwable t) {
                    return createResultFromException(new Exception(t));
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

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    List<Message> inboxMessages = client.getMe().getMailFolders().getById("Inbox").getMessages().top(1).read().get();
                    if (inboxMessages.size() == 0) {
                        String mailSubject = "Test reply Message";
                        Message message = getSampleMessage(mailSubject, ApplicationContext.getTestMail(), "");
                        client.getMe().getOperations().sendMail(message, true).get();
                        Thread.sleep(2000);
                        inboxMessages = client.getMe().getMailFolders().getById("Inbox").getMessages().top(1).read().get();
                    }

                    Message messageToReply = inboxMessages.get(0);
                    //Act
                    Message repliedMessage = client.getMe()
                            .getMessages()
                            .getById(messageToReply.getId())
                            .getOperations().createReply().get();

                    //Assert
                    List<Message> messages = client.getMe()
                            .getMailFolders()
                            .getById("Drafts")
                            .getMessages().read().get();

                    boolean exists = false;
                    for (Message m : messages) {
                        if (m.getConversationId().equals(messageToReply.getConversationId()))
                            exists = true;
                    }

                    if (!exists)
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
                } catch (Throwable t) {
                    return createResultFromException(new Exception(t));
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

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    List<Message> inboxMessages = client.getMe().getMailFolders().getById("Inbox").getMessages().top(1).read().get();
                    if (inboxMessages.size() == 0) {
                        String mailSubject = "Test reply all Message";
                        Message message = getSampleMessage(mailSubject, ApplicationContext.getTestMail(), "");
                        client.getMe().getOperations().sendMail(message, true).get();
                        Thread.sleep(2000);
                        inboxMessages = client.getMe().getMailFolders().getById("Inbox").getMessages().top(1).read().get();
                    }

                    Message messageToReply = inboxMessages.get(0);
                    //Act
                    Message repliedMessage = client.getMe()
                            .getMailFolders()
                            .getById("Drafts")
                            .getMessages()
                            .getById(messageToReply.getId())
                            .getOperations().createReplyAll().get();

                    //Assert
                    List<Message> messages = client.getMe()
                            .getMailFolders()
                            .getById("Drafts")
                            .getMessages().read().get();

                    boolean exists = false;
                    for (Message m : messages) {
                        if (m.getConversationId().equals(messageToReply.getConversationId()))
                            exists = true;
                    }

                    if (!exists)
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
                } catch (Throwable t) {
                    return createResultFromException(new Exception(t));
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

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    List<Message> inboxMessages = client.getMe().getMailFolders().getById("Inbox").getMessages().top(1).read().get();
                    if (inboxMessages.size() == 0) {
                        String mailSubject = "Test fw Message";
                        Message message = getSampleMessage(mailSubject, ApplicationContext.getTestMail(), "");

                        client.getMe().getOperations().sendMail(message, true).get();
                        Thread.sleep(2000);
                        inboxMessages = client.getMe().getMailFolders().getById("Inbox").getMessages().top(1).read().get();
                    }

                    Message messageToReply = inboxMessages.get(0);
                    //Act
                    Message repliedMessage = client.getMe()
                            .getMailFolders()
                            .getById("Drafts")
                            .getMessages()
                            .getById(messageToReply.getId())
                            .getOperations().createForward().get();

                    //Assert
                    List<Message> messages = client.getMe()
                            .getMailFolders()
                            .getById("Drafts")
                            .getMessages().read().get();

                    boolean exists = false;
                    for (Message m : messages) {
                        if (m.getConversationId().equals(messageToReply.getConversationId()))
                            exists = true;
                    }

                    if (!exists)
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
                } catch (Throwable t) {
                    return createResultFromException(new Exception(t));
                }
            }
        };

        test.setName(name);
        test.setEnabled(enabled);
        return test;
    }

    private TestCase canUpdateSendingOnlyUpdatedValues(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();
                    Message message = client.getMe().getMailFolders().getById("Inbox").getMessages()
                            .top(1).read().get().get(0);
                    message.setIsRead(false);
                    message.setIsRead(true);

                    Message updatedMessage = client.getMe().getMessage(message.getId()).update(message).get();

                    if (updatedMessage == null || !updatedMessage.getIsRead()) {
                        result.setStatus(TestStatus.Failed);
                    }

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
    
    private TestCase canGetUserCalendars(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Act
                    List<Calendar> Calendars = client.getUsers().getById(ApplicationContext.getTestMail()).getCalendars().read().get();

                    //Assert
                    if (Calendars != null)
                        result.setStatus(TestStatus.Passed);

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

    private TestCase canGetUserCalendarsById(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Prepare
                    List<Calendar> Calendars = client.getUsers().getById(ApplicationContext.getTestMail()).getCalendars().top(1).read().get();
                    String calendarId;
                    if (Calendars != null && Calendars.size() > 0) {
                        calendarId = Calendars.get(0).getId().toString();
                        //Act
                        Calendar calendar = client.getUsers().getById(ApplicationContext.getTestMail())
                                .getCalendar(calendarId).read().get();
                        //Assert
                        if (calendar != null)
                            result.setStatus(TestStatus.Passed);
                    } else {
                        result.setException(new Exception("No available Calendars"));
                    }
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

    private TestCase canGetUserCalendar(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Act
                    Calendar calendar = client.getUsers().getById(ApplicationContext.getTestMail()).getCalendar().read().get();

                    //Assert
                    if (calendar != null)
                        result.setStatus(TestStatus.Passed);

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

    private TestCase canGetUserCalendarGroups(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Act
                    List<CalendarGroup> CalendarGroups = client.getUsers().getById(ApplicationContext.getTestMail()).getCalendarGroups().read().get();

                    //Assert
                    if (CalendarGroups != null)
                        result.setStatus(TestStatus.Passed);

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

    private TestCase canGetUserCalendarGroupsById(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

//Prepare
                    List<CalendarGroup> CalendarGroups = client.getUsers().getById(ApplicationContext.getTestMail()).getCalendarGroups().top(1).read().get();
                    String calendarGroupId;
                    if (CalendarGroups != null && CalendarGroups.size() > 0) {
                        calendarGroupId = CalendarGroups.get(0).getId().toString();
                        //Act
                        CalendarGroup calendarGroup = client.getUsers().getById(ApplicationContext.getTestMail())
                                .getCalendarGroup(calendarGroupId).read().get();
                        //Assert
                        if (calendarGroup != null)
                            result.setStatus(TestStatus.Passed);
                    } else {
                        result.setException(new Exception("No available CalendarGroups"));
                    }
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

    private TestCase canGetUserEvents(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Act
                    List<Event> Events = client.getUsers().getById(ApplicationContext.getTestMail()).getEvents().read().get();

                    //Assert
                    if (Events != null)
                        result.setStatus(TestStatus.Passed);

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

    private TestCase canGetUserEventsById(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Prepare
                    List<Event> Events = client.getUsers().getById(ApplicationContext.getTestMail()).getEvents().top(1).read().get();
                    String eventId;
                    if (Events != null && Events.size() > 0) {
                        eventId = Events.get(0).getId().toString();
                        //Act
                        Event event = client.getUsers().getById(ApplicationContext.getTestMail())
                                .getEvent(eventId).read().get();
                        //Assert
                        if (event != null)
                            result.setStatus(TestStatus.Passed);
                    } else {
                        result.setException(new Exception("No available Events"));
                    }
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

    private TestCase canCreateUserEvent(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    // Prepare
                    Event event = getSampleEvent();

                    // Act
                    Event addedEvent = client.getUsers().getById(ApplicationContext.getTestMail()).getEvents().add(event).get();

                    //Assert
                    if (!addedEvent.getSubject().equals(event.getSubject()))
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getUsers().getById(ApplicationContext.getTestMail())
                            .getEvents()
                            .getById(addedEvent.getId())
                            .delete().get();
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

    private TestCase canUpdateUserEvent(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    // Prepare
                    Event event = getSampleEvent();
                    Event addedEvent = client.getUsers().getById(ApplicationContext.getTestMail()).getEvents().add(event).get();

                    // Act
                    event.setSubject("Updated Subject");
                    event.setImportance(Importance.low);

                    Event updatedEvent = client.getUsers()
                            .getById(ApplicationContext.getTestMail())
                            .getEvents()
                            .getById(addedEvent.getId())
                            .update(event).get();

                    //Assert
                    if (updatedEvent.getImportance() != Importance.low || !updatedEvent.getSubject().equals("Updated Subject"))
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getUsers()
                            .getById(ApplicationContext.getTestMail())
                            .getEvents()
                            .getById(addedEvent.getId())
                            .delete().get();

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

    private TestCase canDeleteUserEvent(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    // Prepare
                    Event event = getSampleEvent();
                    Event addedEvent = client.getUsers()
                            .getById(ApplicationContext.getTestMail())
                            .getEvents().add(event).get();

                    // Act
                    client.getUsers()
                            .getById(ApplicationContext.getTestMail())
                            .getEvents()
                            .getById(addedEvent.getId())
                            .delete().get();

                    //Assert
                    try {
                        client.getUsers()
                                .getById(ApplicationContext.getTestMail())
                                .getEvents()
                                .getById(addedEvent.getId()).read().get();
                    } catch (Exception e) {
                        //It's supposed to fail
                        result.setStatus(TestStatus.Passed);
                    }

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

    private TestCase canGetUserCalendarView(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();


                    //format date properly
                    java.util.Calendar dateStart = java.util.Calendar.getInstance();
                    dateStart.add(java.util.Calendar.HOUR, -2);

                    java.util.Calendar dateEnd = java.util.Calendar.getInstance();
                    dateStart.add(java.util.Calendar.HOUR, 2);

                    //Act
                    List<Event> calendarView = client.getUsers().getById(ApplicationContext.getTestMail()).getCalendarView()
                            .addParameter("startdatetime", dateStart)
                            .addParameter("enddatetime", dateEnd)
                            .read().get();

                    //Assert
                    if (calendarView != null)
                        result.setStatus(TestStatus.Passed);

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

    private TestCase canGetUserUserPhoto(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Act
                    ProfilePhoto userPhoto = client.getUsers().getById(ApplicationContext.getTestMail()).getPhoto().read().get();

                    //Assert
                    if (userPhoto != null)
                        result.setStatus(TestStatus.Passed);

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

    private TestCase canGetUserDrive(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Act
                    Drive drive = client.getUsers().getById(ApplicationContext.getTestMail()).getDrive().read().get();

                    //Assert
                    if (drive != null)
                        result.setStatus(TestStatus.Passed);

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


    private TestCase canGetUserFilesById(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Prepare
                    List<DriveItem> files = client.getUsers().getById(ApplicationContext.getTestMail()).getDrive().getRoot().getChildren().top(1).read().get();
                    String fileId;
                    if (files != null && files.size() > 0) {
                        fileId = files.get(0).getId().toString();
                        //Act
                        DriveItem file = client.getUsers().getById(ApplicationContext.getTestMail())
                                .getDrive().getItem(fileId).read().get();
                        //Assert
                        if (file != null)
                            result.setStatus(TestStatus.Passed);
                    } else {
                        result.setException(new Exception("No available Files"));
                    }
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

    private TestCase canCreateUserFiles(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Prepare
                    DriveItem newFile = new DriveItem();

                    File file = new File();
                    newFile.setFile(file);
                    newFile.setName(UUID.randomUUID().toString() + ".txt");

                    DriveItem addedFile = client.getUsers().getById(ApplicationContext.getTestMail()).getDrive().getItems().add(newFile).get();
                    client.getUsers().getById(ApplicationContext.getTestMail())
                            .getDrive().getItems().getById(addedFile.getId()).getContent().putContent("My Content".getBytes()).get();

                    InputStream content = client.getUsers().getById(ApplicationContext.getTestMail()).getDrive().getItems().getById(addedFile.getId()).getContent().getStream().get();

                    //Assert
                    if (addedFile == null)
                        result.setStatus(TestStatus.Failed);

                    if (content == null)
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getUsers().getById(ApplicationContext.getTestMail()).getDrive().getItems().getById(addedFile.getId()).addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();


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

    private TestCase canUpdateUserFiles(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Prepare
                    DriveItem newFile = new DriveItem();
                    File file = new File();
                    newFile.setFile(file);
                    newFile.setName(UUID.randomUUID().toString() + ".txt");

                    DriveItem addedFile = client.getUsers().getById(ApplicationContext.getTestMail()).getDrive().getItems().add(newFile).get();
                    //Prepare
                    client.getUsers().getById(ApplicationContext.getTestMail()).getDrive().getItems().getById(addedFile.getId()).getContent().putContent("My Content".getBytes()).get();

                    //Act
                    client.getUsers().getById(ApplicationContext.getTestMail()).getDrive().getItems().getById(addedFile.getId()).getContent().putContent("My other Content".getBytes()).get();

                    //Assert
                    InputStream content = client.getUsers().getById(ApplicationContext.getTestMail()).getDrive().getItems().getById(addedFile.getId()).getContent().getStream().get();

                    String strContent = getStringFromInputStream(content);

                    if (addedFile != null && strContent.equals("My other Content"))
                        result.setStatus(TestStatus.Passed);

                    //Cleanup
                    client.getUsers().getById(ApplicationContext.getTestMail()).getDrive().getItems().getById(addedFile.getId()).addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();


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

    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

    private TestCase canDeleteUserFiles(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();

                    //Prepare
                    DriveItem newFile = new DriveItem();
                    File file = new File();
                    newFile.setFile(file);
                    newFile.setName(UUID.randomUUID().toString() + ".txt");

                    DriveItem addedFile = client.getUsers().getById(ApplicationContext.getTestMail()).getDrive().getItems().add(newFile).get();

                    //Act
                    client.getUsers().getById(ApplicationContext.getTestMail()).getDrive().getItems().getById(addedFile.getId()).addHeader(Constants.IF_MATCH_HEADER, "*").delete().get();


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

    private TestCase canRetrieveFolders(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();
                    List<MailFolder> folders = client.getMe().getMailFolders().read().get();
                    if (folders == null || folders.size() == 0)
                        result.setStatus(TestStatus.Failed);

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

    private TestCase canRetrieveFolderById(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();
                    MailFolder folder = client.getMe().getMailFolders().getById("Inbox").read().get();
                    if (folder == null || !folder.getDisplayName().equals("Inbox"))
                        result.setStatus(TestStatus.Failed);

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

    private TestCase canRetrieveFolder(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();
                    MailFolder folder = client.getMe().getMailFolder("Inbox").read().get();
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

                    String newFolderName = "TestFolder" + UUID.randomUUID();
                    String parentFolderName = "Inbox";

                    //Create new folder
                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();
                    MailFolder newFolder = new MailFolder();
                    newFolder.setDisplayName(newFolderName);
                    MailFolder addedFolder = client.getMe()
                            .getMailFolders()
                            .getById(parentFolderName)
                            .getChildFolders()
                            .add(newFolder).get();

                    // Assert
                    MailFolder folder = client.getMe()
                            .getMailFolders()
                            .getById(parentFolderName)
                            .getChildFolders()
                            .getById(addedFolder.getId()).read().get();

                    if (folder == null || !folder.getDisplayName().equals(newFolderName)) {
                        result.setStatus(TestStatus.Failed);
                    }

                    //Cleanup
                    client.getMe()
                            .getMailFolders()
                            .getById(parentFolderName)
                            .getChildFolders()
                            .getById(folder.getId())
                            .delete().get();

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

    private TestCase canDeleteFolder(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    String newFolderName = "TestFolder" + UUID.randomUUID();
                    ;
                    String parentFolderName = "Inbox";

                    //Prepare for test
                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();
                    MailFolder newFolder = new MailFolder();
                    newFolder.setDisplayName(newFolderName);
                    MailFolder addedFolder = client.getMe()
                            .getMailFolders()
                            .getById(parentFolderName)
                            .getChildFolders()
                            .add(newFolder).get();

                    // Delete folder
                    client.getMe()
                            .getMailFolders()
                            .getById(parentFolderName)
                            .getChildFolders()
                            .getById(addedFolder.getId())
                            .delete().get();

                    // Assert
                    List<MailFolder> folders = client.getMe()
                            .getMailFolders()
                            .getById(parentFolderName)
                            .getChildFolders()
                            .read().get();

                    boolean exists = false;
                    for (MailFolder f : folders) {
                        if (f.getId().equals(addedFolder.getId())) {
                            exists = true;
                        }
                    }
                    if (exists)
                        result.setStatus(TestStatus.Failed);

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

    private TestCase canMoveFolder(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    String newFolderName = "TestFolder" + UUID.randomUUID();

                    String parentFolderName = "Inbox";
                    String destinationFolderName = "Drafts";

                    //Create new folder
                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();
                    MailFolder newFolder = new MailFolder();
                    newFolder.setDisplayName(newFolderName);
                    MailFolder addedFolder = client.getMe()
                            .getMailFolders()
                            .getById(parentFolderName)
                            .getChildFolders()
                            .add(newFolder).get();

                    //Act
                    client.getMe()
                            .getMailFolders()
                            .getById(parentFolderName).getChildFolders()
                            .getById(addedFolder.getId())
                            .getOperations().move(destinationFolderName).get();

                    //Assert
                    MailFolder movedFolder = client.getMe()
                            .getMailFolders()
                            .getById(destinationFolderName)
                            .getChildFolders()
                            .getById(addedFolder.getId())
                            .read().get();

                    if (movedFolder != null && movedFolder.getDisplayName().equals(newFolderName))
                        result.setStatus(TestStatus.Passed);

                    //Cleanup
                    client.getMe()
                            .getMailFolders()
                            .getById(destinationFolderName)
                            .getChildFolders()
                            .getById(movedFolder.getId())
                            .delete().get();

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

    private TestCase canCopyFolder(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    String newFolderName = "TestFolder" + UUID.randomUUID();
                    ;
                    String parentFolderName = "Inbox";
                    String destinationFolderName = "Drafts";

                    //Create new folder
                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();
                    MailFolder newFolder = new MailFolder();
                    newFolder.setDisplayName(newFolderName);
                    MailFolder addedFolder = client.getMe()
                            .getMailFolders()
                            .getById(parentFolderName)
                            .getChildFolders()
                            .add(newFolder).get();

                    //Act
                    MailFolder copiedFolder = client.getMe()
                            .getMailFolders()
                            .getById(parentFolderName).getChildFolders()
                            .getById(addedFolder.getId())
                            .getOperations().copy(destinationFolderName).get();

                    //Assert
                    MailFolder folder = client.getMe()
                            .getMailFolders()
                            .getById(destinationFolderName)
                            .getChildFolders()
                            .getById(copiedFolder.getId())
                            .read().get();

                    if (folder != null && folder.getDisplayName().equals(newFolderName))
                        result.setStatus(TestStatus.Passed);

                    //Cleanup
                    client.getMe()
                            .getMailFolders()
                            .getById(destinationFolderName)
                            .getChildFolders()
                            .getById(copiedFolder.getId())
                            .delete().get();

                    client.getMe()
                            .getMailFolders()
                            .getById(parentFolderName)
                            .getChildFolders()
                            .getById(addedFolder.getId())
                            .delete().get();

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


    private TestCase canUpdateFolder(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    String folderName = "TestFolder" + UUID.randomUUID();
                    ;
                    String updatedFolderName = "UpdatedTestFolder" + UUID.randomUUID();
                    ;
                    String parentFolderName = "Inbox";

                    //Create new folder
                    GraphServiceClient client = ApplicationContext.getGraphServiceClient();
                    MailFolder newFolder = new MailFolder();
                    newFolder.setDisplayName(folderName);
                    MailFolder addedFolder = client.getMe()
                            .getMailFolders()
                            .getById(parentFolderName)
                            .getChildFolders()
                            .add(newFolder).get();

                    //Act
                    newFolder.setDisplayName(updatedFolderName);
                    client.getMe()
                            .getMailFolders()
                            .getById(addedFolder.getId())
                            .update(newFolder).get();

                    // Assert
                    MailFolder folder = client.getMe()
                            .getMailFolders()
                            .getById(addedFolder.getId()).read().get();

                    if (folder == null || !folder.getDisplayName().equals(updatedFolderName)) {
                        result.setStatus(TestStatus.Failed);
                    }

                    //Cleanup
                    client.getMe()
                            .getMailFolders()
                            .getById(folder.getId())
                            .delete().get();

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


    private Message getSampleMessage(String subject, String to, String cc) {
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
        if (!cc.isEmpty()) {
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
        att.setName(UUID.randomUUID().toString() + "-myFile.txt");

        return att;
    }
    
    private Event getSampleEvent() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String formattedTime = formatter.format(new Date());

        DateTimeTimeZone dtz = new DateTimeTimeZone();
        dtz.setDateTime(formattedTime);
        dtz.setTimeZone("UTC");

        Event event = new Event();
        event.setSubject("Today's appointment");
        event.setStart(dtz);
        event.setImportance(Importance.high);

        //Event body
        ItemBody itemBody = new ItemBody();
        itemBody.setContent("This is the appointment info");
        itemBody.setContentType(BodyType.text);

        event.setBody(itemBody);

        //Attendees
        Attendee attendee1 = new Attendee();
        EmailAddress email = new EmailAddress();
        email.setAddress(ApplicationContext.getTestMail());
        attendee1.setEmailAddress(email);
        List<Attendee> listAttendees = new ArrayList<Attendee>();
        listAttendees.add(attendee1);
        event.setAttendees(listAttendees);

        return event;
    }
}
