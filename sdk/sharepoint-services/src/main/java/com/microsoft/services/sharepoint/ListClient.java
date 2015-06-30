/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.sharepoint;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

/**
 * The Class ListClient.
 */
public class ListClient extends SharePointClient {

    /**
     * Instantiates a new sharepoint lists client.
     *
     * @param serverUrl the server url
     * @param siteRelativeUrl the site relative url
     * @param credentials the credentials
     */
	public ListClient(String serverUrl, String siteRelativeUrl, Credentials credentials) {
		super(serverUrl, siteRelativeUrl, credentials);
	}

    /**
     * Instantiates a new sharepoint lists client.
     *
     * @param serverUrl the server url
     * @param siteRelativeUrl the site relative url
     * @param credentials the credentials
     * @param logger the logger
     */
	public ListClient(String serverUrl, String siteRelativeUrl, Credentials credentials, Logger logger) {
		super(serverUrl, siteRelativeUrl, credentials, logger);
	}


    /**
     * Gets lists.
     *
     * @param query the query
     * @return the lists
     */
    public ListenableFuture<List<SPList>> getLists(Query query) {
		final SettableFuture<List<SPList>> result = SettableFuture.create();

		String queryOData = generateODataQueryString(query);
		String getListsUrl = getSiteUrl() + "_api/web/lists/?" + queryEncode(queryOData);

		ListenableFuture<JSONObject> request = executeRequestJson(getListsUrl, "GET");

		Futures.addCallback(request, new FutureCallback<JSONObject>() {
			@Override
			public void onFailure(Throwable t) {
				result.setException(t);
			}

			@Override
			public void onSuccess(JSONObject json) {
				try {
					List<SPList> list = SPList.listFromJson(json);
					result.set(list);
				} catch (JSONException e) {
					log(e);
				}
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
		ListenableFuture<JSONObject> request = executeRequestJson(getListUrl, "GET");

		Futures.addCallback(request, new FutureCallback<JSONObject>() {
			@Override
			public void onFailure(Throwable t) {
				result.setException(t);
			}

			@Override
			public void onSuccess(JSONObject json) {
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
     * @param query the query
     * @return the list items
     */
	public ListenableFuture<List<SPListItem>> getListItems(String listName, Query query) {
		final SettableFuture<List<SPListItem>> result = SettableFuture.create();

		String listNamePart = String.format("_api/web/lists/GetByTitle('%s')/Items?", urlEncode(listName));
		String getListUrl = getSiteUrl() + listNamePart + generateODataQueryString(query);
		ListenableFuture<JSONObject> request = executeRequestJson(getListUrl, "GET");

		Futures.addCallback(request, new FutureCallback<JSONObject>() {
			@Override
			public void onFailure(Throwable t) {
				result.setException(t);
			}

			@Override
			public void onSuccess(JSONObject json) {
				try {
					result.set(SPListItem.listFromJson(json));
				} catch (JSONException e) {
					log(e);
				}
			}
		});

		return result;
	}

    /**
     * Gets the list fields.
     *
     * @param listName the list name
     * @param query the query
     * @return the list fields
     */
	public ListenableFuture<List<SPListField>> getListFields(String listName, Query query) {
		final SettableFuture<List<SPListField>> result = SettableFuture.create();

		String getListUrl = getSiteUrl() + "_api/web/lists/GetByTitle('%s')/Fields?" + generateODataQueryString(query);
		getListUrl = String.format(getListUrl, urlEncode(listName));
		ListenableFuture<JSONObject> request = executeRequestJson(getListUrl, "GET");

		Futures.addCallback(request, new FutureCallback<JSONObject>() {
			@Override
			public void onFailure(Throwable t) {
				result.setException(t);
			}

			@Override
			public void onSuccess(JSONObject json) {
				try {
					result.set(SPListField.listFromJson(json));
				} catch (JSONException e) {
					log(e);
				}
			}
		});

		return result;
	}

    /**
     * Insert list item.
     *
     * @param listItem the list item
     * @param list the list
     * @return the office future
     */
	public ListenableFuture<Void> insertListItem(final SPListItem listItem, final SPList list) {
		final SettableFuture<Void> result = SettableFuture.create();

		String getListUrl = getSiteUrl() + "_api/web/lists/GetByTitle('%s')/Items";
		getListUrl = String.format(getListUrl, urlEncode(list.getTitle()));

		try {
			JSONObject payload = new JSONObject();
			JSONObject metadata = new JSONObject();
			metadata.put("type", list.getListItemEntityTypeFullName());
			payload.put("__metadata", metadata);

			for (String key : listItem.getValues().keySet()) {

				Object object = listItem.getValues().get(key);
				// we assume you're trying to store a value on a linked
				// sharepoint list
				if (object instanceof JSONArray) {
					JSONObject container = new JSONObject();
					container.put("results", object);
					payload.put(key + "Id", container);
				} else {
					payload.put(key, object);
				}
			}

			ListenableFuture<JSONObject> request = executeRequestJsonWithDigest(getListUrl, "POST", null,
					getBytes(payload.toString()));

			Futures.addCallback(request, new FutureCallback<JSONObject>() {
				@Override
				public void onFailure(Throwable t) {
					result.setException(t);
				}

				@Override
				public void onSuccess(JSONObject json) {
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
     * @param list the list
     * @return the office future
     */
	public ListenableFuture<Void> updateListItem(final SPListItem listItem, final SPList list) {
		final SettableFuture<Void> result = SettableFuture.create();

		String getListUrl = getSiteUrl() + "_api/web/lists/GetByTitle('%s')/items(" + listItem.getId() + ")";
		getListUrl = String.format(getListUrl, urlEncode(list.getTitle()));

		try {
			JSONObject payload = new JSONObject();
			JSONObject metadata = new JSONObject();
			metadata.put("type", list.getListItemEntityTypeFullName());
			payload.put("__metadata", metadata);

			for (String key : listItem.getValues().keySet()) {
				Object object = listItem.getValues().get(key);
				// we assume you're trying to store a value on a linked
				// sharepoint list
				if (object instanceof JSONArray) {
					JSONObject container = new JSONObject();
					container.put("results", object);
					payload.put(key + "Id", container);
				} else {
					payload.put(key, object);
				}
			}

			Map<String, String> headers = new HashMap<String, String>();
			headers.put("X-HTTP-Method", "MERGE");
			headers.put("If-Match", "*");

			ListenableFuture<JSONObject> request = executeRequestJsonWithDigest(getListUrl, "POST", headers,
					getBytes(payload.toString()));

			Futures.addCallback(request, new FutureCallback<JSONObject>() {
				@Override
				public void onFailure(Throwable t) {
					result.setException(t);
				}

				@Override
				public void onSuccess(JSONObject json) {
					result.set(null);
				}
			});
		} catch (JSONException e) {
			result.setException(e);
		}
		return result;
	}

    /**
     * Delete list item.
     *
     * @param listItem the list item
     * @param listName the list name
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

			ListenableFuture<JSONObject> request = executeRequestJsonWithDigest(getListUrl, "POST", headers, null);

			Futures.addCallback(request, new FutureCallback<JSONObject>() {
				@Override
				public void onFailure(Throwable t) {
					result.setException(t);
				}

				@Override
				public void onSuccess(JSONObject json) {
					result.set(null);
				}
			});

		} catch (Throwable t) {
			result.setException(t);
		}

		return result;
	}


    /**
     * Gets columns from default view.
     *
     * @param listName the list name
     * @return the columns from default view
     */
    public ListenableFuture<List<String>> getColumnsFromDefaultView(final String listName) {
		final SettableFuture<List<String>> result = SettableFuture.create();
		String getViewUrl = getSiteUrl()
				+ String.format("_api/web/lists/GetByTitle('%s')/defaultView/viewfields", urlEncode(listName));
		ListenableFuture<JSONObject> request = executeRequestJson(getViewUrl, "GET");

		Futures.addCallback(request, new FutureCallback<JSONObject>() {
			@Override
			public void onFailure(Throwable t) {
				result.setException(t);
			}

			@Override
			public void onSuccess(JSONObject json) {
				try {
					JSONObject container = json.getJSONObject("d");
					JSONArray results = container.getJSONObject("Items").getJSONArray("results");
					ArrayList<String> columnNames = new ArrayList<String>();

					for (int i = 0; i < results.length(); i++) {
						columnNames.add(results.get(i).toString());
					}
					result.set(columnNames);
				} catch (JSONException e) {
					log(e);
				}
			}
		});
		return result;
	}


    /**
     * Gets user properties.
     *
     * @return the user properties
     */
    public ListenableFuture<String> getUserProperties() {
		final SettableFuture<String> result = SettableFuture.create();

		String url = getSiteUrl() + "/_api/SP.UserProfiles.PeopleManager/GetMyProperties";

		ListenableFuture<JSONObject> request = executeRequestJson(url, "GET");

		Futures.addCallback(request, new FutureCallback<JSONObject>() {
			@Override
			public void onFailure(Throwable t) {
				result.setException(t);
			}

			@Override
			public void onSuccess(JSONObject json) {
				result.set(json.toString());
			}
		});
		return result;
	}

	/**
	 * Gets the bytes from a given string.
	 * 
	 * @param s
	 *            the s
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
