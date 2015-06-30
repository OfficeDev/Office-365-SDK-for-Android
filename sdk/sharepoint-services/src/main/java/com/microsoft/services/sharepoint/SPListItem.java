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

// TODO: Auto-generated Javadoc
/**
 * The Class SPListItem.
 */
public class SPListItem extends OfficeEntity {

	/** The m values. */
	private Map<String, Object> mValues;
	
	/**
	 * List from json.
	 * 
	 * @param json
	 *            the json
	 * @return the list
	 * @throws org.json.JSONException
	 *             the JSON exception
	 */
	public static List<SPListItem> listFromJson(JSONObject json) throws JSONException {
		return listFromJson(json, SPListItem.class);
	}
	
	/**
	 * Instantiates a new SP list item.
	 */
	public SPListItem()  {
		mValues = new HashMap<String, Object>();
	}
	
	/**
	 * Adds data to the item.
	 * 
	 * @param key
	 *            the key
	 * @param data
	 *            the data
	 */
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
	public String getTitle() {
		return getData("Title").toString();
	}
	
	/**
	 * Gets the guid.
	 * 
	 * @return the guid
	 */
	public String getGUID() {
		return getData("GUID").toString();
	}
	
	/**
	 * Gets the sub items.
	 * 
	 * @param field
	 *            the field
	 * @return the sub items
	 */
	public List<SPListItem> getSubItems(String field) {
		JSONObject subItemsJson = (JSONObject) getData(field);
		
		try {
			return listFromJson(subItemsJson, SPListItem.class);
		} catch (JSONException e) {
			throw new IllegalArgumentException("Cant get sub items from field " + field, e);
		}
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
