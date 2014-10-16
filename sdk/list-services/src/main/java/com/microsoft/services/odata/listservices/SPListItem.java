/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.odata.listservices;

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


// TODO: Auto-generated Javadoc

/**
 * The Class SPListItem.
 */
public class SPListItem extends OfficeEntity {

    /**
     * The m values.
     */
    private Map<String, Object> mValues;

    /**
     * List from json.
     *
     * @param json the json
     * @return the list
     */
    public static List<SPListItem> listFromJson(JsonObject json) {
        return OfficeEntity.listFromJson(json, SPListItem.class);
    }

    /**
     * Instantiates a new SP list item.
     */
    public SPListItem() {
        mValues = new HashMap<String, Object>();
    }

    /**
     * Adds data to the item.
     *
     * @param key  the key
     * @param data the data
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
     * @param field the field
     * @return the sub items
     */
    public List<SPListItem> getSubItems(String field) {
        JsonObject subItemsJson = (JsonObject) getData(field);
        return OfficeEntity.listFromJson(subItemsJson, SPListItem.class);
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
