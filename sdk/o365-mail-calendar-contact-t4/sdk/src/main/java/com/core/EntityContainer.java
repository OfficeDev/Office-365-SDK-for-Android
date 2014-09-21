
package com.core;


import com.microsoft.exchange.services.odata.model.*;
import com.microsoft.office365.Credentials;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;

public class EntityContainer{

	private String mUrl;
	private Credentials mCredentials;
	private static EntityContainer mEntityContainer;
	
	public static EntityContainer getEntityContainer(){
		if(mEntityContainer == null){
			//TODO Throw initialization exception
		}
		
		return mEntityContainer;
	}

	public Credentials getCredentials(){
		return mCredentials;
	}

	public String getUrl(){
		return mUrl;
	}

	public EntityContainer(String url, Credentials credentials){
		
		this.mCredentials = credentials;
		this.mUrl = url;
		mEntityContainer = this;
	}


	public ListenableFuture<Folder> createFolder (Folder folder, String path){
		String url = mUrl + "/" + "Me";
		url += (path != null ? "/" + path + "/" : "/") + "Folders";
		
		BaseClient<Folder> client = new BaseClient<Folder>(mCredentials);
		Gson gson = new Gson();
		
		String json = gson.toJson(folder);

		return client.execute(url, json,  Folder.class, "POST");
	}

	public ListenableFuture<Folder> getFolderById (Folder folder,String path){
		return null;
	}

	public ListenableFuture<java.util.List<Folder>> getFolders (String path){
		String url = mUrl + "/" + "Me";
		url += (path != null ? "/" + path + "/" : "/") + "Folders";
		
		BaseClient<Folder> client = new BaseClient<Folder>(mCredentials);
		
		return client.getList(url, Folder[].class);
	}

	public ListenableFuture<Folder> updateFolder (Folder folder,String path){
		return null;
	}

	public ListenableFuture<Integer> deleteFolder (Folder folder,String path){
		return null;
	}


	public ListenableFuture<Message> createMessage (Message message, String path){
		String url = mUrl + "/" + "Me";
		url += (path != null ? "/" + path + "/" : "/") + "Messages";
		
		BaseClient<Message> client = new BaseClient<Message>(mCredentials);
		Gson gson = new Gson();
		
		String json = gson.toJson(message);

		return client.execute(url, json,  Message.class, "POST");
	}

	public ListenableFuture<Message> getMessageById (Message message,String path){
		return null;
	}

	public ListenableFuture<java.util.List<Message>> getMessages (String path){
		String url = mUrl + "/" + "Me";
		url += (path != null ? "/" + path + "/" : "/") + "Messages";
		
		BaseClient<Message> client = new BaseClient<Message>(mCredentials);
		
		return client.getList(url, Message[].class);
	}

	public ListenableFuture<Message> updateMessage (Message message,String path){
		return null;
	}

	public ListenableFuture<Integer> deleteMessage (Message message,String path){
		return null;
	}


	public ListenableFuture<Folder> getRootFolder (String path){
		return null;
	}


	public ListenableFuture<Folder> getInbox (String path){
		return null;
	}


	public ListenableFuture<Folder> getDrafts (String path){
		return null;
	}


	public ListenableFuture<Folder> getSentItems (String path){
		return null;
	}


	public ListenableFuture<Folder> getDeletedItems (String path){
		return null;
	}


	public ListenableFuture<Calendar> createCalendar (Calendar calendar, String path){
		String url = mUrl + "/" + "Me";
		url += (path != null ? "/" + path + "/" : "/") + "Calendars";
		
		BaseClient<Calendar> client = new BaseClient<Calendar>(mCredentials);
		Gson gson = new Gson();
		
		String json = gson.toJson(calendar);

		return client.execute(url, json,  Calendar.class, "POST");
	}

	public ListenableFuture<Calendar> getCalendarById (Calendar calendar,String path){
		return null;
	}

	public ListenableFuture<java.util.List<Calendar>> getCalendars (String path){
		String url = mUrl + "/" + "Me";
		url += (path != null ? "/" + path + "/" : "/") + "Calendars";
		
		BaseClient<Calendar> client = new BaseClient<Calendar>(mCredentials);
		
		return client.getList(url, Calendar[].class);
	}

	public ListenableFuture<Calendar> updateCalendar (Calendar calendar,String path){
		return null;
	}

	public ListenableFuture<Integer> deleteCalendar (Calendar calendar,String path){
		return null;
	}


	public ListenableFuture<Calendar> getCalendar (String path){
		return null;
	}


