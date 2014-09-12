package com.microsoft.office365.api;

import java.sql.Timestamp;

import com.microsoft.office.microsoft.exchange.services.odata.model.types.CalendarGroupCollection;
import com.microsoft.office365.oauth.OAuthCredentials;
import com.microsoft.office.microsoft.exchange.services.odata.model.types.Attendee;
import com.microsoft.office.microsoft.exchange.services.odata.model.types.AttendeeCollection;
import com.microsoft.office.microsoft.exchange.services.odata.model.types.Calendar;
import com.microsoft.office.microsoft.exchange.services.odata.model.types.CalendarCollection;
import com.microsoft.office.microsoft.exchange.services.odata.model.types.Event;
import com.microsoft.office.microsoft.exchange.services.odata.model.types.EventCollection;

/**
 * The Class CalendarClient.
 */
public class CalendarClient extends BaseOfficeClient {

    Builder mBuilder;

    protected CalendarClient(Builder builder) {
        super(builder);

        mBuilder = builder;
    }

    public void newEvent(String subject, AttendeeCollection attendees) {

        Event event = container.newEntityInstance(Event.class);
        event.setSubject(subject);

        AttendeeCollection ac = container.newComplexCollection(AttendeeCollection.class);
        Attendee a = container.newComplexInstance(Attendee.class);

        ac.add(a);
        event.setAttendees(attendees);

        container.getMe().getEvents().add(event);
        container.flush();
    }

    public CalendarGroupCollection getCalendarGroups(){
        return container.getMe().getCalendarGroups().execute();
    }


    /**
     * Gets the events.
     *
     * @return the events
     */
    public EventCollection getEvents() {
        return container.getMe().getEvents().execute();
    }

    /**
     * Gets the event.
     *
     * @param eventId the event id
     * @return the event
     */
    public Event getEvent(String eventId) {
        return container.getMe().getEvents().getByKey(eventId);
    }

    /**
     * Gets the calendars.
     *
     * @return the calendars
     */
    public CalendarCollection getCalendars() {
        return container.getMe().getCalendars().execute();
    }

    /**
     * Gets the calendar.
     *
     * @param calendarId the calendar id
     * @return the calendar
     */
    public Calendar getCalendar(String calendarId) {
        return container.getMe().getCalendars().getByKey(calendarId);
    }

    /**
     * The Class Builder.
     */
    public static final class Builder extends BaseOfficeClient.Builder {

        /**
         * Instantiates a new builder.
         */
        public Builder() {
            super();
        }

        /**
         * Instantiates a new builder.
         *
         * @param credentials   the credentials
         * @param resourceId    the resource id
         * @param odataEndpoint the odata endpoint
         */
        public Builder(OAuthCredentials credentials, String resourceId, String odataEndpoint) {
            super(credentials, resourceId, odataEndpoint);
        }

        /* (non-Javadoc)
         * @see com.microsoft.office365.api.BaseOfficeClient.Builder#build()
         */
        @Override
        public CalendarClient build() {
            return new CalendarClient(this);
        }

        /* (non-Javadoc)
         * @see com.microsoft.office365.api.BaseOfficeClient.Builder#setCredentials(com.microsoft.office365.http.OAuthCredentials)
         */
        @Override
        public Builder setCredentials(OAuthCredentials credentials) {
            return (Builder) super.setCredentials(credentials);
        }

        /* (non-Javadoc)
         * @see com.microsoft.office365.api.BaseOfficeClient.Builder#setOdataEndpoint(java.lang.String)
         */
        @Override
        public Builder setOdataEndpoint(String odataEndpoint) {
            return (Builder) super.setOdataEndpoint(odataEndpoint);
        }

        /* (non-Javadoc)
         * @see com.microsoft.office365.api.BaseOfficeClient.Builder#setResourceId(java.lang.String)
         */
        @Override
        public Builder setResourceId(String resourceId) {
            return (Builder) super.setResourceId(resourceId);
        }
    }
}
