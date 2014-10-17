/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.listservices;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class OfficeEntity.
 */
public abstract class OfficeEntity {

    /**
     * The m json data.
     */
    private JsonObject mJsonData;

    /**
     * Gets the json data.
     *
     * @return the json data
     */
    protected JsonObject getJsonData() {
        return mJsonData;
    }

    /**
     * List from json.
     *
     * @param <E>   the element type
     * @param json  the json
     * @param clazz the clazz
     * @return the list
     */
    protected static <E extends OfficeEntity> List<E> listFromJson(JsonObject json,
                                                                   Class<E> clazz) {
        List<E> list = new ArrayList<E>();

        JsonArray results;
        if (json.has("d")) {
            results = json.get("d").getAsJsonObject().getAsJsonArray("results");
        } else {
            results = json.getAsJsonArray("results");
        }

        for (int i = 0; i < results.size(); i++) {
            //TODO:REVIEW
            JsonObject result = (JsonObject) results.get(i);
            E item = null;
            try {
                item = (E) clazz.newInstance();
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
     * @param json the json
     */
    public void loadFromJson(JsonObject json) {
        mJsonData = json;
    }

    /**
     * Load from json.
     *
     * @param json        the json
     * @param isPlainItem the is plain item
     */
    public void loadFromJson(JsonObject json, boolean isPlainItem) {
        if (isPlainItem) {
            loadFromJson(json);
        } else {
            JsonObject innerJson;
            innerJson = json.getAsJsonObject("d");
            loadFromJson(innerJson);
        }
    }

    /**
     * Gets the data.
     *
     * @param field the field
     * @return the data
     */
    public Object getData(String field) {
        JsonObject innerJson = null;
        if (mJsonData.has("d")) {
            innerJson = mJsonData.getAsJsonObject("d");
            return innerJson.get(field);
        } else {
            return mJsonData.get(field);
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
