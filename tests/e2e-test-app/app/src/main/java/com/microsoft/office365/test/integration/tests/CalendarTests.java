package com.microsoft.office365.test.integration.tests;


import com.microsoft.office365.test.integration.ApplicationContext;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestGroup;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.office365.test.integration.framework.TestStatus;
import com.microsoft.outlookservices.Attendee;
import com.microsoft.outlookservices.BodyType;
import com.microsoft.outlookservices.Calendar;
import com.microsoft.outlookservices.CalendarGroup;
import com.microsoft.outlookservices.EmailAddress;
import com.microsoft.outlookservices.Event;
import com.microsoft.outlookservices.Importance;
import com.microsoft.outlookservices.ItemBody;
import com.microsoft.outlookservices.odata.OutlookClient;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CalendarTests extends TestGroup {

    public CalendarTests() {
        super("Calendar tests");

        //Calendar groups
        this.addTest(canCreateCalendarGroup("Can create calendar group", true));
        this.addTest(canGetCalendarGroups("Can get calendar groups", true));
        this.addTest(canGetCalendarGroupById("Can get calendar group by id", true));
        this.addTest(canUpdateCalendarGroup("Can update calendar group", true));
        this.addTest(canDeleteCalendarGroup("Can delete calendar group", true));

        // Calendars
        this.addTest(canCreateCalendar("Can create calendar", true));
        this.addTest(canGetCalendars("Can get calendars", true));
        this.addTest(canGetDefaultCalendar("Can get default calendar", true));
        this.addTest(canGetCalendarById("Can get calendar by id", true));
        this.addTest(canUpdateCalendar("Can update calendar", true));
        this.addTest(canDeleteCalendar("Can delete calendar", true));

        //Events
        this.addTest(canGetEvents("Can get events", true));
        this.addTest(canCreateEvents("Can create events", true));
        this.addTest(canUpdateEvents("Can update events", true));
        this.addTest(canDeleteEvents("Can delete events", true));
    }

    private TestCase canCreateCalendarGroup(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    OutlookClient client = ApplicationContext.getMailCalendarContactClient();

                    // Act
                    CalendarGroup calendarGroup = new CalendarGroup();
                    calendarGroup.setName("My testing calendar Group");
                    CalendarGroup addedCalendarGroup = client.getMe()
                            .getCalendarGroups()
                            .add(calendarGroup).get();

                    //Assert
                    if(!addedCalendarGroup.getName().equals(calendarGroup.getName()))
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getMe().getCalendarGroups()
                            .getById(addedCalendarGroup.getId())
                            .delete().get();

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

    private TestCase canGetCalendarGroups(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    OutlookClient client = ApplicationContext.getMailCalendarContactClient();

                    // Prepare
                    CalendarGroup calendarGroup = new CalendarGroup();
                    calendarGroup.setName("My testing calendar Group");
                    CalendarGroup addedCalendarGroup = client.getMe()
                            .getCalendarGroups()
                            .add(calendarGroup).get();

                    // Act
                    List<CalendarGroup> calendarGroups = client.getMe().getCalendarGroups().read().get();

                    //Assert
                    if(calendarGroups.size() == 0)
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getMe().getCalendarGroups()
                            .getById(addedCalendarGroup.getId())
                            .delete().get();

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

    private TestCase canGetCalendarGroupById(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    OutlookClient client = ApplicationContext.getMailCalendarContactClient();

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
                    if(!storedCalendarGroup.getName().equals(addedCalendarGroup.getName()))
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getMe().getCalendarGroups()
                            .getById(addedCalendarGroup.getId())
                            .delete().get();

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

    private TestCase canUpdateCalendarGroup(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    OutlookClient client = ApplicationContext.getMailCalendarContactClient();

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
                    if(!updatedCalendarGroup.getName().equals("Updated Calendar Group"))
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getMe().getCalendarGroups()
                            .getById(updatedCalendarGroup.getId())
                            .delete().get();

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

    private TestCase canDeleteCalendarGroup(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    OutlookClient client = ApplicationContext.getMailCalendarContactClient();

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
                    }
                    catch (Exception e){
                        //It's supposed to fail
                        result.setStatus(TestStatus.Passed);
                    }

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

    private TestCase canGetCalendars(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    OutlookClient client = ApplicationContext.getMailCalendarContactClient();

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
                    if(calendars.size() == 0)
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getMe().getCalendars()
                            .getById(addedCalendar.getId())
                            .delete().get();

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

    private TestCase canGetDefaultCalendar(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    OutlookClient client = ApplicationContext.getMailCalendarContactClient();

                    // Act
                    Calendar calendar = client.getMe().getCalendar().read().get();

                    //Assert
                    if(calendar.getName() == "")
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

    private TestCase canCreateCalendar(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    OutlookClient client = ApplicationContext.getMailCalendarContactClient();

                    // Act
                    String calendarName = "My testing calendar" + UUID.randomUUID().toString();
                    Calendar calendar = new Calendar();
                    calendar.setName(calendarName);
                    Calendar addedCalendar = client.getMe()
                            .getCalendars()
                            .add(calendar).get();

                    //Assert
                    if(!addedCalendar.getName().equals(calendar.getName()))
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getMe().getCalendars()
                            .getById(addedCalendar.getId())
                            .delete().get();

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

    private TestCase canGetCalendarById(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    OutlookClient client = ApplicationContext.getMailCalendarContactClient();

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
                    if(!storedCalendar.getName().equals(addedCalendar.getName()))
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getMe().getCalendars()
                            .getById(addedCalendar.getId())
                            .delete().get();

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

    private TestCase canUpdateCalendar(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    OutlookClient client = ApplicationContext.getMailCalendarContactClient();

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
                    if(!updatedCalendar.getName().equals(updatedCalendarName))
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getMe().getCalendars()
                            .getById(updatedCalendar.getId())
                            .delete().get();

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

    private TestCase canDeleteCalendar(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    OutlookClient client = ApplicationContext.getMailCalendarContactClient();

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
                    }
                    catch (Exception e){
                        //It's supposed to fail
                        result.setStatus(TestStatus.Passed);
                    }

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

    private TestCase canGetEvents(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    OutlookClient client = ApplicationContext.getMailCalendarContactClient();

                    // Prepare
                    Event event = getSampleEvent();
                    Event addedEvent = client.getMe().getCalendars().getById("Calendar").getEvents().add(event).get();

                    // Act
                    List<Event> events = client.getMe().getCalendars().getById("Calendar").getEvents().read().get();

                    //Assert
                    if(events.size() == 0)
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getMe().getCalendars()
                            .getById("Calendar")
                            .getEvents()
                            .getById(addedEvent.getId())
                            .delete().get();

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

    private TestCase canCreateEvents(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    OutlookClient client = ApplicationContext.getMailCalendarContactClient();

                    // Prepare
                    Event event = getSampleEvent();

                    // Act
                    Event addedEvent = client.getMe().getCalendars().getById("Calendar").getEvents().add(event).get();

                    //Assert
                    if(!addedEvent.getSubject().equals(event.getSubject()))
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getMe()
                            .getEvents()
                            .getById(addedEvent.getId())
                            .delete().get();

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

    private TestCase canUpdateEvents(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    OutlookClient client = ApplicationContext.getMailCalendarContactClient();

                    // Prepare
                    Event event = getSampleEvent();
                    Event addedEvent = client.getMe().getCalendars().getById("Calendar").getEvents().add(event).get();

                    // Act
                    event.setSubject("Updated Subject");
                    event.setImportance(Importance.Low);

                    Event updatedEvent = client.getMe().getEvents().getById(addedEvent.getId()).update(event).get();

                    //Assert
                    if(updatedEvent.getImportance() != Importance.Low || !updatedEvent.getSubject().equals("Updated Subject"))
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getMe()
                            .getEvents()
                            .getById(addedEvent.getId())
                            .delete().get();

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

    private TestCase canDeleteEvents(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    OutlookClient client = ApplicationContext.getMailCalendarContactClient();

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
                    }
                    catch (Exception e){
                        //It's supposed to fail
                        result.setStatus(TestStatus.Passed);
                    }

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

    private TestCase canRespondEvents(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    OutlookClient client = ApplicationContext.getMailCalendarContactClient();

                    // Prepare
                    Event event = getSampleEvent();
                    Event addedEvent = client.getMe().getCalendars().getById("Calendar").getEvents().add(event).get();

                    // Act
                    Integer accepted = client.getMe()
                            .getEvents()
                            .getById(addedEvent.getId())
                            .getOperations().accept("Accepted").get();

                    //Assert
                    if(!addedEvent.getSubject().equals(event.getSubject()))
                        result.setStatus(TestStatus.Failed);

                    //Cleanup
                    client.getMe()
                        .getEvents()
                        .getById(addedEvent.getId())
                        .delete().get();

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

    private Event getSampleEvent(){
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
        email.setAddress(ApplicationContext.getTestMail());
        attendee1.setEmailAddress(email);
        List<Attendee> listAttendees = new ArrayList<Attendee>();
        listAttendees.add(attendee1);
        event.setAttendees(listAttendees);

        return event;
    }

}
