/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.outlookservices.*;

/**
 * The type  UserFetcher.
 */
public class UserFetcher extends ODataEntityFetcher<User,UserOperations> implements Readable<User> {

     /**
     * Instantiates a new UserFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
	 public UserFetcher(String urlComponent, ODataExecutable parent) {
		super(urlComponent, parent, User.class,UserOperations.class);
    }
     /**
     * Gets folders.
     *
     * @return the folders
     */
	public ODataCollectionFetcher<Folder, FolderFetcher, FolderCollectionOperations> getFolders() {
		return new ODataCollectionFetcher<Folder, FolderFetcher,FolderCollectionOperations>("Folders", this, Folder.class,FolderCollectionOperations.class);
    }
     /**
     * Gets messages.
     *
     * @return the messages
     */
	public ODataCollectionFetcher<Message, MessageFetcher, MessageCollectionOperations> getMessages() {
		return new ODataCollectionFetcher<Message, MessageFetcher,MessageCollectionOperations>("Messages", this, Message.class,MessageCollectionOperations.class);
    }
	 /**
     * Gets rootfolder.
     *
     * @return the root folder
     */
	public FolderFetcher getRootFolder() {
		return new FolderFetcher("RootFolder", this);
    }
     /**
     * Gets calendars.
     *
     * @return the calendars
     */
	public ODataCollectionFetcher<Calendar, CalendarFetcher, CalendarCollectionOperations> getCalendars() {
		return new ODataCollectionFetcher<Calendar, CalendarFetcher,CalendarCollectionOperations>("Calendars", this, Calendar.class,CalendarCollectionOperations.class);
    }
	 /**
     * Gets calendar.
     *
     * @return the calendar
     */
	public CalendarFetcher getCalendar() {
		return new CalendarFetcher("Calendar", this);
    }
     /**
     * Gets calendar groups.
     *
     * @return the calendar groups
     */
	public ODataCollectionFetcher<CalendarGroup, CalendarGroupFetcher, CalendarGroupCollectionOperations> getCalendarGroups() {
		return new ODataCollectionFetcher<CalendarGroup, CalendarGroupFetcher,CalendarGroupCollectionOperations>("CalendarGroups", this, CalendarGroup.class,CalendarGroupCollectionOperations.class);
    }
     /**
     * Gets events.
     *
     * @return the events
     */
	public ODataCollectionFetcher<Event, EventFetcher, EventCollectionOperations> getEvents() {
		return new ODataCollectionFetcher<Event, EventFetcher,EventCollectionOperations>("Events", this, Event.class,EventCollectionOperations.class);
    }
     /**
     * Gets calendar view.
     *
     * @return the calendar view
     */
	public ODataCollectionFetcher<Event, EventFetcher, EventCollectionOperations> getCalendarView() {
		return new ODataCollectionFetcher<Event, EventFetcher,EventCollectionOperations>("CalendarView", this, Event.class,EventCollectionOperations.class);
    }
     /**
     * Gets contacts.
     *
     * @return the contacts
     */
	public ODataCollectionFetcher<Contact, ContactFetcher, ContactCollectionOperations> getContacts() {
		return new ODataCollectionFetcher<Contact, ContactFetcher,ContactCollectionOperations>("Contacts", this, Contact.class,ContactCollectionOperations.class);
    }
     /**
     * Gets contact folders.
     *
     * @return the contact folders
     */
	public ODataCollectionFetcher<ContactFolder, ContactFolderFetcher, ContactFolderCollectionOperations> getContactFolders() {
		return new ODataCollectionFetcher<ContactFolder, ContactFolderFetcher,ContactFolderCollectionOperations>("ContactFolders", this, ContactFolder.class,ContactFolderCollectionOperations.class);
    }
}