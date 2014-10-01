/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import com.microsoft.office365.exchange.services.*;

public class UserODataComponent extends BaseEntityODataComponent<User> implements Executable<User> {

	 public UserODataComponent(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, User.class);
    }
	public ODataCollection<Folder, FolderODataComponent, FolderCollectionOperations> getFolders() {
        return new ODataCollection<Folder, FolderODataComponent,FolderCollectionOperations>("Folders", this, Folder.class,FolderCollectionOperations.class);
    }
	public ODataCollection<Message, MessageODataComponent, MessageCollectionOperations> getMessages() {
        return new ODataCollection<Message, MessageODataComponent,MessageCollectionOperations>("Messages", this, Message.class,MessageCollectionOperations.class);
    }
	public FolderODataComponent getRootFolder() {
        return new FolderODataComponent("RootFolder", this);
    }
	public FolderODataComponent getInbox() {
        return new FolderODataComponent("Inbox", this);
    }
	public FolderODataComponent getDrafts() {
        return new FolderODataComponent("Drafts", this);
    }
	public FolderODataComponent getSentItems() {
        return new FolderODataComponent("SentItems", this);
    }
	public FolderODataComponent getDeletedItems() {
        return new FolderODataComponent("DeletedItems", this);
    }
	public ODataCollection<Calendar, CalendarODataComponent, CalendarCollectionOperations> getCalendars() {
        return new ODataCollection<Calendar, CalendarODataComponent,CalendarCollectionOperations>("Calendars", this, Calendar.class,CalendarCollectionOperations.class);
    }
	public CalendarODataComponent getCalendar() {
        return new CalendarODataComponent("Calendar", this);
    }
	public ODataCollection<CalendarGroup, CalendarGroupODataComponent, CalendarGroupCollectionOperations> getCalendarGroups() {
        return new ODataCollection<CalendarGroup, CalendarGroupODataComponent,CalendarGroupCollectionOperations>("CalendarGroups", this, CalendarGroup.class,CalendarGroupCollectionOperations.class);
    }
	public ODataCollection<Event, EventODataComponent, EventCollectionOperations> getEvents() {
        return new ODataCollection<Event, EventODataComponent,EventCollectionOperations>("Events", this, Event.class,EventCollectionOperations.class);
    }
	public ODataCollection<Contact, ContactODataComponent, ContactCollectionOperations> getContacts() {
        return new ODataCollection<Contact, ContactODataComponent,ContactCollectionOperations>("Contacts", this, Contact.class,ContactCollectionOperations.class);
    }
	public ODataCollection<ContactFolder, ContactFolderODataComponent, ContactFolderCollectionOperations> getContactFolders() {
        return new ODataCollection<ContactFolder, ContactFolderODataComponent,ContactFolderCollectionOperations>("ContactFolders", this, ContactFolder.class,ContactFolderCollectionOperations.class);
    }
}