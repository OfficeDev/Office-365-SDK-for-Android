package com.microsoft.office365.test.integration.tests.filters;

import com.microsoft.office365.test.integration.framework.objectFiller.ObjectFiller;
import com.microsoft.office365.test.integration.framework.objectFiller.PropertyFilter;
import com.microsoft.office365.test.integration.framework.objectFiller.PropertyFilterContext;
import com.microsoft.services.outlook.Calendar;
import com.microsoft.services.outlook.CalendarColor;

import java.util.UUID;


public class CalendarFilter implements PropertyFilter {

    public boolean canHandle(PropertyFilterContext context) {
        if (context.getPropertyClass().equals(Calendar.class)) {
            return true;
        }

        return false;
    }

    public Object handle(PropertyFilterContext context, ObjectFiller objectFiller) throws IllegalAccessException, InstantiationException {
        Object obj = null;

        if (context.getPropertyClass().equals(Calendar.class)) {
            Calendar calendar = (Calendar) context.getPropertyObject();
            calendar.setName("Calendar" + UUID.randomUUID().toString());
            calendar.setColor(CalendarColor.LightBlue);
            obj = calendar;
        }

        return obj;
    }
}
