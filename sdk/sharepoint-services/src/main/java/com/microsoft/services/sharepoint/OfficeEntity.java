/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.sharepoint;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The Class OfficeEntity.
 */
public abstract class OfficeEntity {

	/** The m json data. */
	private JSONObject mJsonData;

	/**
	 * Gets the json data.
	 * 
	 * @return the json data
	 */
	protected JSONObject getJsonData() {
		return mJsonData;
	}

	/**
	 * List from json.
	 * 
	 * @param <E>
	 *            the element type
	 * @param json
	 *            the json
	 * @param clazz
	 *            the clazz
	 * @return the list
	 * @throws org.json.JSONException
	 *             the JSON exception
	 */
	protected static <E extends OfficeEntity> List<E> listFromJson(JSONObject json,
			Class<E> clazz) throws JSONException {
		List<E> list = new ArrayList<E>();

		JSONArray results;
		if (json.has("d")) {
			results = json.getJSONObject("d").getJSONArray("results");
		} else {
			results = json.getJSONArray("results");
		}

		for (int i = 0; i < results.length(); i++) {
			JSONObject result = results.getJSONObject(i);

			E item = null;
			try {
				item = clazz.newInstance();
			} catch (Throwable e) {
			}

			if (item != null) {
				item.loadFromJson(result);
				list.add(item);
			}
		}

		return list;
	}

	/**
	 * Load from json.
	 * 
	 * @param json
	 *            the json
	 */
	public void loadFromJson(JSONObject json) {
		mJsonData = json;
	}

	/**
	 * Load from json.
	 * 
	 * @param json
	 *            the json
	 * @param isPlainItem
	 *            the is plain item
	 */
	public void loadFromJson(JSONObject json, boolean isPlainItem) {
		if (isPlainItem) {
			loadFromJson(json);
		} else {
			JSONObject innerJson;
			try {
				innerJson = json.getJSONObject("d");
				loadFromJson(innerJson);
			} catch (JSONException e) {
				throw new IllegalArgumentException("Expected 'd' element", e);
			}
		}
	}

	/**
	 * Gets the data.
	 * 
	 * @param field
	 *            the field
	 * @return the data
	 */
	public Object getData(String field) {
		try {
			JSONObject innerJson;
			if (mJsonData.has("d")) {
				innerJson = mJsonData.getJSONObject("d");
				return innerJson.get(field);
			} else {
				return mJsonData.get(field);
			}
		} catch (JSONException e) {
			throw new IllegalArgumentException("Invalid field name " + field, e);
		}
	}

	@Override
	public String toString() {
		if (mJsonData != null) {
			return mJsonData.toString();
		}
		return super.toString();
	}
}
