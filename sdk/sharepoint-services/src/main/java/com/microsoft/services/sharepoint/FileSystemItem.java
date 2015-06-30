/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.sharepoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * The Class FileSystemItem.
 */
public class FileSystemItem extends OfficeEntity {

	/** The m values. */
	private Map<String, Object> mValues = new HashMap<String, Object>();

	public void setData(String key, Object data) {
		mValues.put(key, data);
	}

	/**
	 * Gets the values.
	 * 
	 * @return the values
	 */
	Map<String, Object> getValues() {
		return new HashMap<String, Object>(mValues);
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public int getId() {
		return (Integer) getData("Id");
	}

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getName() {
		return getData("Name").toString();
	}

	/**
	 * Gets the sub items.
	 * 
	 * @param field
	 *            the field
	 * @return the sub items
	 */
	public List<FileSystemItem> getSubItems(String field) {
		JSONObject subItemsJson = (JSONObject) getData(field);

		try {
			return OfficeEntity.listFromJson(subItemsJson, FileSystemItem.class);
		} catch (JSONException e) {
			throw new IllegalArgumentException("Cant get sub items from field " + field, e);
		}
	}

	/**
	 * List from.
	 * 
	 * @param json
	 *            the json
	 * @return the list
	 * @throws org.json.JSONException
	 *             the JSON exception
	 */
	public static List<FileSystemItem> listFrom(JSONObject json) throws Throwable {
		return OfficeEntity.listFromJson(json, FileSystemItem.class);
	}

	@Override
	public Object getData(String field) {
		if (mValues.containsKey(field)) {
			return mValues.get(field);
		} else {
			return super.getData(field);
		}
	}

}
