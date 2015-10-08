package microsoft.com.unittests;

import com.google.gson.JsonElement;
import com.microsoft.aad.adal4j.AuthenticationResult;
import com.microsoft.services.orc.auth.AuthenticationCredentials;
import com.microsoft.services.orc.core.DependencyResolver;
import com.microsoft.services.orc.http.Credentials;
import com.microsoft.services.orc.http.impl.OAuthCredentials;
import com.microsoft.services.orc.http.impl.OkHttpTransport;
import com.microsoft.services.orc.serialization.impl.CalendarSerializer;
import com.microsoft.services.orc.serialization.impl.GsonSerializer;
import com.microsoft.services.outlook.Attachment;
import com.microsoft.services.outlook.Attendee;
import com.microsoft.services.outlook.BodyType;
import com.microsoft.services.outlook.Calendar;
import com.microsoft.services.outlook.CalendarGroup;
import com.microsoft.services.outlook.Contact;
import com.microsoft.services.outlook.EmailAddress;
import com.microsoft.services.outlook.Event;
import com.microsoft.services.outlook.FileAttachment;
import com.microsoft.services.outlook.Folder;
import com.microsoft.services.outlook.Importance;
import com.microsoft.services.outlook.ItemBody;
import com.microsoft.services.outlook.Message;
import com.microsoft.services.outlook.Recipient;
import com.microsoft.services.outlook.User;
import com.microsoft.services.outlook.fetchers.OutlookClient;

import junit.framework.TestCase;
import junit.framework.TestResult;

