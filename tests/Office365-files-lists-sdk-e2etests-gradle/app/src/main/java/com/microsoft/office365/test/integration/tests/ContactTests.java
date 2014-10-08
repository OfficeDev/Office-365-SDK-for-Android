package com.microsoft.office365.test.integration.tests;


import com.microsoft.office365.exchange.services.model.Contact;
import com.microsoft.office365.exchange.services.model.EmailAddress;
import com.microsoft.office365.exchange.services.odata.EntityContainerClient;
import com.microsoft.office365.test.integration.ApplicationContext;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestGroup;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.office365.test.integration.framework.TestStatus;

import java.util.ArrayList;
import java.util.List;

public class ContactTests extends TestGroup {

    public ContactTests() {
        super("Contact tests");

        this.addTest(canGetContacts("Can get contacts", true));
        this.addTest(canCreateContact("Can create contacts", true));
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
                    List<Contact> contacts = client.getMe().getContacts().top(2).execute().get();

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

                    //Act
                    Contact storedContact = client.getMe()
                            .getContacts()
                            .getById(addedContact.getId()).execute().get();

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
