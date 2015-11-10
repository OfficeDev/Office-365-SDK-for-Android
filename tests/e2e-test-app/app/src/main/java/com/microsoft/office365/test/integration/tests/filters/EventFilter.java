package com.microsoft.office365.test.integration.tests.filters;

import com.microsoft.office365.test.integration.ApplicationContext;
import com.microsoft.office365.test.integration.framework.objectFiller.ObjectFiller;
import com.microsoft.office365.test.integration.framework.objectFiller.PropertyFilter;
import com.microsoft.office365.test.integration.framework.objectFiller.PropertyFilterContext;
import com.microsoft.services.outlook.Attendee;
import com.microsoft.services.outlook.BodyType;
import com.microsoft.services.outlook.EmailAddress;
import com.microsoft.services.outlook.Event;
import com.microsoft.services.outlook.Importance;
import com.microsoft.services.outlook.ItemBody;

import java.util.ArrayList;
import java.util.List;


public class EventFilter implements PropertyFilter {

    public boolean canHandle(PropertyFilterContext context) {
        if (context.getPropertyClass().equals(Event.class)) {
            return true;
        }

        return false;
    }

    public Object handle(PropertyFilterContext context, ObjectFiller objectFiller) throws IllegalAccessException, InstantiationException {
        Object obj = null;

        if (context.getPropertyClass().equals(Event.class)) {
            Event event = (Event) context.getPropertyObject();
            fillEvent(event);
            obj = event;
        }

        return obj;
    }

    private void fillEvent(Event event) {
        /*
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
        email.setAddress(ApplicationContext.getTestMail());
        attendee1.setEmailAddress(email);
        List<Attendee> listAttendees = new ArrayList<Attendee>();
        listAttendees.add(attendee1);
        event.setAttendees(listAttendees);
        */
    }
}
