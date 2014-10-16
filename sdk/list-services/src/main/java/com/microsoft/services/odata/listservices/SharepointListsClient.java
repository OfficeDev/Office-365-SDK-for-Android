/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.odata.listservices;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.microsoft.services.odata.Constants;
import com.microsoft.services.odata.interfaces.DependencyResolver;
import com.microsoft.services.odata.interfaces.HttpVerb;
import com.microsoft.services.odata.interfaces.Logger;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The Class SharepointListsClient.
 */
public class SharepointListsClient extends SharepointClient {


    /**
     * Instantiates a new Sharepoint lists client.
     *
     * @param serverUrl       the server url
     * @param siteRelativeUrl the site relative url
     * @param resolver        the resolver
     */
    public SharepointListsClient(String serverUrl, String siteRelativeUrl,
                                 DependencyResolver resolver) {
        super(serverUrl, siteRelativeUrl, resolver);
    }


    /**
     * Instantiates a new Sharepoint lists client.
     *
     * @param serverUrl       the server url
     * @param siteRelativeUrl the site relative url
     * @param resolver        the resolver
     * @param logger          the logger
     */
    public SharepointListsClient(String serverUrl, String siteRelativeUrl,
                                 DependencyResolver resolver, Logger logger) {
        super(serverUrl, siteRelativeUrl, resolver, logger);
    }

    /**
     * Gets the lists.
     *
     * @param query the query
     * @return the lists
     */
    public ListenableFuture<List<SPList>> getLists(Query query) {
        final SettableFuture<List<SPList>> result = SettableFuture.create();

        String queryOData = generateODataQueryString(query);
        String getListsUrl = getSiteUrl() + "_api/web/lists/?" + queryEncode(queryOData);
        ListenableFuture<JsonObject> request = executeRequestJson(getListsUrl, HttpVerb.GET);

        Futures.addCallback(request, new FutureCallback<JsonObject>() {
            @Override
            public void onFailure(Throwable t) {
                result.setException(t);
            }

            @Override
            public void onSuccess(JsonObject json) {
                List<SPList> list = SPList.listFromJson(json);
                result.set(list);
            }
        });

        return result;
    }

    /**
     * Gets the list.
     *
     * @param listName the list name
     * @return the list
     */
    public ListenableFuture<SPList> getList(String listName) {
        final SettableFuture<SPList> result = SettableFuture.create();

        String getListUrl = getSiteUrl() + "_api/web/lists/GetByTitle('%s')";
        getListUrl = String.format(getListUrl, urlEncode(listName));
        ListenableFuture<JsonObject> request = executeRequestJson(getListUrl, HttpVerb.GET);

        Futures.addCallback(request, new FutureCallback<JsonObject>() {
            @Override
            public void onFailure(Throwable t) {
                result.setException(t);
            }

            @Override
            public void onSuccess(JsonObject json) {
                SPList list = new SPList();
                list.loadFromJson(json, true);
                result.set(list);
            }
        });

        return result;
    }

    /**
     * Gets the list items.
     *
     * @param listName the list name
     * @param query    the query
     * @return the list items
     */
    public ListenableFuture<List<SPListItem>> getListItems(String listName, Query query) {
        final SettableFuture<List<SPListItem>> result = SettableFuture.create();

        String listNamePart = String.format("_api/web/lists/GetByTitle('%s')/Items?", urlEncode(listName));
        String getListUrl = getSiteUrl() + listNamePart + generateODataQueryString(query);
        ListenableFuture<JsonObject> request = executeRequestJson(getListUrl, HttpVerb.GET);

        Futures.addCallback(request, new FutureCallback<JsonObject>() {
            @Override
            public void onFailure(Throwable t) {
                result.setException(t);
            }

            @Override
            public void onSuccess(JsonObject json) {
                result.set(SPListItem.listFromJson(json));
            }
        });

        return result;
    }

    /**
     * Gets the list fields.
     *
     * @param listName the list name
     * @param query    the query
     * @return the list fields
     */
    public ListenableFuture<List<SPListField>> getListFields(String listName, Query query) {
        final SettableFuture<List<SPListField>> result = SettableFuture.create();

        String getListUrl = getSiteUrl() + "_api/web/lists/GetByTitle('%s')/Fields?" + generateODataQueryString(query);
        getListUrl = String.format(getListUrl, urlEncode(listName));
        ListenableFuture<JsonObject> request = executeRequestJson(getListUrl, HttpVerb.GET);

        Futures.addCallback(request, new FutureCallback<JsonObject>() {
            @Override
            public void onFailure(Throwable t) {
                result.setException(t);
            }

            @Override
            public void onSuccess(JsonObject json) {
                result.set(SPListField.listFromJson(json));
            }
        });

        return result;
    }

