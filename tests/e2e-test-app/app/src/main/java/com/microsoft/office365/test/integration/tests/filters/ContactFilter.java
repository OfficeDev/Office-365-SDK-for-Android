package com.microsoft.office365.test.integration.tests.filters;

import com.microsoft.office365.test.integration.framework.objectFiller.ObjectFiller;
import com.microsoft.office365.test.integration.framework.objectFiller.PropertyFilter;
import com.microsoft.office365.test.integration.framework.objectFiller.PropertyFilterContext;
import com.microsoft.services.outlook.Contact;
import com.microsoft.services.outlook.EmailAddress;

import java.util.ArrayList;
import java.util.List;


public class ContactFilter implements PropertyFilter {

    public boolean canHandle(PropertyFilterContext context) {
        if (context.getPropertyClass().equals(Contact.class)) {
            return true;
        }

        return false;
    }

    public Object handle(PropertyFilterContext context, ObjectFiller objectFiller) throws IllegalAccessException, InstantiationException {
        Object obj = null;

        if (context.getPropertyClass().equals(Contact.class)) {
            Contact contact = (Contact) context.getPropertyObject();
            fillContact(contact);
            obj = contact;
        }

        return obj;
    }

    private void fillContact(Contact contact) {
        contact.setDisplayName("Test Contact");
        contact.setGivenName("Test Contact Name");
        final EmailAddress email = new EmailAddress();
        email.setAddress("test@test.com");
        List<EmailAddress> list = new ArrayList<EmailAddress>();
        list.add(email);
        contact.setEmailAddresses(list);
    }
}
