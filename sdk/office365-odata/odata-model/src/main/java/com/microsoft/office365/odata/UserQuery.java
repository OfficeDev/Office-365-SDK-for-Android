/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.exchange.services.*;

public class UserQuery extends ODataEntityQuery<User> implements Executable<User> {

	 public UserQuery(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, User.class);
    }
	public ODataCollection<Folder, FolderQuery, FolderCollectionOperations> getFolders() {
        return new ODataCollection<Folder, FolderQuery,FolderCollectionOperations>("Folders", this, Folder.class,FolderCollectionOperations.class);
    }
	public ODataCollection<Message, MessageQuery, MessageCollectionOperations> getMessages() {
        return new ODataCollection<Message, MessageQuery,MessageCollectionOperations>("Messages", this, Message.class,MessageCollectionOperations.class);
    }
	public FolderQuery getRootFolder() {
        return new FolderQuery("RootFolder", this);
    }
	public FolderQuery getInbox() {
        return new FolderQuery("Inbox", this);
    }
	public FolderQuery getDrafts() {
        return new FolderQuery("Drafts", this);
    }
	public FolderQuery getSentItems() {
        return new FolderQuery("SentItems", this);
    }
	public FolderQuery getDeletedItems() {
        return new FolderQuery("DeletedItems", this);
    }
	public ODataCollection<Calendar, CalendarQuery, CalendarCollectionOperations> getCalendars() {
        return new ODataCollection<Calendar, CalendarQuery,CalendarCollectionOperations>("Calendars", this, Calendar.class,CalendarCollectionOperations.class);
    }
	public CalendarQuery getCalendar() {
        return new CalendarQuery("Calendar", this);
    }
	public ODataCollection<CalendarGroup, CalendarGroupQuery, CalendarGroupCollectionOperations> getCalendarGroups() {
        return new ODataCollection<CalendarGroup, CalendarGroupQuery,CalendarGroupCollectionOperations>("CalendarGroups", this, CalendarGroup.class,CalendarGroupCollectionOperations.class);
    }
	public ODataCollection<Event, EventQuery, EventCollectionOperations> getEvents() {
        return new ODataCollection<Event, EventQuery,EventCollectionOperations>("Events", this, Event.class,EventCollectionOperations.class);
    }
	public ODataCollection<Contact, ContactQuery, ContactCollectionOperations> getContacts() {
        return new ODataCollection<Contact, ContactQuery,ContactCollectionOperations>("Contacts", this, Contact.class,ContactCollectionOperations.class);
    }
	public ODataCollection<ContactFolder, ContactFolderQuery, ContactFolderCollectionOperations> getContactFolders() {
        return new ODataCollection<ContactFolder, ContactFolderQuery,ContactFolderCollectionOperations>("ContactFolders", this, ContactFolder.class,ContactFolderCollectionOperations.class);
    }
}