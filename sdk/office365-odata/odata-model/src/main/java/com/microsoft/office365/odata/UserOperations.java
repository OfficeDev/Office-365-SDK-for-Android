/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.exchange.services.*;

public class UserOperations extends BaseEntityOperations<User> implements Executable<User> {

	 public UserOperations(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, User.class);
    }
	public ODataCollection<Folder, FolderOperations, FolderCollectionOperations> getFolders() {
        return new ODataCollection<Folder, FolderOperations,FolderCollectionOperations>("Folders", this, Folder.class,FolderCollectionOperations.class);
    }
	public ODataCollection<Message, MessageOperations, MessageCollectionOperations> getMessages() {
        return new ODataCollection<Message, MessageOperations,MessageCollectionOperations>("Messages", this, Message.class,MessageCollectionOperations.class);
    }
	public FolderOperations getRootFolder() {
        return new FolderOperations("RootFolder", this);
    }
	public ODataCollection<Calendar, CalendarOperations, CalendarCollectionOperations> getCalendars() {
        return new ODataCollection<Calendar, CalendarOperations,CalendarCollectionOperations>("Calendars", this, Calendar.class,CalendarCollectionOperations.class);
    }
	public CalendarOperations getCalendar() {
        return new CalendarOperations("Calendar", this);
    }
	public ODataCollection<CalendarGroup, CalendarGroupOperations, CalendarGroupCollectionOperations> getCalendarGroups() {
        return new ODataCollection<CalendarGroup, CalendarGroupOperations,CalendarGroupCollectionOperations>("CalendarGroups", this, CalendarGroup.class,CalendarGroupCollectionOperations.class);
    }
	public ODataCollection<Event, EventOperations, EventCollectionOperations> getEvents() {
        return new ODataCollection<Event, EventOperations,EventCollectionOperations>("Events", this, Event.class,EventCollectionOperations.class);
    }
	public ODataCollection<Contact, ContactOperations, ContactCollectionOperations> getContacts() {
        return new ODataCollection<Contact, ContactOperations,ContactCollectionOperations>("Contacts", this, Contact.class,ContactCollectionOperations.class);
    }
	public ODataCollection<ContactFolder, ContactFolderOperations, ContactFolderCollectionOperations> getContactFolders() {
        return new ODataCollection<ContactFolder, ContactFolderOperations,ContactFolderCollectionOperations>("ContactFolders", this, ContactFolder.class,ContactFolderCollectionOperations.class);
    }
			
	public ListenableFuture<Integer> sendMail(Message message, Boolean savetosentitems) {
        final SettableFuture<Integer> result = SettableFuture.create();

        ListenableFuture<byte[]> future = oDataExecute("SendMail", null, HttpVerb.POST);

        Futures.addCallback(future, new FutureCallback<byte[]>() {
            @Override
            public void onSuccess(byte[] integer) {
                DependencyResolver resolver = getResolver();

                try {
                    result.set(resolver.getJsonSerializer().deserialize(new String(integer, Constants.UTF8), Integer.class));
                } catch (Throwable throwable) {
                    result.setException(throwable);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                result.setException(t);
            }
        });

        return result;
    }
}