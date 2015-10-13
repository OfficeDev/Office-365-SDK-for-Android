package com.microsoft.office365.test.integration.tests.filters;

import com.microsoft.office365.test.integration.framework.objectFiller.ObjectFiller;
import com.microsoft.office365.test.integration.framework.objectFiller.PropertyFilter;
import com.microsoft.office365.test.integration.framework.objectFiller.PropertyFilterContext;
import com.microsoft.services.outlook.FileAttachment;

import java.util.UUID;


public class FileAttachmentFilter implements PropertyFilter {

    public boolean canHandle(PropertyFilterContext context) {
        if (context.getPropertyClass().equals(FileAttachment.class)) {
            return true;
        }

        return false;
    }

    public Object handle(PropertyFilterContext context, ObjectFiller objectFiller) throws IllegalAccessException, InstantiationException {
        Object obj = null;

        if (context.getPropertyClass().equals(FileAttachment.class)) {
            FileAttachment fileAttachment = (FileAttachment) context.getPropertyObject();
            fileAttachment.setName(UUID.randomUUID().toString() +  "-myFile.txt");
            fileAttachment.setContentBytes("hello world".getBytes());
            obj = fileAttachment;
        }

        return obj;
    }
}