    /**
     * Insert list item.
     *
     * @param listItem the list item
     * @param list     the list
     * @return the office future
     */
    public ListenableFuture<Void> insertListItem(final SPListItem listItem, final SPList list) {
        final SettableFuture<Void> result = SettableFuture.create();

        String getListUrl = getSiteUrl() + "_api/web/lists/GetByTitle('%s')/Items";
        getListUrl = String.format(getListUrl, urlEncode(list.getTitle()));

        try {
            JsonObject payload = new JsonObject();
            JsonObject metadata = new JsonObject();
            //metadata.add("type", list.getListItemEntityTypeFullName());
            payload.add("__metadata", metadata);

            for (String key : listItem.getValues().keySet()) {

                Object object = listItem.getValues().get(key);
                // we assume you're trying to store a value on a linked
                // sharepoint list
                if (object instanceof JsonArray) {
                    JsonObject container = new JsonObject();
                    //container.add("results", object);
                    payload.add(key + "Id", container);
                } else {
                    //payload.add(key, object);
                }
            }

            ListenableFuture<JsonObject> request = executeRequestJsonWithDigest(getListUrl, HttpVerb.POST, null,
                    getBytes(payload.toString()));

            Futures.addCallback(request, new FutureCallback<JsonObject>() {
                @Override
                public void onFailure(Throwable t) {
                    result.setException(t);
                }

                @Override
                public void onSuccess(JsonObject json) {
                    result.set(null);
                }
            });
        } catch (Throwable t) {
            result.setException(t);
        }

        return result;
    }

    /**
     * Update list item.
     *
     * @param listItem the list item
     * @param list     the list
     * @return the office future
     */
    public ListenableFuture<Void> updateListItem(final SPListItem listItem, final SPList list) {
        final SettableFuture<Void> result = SettableFuture.create();

        String getListUrl = getSiteUrl() + "_api/web/lists/GetByTitle('%s')/items(" + listItem.getId() + ")";
        getListUrl = String.format(getListUrl, urlEncode(list.getTitle()));

        JsonObject payload = new JsonObject();
        JsonObject metadata = new JsonObject();
        //metadata.put("type", list.getListItemEntityTypeFullName());
        //payload.put("__metadata", metadata);

        for (String key : listItem.getValues().keySet()) {
            Object object = listItem.getValues().get(key);
            // we assume you're trying to store a value on a linked
            // sharepoint list
            if (object instanceof JsonArray) {
                JsonObject container = new JsonObject();
                //container.put("results", object);
                //payload.put(key + "Id", container);
            } else {
                //payload.put(key, object);
            }
        }

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("X-HTTP-Method", "MERGE");
        headers.put("If-Match", "*");

        ListenableFuture<JsonObject> request = executeRequestJsonWithDigest(getListUrl, HttpVerb.POST, headers,
                getBytes(payload.toString()));

        Futures.addCallback(request, new FutureCallback<JsonObject>() {
            @Override
            public void onFailure(Throwable t) {
                result.setException(t);
            }

            @Override
            public void onSuccess(JsonObject json) {
                result.set(null);
            }
        });
        return result;
    }

    /**
     * Delete list item.
     *
     * @param listItem the list item
     * @return the office future
     */
    public ListenableFuture<Void> deleteListItem(final SPListItem listItem, final String listName) {
        final SettableFuture<Void> result = SettableFuture.create();

        String getListUrl = getSiteUrl() + "_api/web/lists/GetByTitle('%s')/items(" + listItem.getId() + ")";
        getListUrl = String.format(getListUrl, urlEncode(listName));

        try {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("X-HTTP-Method", "DELETE");
            headers.put("If-Match", "*");

            ListenableFuture<JsonObject> request = executeRequestJsonWithDigest(getListUrl, HttpVerb.POST, headers, null);

            Futures.addCallback(request, new FutureCallback<JsonObject>() {
                @Override
                public void onFailure(Throwable t) {
                    result.setException(t);
                }

                @Override
                public void onSuccess(JsonObject json) {
                    result.set(null);
                }
            });

        } catch (Throwable t) {
            result.setException(t);
        }

        return result;
    }

    public ListenableFuture<List<String>> getColumnsFromDefaultView(final String listName) {
        final SettableFuture<List<String>> result = SettableFuture.create();

        String getViewUrl = getSiteUrl()
                + String.format("_api/web/lists/GetByTitle('%s')/defaultView/viewfields", urlEncode(listName));
        ListenableFuture<JsonObject> request = executeRequestJson(getViewUrl, HttpVerb.GET);

        Futures.addCallback(request, new FutureCallback<JsonObject>() {
            @Override
            public void onFailure(Throwable t) {
                result.setException(t);
            }

            @Override
            public void onSuccess(JsonObject json) {
                JsonObject container = json.getAsJsonObject("d");
                ArrayList<String> columnNames = new ArrayList<String>();
                //TODO:FIX
                /*
                JsonArray results = container.getJSONObject("Items").getJSONArray("results");

                for (int i = 0; i < results.length(); i++) {
                    columnNames.add(results.get(i).toString());
                }
                */
                result.set(columnNames);
            }
        });
        return result;
    }

    public ListenableFuture<String> getUserProperties() {
        final SettableFuture<String> result = SettableFuture.create();

        String url = getSiteUrl() + "/_api/SP.UserProfiles.PeopleManager/GetMyProperties";
        ListenableFuture<JsonObject> request = executeRequestJson(url, HttpVerb.GET);

        Futures.addCallback(request, new FutureCallback<JsonObject>() {
            @Override
            public void onFailure(Throwable t) {
                result.setException(t);
            }

            @Override
            public void onSuccess(JsonObject json) {
                result.set(json.toString());
            }
        });
        return result;
    }

    /**
     * Gets the bytes from a given string.
     *
     * @param s the s
     * @return the bytes
     */
    private byte[] getBytes(String s) {
        try {
            return s.getBytes(Constants.UTF8_NAME);
        } catch (UnsupportedEncodingException e) {
            return s.getBytes();
        }
    }
}
