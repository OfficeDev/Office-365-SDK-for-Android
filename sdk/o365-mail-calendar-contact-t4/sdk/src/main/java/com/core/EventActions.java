
package com.core;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.microsoft.exchange.services.odata.model.*;

public class EventActions{
	private String mId;
	private EntityContainer mEntityContainer;


	public EventActions(String id){
		mId = id;
		mEntityContainer = EntityContainer.getEntityContainer();
	}


	public ListenableFuture<Integer> Accept(String comment){

		String url =  String.format("%s//Me//Events('%s')//Accept", mEntityContainer.getUrl(),mId);

		BaseClient<Integer> client = new BaseClient<Integer>(mEntityContainer.getCredentials());
		

		JsonObject jObject = null;
		Gson gson = new Gson();
		jObject = new JsonObject();


		jObject.addProperty("Comment" , comment);

		return client.execute(url, gson.toJson(jObject), null, "POST");
	}


	public ListenableFuture<Integer> Decline(String comment){

		String url =  String.format("%s//Me//Events('%s')//Decline", mEntityContainer.getUrl(),mId);

		BaseClient<Integer> client = new BaseClient<Integer>(mEntityContainer.getCredentials());
		

		JsonObject jObject = null;
		Gson gson = new Gson();
		jObject = new JsonObject();


		jObject.addProperty("Comment" , comment);

		return client.execute(url, gson.toJson(jObject), null, "POST");
	}


	public ListenableFuture<Integer> TentativelyAccept(String comment){

		String url =  String.format("%s//Me//Events('%s')//TentativelyAccept", mEntityContainer.getUrl(),mId);

		BaseClient<Integer> client = new BaseClient<Integer>(mEntityContainer.getCredentials());
		

		JsonObject jObject = null;
		Gson gson = new Gson();
		jObject = new JsonObject();


		jObject.addProperty("Comment" , comment);

		return client.execute(url, gson.toJson(jObject), null, "POST");
	}


}