import org.junit.BeforeClass;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class OutlookUnitTest {

    protected static OutlookClient client;

    @BeforeClass
    public static void setUp() throws Exception {
        final AuthenticationResult result = LoginHelper.getAccessTokenFromUserCredentials(Constants.OUTLOOK_RESOURCE, Constants.USER, Constants.PASSWORD);
        DependencyResolver dependencyResolver = new DependencyResolver.Builder(
                new OkHttpTransport(), new GsonSerializer(),
                new AuthenticationCredentials() {
                    @Override
                    public Credentials getCredentials() {
                        return new OAuthCredentials(result.getAccessToken());
                    }
                }).build();

        client = new OutlookClient(Constants.OUTLOOK_ENDPOINT, dependencyResolver);
    }

    @Test
    public void canUpdateSendingOnlyUpdatedValues() throws ExecutionException, InterruptedException {

        Message message = client.getMe().getFolders().getById("Inbox").getMessages()
                .top(1).read().get().get(0);
        message.setIsRead(false);
        message.setIsRead(true);

        Message updatedMessage = client.getMe().getMessage(message.getId()).update(message).get();

        assertNotNull(updatedMessage);
        assertTrue(updatedMessage.getIsRead());
    }


    @Test
    public void canRetrieveFolderById() throws ExecutionException, InterruptedException {
        Folder folder = client.getMe().getFolders().getById("Inbox").read().get();

        assertNotNull(folder);
        assertEquals("Inbox", folder.getDisplayName());
    }

    @Test
    public void canRetrieveFolder() throws ExecutionException, InterruptedException {
        Folder folder = client.getMe().getFolder("Inbox").read().get();

        assertNotNull(folder);
        assertEquals("Inbox", folder.getDisplayName());
    }

    @Test
    public void canCreateFolder() throws ExecutionException, InterruptedException {
        String newFolderName = "TestFolder" + UUID.randomUUID();
        String parentFolderName = "Inbox";

        //Create new folder
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

        assertNotNull(folder);
        assertEquals(newFolderName, folder.getDisplayName());

        //Cleanup
        client.getMe()
                .getFolders()
                .getById(parentFolderName)
                .getChildFolders()
                .getById(folder.getId())
                .delete().get();
    }

    @Test
    public void canDeleteFolder() throws ExecutionException, InterruptedException {
        String newFolderName = "TestFolder" + UUID.randomUUID();
        String parentFolderName = "Inbox";

        //Prepare for test
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

        assertFalse(exists);
    }

    @Test
    public void canMoveFolder() throws ExecutionException, InterruptedException {
        String newFolderName = "TestFolder" + UUID.randomUUID();

        String parentFolderName = "Inbox";
        String destinationFolderName = "Drafts";

        //Create new folder
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

        assertNotNull(movedFolder);
        assertEquals(newFolderName, movedFolder.getDisplayName());

        //Cleanup
        client.getMe()
                .getFolders()
                .getById(destinationFolderName)
                .getChildFolders()
                .getById(movedFolder.getId())
                .delete().get();
    }

    @Test
    public void canCopyFolder() throws ExecutionException, InterruptedException {
        String newFolderName = "TestFolder" + UUID.randomUUID();
        String parentFolderName = "Inbox";
        String destinationFolderName = "Drafts";

        //Create new folder
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

        assertNotNull(folder);
        assertEquals(newFolderName, folder.getDisplayName());

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

    }

    @Test
    public void canUpdateFolder() throws ExecutionException, InterruptedException {
        String folderName = "TestFolder" + UUID.randomUUID();
        String updatedFolderName = "UpdatedTestFolder" + UUID.randomUUID();
        String parentFolderName = "Inbox";

        //Create new folder
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
                .update(newFolder).get();

        // Assert
        Folder folder = client.getMe()
                .getFolders()
                .getById(addedFolder.getId()).read().get();

        assertNotNull(folder);
        assertEquals(updatedFolderName, folder.getDisplayName());

        //Cleanup
        client.getMe()
                .getFolders()
                .getById(folder.getId())
                .delete().get();
    }

    // Messages tests
    @Test
    public void canGetMessages() throws ExecutionException, InterruptedException {
        List<Message> inboxMessages = client.getMe().getFolders().getById("Inbox").getMessages().top(3).read().get();
        if (inboxMessages.size() == 0) {
            String mailSubject = "Test get Message";
            Message message = getSampleMessage(mailSubject, Constants.TEST_MAIL, "");

            client.getMe().getOperations().sendMail(message, true).get();
            Thread.sleep(2000);
            inboxMessages = client.getMe().getFolders().getById("Inbox").getMessages().top(1).read().get();
        }

        assertNotNull(inboxMessages);
        assertTrue(inboxMessages.size() > 0 && inboxMessages.size() <= 3);
    }

    //Test new syntatic collection fetcher overload to avoid calling getByIdXXX methods.
    @Test
    public void canGetMessage() throws ExecutionException, InterruptedException {
        Message message = getSampleMessage("Test message", Constants.TEST_MAIL, "");

        //Act
        Message createdMessage = client.getMe().getMessages().add(message).get();

        //Assert
        Message searchedMessage = client.getMe()
                .getFolder("Drafts")
                .getMessage(createdMessage.getId()).read().get();

        assertNotNull(searchedMessage);
        assertEquals("Test message", searchedMessage.getSubject());

        //Cleanup
        client.getMe().getFolder("Drafts")
                .getMessage(createdMessage.getId())
                .delete().get();
    }

    @Test
    public void canGetMe() throws ExecutionException, InterruptedException {
        User me = client.getMe().read().get();
        assertNotNull(me);
    }

    @Test
    public void canRetrieveFolders() throws ExecutionException, InterruptedException {
        List<Folder> folders = client.getMe().getFolders().read().get();
        assertNotNull(folders);
        assertTrue(folders.size() > 0);
    }

    @Test
    public void canCreateMessage() throws ExecutionException, InterruptedException {
        Message message = getSampleMessage("Test message", Constants.TEST_MAIL, "");

        //Act
        Message createdMessage = client.getMe().getMessages().add(message).get();

        //Assert
        Message searchedMessage = client.getMe()
                .getFolders()
                .getById("Drafts")
                .getMessages()
                .getById(createdMessage.getId()).read().get();

        assertNotNull(searchedMessage);
        assertEquals("Test message", searchedMessage.getSubject());

        //Cleanup
        client.getMe().getFolders()
                .getById("Drafts")
                .getMessages()
                .getById(createdMessage.getId())
                .delete().get();
    }

    @Test
    public void canCreateMessageAttachment() throws ExecutionException, InterruptedException {
        Message message = getSampleMessage("Test message", Constants.TEST_MAIL, "");

        //Act
        Message added = client.getMe().getMessages().add(message).get();
        client.getMe().getMessages()
                .getById(added.getId())
                .getAttachments()
                .add(getFileAttachment()).get();

        //Assert
        Message storedMessage = client.getMe().getMessages().getById(added.getId()).read().get();

        assertTrue(storedMessage.getHasAttachments());

        //Cleanup
        client.getMe().getMessage(added.getId()).delete().get();
    }

    @Test
    public void canGetMessageAttachments() throws ExecutionException, InterruptedException {
        Message message = getSampleMessage("Test message", Constants.TEST_MAIL, "");

        //Prepare
        Message added = client.getMe().getMessages().add(message).get();
        client.getMe().getMessages()
                .getById(added.getId())
                .getAttachments()
                .add(getFileAttachment()).get();

        //Act
        List<Attachment> attachments = client.getMe().getMessage(added.getId()).getAttachments().read().get();

        //Assert
        assertNotNull(attachments);
        assertTrue(attachments.size() > 0);

        //Cleanup
        client.getMe().getMessage(added.getId()).delete().get();
    }

    @Test
    public void canSendMessage() throws ExecutionException, InterruptedException {
        String mailSubject = "Test Send Message";
        Message message = getSampleMessage(mailSubject, Constants.TEST_MAIL, "");

        //Act
        client.getMe().getOperations().sendMail(message, true).get();
    }

    @Test
    public void canSendWithMessageOperations() throws ExecutionException, InterruptedException {
        String mailSubject = "Test Send Message";
        Message message = getSampleMessage(mailSubject, Constants.TEST_MAIL, "");

        //Act
        Message addedMessage = client.getMe().getMessages().add(message).get();
        client.getMe().getMessage(addedMessage.getId()).getOperations().send();
    }

    @Test
    public void canSendHtmlMessage() throws ExecutionException, InterruptedException {
        String mailSubject = "Test Send Message" + UUID.randomUUID().toString();
        Message message = getSampleMessage(mailSubject, Constants.TEST_MAIL, "");
        ItemBody itemBody = message.getBody();
        itemBody.setContentType(BodyType.HTML);
        itemBody.setContent("<h1>This is an Html body.</h1><a href='#'>With Link!</a>");
        message.setBody(itemBody);

        //Act
        client.getMe().getOperations().sendMail(message, true).get();

        //Assert
        List<Folder> sentFolder = client.getMe().getFolders().filter("DisplayName eq 'Sent Items'").read().get();
        List<Message> messages = client.getMe()
                .getFolder(sentFolder.get(0).getId())
                .getMessages()
                .filter("Subject eq '" + mailSubject + "'")
                .read().get();

        assertNotNull(messages);
        assertTrue(messages.size() > 0);
        assertEquals(BodyType.HTML, messages.get(0).getBody().getContentType());
    }

    @Test
    public void canReplyHtmlMessage() throws ExecutionException, InterruptedException {
        String mailSubject = "Test Send Message" + UUID.randomUUID().toString();
        Message message = getSampleMessage(mailSubject, Constants.TEST_MAIL, "");
        ItemBody itemBody = message.getBody();
        itemBody.setContentType(BodyType.HTML);
        itemBody.setContent("<h1>This is an Html body.</h1><a href='#'>With Link!</a>");
        message.setBody(itemBody);

        //Prepare
        client.getMe().getOperations().sendMail(message, true).get();

        List<Folder> sentFolder = client.getMe().getFolders().filter("DisplayName eq 'Sent Items'").read().get();
        List<Message> messages = client.getMe()
                .getFolder(sentFolder.get(0).getId())
                .getMessages()
                .filter("Subject eq '" + mailSubject + "'")
                .read().get();

        //Act
        Message messageToReply = client.getMe().getFolder(sentFolder.get(0).getId())
                .getMessage(messages.get(0).getId()).getOperations().createReply().get();

        //Assert
        assertEquals(BodyType.HTML, messageToReply.getBody().getContentType());

        //Cleanup
        client.getMe().getMessage(messageToReply.getId()).delete().get();
    }

    @Test
    public void canUpdateMessage() throws ExecutionException, InterruptedException {
        String mailSubject = "Test Update Message";
        Message message = getSampleMessage(mailSubject, Constants.TEST_MAIL, "");

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

        assertNotNull(updatedMessage);
        assertEquals("New Test Update Message", updatedMessage.getSubject());

        //Cleanup
        client.getMe().getFolders()
                .getById("Drafts")
                .getMessages()
                .getById(updatedMessage.getId())
                .delete().get();
    }

    @Test
    public void canDeleteMessage() throws ExecutionException, InterruptedException {
        String mailSubject = "Test Delete Message";
        Message message = getSampleMessage(mailSubject, Constants.TEST_MAIL, "");

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

        assertFalse(exists);
    }

    @Test
    public void canMoveMessage() throws ExecutionException, InterruptedException {
        String destinationFolderName = "Inbox";

        String mailSubject = "Test move Message";
        Message message = getSampleMessage(mailSubject, Constants.TEST_MAIL, "");

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

            assertNotNull(m);
            assertEquals(movedMessage.getId(), m.getId());
        } catch (Throwable t) {
            fail();
        }


        //Cleanup
        client.getMe()
                .getMessages()
                .getById(movedMessage.getId())
                .delete().get();

    }

    @Test
    public void canCopyMessage() throws ExecutionException, InterruptedException {
        String destinationFolderName = "Inbox";

        String mailSubject = "Test copy Message";
        Message message = getSampleMessage(mailSubject, Constants.TEST_MAIL, "");

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

            assertNotNull(m);
            assertEquals(m.getId(), copiedMessage.getId());
        } catch (Throwable t) {
            fail("Error:" + t.getLocalizedMessage());
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
    }

    @Test
    public void canCreateReplyMessage() throws ExecutionException, InterruptedException {
        List<Message> inboxMessages;
        String mailSubject = "Test reply Message";
        Message message = getSampleMessage(mailSubject, Constants.TEST_MAIL, "");
        client.getMe().getOperations().sendMail(message, true).get();
        Thread.sleep(4000);
        inboxMessages = client.getMe().getFolders().getById("Inbox").getMessages().top(1).read().get();

        Message messageToReply = inboxMessages.get(0);
        //Act
        Message repliedMessage = client.getMe()
                .getMessages()
                .getById(messageToReply.getId())
                .getOperations().createReply().get();

        //Assert
        List<Message> messages = client.getMe()
                .getFolders()
                .getById("Drafts")
                .getMessages().read().get();

        boolean exists = false;
        for (Message m : messages) {
            if (m.getConversationId().equals(messageToReply.getConversationId()))
                exists = true;
        }

        assertTrue(exists);

        //Cleanup
        client.getMe()
                .getMessages()
                .getById(messageToReply.getId())
                .delete().get();

        client.getMe()
                .getMessages()
                .getById(repliedMessage.getId())
                .delete().get();
    }

    @Test
    public void canCreateReplyAllMessage() throws ExecutionException, InterruptedException {
        List<Message> inboxMessages;
        String mailSubject = "Test reply all Message";
        Message message = getSampleMessage(mailSubject, Constants.TEST_MAIL, "");
        client.getMe().getOperations().sendMail(message, true).get();
        Thread.sleep(4000);
        inboxMessages = client.getMe().getFolders().getById("Inbox").getMessages().top(1).read().get();

        Message messageToReply = inboxMessages.get(0);
        //Act
        Message repliedMessage = client.getMe()
                .getFolders()
                .getById("Drafts")
                .getMessages()
                .getById(messageToReply.getId())
                .getOperations().createReplyAll().get();

        //Assert
        List<Message> messages = client.getMe()
                .getFolders()
                .getById("Drafts")
                .getMessages().read().get();

        boolean exists = false;
        for (Message m : messages) {
            if (m.getConversationId().equals(messageToReply.getConversationId()))
                exists = true;
        }

        assertTrue(exists);

        //Cleanup
        client.getMe()
                .getMessages()
                .getById(messageToReply.getId())
                .delete().get();

        client.getMe()
                .getMessages()
                .getById(repliedMessage.getId())
                .delete().get();
    }

    @Test
    public void canCreateForwardMessage() throws ExecutionException, InterruptedException {
        List<Message> inboxMessages = client.getMe().getFolders().getById("Inbox").getMessages().top(1).read().get();
        if (inboxMessages.size() == 0) {
            String mailSubject = "Test fw Message";
            Message message = getSampleMessage(mailSubject, Constants.TEST_MAIL, "");

            client.getMe().getOperations().sendMail(message, true).get();
            Thread.sleep(2000);
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

        //Assert
        List<Message> messages = client.getMe()
                .getFolders()
                .getById("Drafts")
                .getMessages().read().get();

        boolean exists = false;
        for (Message m : messages) {
            if (m.getConversationId().equals(messageToReply.getConversationId()))
                exists = true;
        }

        assertTrue(exists);

        //Cleanup
        client.getMe()
                .getMessages()
                .getById(messageToReply.getId())
                .delete().get();

        client.getMe()
                .getMessages()
                .getById(repliedMessage.getId())
                .delete().get();
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
            ArrayList<Recipient> ccRecipients = new ArrayList<>();
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

    // Calendar Tests
    @Test
    public void canCreateCalendarGroup() throws ExecutionException, InterruptedException {
        // Act
        CalendarGroup calendarGroup = new CalendarGroup();
        calendarGroup.setName("My testing calendar Group");
        CalendarGroup addedCalendarGroup = client.getMe()
                .getCalendarGroups()
                .add(calendarGroup).get();

        //Assert
        assertEquals(calendarGroup.getName(), addedCalendarGroup.getName());

        //Cleanup
        client.getMe().getCalendarGroups()
                .getById(addedCalendarGroup.getId())
                .delete().get();
    }

    @Test
    public void canGetCalendarGroups() throws ExecutionException, InterruptedException {
        // Prepare
        CalendarGroup calendarGroup = new CalendarGroup();
        calendarGroup.setName("My testing calendar Group");
        CalendarGroup addedCalendarGroup = client.getMe()
                .getCalendarGroups()
                .add(calendarGroup).get();

        // Act
        List<CalendarGroup> calendarGroups = client.getMe().getCalendarGroups().read().get();

        //Assert
        assertTrue(calendarGroups.size() > 0);

        //Cleanup
        client.getMe().getCalendarGroups()
                .getById(addedCalendarGroup.getId())
                .delete().get();
    }

    @Test
    public void canGetCalendarGroupById() throws ExecutionException, InterruptedException {
        // Prepare
        CalendarGroup calendarGroup = new CalendarGroup();
        calendarGroup.setName("My testing calendar Group");
        CalendarGroup addedCalendarGroup = client.getMe()
                .getCalendarGroups()
                .add(calendarGroup).get();

        // Act
        CalendarGroup storedCalendarGroup = client.getMe().getCalendarGroups()
                .getById(addedCalendarGroup.getId()).read().get();

        //Assert
        assertEquals(addedCalendarGroup.getName(), storedCalendarGroup.getName());

        //Cleanup
        client.getMe().getCalendarGroups()
                .getById(addedCalendarGroup.getId())
                .delete().get();
    }

    @Test
    public void canUpdateCalendarGroup() throws ExecutionException, InterruptedException {
        // Prepare
        CalendarGroup calendarGroup = new CalendarGroup();
        calendarGroup.setName("My testing calendar Group");
        CalendarGroup addedCalendarGroup = client.getMe()
                .getCalendarGroups()
                .add(calendarGroup).get();

        // Act
        calendarGroup.setName("Updated Calendar Group");
        CalendarGroup updatedCalendarGroup = client.getMe().getCalendarGroups()
                .getById(addedCalendarGroup.getId())
                .update(calendarGroup).get();

        //Assert
        assertEquals("Updated Calendar Group", updatedCalendarGroup.getName());

        //Cleanup
        client.getMe().getCalendarGroups()
                .getById(updatedCalendarGroup.getId())
                .delete().get();
    }

    @Test
    public void canDeleteCalendarGroup() throws ExecutionException, InterruptedException {
        // Prepare
        CalendarGroup calendarGroup = new CalendarGroup();
        calendarGroup.setName("My testing calendar Group");
        CalendarGroup addedCalendarGroup = client.getMe()
                .getCalendarGroups()
                .add(calendarGroup).get();

        // Act
        calendarGroup.setName("Updated Calendar Group");
        client.getMe().getCalendarGroups()
                .getById(addedCalendarGroup.getId())
                .delete().get();

        //Assert
        try {
            client.getMe().getCalendarGroups()
                    .getById(addedCalendarGroup.getId()).read().get();
            fail("Not supposed to be here");
        } catch (Exception e) {
            //It's supposed to fail
            assertNotNull(e);
        }
    }

    @Test
    public void canGetCalendars() throws ExecutionException, InterruptedException {
        // Prepare
        String calendarName = "My testing calendar" + UUID.randomUUID().toString();
        Calendar calendar = new Calendar();
        calendar.setName(calendarName);
        Calendar addedCalendar = client.getMe()
                .getCalendars()
                .add(calendar).get();

        // Act
        List<Calendar> calendars = client.getMe().getCalendars().read().get();

        //Assert
        assertTrue(calendars.size() > 0);

        //Cleanup
        client.getMe().getCalendars()
                .getById(addedCalendar.getId())
                .delete().get();
    }

    @Test
    public void canGetDefaultCalendar() throws ExecutionException, InterruptedException {
        // Act
        Calendar calendar = client.getMe().getCalendar().read().get();

        //Assert
        assertNotNull(calendar);
        assertNotEquals("", calendar.getName());
    }

    @Test
    public void canCreateCalendar() throws ExecutionException, InterruptedException {
        // Act
        String calendarName = "My testing calendar" + UUID.randomUUID().toString();
        Calendar calendar = new Calendar();
        calendar.setName(calendarName);
        Calendar addedCalendar = client.getMe()
                .getCalendars()
                .add(calendar).get();

        //Assert
        assertEquals(calendar.getName(), addedCalendar.getName());

        //Cleanup
        client.getMe().getCalendars()
                .getById(addedCalendar.getId())
                .delete().get();
    }

    @Test
    public void canGetCalendarById() throws ExecutionException, InterruptedException {
        // Prepare
        String calendarName = "My testing calendar" + UUID.randomUUID().toString();
        Calendar calendar = new Calendar();
        calendar.setName(calendarName);
        Calendar addedCalendar = client.getMe()
                .getCalendars()
                .add(calendar).get();

        // Act
        Calendar storedCalendar = client.getMe().getCalendars()
                .getById(addedCalendar.getId()).read().get();

        //Assert
        assertEquals(addedCalendar.getName(), storedCalendar.getName());

        //Cleanup
        client.getMe().getCalendars()
                .getById(addedCalendar.getId())
                .delete().get();
    }

    @Test
    public void canUpdateCalendar() throws ExecutionException, InterruptedException {
        // Prepare
        String calendarName = "My testing calendar" + UUID.randomUUID().toString();
        Calendar calendar = new Calendar();
        calendar.setName(calendarName);
        Calendar addedCalendar = client.getMe()
                .getCalendars()
                .add(calendar).get();

        // Act
        String updatedCalendarName = "Updated Calendar" + UUID.randomUUID().toString();
        calendar.setName(updatedCalendarName);
        Calendar updatedCalendar = client.getMe().getCalendars()
                .getById(addedCalendar.getId())
                .update(calendar).get();

        //Assert
        assertEquals(updatedCalendarName, updatedCalendar.getName());

        //Cleanup
        client.getMe().getCalendars()
                .getById(updatedCalendar.getId())
                .delete().get();
    }

    @Test
    public void canDeleteCalendar() throws ExecutionException, InterruptedException {
        // Prepare
        String calendarName = "My testing calendar" + UUID.randomUUID().toString();
        Calendar calendar = new Calendar();
        calendar.setName(calendarName);
        Calendar addedCalendar = client.getMe()
                .getCalendars()
                .add(calendar).get();

        // Act
        client.getMe().getCalendars()
                .getById(addedCalendar.getId())
                .delete().get();

        //Assert
        try {
            client.getMe().getCalendars()
                    .getById(addedCalendar.getId()).read().get();
            fail("Not supponsed to pass here");
        } catch (Exception e) {
            //It's supposed to fail
            assertNotNull(e);
        }
    }

    @Test
    public void canGetCalendarView() throws ExecutionException, InterruptedException {
        // Prepare
        Event event = getSampleEvent();
        Event addedEvent = client.getMe().getCalendars().getById("Calendar").getEvents().add(event).get();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String formattedTime = formatter.format(addedEvent.getStart().getTime());

        //format date properly
        java.util.Calendar dateStart = (java.util.Calendar) addedEvent.getStart().clone();
        dateStart.add(java.util.Calendar.HOUR, -2);

        java.util.Calendar dateEnd = (java.util.Calendar) addedEvent.getStart().clone();
        dateEnd.add(java.util.Calendar.HOUR, 2);

        // Act
        List<Event> calendarView = client.getMe().getCalendarView()
                .addParameter("startdatetime", dateStart)
                .addParameter("enddatetime", dateEnd)
                .read().get();

        Calendar c = client.getMe().getCalendar().read().get();
        List<Event> calendarViewById = client.getMe().getCalendar(c.getId())
                .getCalendarView()
                .addParameter("startdatetime", dateStart)
                .addParameter("enddatetime", dateEnd)
                .read().get();

        //Assert
        assertNotNull(calendarView);
        assertTrue(calendarView.size() > 0);
        assertNotNull(calendarViewById);
        assertTrue(calendarViewById.size() > 0);

        //Cleanup
        client.getMe().getEvent(addedEvent.getId()).delete();
    }

    @Test
    public void canGetEvents() throws ExecutionException, InterruptedException {
        // Prepare
        Event event = getSampleEvent();
        Event addedEvent = client.getMe().getCalendars().getById("Calendar").getEvents().add(event).get();

        // Act
        List<Event> events = client.getMe().getCalendars().getById("Calendar").getEvents().read().get();

        //Assert
        assertNotNull(events);
        assertTrue(events.size() > 0);

        //Cleanup
        client.getMe().getCalendars()
                .getById("Calendar")
                .getEvents()
                .getById(addedEvent.getId())
                .delete().get();
    }

    @Test
    public void canCreateEvents() throws ExecutionException, InterruptedException {
        // Prepare
        Event event = getSampleEvent();

        // Act
        Event addedEvent = client.getMe().getCalendars().getById("Calendar").getEvents().add(event).get();

        //Assert
        assertEquals(event.getSubject(), addedEvent.getSubject());

        //Cleanup
        client.getMe()
                .getEvents()
                .getById(addedEvent.getId())
                .delete().get();
    }

    @Test
    public void canUpdateEvents() throws ExecutionException, InterruptedException {
        // Prepare
        Event event = getSampleEvent();
        Event addedEvent = client.getMe().getCalendars().getById("Calendar").getEvents().add(event).get();

        // Act
        event.setSubject("Updated Subject");
        event.setImportance(Importance.Low);

        Event updatedEvent = client.getMe().getEvents().getById(addedEvent.getId()).update(event).get();

        //Assert
        assertNotNull(updatedEvent);
        assertEquals(Importance.Low, updatedEvent.getImportance());
        assertEquals("Updated Subject", updatedEvent.getSubject());

        //Cleanup
        client.getMe()
                .getEvents()
                .getById(addedEvent.getId())
                .delete().get();
    }

    @Test
    public void canDeleteEvents() throws ExecutionException, InterruptedException {
        // Prepare
        Event event = getSampleEvent();
        Event addedEvent = client.getMe().getCalendars().getById("Calendar").getEvents().add(event).get();

        // Act
        client.getMe()
                .getEvents()
                .getById(addedEvent.getId())
                .delete().get();

        //Assert
        try {
            client.getMe().getEvents()
                    .getById(addedEvent.getId()).read().get();
            fail("Not supposed to be here");
        } catch (Exception e) {
            //It's supposed to fail
            assertNotNull(e);
        }
    }

    /*
    @Test
    public void canRespondEvents() throws ExecutionException, InterruptedException {
        // Prepare
        Event event = getSampleEvent();
        Event addedEvent = client.getMe().getCalendars().getById("Calendar").getEvents().add(event).get();
        Thread.sleep(2000);
        // Act
        Integer accepted = client.getMe()
                .getEvents()
                .getById(addedEvent.getId())
                .getOperations().accept("Accepted").get();

        //Assert
        assertEquals(event.getSubject(), addedEvent.getSubject());

        //Cleanup
        client.getMe()
                .getEvents()
                .getById(addedEvent.getId())
                .delete().get();
    }
    */
    @Test
    public void canCreateAllDayEvent() throws ExecutionException, InterruptedException {
        // Prepare
        Event event = getSampleEvent();
        event.setIsAllDay(true);
        event.setSubject("all day event");

        java.util.Calendar start = java.util.Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        // reset hour, minutes, seconds and millis
        start.set(java.util.Calendar.HOUR_OF_DAY, 0);
        start.set(java.util.Calendar.MINUTE, 0);
        start.set(java.util.Calendar.SECOND, 0);
        start.set(java.util.Calendar.MILLISECOND, 0);

        event.setStart(start);

        java.util.Calendar end = java.util.Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        // reset hour, minutes, seconds and millis
        end.set(java.util.Calendar.HOUR_OF_DAY, 0);
        end.set(java.util.Calendar.MINUTE, 0);
        end.set(java.util.Calendar.SECOND, 0);
        end.set(java.util.Calendar.MILLISECOND, 0);

        end.add(java.util.Calendar.DATE, 1);
        event.setEnd(end);

        Event addedEvent = client.getMe().getCalendars().getById("Calendar").getEvents().add(event).get();

        //Assert
        assertEquals(event.getSubject(), addedEvent.getSubject());
    }

    private Event getSampleEvent() {
        Event event = new Event();
        event.setSubject("Today's appointment");
        event.setStart(java.util.Calendar.getInstance());
        event.setImportance(Importance.High);

        //Event body
        ItemBody itemBody = new ItemBody();
        itemBody.setContent("This is the appointment info");
        itemBody.setContentType(BodyType.Text);

        event.setBody(itemBody);

        //Attendees
        Attendee attendee1 = new Attendee();
        EmailAddress email = new EmailAddress();
        email.setAddress(Constants.TEST_MAIL);
        attendee1.setEmailAddress(email);
        List<Attendee> listAttendees = new ArrayList<Attendee>();
        listAttendees.add(attendee1);
        event.setAttendees(listAttendees);

        return event;
    }

    //Contacts
    @Test
    public void canGetContactsFolder() throws ExecutionException, InterruptedException {
        //Act
        List<Contact> contacts = client.getMe()
                .getContactFolders()
                .getById("Contacts")
                .getContacts().read().get();

        //Assert
        assertNotNull(contacts);
    }

    @Test
    public void canGetContacts() throws ExecutionException, InterruptedException {
        //Prepare
        Contact addedContact = client.getMe().getContacts().add(getContact()).get();

        //Act
        List<Contact> contacts = client.getMe().getContacts().top(2).read().get();

        //Assert
        assertNotNull(contacts);
        assertTrue(contacts.size() > 0 || contacts.size() <= 2);
    }

    @Test
    public void canCreateContact() throws ExecutionException, InterruptedException {
        //Prepare
        Contact addedContact = client.getMe().getContacts().add(getContact()).get();
        Thread.sleep(2000);
        //Act
        Contact storedContact = client.getMe()
                .getContacts()
                .getById(addedContact.getId()).read().get();

        //Assert
        assertEquals(addedContact.getId(), storedContact.getId());

        //Cleanup
        client.getMe().getContacts().getById(addedContact.getId()).delete().get();
    }

    @Test
    public void canDeleteContact() throws ExecutionException, InterruptedException {
        //Prepare
        Contact addedContact = client.getMe().getContacts().add(getContact()).get();
        //Act
        client.getMe().getContacts().getById(addedContact.getId()).delete().get();

        //Assert
        List<Contact> contacts = client.getMe().getContacts().read().get();

        boolean exists = false;
        for (Contact c : contacts) {
            if (c.getId().equals(addedContact.getId()))
                exists = true;
        }

        assertFalse(exists);
    }

    @Test
    public void canUpdateContact() throws ExecutionException, InterruptedException {
        //Prepare
        Contact contact = getContact();
        Contact addedContact = client.getMe().getContacts().add(contact).get();
        contact.setGivenName("Updated given name");
        //Act
        client.getMe().getContacts().getById(addedContact.getId()).update(contact).get();
        Thread.sleep(2000);
        Contact updatedContact = client.getMe()
                .getContacts()
                .getById(addedContact.getId()).read().get();

        //Assert
        assertEquals(addedContact.getId(), updatedContact.getId());
        assertEquals("Updated given name", updatedContact.getGivenName());

        //Cleanup
        client.getMe().getContacts().getById(addedContact.getId()).delete().get();
    }

    private Contact getContact() {
        final Contact newContact = new Contact();
        newContact.setDisplayName("Test Contact");
        newContact.setGivenName("Test Contact Name");
        final EmailAddress email = new EmailAddress();
        email.setAddress("test@test.com");
        List<EmailAddress> list = new ArrayList<EmailAddress>();
        list.add(email);
        newContact.setEmailAddresses(list);

        return newContact;
    }

    // Filter, Select, Top, Skip
    @Test
    public void canFilterMessages() throws ExecutionException, InterruptedException {
        String subject = "Test Subject " + UUID.randomUUID().toString();
        Message message = getSampleMessage(subject, Constants.TEST_MAIL, "");

        //Prepare
        Message addedMessage = client.getMe().getFolders().getById("Drafts").getMessages().add(message).get();

        //Act
        java.util.Calendar dateGt = addedMessage.getDateTimeCreated();
        dateGt.add(java.util.Calendar.SECOND, -2);

        //format date properly
        String formatted = CalendarSerializer.serialize(dateGt);

        List<Message> messages = client.getMe().getFolders().getById("Drafts").getMessages()
                .filter("Subject eq '" + addedMessage.getSubject() + "' and DateTimeCreated gt " + formatted)
                .read().get();

        //Assert
        assertEquals(1, messages.size());

        //Cleanup
        client.getMe().getMessages().getById(addedMessage.getId()).delete().get();
    }

    @Test
    public void canSelectMessages() throws ExecutionException, InterruptedException {
        String subject = "Test Subject " + UUID.randomUUID().toString();
        Message message = getSampleMessage(subject, Constants.TEST_MAIL, "");

        //Prepare
        Message addedMessage = client.getMe().getFolders().getById("Drafts").getMessages().add(message).get();

        //Act
        List<Message> messages = client.getMe().getFolders().getById("Drafts").getMessages()
                .filter("Subject eq '" + subject + "'")
                .select("Subject,DateTimeCreated")
                .read().get();

        //Assert
        assertNotNull(messages);
        assertTrue(messages.size() > 0);
        assertNotEquals("", messages.get(0).getSubject());
        assertNull(messages.get(0).getDateTimeReceived());

        //Cleanup
        client.getMe().getMessages().getById(addedMessage.getId()).delete().get();
    }


    @Test
    public void canTopMessages() throws ExecutionException, InterruptedException {
        String subject = "Test Subject " + UUID.randomUUID().toString();
        Message message = getSampleMessage(subject, Constants.TEST_MAIL, "");

        //Prepare
        Message addedMessage1 = client.getMe().getFolders().getById("Drafts").getMessages().add(message).get();
        Message addedMessage2 = client.getMe().getFolders().getById("Drafts").getMessages().add(message).get();

        //Act
        List<Message> messages = client.getMe().getFolders().getById("Drafts").getMessages()
                .filter("Subject eq '" + subject + "'")
                .top(1)
                .read().get();

        //Assert
        assertTrue(messages.size() > 0);
        assertEquals(subject, messages.get(0).getSubject());

        //Cleanup
        client.getMe().getMessages().getById(addedMessage1.getId()).delete().get();
        client.getMe().getMessages().getById(addedMessage2.getId()).delete().get();
    }

    @Test
    public void canExpandMessages() throws ExecutionException, InterruptedException {
        String messageSubject = "Test message" + UUID.randomUUID().toString();
        Message message = getSampleMessage(messageSubject, Constants.TEST_MAIL, "");

        FileAttachment fileAttachment = getFileAttachment();
        //Prepare
        Message added = client.getMe().getFolders()
                .getById("Drafts").getMessages().add(message).get();

        client.getMe().getMessages()
                .getById(added.getId())
                .getAttachments()
                .add(fileAttachment).get();

        //Act
        List<Message> messagesWithExpand = client.getMe()
                .getFolder("Drafts")
                .getMessages()
                .filter("Subject eq '" + added.getSubject() + "'")
                .expand("Attachments").read().get();

        List<Message> messagesWithoutExpand = client.getMe()
                .getFolder("Drafts")
                .getMessages()
                .filter("Subject eq '" + added.getSubject() + "'")
                .read().get();

        //Assert
        assertEquals(1, messagesWithExpand.size());
        assertEquals(1, messagesWithoutExpand.size());
        assertEquals(1, messagesWithExpand.get(0).getAttachments().size());
        assertNull(messagesWithoutExpand.get(0).getAttachments());
        assertEquals(fileAttachment.getName(), messagesWithExpand.get(0).getAttachments().get(0).getName());

        //Cleanup
        client.getMe().getMessage(added.getId()).delete().get();
    }

    @Test
    public void canExpandMessage() throws ExecutionException, InterruptedException {
        String messageSubject = "Test message" + UUID.randomUUID().toString();
        Message message = getSampleMessage(messageSubject, Constants.TEST_MAIL, "");

        FileAttachment fileAttachment = getFileAttachment();
        //Prepare
        Message added = client.getMe().getMessages().add(message).get();
        client.getMe().getMessages()
                .getById(added.getId())
                .getAttachments()
                .add(fileAttachment).get();

        //Act
        Message messageWithExpand = client.getMe()
                .getMessage(added.getId())
                .expand("Attachments").read().get();

        Message messageWithoutExpand = client.getMe()
                .getMessage(added.getId())
                .read().get();

        //Assert
        assertNotNull(messageWithExpand);
        assertNotNull(messageWithoutExpand);
        assertEquals(1, messageWithExpand.getAttachments().size());
        assertNull(messageWithoutExpand.getAttachments());
        assertEquals(fileAttachment.getName(), messageWithExpand.getAttachments().get(0).getName());

        //Cleanup
        client.getMe().getMessage(added.getId()).delete().get();
    }

    @Test
    public void canSelectMessage() throws ExecutionException, InterruptedException {
        String subject = "Test Subject " + UUID.randomUUID().toString();
        Message message = getSampleMessage(subject, Constants.TEST_MAIL, "");

        //Prepare
        Message addedMessage = client.getMe().getFolders().getById("Drafts").getMessages().add(message).get();

        //Act
        Message messageWithSelect = client.getMe()
                .getMessage(addedMessage.getId())
                .select("Subject,DateTimeCreated")
                .read().get();

        //Assert
        assertNotNull(messageWithSelect);
        assertEquals(subject, messageWithSelect.getSubject());
        assertNull(messageWithSelect.getDateTimeReceived());

        //Cleanup
        client.getMe().getMessages().getById(addedMessage.getId()).delete().get();
    }

    @Test
    public void canOrderByContacts() throws ExecutionException, InterruptedException {
        String name1 = "AA" + UUID.randomUUID().toString();
        String name2 = "BB" + UUID.randomUUID().toString();

        Contact contact1 = getContact();
        contact1.setDisplayName(name1);

        Contact contact2 = getContact();
        contact2.setDisplayName(name2);

        //Prepare
        Contact addedContact1 = client.getMe().getContacts().add(contact1).get();
        Contact addedContact2 = client.getMe().getContacts().add(contact2).get();

        //Act
        String filter = String.format("DisplayName eq '%s' or DisplayName eq '%s'", name1, name2);
        List<Contact> resultAsc = client.getMe().getContacts().filter(filter).orderBy("DisplayName asc").read().get();
        List<Contact> resultDesc = client.getMe().getContacts().filter(filter).orderBy("DisplayName desc").read().get();

        //Assert
        assertNotNull(resultAsc);
        assertEquals(2, resultAsc.size());
        assertTrue(resultAsc.get(0).getDisplayName().startsWith("AA"));

        assertNotNull(resultDesc);
        assertEquals(2, resultDesc.size());
        assertTrue(resultDesc.get(0).getDisplayName().startsWith("BB"));

        //Cleanup
        client.getMe().getContact(addedContact1.getId()).delete().get();
        client.getMe().getContact(addedContact2.getId()).delete().get();
    }

    @Test
    public void canSkipContacts() throws ExecutionException, InterruptedException {
        String name1 = "AA" + UUID.randomUUID().toString();
        String name2 = "BB" + UUID.randomUUID().toString();

        Contact contact1 = getContact();
        contact1.setDisplayName(name1);

        Contact contact2 = getContact();
        contact2.setDisplayName(name2);

        //Prepare
        Contact addedContact1 = client.getMe().getContacts().add(contact1).get();
        Contact addedContact2 = client.getMe().getContacts().add(contact2).get();

        //Act
        String filter = String.format("DisplayName eq '%s' or DisplayName eq '%s'", name1, name2);
        List<Contact> resultDesc = client.getMe().getContacts()
                .filter(filter)
                .orderBy("DisplayName desc")
                .skip(1)
                .read().get();

        //Assert
        assertNotNull(resultDesc);
        assertEquals(1, resultDesc.size());
        assertTrue(resultDesc.get(0).getDisplayName().startsWith("AA"));

        //Cleanup
        client.getMe().getContact(addedContact1.getId()).delete().get();
        client.getMe().getContact(addedContact2.getId()).delete().get();
    }
}
