/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.listservices;

import org.json.JSONException;
import org.json.JSONObject;

public class SPFieldUrlValue {
	public static JSONObject getJsonForUrl(String url, String description) throws JSONException {
		JSONObject json = new JSONObject();
		
		json.put("Description", description);
		json.put("Url", url);
		
		return json;
	}
}
