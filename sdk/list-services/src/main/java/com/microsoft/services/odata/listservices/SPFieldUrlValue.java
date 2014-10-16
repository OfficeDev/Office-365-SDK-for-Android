/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.odata.listservices;


import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class SPFieldUrlValue {
	public static JsonObject getJsonForUrl(String url, String description) {
		JsonObject json = new JsonObject();
		
		json.add("Description", new JsonPrimitive(description));
		json.add("Url", new JsonPrimitive(url));
		
		return json;
	}
}
