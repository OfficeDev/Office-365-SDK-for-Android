package com.microsoft.office365.test.integration.tests.filters;

import com.microsoft.office365.test.integration.R;
import com.microsoft.office365.test.integration.framework.objectFiller.PropertyFilter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OutlookFilters {

    private final Map<String, String> mExcluded;

    public OutlookFilters(Map<String,String> excluded) {
        mExcluded = excluded;
    }

    public List<PropertyFilter> getFilters(){
        List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
        filters.add(new FileAttachmentFilter());
        filters.add(new FolderFilter());
        filters.add(new MessageFilter());
        filters.add(new EventFilter());
        filters.add(new CalendarFilter());
        filters.add(new CalendarGroupFilter());
        filters.add(new ContactFilter());
        filters.add(new ContactFolderFilter());

        return filters;
    }

    public Set<String> getNotSupportedTests(){

         return mExcluded.keySet();

        /*
        List<String> notSupported = new ArrayList<>();
        notSupported.add("canGetUsers");
        notSupported.add("canCreateUsers");
        notSupported.add("canGetUsersFolders");
        notSupported.add("canCreateUsersFolders");
        notSupported.add("canGetUsersFoldersMessages");
        notSupported.add("canCreateUsersFoldersMessages");
        notSupported.add("canGetUsersFoldersMessagesAttachments");
        notSupported.add("canCreateUsersFoldersMessagesAttachments");
        notSupported.add("canGetUsersMessages");
        notSupported.add("canCreateUsersMessages");
        notSupported.add("canGetUsersMessagesAttachments");
        notSupported.add("canCreateUsersMessagesAttachments");
        notSupported.add("canGetUsersRootFolder");
        notSupported.add("canGetUsersRootFolderMessages");
        notSupported.add("canCreateUsersRootFolderMessages");
        notSupported.add("canGetUsersRootFolderMessagesAttachments");
        notSupported.add("canCreateUsersRootFolderMessagesAttachments");
        notSupported.add("canGetUsersCalendars");
        notSupported.add("canCreateUsersCalendars");
        notSupported.add("canGetUsersCalendar");
        notSupported.add("canGetMeRootFolder");
        notSupported.add("canGetMeRootFolderMessages");
        notSupported.add("canCreateMeRootFolderMessages");
        notSupported.add("canGetMeRootFolderMessagesAttachments");
        notSupported.add("canCreateMeRootFolderMessagesAttachments");

        notSupported.add("canGetMeRootFolder");
        notSupported.add("canGetMeRootFolderChildFolders");
        notSupported.add("canCreateMeRootFolderChildFolders");
        notSupported.add("canGetMeRootFolderChildFoldersMessages");
        notSupported.add("canCreateMeRootFolderChildFoldersMessages");
        notSupported.add("canGetMeRootFolderChildFoldersMessagesAttachments");
        notSupported.add("canCreateMeRootFolderChildFoldersMessagesAttachments");
        notSupported.add("canGetMeRootFolderMessages");
        notSupported.add("canCreateMeRootFolderMessages");
        notSupported.add("canGetMeRootFolderMessagesAttachments");
        notSupported.add("canCreateMeRootFolderMessagesAttachments");

        notSupported.add("canGetMeCalendarsCalendarView");
        notSupported.add("canCreateMeCalendarsCalendarView");
        notSupported.add("canGetMeCalendarsCalendarViewAttachments");
        notSupported.add("canCreateMeCalendarsCalendarViewAttachments");
        notSupported.add("canGetMeCalendarsCalendarViewInstances");
        notSupported.add("canCreateMeCalendarsCalendarViewInstances");
        notSupported.add("canGetMeCalendarsCalendarViewInstancesAttachments");
        notSupported.add("canCreateMeCalendarsCalendarViewInstancesAttachments");

        notSupported.add("canGetMeCalendarGroupsCalendarsCalendarView");
        notSupported.add("canCreateMeCalendarGroupsCalendarsCalendarView");
        notSupported.add("canGetMeCalendarGroupsCalendarsCalendarViewAttachments");
        notSupported.add("canCreateMeCalendarGroupsCalendarsCalendarViewAttachments");
        notSupported.add("canGetMeCalendarGroupsCalendarsCalendarViewInstances");
        notSupported.add("canCreateMeCalendarGroupsCalendarsCalendarViewInstances");
        notSupported.add("canGetMeCalendarGroupsCalendarsCalendarViewInstancesAttachments");
        notSupported.add("canCreateMeCalendarGroupsCalendarsCalendarViewInstancesAttachments");
        notSupported.add("canGetMeCalendarView");
        notSupported.add("canCreateMeCalendarView");
        notSupported.add("canGetMeCalendarViewAttachments");
        notSupported.add("canCreateMeCalendarViewAttachments");
        notSupported.add("canGetMeCalendarViewCalendar");
        notSupported.add("canGetMeCalendarViewInstances");
        notSupported.add("canCreateMeCalendarViewInstances");
        notSupported.add("canGetMeCalendarViewInstancesAttachments");
        notSupported.add("canCreateMeCalendarViewInstancesAttachments");
        notSupported.add("canGetMeCalendarViewInstancesCalendar");

        notSupported.add("canGetMeCalendarGroupsCalendarsEventsInstancesgetTestStatus");
        notSupported.add("canCreateMeCalendarGroupsCalendarsEventsInstances");
        notSupported.add("canGetMeCalendarGroupsCalendarsEventsInstancesAttachments");
        notSupported.add("canCreateMeCalendarGroupsCalendarsEventsInstancesAttachments");
        notSupported.add("canGetMeCalendarEventsInstances");
        notSupported.add("canCreateMeCalendarEventsInstances");
        notSupported.add("canGetMeCalendarEventsInstancesAttachments");
        notSupported.add("canCreateMeCalendarEventsInstancesAttachments");

        notSupported.add("canGetMeCalendarsEventsInstances");
        notSupported.add("canCreateMeCalendarsEventsInstances");
        notSupported.add("canGetMeCalendarsEventsInstancesAttachments");
        notSupported.add("canCreateMeCalendarsEventsInstancesAttachments");
        notSupported.add("canGetMeCalendarCalendarView");
        notSupported.add("canCreateMeCalendarCalendarView");
        notSupported.add("canGetMeCalendarCalendarViewAttachments");
        notSupported.add("canCreateMeCalendarCalendarViewAttachments");
        notSupported.add("canGetMeCalendarCalendarViewInstances");
        notSupported.add("canCreateMeCalendarCalendarViewInstances");
        notSupported.add("canGetMeCalendarCalendarViewInstancesAttachments");
        notSupported.add("canCreateMeCalendarCalendarViewInstancesAttachments");
        notSupported.add("canGetMeCalendarGroupsCalendarsEventsInstances");
        notSupported.add("canGetMeEventsInstances");
        notSupported.add("canCreateMeEventsInstances");
        notSupported.add("canGetMeEventsInstancesAttachments");
        notSupported.add("canCreateMeEventsInstancesAttachments");
        notSupported.add("canGetMeEventsInstancesCalendar");

        notSupported.add("canCreateMeContactFolders");
        notSupported.add("canGetMeContactFoldersContacts");
        notSupported.add("canCreateMeContactFoldersContacts");
        notSupported.add("canGetMeContactFoldersChildFolders");
        notSupported.add("canCreateMeContactFoldersChildFolders");
        notSupported.add("canGetMeContactFoldersChildFoldersContacts");
        notSupported.add("canCreateMeContactFoldersChildFoldersContacts");

        return notSupported;
        */
    }
}
