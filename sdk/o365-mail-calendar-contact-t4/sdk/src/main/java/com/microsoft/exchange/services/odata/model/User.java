
package com.microsoft.exchange.services.odata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



import com.microsoft.exchange.services.odata.model.Folder;



import com.microsoft.exchange.services.odata.model.Message;



import com.microsoft.exchange.services.odata.model.Calendar;



import com.microsoft.exchange.services.odata.model.CalendarGroup;



import com.microsoft.exchange.services.odata.model.Event;



import com.microsoft.exchange.services.odata.model.Contact;



import com.microsoft.exchange.services.odata.model.ContactFolder;



public class User extends Entity {

	@SerializedName("DisplayName")
	@Expose
	private String displayName;

	public String getDisplayName() {
		 return displayName; 
	}

	public void setDisplayName(String value) { 
		displayName = value; 
	}

	@SerializedName("Alias")
	@Expose
	private String alias;

	public String getAlias() {
		 return alias; 
	}

	public void setAlias(String value) { 
		alias = value; 
	}

	@SerializedName("MailboxGuid")
	@Expose
	private java.util.UUID mailboxGuid;

	public java.util.UUID getMailboxGuid() {
		 return mailboxGuid; 
	}

	public void setMailboxGuid(java.util.UUID value) { 
		mailboxGuid = value; 
	}

	@SerializedName("Folders")
	@Expose
	private java.util.List<Folder> folders;

	public java.util.List<Folder> getFolders() {
		 return folders; 
	}

	public void setFolders(java.util.List<Folder> value) { 
		folders = value; 
	}

	@SerializedName("Messages")
	@Expose
	private java.util.List<Message> messages;

	public java.util.List<Message> getMessages() {
		 return messages; 
	}

	public void setMessages(java.util.List<Message> value) { 
		messages = value; 
	}

	@SerializedName("RootFolder")
	@Expose
	private Folder rootFolder;

	public Folder getRootFolder() {
		 return rootFolder; 
	}

	public void setRootFolder(Folder value) { 
		rootFolder = value; 
	}

	@SerializedName("Inbox")
	@Expose
	private Folder inbox;

	public Folder getInbox() {
		 return inbox; 
	}

	public void setInbox(Folder value) { 
		inbox = value; 
	}

	@SerializedName("Drafts")
	@Expose
	private Folder drafts;

	public Folder getDrafts() {
		 return drafts; 
	}

	public void setDrafts(Folder value) { 
		drafts = value; 
	}

	@SerializedName("SentItems")
	@Expose
	private Folder sentItems;

	public Folder getSentItems() {
		 return sentItems; 
	}

	public void setSentItems(Folder value) { 
		sentItems = value; 
	}

	@SerializedName("DeletedItems")
	@Expose
	private Folder deletedItems;

	public Folder getDeletedItems() {
		 return deletedItems; 
	}

	public void setDeletedItems(Folder value) { 
		deletedItems = value; 
	}

	@SerializedName("Calendars")
	@Expose
	private java.util.List<Calendar> calendars;

	public java.util.List<Calendar> getCalendars() {
		 return calendars; 
	}

	public void setCalendars(java.util.List<Calendar> value) { 
		calendars = value; 
	}

	@SerializedName("Calendar")
	@Expose
	private Calendar calendar;

	public Calendar getCalendar() {
		 return calendar; 
	}

	public void setCalendar(Calendar value) { 
		calendar = value; 
	}

	@SerializedName("CalendarGroups")
	@Expose
	private java.util.List<CalendarGroup> calendarGroups;

	public java.util.List<CalendarGroup> getCalendarGroups() {
		 return calendarGroups; 
	}

	public void setCalendarGroups(java.util.List<CalendarGroup> value) { 
		calendarGroups = value; 
	}

	@SerializedName("Events")
	@Expose
	private java.util.List<Event> events;

	public java.util.List<Event> getEvents() {
		 return events; 
	}

	public void setEvents(java.util.List<Event> value) { 
		events = value; 
	}

	@SerializedName("Contacts")
	@Expose
	private java.util.List<Contact> contacts;

	public java.util.List<Contact> getContacts() {
		 return contacts; 
	}

	public void setContacts(java.util.List<Contact> value) { 
		contacts = value; 
	}

	@SerializedName("ContactFolders")
	@Expose
	private java.util.List<ContactFolder> contactFolders;

	public java.util.List<ContactFolder> getContactFolders() {
		 return contactFolders; 
	}

	public void setContactFolders(java.util.List<ContactFolder> value) { 
		contactFolders = value; 
	}

}