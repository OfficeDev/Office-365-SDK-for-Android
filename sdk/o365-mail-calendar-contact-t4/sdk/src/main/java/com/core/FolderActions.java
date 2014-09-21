
package com.core;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.microsoft.exchange.services.odata.model.*;

public class FolderActions{
	private String mId;
	private EntityContainer mEntityContainer;


	public FolderActions(String id){
		mId = id;
		mEntityContainer = EntityContainer.getEntityContainer();
	}


	public ListenableFuture<Folder> Copy(String destinationid){

		String url =  String.format("%s//Me//Folders('%s')//Copy", mEntityContainer.getUrl(),mId);

		BaseClient<Folder> client = new BaseClient<Folder>(mEntityContainer.getCredentials());
		

		JsonObject jObject = null;
		Gson gson = new Gson();
		jObject = new JsonObject();


		jObject.addProperty("DestinationId" , destinationid);

		return client.execute(url, gson.toJson(jObject), null, "POST");
	}


	public ListenableFuture<Folder> Move(String destinationid){

		String url =  String.format("%s//Me//Folders('%s')//Move", mEntityContainer.getUrl(),mId);

		BaseClient<Folder> client = new BaseClient<Folder>(mEntityContainer.getCredentials());
		

		JsonObject jObject = null;
		Gson gson = new Gson();
		jObject = new JsonObject();


		jObject.addProperty("DestinationId" , destinationid);

		return client.execute(url, gson.toJson(jObject), null, "POST");
	}


}