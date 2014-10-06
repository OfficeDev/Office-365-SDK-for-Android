/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.exchange.services.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.exchange.services.model.*;

public class UserFetcher extends ODataEntityFetcher<User,UserOperations> implements Executable<User> {

	 public UserFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, User.class,UserOperations.class);
    }
	public ODataCollectionFetcher<Folder, FolderFetcher, FolderCollectionOperations> getFolders() {
        return new ODataCollectionFetcher<Folder, FolderFetcher,FolderCollectionOperations>("Folders", this, Folder.class,FolderCollectionOperations.class);
    }
	public ODataCollectionFetcher<Message, MessageFetcher, MessageCollectionOperations> getMessages() {
        return new ODataCollectionFetcher<Message, MessageFetcher,MessageCollectionOperations>("Messages", this, Message.class,MessageCollectionOperations.class);
    }
	public FolderFetcher getRootFolder() {
        return new FolderFetcher("RootFolder", this);
    }
	public ODataCollectionFetcher<Calendar, CalendarFetcher, CalendarCollectionOperations> getCalendars() {
        return new ODataCollectionFetcher<Calendar, CalendarFetcher,CalendarCollectionOperations>("Calendars", this, Calendar.class,CalendarCollectionOperations.class);
    }
	public CalendarFetcher getCalendar() {
        return new CalendarFetcher("Calendar", this);
    }
	public ODataCollectionFetcher<CalendarGroup, CalendarGroupFetcher, CalendarGroupCollectionOperations> getCalendarGroups() {
        return new ODataCollectionFetcher<CalendarGroup, CalendarGroupFetcher,CalendarGroupCollectionOperations>("CalendarGroups", this, CalendarGroup.class,CalendarGroupCollectionOperations.class);
    }
	public ODataCollectionFetcher<Event, EventFetcher, EventCollectionOperations> getEvents() {
        return new ODataCollectionFetcher<Event, EventFetcher,EventCollectionOperations>("Events", this, Event.class,EventCollectionOperations.class);
    }
	public ODataCollectionFetcher<Contact, ContactFetcher, ContactCollectionOperations> getContacts() {
        return new ODataCollectionFetcher<Contact, ContactFetcher,ContactCollectionOperations>("Contacts", this, Contact.class,ContactCollectionOperations.class);
    }
	public ODataCollectionFetcher<ContactFolder, ContactFolderFetcher, ContactFolderCollectionOperations> getContactFolders() {
        return new ODataCollectionFetcher<ContactFolder, ContactFolderFetcher,ContactFolderCollectionOperations>("ContactFolders", this, ContactFolder.class,ContactFolderCollectionOperations.class);
    }
}