	public ListenableFuture<CalendarGroup> createCalendarGroup (CalendarGroup calendargroup, String path){
		String url = mUrl + "/" + "Me";
		url += (path != null ? "/" + path + "/" : "/") + "CalendarGroups";
		
		BaseClient<CalendarGroup> client = new BaseClient<CalendarGroup>(mCredentials);
		Gson gson = new Gson();
		
		String json = gson.toJson(calendargroup);

		return client.execute(url, json,  CalendarGroup.class, "POST");
	}

	public ListenableFuture<CalendarGroup> getCalendarGroupById (CalendarGroup calendargroup,String path){
		return null;
	}

	public ListenableFuture<java.util.List<CalendarGroup>> getCalendarGroups (String path){
		String url = mUrl + "/" + "Me";
		url += (path != null ? "/" + path + "/" : "/") + "CalendarGroups";
		
		BaseClient<CalendarGroup> client = new BaseClient<CalendarGroup>(mCredentials);
		
		return client.getList(url, CalendarGroup[].class);
	}

	public ListenableFuture<CalendarGroup> updateCalendarGroup (CalendarGroup calendargroup,String path){
		return null;
	}

	public ListenableFuture<Integer> deleteCalendarGroup (CalendarGroup calendargroup,String path){
		return null;
	}


	public ListenableFuture<Event> createEvent (Event event, String path){
		String url = mUrl + "/" + "Me";
		url += (path != null ? "/" + path + "/" : "/") + "Events";
		
		BaseClient<Event> client = new BaseClient<Event>(mCredentials);
		Gson gson = new Gson();
		
		String json = gson.toJson(event);

		return client.execute(url, json,  Event.class, "POST");
	}

	public ListenableFuture<Event> getEventById (Event event,String path){
		return null;
	}

	public ListenableFuture<java.util.List<Event>> getEvents (String path){
		String url = mUrl + "/" + "Me";
		url += (path != null ? "/" + path + "/" : "/") + "Events";
		
		BaseClient<Event> client = new BaseClient<Event>(mCredentials);
		
		return client.getList(url, Event[].class);
	}

	public ListenableFuture<Event> updateEvent (Event event,String path){
		return null;
	}

	public ListenableFuture<Integer> deleteEvent (Event event,String path){
		return null;
	}


	public ListenableFuture<Contact> createContact (Contact contact, String path){
		String url = mUrl + "/" + "Me";
		url += (path != null ? "/" + path + "/" : "/") + "Contacts";
		
		BaseClient<Contact> client = new BaseClient<Contact>(mCredentials);
		Gson gson = new Gson();
		
		String json = gson.toJson(contact);

		return client.execute(url, json,  Contact.class, "POST");
	}

	public ListenableFuture<Contact> getContactById (Contact contact,String path){
		return null;
	}

	public ListenableFuture<java.util.List<Contact>> getContacts (String path){
		String url = mUrl + "/" + "Me";
		url += (path != null ? "/" + path + "/" : "/") + "Contacts";
		
		BaseClient<Contact> client = new BaseClient<Contact>(mCredentials);
		
		return client.getList(url, Contact[].class);
	}

	public ListenableFuture<Contact> updateContact (Contact contact,String path){
		return null;
	}

	public ListenableFuture<Integer> deleteContact (Contact contact,String path){
		return null;
	}


	public ListenableFuture<ContactFolder> createContactFolder (ContactFolder contactfolder, String path){
		String url = mUrl + "/" + "Me";
		url += (path != null ? "/" + path + "/" : "/") + "ContactFolders";
		
		BaseClient<ContactFolder> client = new BaseClient<ContactFolder>(mCredentials);
		Gson gson = new Gson();
		
		String json = gson.toJson(contactfolder);

		return client.execute(url, json,  ContactFolder.class, "POST");
	}

	public ListenableFuture<ContactFolder> getContactFolderById (ContactFolder contactfolder,String path){
		return null;
	}

	public ListenableFuture<java.util.List<ContactFolder>> getContactFolders (String path){
		String url = mUrl + "/" + "Me";
		url += (path != null ? "/" + path + "/" : "/") + "ContactFolders";
		
		BaseClient<ContactFolder> client = new BaseClient<ContactFolder>(mCredentials);
		
		return client.getList(url, ContactFolder[].class);
	}

	public ListenableFuture<ContactFolder> updateContactFolder (ContactFolder contactfolder,String path){
		return null;
	}

	public ListenableFuture<Integer> deleteContactFolder (ContactFolder contactfolder,String path){
		return null;
	}


}