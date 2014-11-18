/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type User.
*/
public class User extends Entity {

    public User(){
        setODataType("#Microsoft.OutlookServices.User");
    }

    
    private java.util.List<Folder> Folders = new java.util.ArrayList<Folder>();

    /**
    * Gets the Folders.
    *
    * @return the Folders
    */
    public java.util.List<Folder> getFolders() {
        return this.Folders; 
    }
        
    private java.util.List<Message> Messages = new java.util.ArrayList<Message>();

    /**
    * Gets the Messages.
    *
    * @return the Messages
    */
    public java.util.List<Message> getMessages() {
        return this.Messages; 
    }
        
    private Folder RootFolder = null;

    /**
    * Gets the Root Folder.
    *
    * @return the RootFolder
    */
    public Folder getRootFolder() {
        return this.RootFolder; 
    }
    
    /**
    * Sets the Root Folder.
    *
    * @param value the Folder
    */
    public void setRootFolder(Folder value) { 
        this.RootFolder = value; 
    }
        
    private java.util.List<Calendar> Calendars = new java.util.ArrayList<Calendar>();

    /**
    * Gets the Calendars.
    *
    * @return the Calendars
    */
    public java.util.List<Calendar> getCalendars() {
        return this.Calendars; 
    }
        
    private Calendar Calendar = null;

    /**
    * Gets the Calendar.
    *
    * @return the Calendar
    */
    public Calendar getCalendar() {
        return this.Calendar; 
    }
    
    /**
    * Sets the Calendar.
    *
    * @param value the Calendar
    */
    public void setCalendar(Calendar value) { 
        this.Calendar = value; 
    }
        
    private java.util.List<CalendarGroup> CalendarGroups = new java.util.ArrayList<CalendarGroup>();

    /**
    * Gets the Calendar Groups.
    *
    * @return the CalendarGroups
    */
    public java.util.List<CalendarGroup> getCalendarGroups() {
        return this.CalendarGroups; 
    }
        
    private java.util.List<Event> Events = new java.util.ArrayList<Event>();

    /**
    * Gets the Events.
    *
    * @return the Events
    */
    public java.util.List<Event> getEvents() {
        return this.Events; 
    }
        
    private java.util.List<Event> CalendarView = new java.util.ArrayList<Event>();

    /**
    * Gets the Calendar View.
    *
    * @return the CalendarView
    */
    public java.util.List<Event> getCalendarView() {
        return this.CalendarView; 
    }
        
    private java.util.List<Contact> Contacts = new java.util.ArrayList<Contact>();

    /**
    * Gets the Contacts.
    *
    * @return the Contacts
    */
    public java.util.List<Contact> getContacts() {
        return this.Contacts; 
    }
        
    private java.util.List<ContactFolder> ContactFolders = new java.util.ArrayList<ContactFolder>();

    /**
    * Gets the Contact Folders.
    *
    * @return the ContactFolders
    */
    public java.util.List<ContactFolder> getContactFolders() {
        return this.ContactFolders; 
    }
            
	private String DisplayName;

    /**
    * Gets the Display Name.
    *
    * @return the String
    */
    public String getDisplayName() {
        return this.DisplayName; 
    }

    /**
    * Sets the Display Name.
    *
    * @param value the String
    */
    public void setDisplayName(String value) { 
        this.DisplayName = value; 
    }
    
	private String Alias;

    /**
    * Gets the Alias.
    *
    * @return the String
    */
    public String getAlias() {
        return this.Alias; 
    }

    /**
    * Sets the Alias.
    *
    * @param value the String
    */
    public void setAlias(String value) { 
        this.Alias = value; 
    }
    
	private java.util.UUID MailboxGuid;

    /**
    * Gets the Mailbox Guid.
    *
    * @return the java.util.UUID
    */
    public java.util.UUID getMailboxGuid() {
        return this.MailboxGuid; 
    }

    /**
    * Sets the Mailbox Guid.
    *
    * @param value the java.util.UUID
    */
    public void setMailboxGuid(java.util.UUID value) { 
        this.MailboxGuid = value; 
    }
}