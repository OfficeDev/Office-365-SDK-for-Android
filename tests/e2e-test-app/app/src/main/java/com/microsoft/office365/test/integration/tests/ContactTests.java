package com.microsoft.office365.test.integration.tests;


import com.microsoft.office365.test.integration.ApplicationContext;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestGroup;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.office365.test.integration.framework.TestStatus;
import com.microsoft.outlookservices.Contact;
import com.microsoft.outlookservices.EmailAddress;
import com.microsoft.outlookservices.odata.EntityContainerClient;

import java.util.ArrayList;
import java.util.List;

public class ContactTests extends TestGroup {

    public ContactTests() {
        super("Contact tests");

        this.addTest(canGetContactsFolder("Can get contacts folder", true));
        this.addTest(canGetContacts("Can get contacts", true));
        this.addTest(canCreateContact("Can create contacts", true));
        this.addTest(canDeleteContact("Can delete contacts", true));
        this.addTest(canUpdateContact("Can update contacts", true));
    }

    private TestCase canGetContactsFolder(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();

                    //Act
                    List<Contact> contacts = client.getMe()
                            .getContactFolders()
                            .getById("Contacts")
                            .getContacts().read().get();

                    //Assert
                    if(contacts == null)
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

    private TestCase canGetContacts(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();

                    //Prepare
                    Contact addedContact = client.getMe().getContacts().add(getContact()).get();

                    //Act
                    List<Contact> contacts = client.getMe().getContacts().top(2).read().get();

                    //Assert
                    if(contacts.size() == 0 || contacts.size() >2)
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getMe().getContacts().getById(addedContact.getId()).delete().get();
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

    private TestCase canCreateContact(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();

                    //Prepare
                    Contact addedContact = client.getMe().getContacts().add(getContact()).get();
                    Thread.sleep(2000);
                    //Act
                    Contact storedContact = client.getMe()
                            .getContacts()
                            .getById(addedContact.getId()).read().get();

                    //Assert
                    if(!storedContact.getId().equals(addedContact.getId()))
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getMe().getContacts().getById(addedContact.getId()).delete().get();
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

    private TestCase canDeleteContact(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();

                    //Prepare
                    Contact addedContact = client.getMe().getContacts().add(getContact()).get();
                    Thread.sleep(2000);
                    //Act
                    client.getMe().getContacts().getById(addedContact.getId()).delete().get();

                    //Assert
                    List<Contact> contacts = client.getMe().getContacts().read().get();

                    boolean exists = false;
                    for(Contact c : contacts)
                    {
                        if(c.getId().equals(addedContact.getId()))
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

    private TestCase canUpdateContact(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    EntityContainerClient client = ApplicationContext.getMailCalendarContactClient();

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
                    if(!updatedContact.getId().equals(addedContact.getId()) || !updatedContact.getGivenName().equals("Updated given name"))
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getMe().getContacts().getById(addedContact.getId()).delete().get();
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

    private Contact getContact(){
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

}
