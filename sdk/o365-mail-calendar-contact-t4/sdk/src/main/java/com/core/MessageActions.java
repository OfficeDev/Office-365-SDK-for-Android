
package com.core;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.microsoft.exchange.services.odata.model.*;

public class MessageActions{
	private String mId;
	private EntityContainer mEntityContainer;


	public MessageActions(String id){
		mId = id;
		mEntityContainer = EntityContainer.getEntityContainer();
	}


	public ListenableFuture<Message> Copy(String destinationid){

		String url =  String.format("%s//Me//Messages('%s')//Copy", mEntityContainer.getUrl(),mId);

		BaseClient<Message> client = new BaseClient<Message>(mEntityContainer.getCredentials());
		

		JsonObject jObject = null;
		Gson gson = new Gson();
		jObject = new JsonObject();


		jObject.addProperty("DestinationId" , destinationid);

		return client.execute(url, gson.toJson(jObject), null, "POST");
	}


	public ListenableFuture<Message> Move(String destinationid){

		String url =  String.format("%s//Me//Messages('%s')//Move", mEntityContainer.getUrl(),mId);

		BaseClient<Message> client = new BaseClient<Message>(mEntityContainer.getCredentials());
		

		JsonObject jObject = null;
		Gson gson = new Gson();
		jObject = new JsonObject();


		jObject.addProperty("DestinationId" , destinationid);

		return client.execute(url, gson.toJson(jObject), null, "POST");
	}


	public ListenableFuture<Message> CreateReply(){

		String url =  String.format("%s//Me//Messages('%s')//CreateReply", mEntityContainer.getUrl(),mId);

		BaseClient<Message> client = new BaseClient<Message>(mEntityContainer.getCredentials());
		

		return client.execute(url, null, null, "POST");
	}


	public ListenableFuture<Message> CreateReplyAll(){

		String url =  String.format("%s//Me//Messages('%s')//CreateReplyAll", mEntityContainer.getUrl(),mId);

		BaseClient<Message> client = new BaseClient<Message>(mEntityContainer.getCredentials());
		

		return client.execute(url, null, null, "POST");
	}


	public ListenableFuture<Message> CreateForward(){

		String url =  String.format("%s//Me//Messages('%s')//CreateForward", mEntityContainer.getUrl(),mId);

		BaseClient<Message> client = new BaseClient<Message>(mEntityContainer.getCredentials());
		

		return client.execute(url, null, null, "POST");
	}


	public ListenableFuture<Integer> Reply(String comment){

		String url =  String.format("%s//Me//Messages('%s')//Reply", mEntityContainer.getUrl(),mId);

		BaseClient<Integer> client = new BaseClient<Integer>(mEntityContainer.getCredentials());
		

		JsonObject jObject = null;
		Gson gson = new Gson();
		jObject = new JsonObject();


		jObject.addProperty("Comment" , comment);

		return client.execute(url, gson.toJson(jObject), null, "POST");
	}


	public ListenableFuture<Integer> ReplyAll(String comment){

		String url =  String.format("%s//Me//Messages('%s')//ReplyAll", mEntityContainer.getUrl(),mId);

		BaseClient<Integer> client = new BaseClient<Integer>(mEntityContainer.getCredentials());
		

		JsonObject jObject = null;
		Gson gson = new Gson();
		jObject = new JsonObject();


		jObject.addProperty("Comment" , comment);

		return client.execute(url, gson.toJson(jObject), null, "POST");
	}


	public ListenableFuture<Integer> Forward(String comment, java.util.List<Recipient> torecipients){

		String url =  String.format("%s//Me//Messages('%s')//Forward", mEntityContainer.getUrl(),mId);

		BaseClient<Integer> client = new BaseClient<Integer>(mEntityContainer.getCredentials());
		

		JsonObject jObject = null;
		Gson gson = new Gson();
		jObject = new JsonObject();


		jObject.addProperty("Comment" , comment);

		jObject.add("ToRecipients" , gson.toJsonTree(torecipients));

		return client.execute(url, gson.toJson(jObject), null, "POST");
	}


	public ListenableFuture<Integer> Send(){

		String url =  String.format("%s//Me//Messages('%s')//Send", mEntityContainer.getUrl(),mId);

		BaseClient<Integer> client = new BaseClient<Integer>(mEntityContainer.getCredentials());
		

		return client.execute(url, null, null, "POST");
	}


}