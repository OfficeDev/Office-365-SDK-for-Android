package com.microsoft.office365.test.integration.tests.filters;

import com.microsoft.office365.test.integration.framework.objectFiller.ObjectFiller;
import com.microsoft.office365.test.integration.framework.objectFiller.PropertyFilter;
import com.microsoft.office365.test.integration.framework.objectFiller.PropertyFilterContext;
import com.microsoft.services.outlook.CalendarGroup;

import java.util.UUID;


public class CalendarGroupFilter implements PropertyFilter {

    public boolean canHandle(PropertyFilterContext context) {
        if (context.getPropertyClass().equals(CalendarGroup.class)) {
            return true;
        }

        return false;
    }

    public Object handle(PropertyFilterContext context, ObjectFiller objectFiller) throws IllegalAccessException, InstantiationException {
        Object obj = null;

        if (context.getPropertyClass().equals(CalendarGroup.class)) {
            CalendarGroup calendarGroup = (CalendarGroup) context.getPropertyObject();
            calendarGroup.setName("Test Calendar Group" + UUID.randomUUID().toString());
            obj = calendarGroup;
        }

        return obj;
    }
}
