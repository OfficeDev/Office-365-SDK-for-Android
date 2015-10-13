package com.microsoft.office365.test.integration.tests.filters;

import com.microsoft.office365.test.integration.framework.objectFiller.ObjectFiller;
import com.microsoft.office365.test.integration.framework.objectFiller.PropertyFilter;
import com.microsoft.office365.test.integration.framework.objectFiller.PropertyFilterContext;
import com.microsoft.services.outlook.ContactFolder;

import java.util.UUID;


public class ContactFolderFilter implements PropertyFilter {

    public boolean canHandle(PropertyFilterContext context) {
        if (context.getPropertyClass().equals(ContactFolder.class)) {
            return true;
        }

        return false;
    }

    public Object handle(PropertyFilterContext context, ObjectFiller objectFiller) throws IllegalAccessException, InstantiationException {
        Object obj = null;

        if (context.getPropertyClass().equals(ContactFolder.class)) {
            ContactFolder contact = (ContactFolder) context.getPropertyObject();
            contact.setDisplayName("Test Contact Folder" + UUID.randomUUID().toString());
            obj = contact;
        }

        return obj;
    }
}
