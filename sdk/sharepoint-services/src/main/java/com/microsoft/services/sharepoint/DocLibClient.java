/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.sharepoint;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;


/**
 * The Class DocLibClient.
 */
public class DocLibClient extends SharePointClient {

	/**
	 * Instantiates a new file API client.
	 * 
	 * @param serverUrl
	 * @param credentials
	 */
	public DocLibClient(String serverUrl, String siteRelativeUrl, Credentials credentials) {
		super(serverUrl, siteRelativeUrl, credentials);
	}

	/**
	 * Instantiates a new file client.
	 * 
	 * @param serverUrl
	 * @param siteRelativeUrl
	 * @param credentials
	 * @param logger
	 */
	public DocLibClient(String serverUrl, String siteRelativeUrl, Credentials credentials, Logger logger) {
		super(serverUrl, siteRelativeUrl, credentials, logger);
	}

	/**
	 * Gets a list of FileSystemItem from the default Document Library
	 * 
	 * @return OfficeFuture<List<FileSystemItem>>
	 */
	public ListenableFuture<List<FileSystemItem>> getFileSystemItems() {

		return getFileSystemItems(null, null);
	}

	/**
	 * Gets children folder with a given path
	 * 
	 * @param path
	 * @return OfficeFuture<FileSystemItem>
	 */
	public ListenableFuture<List<FileSystemItem>> getFileSystemItems(String path, String library) {

		final SettableFuture<List<FileSystemItem>> result = SettableFuture.create();

		String getPath;

		if (library == null) {
			if (path == null || path.length() == 0) {
				getPath = getSiteUrl() + "_api/Files";
			} else {
				getPath = getSiteUrl() + String.format("_api/Files('%s')/children", urlEncode(path));
			}
		} else {
			if (path == null || path.length() == 0) {
				getPath = getSiteUrl() + String.format("_api/web/lists/GetByTitle('%s')/files", urlEncode(library));
			} else {
				getPath = getSiteUrl()
						+ String.format("_api/web/lists/GetByTitle('%s')/files('%s')/children", urlEncode(library),
								urlEncode(path));
			}
		}

		ListenableFuture<JSONObject> request = executeRequestJson(getPath, "GET");

		Futures.addCallback(request, new FutureCallback<JSONObject>() {
			@Override
			public void onFailure(Throwable t) {
				result.setException(t);
			}

			@Override
			public void onSuccess(JSONObject json) {
				List<FileSystemItem> item;
				try {
					item = FileSystemItem.listFrom(json);
					result.set(item);
				} catch (Throwable e) {
					result.setException(e);
				}
			}
		});
		return result;
	}

	public ListenableFuture<FileSystemItem> getFileSystemItem(String path) {
		return getFileSystemItem(path, null);
	}

	/**
	 * Get a FileSystemItem from a path in a document library
	 * 
	 * @param library
	 *            the document library
	 * @param path
	 *            the path
	 * @return OfficeFuture<List<FileSystemItem>>
	 */
	public ListenableFuture<FileSystemItem> getFileSystemItem(String path, final String library) {

		final SettableFuture<FileSystemItem> files = SettableFuture.create();

		String getFilesUrl;
		if (library != null) {
			getFilesUrl = getSiteUrl() + "_api/web/lists/GetByTitle('%s')/files(%s)";
			getFilesUrl = String.format(getFilesUrl, urlEncode(library), getUrlPath(path));
		} else {
			getFilesUrl = getSiteUrl() + String.format("_api/files(%s)", getUrlPath(path));
		}

		try {
			ListenableFuture<JSONObject> request = executeRequestJson(getFilesUrl, "GET");

			Futures.addCallback(request, new FutureCallback<JSONObject>() {
				@Override
				public void onFailure(Throwable t) {
					files.setException(t);
				}

				@Override
				public void onSuccess(JSONObject json) {
					try {
						FileSystemItem item = new FileSystemItem();
						item.loadFromJson(json);
						files.set(item);
					} catch (Throwable e) {
						files.setException(e);
					}
				}
			});

		} catch (Throwable t) {
			files.setException(t);
		}
		return files;
	}

	/**
	 * Retrieves the value of property with a given path and library
	 * 
	 * @param property
	 * @param path
	 * @param library
	 * @return
	 */
	public ListenableFuture<Object> getProperty(final String property, String path, String library) {
		if (path == null || path.length() == 0) {
			throw new IllegalArgumentException("Path cannot be null or empty");
		}

		if (property == null || property.length() == 0) {
			throw new IllegalArgumentException("Property cannot be null or empty");
		}

		String getPropertyUrl;
		if (library == null) {
			getPropertyUrl = getSiteUrl() + String.format("_api/files('%s')/%s", urlEncode(path), property);
		} else {
			String url = getSiteUrl() + "_api/web/Lists/GetByTitle('%s')/files('%s')/%s";
			getPropertyUrl = String.format(url, urlEncode(library.trim()), urlEncode(path), property);
		}

		final SettableFuture<Object> result = SettableFuture.create();
		ListenableFuture<JSONObject> request = executeRequestJson(getPropertyUrl, "GET");

		Futures.addCallback(request, new FutureCallback<JSONObject>() {
			@Override
			public void onFailure(Throwable t) {
				result.setException(t);
			}

			@Override
			public void onSuccess(JSONObject json) {
				Object propertyResult;
				try {
					propertyResult = json.getJSONObject("d").get(property);
					result.set(propertyResult);
				} catch (JSONException e) {
					result.setException(e);
				}
			}
		});
		return result;
	}

	/**
	 * Gets the value of a given property with a given path
	 * 
	 * @param path
	 * @param property
	 * @return OfficeFuture<Object>
	 */
	public ListenableFuture<Object> getProperty(final String property, String path) {
		return getProperty(property, path, null);
	}

	/**
	 * Gets the file.
	 * 
	 * @param path
	 * @return OfficeFuture<byte[]>
	 */
	public ListenableFuture<byte[]> getFile(String path) {
		return getFile(path, null);
	}

	/**
	 * Gets the file.
	 * 
	 * @param path
	 * @return OfficeFuture<byte[]>
	 */
	public ListenableFuture<byte[]> getFile(String path, String library) {
		if (path == null || path.length() == 0) {
			throw new IllegalArgumentException("Path cannot be null or empty");
		}

		String getFileUrl;
		if (library == null) {
			getFileUrl = getSiteUrl() + String.format("_api/files('%s')/$value", urlEncode(path));
		} else {
			getFileUrl = getSiteUrl()
					+ String.format("_api/web/Lists/GetByTitle('%s')/files('%s')/$value", urlEncode(library),
							urlEncode(path));
		}
		return executeRequest(getFileUrl, "GET");
	}

	/**
	 * Creates the folder with a given path
	 * 
	 * @param path
	 * @return OfficeFuture<FileSystemItem>
	 */
	public ListenableFuture<FileSystemItem> createFolder(String path) {

		if (path == null || path.length() == 0) {
			throw new IllegalArgumentException("path cannot be null or empty");
		}
		final ListenableFuture<FileSystemItem> fileMetadata = createEmpty(path, null, FileConstants.FOLDER_CREATE);
		return fileMetadata;
	}

	/**
	 * Creates a folder with a given path and library
	 * 
	 * @param path
	 * @param library
	 * @return OfficeFuture<FileSystemItem>
	 */
	public ListenableFuture<FileSystemItem> createFolder(String path, String library) {

		if (path == null || path.length() == 0) {
			throw new IllegalArgumentException("path cannot be null or empty");
		}

		if (library == null || library.length() == 0) {
			throw new IllegalArgumentException("library name cannot be null or empty");
		}

		final ListenableFuture<FileSystemItem> fileMetadata = createEmpty(path, library, FileConstants.FOLDER_CREATE);
		return fileMetadata;
	}

	/**
	 * Creates an empty file.
	 * 
	 * @param fileName
	 * @return OfficeFuture<FileSystemItem>
	 */
	public ListenableFuture<FileSystemItem> createFile(String fileName) {

		if (fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("fileName cannot be null or empty");
		}

		final ListenableFuture<FileSystemItem> fileMetadata = createEmpty(fileName, null, FileConstants.FILE_CREATE);
		return fileMetadata;
	}

	/**
	 * Creates an empty file.
	 * 
	 * @param fileName
	 * @return OfficeFuture<FileSystemItem>
	 */
	public ListenableFuture<FileSystemItem> createFile(String fileName, String library) {
		if (fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("fileName cannot be null or empty");
		}

		if (library == null || fileName.length() == 0) {
			throw new IllegalArgumentException("libraryName cannot be null or empty");
		}

		final ListenableFuture<FileSystemItem> fileMetadata = createEmpty(fileName, library, FileConstants.FILE_CREATE);
		return fileMetadata;
	}

	/**
	 * Creates a file with a given path inside a given library
	 * 
	 * @param fileName
	 * @param library
	 * @param overwrite
	 * @param content
	 * @return OfficeFuture<FileSystemItem>
	 */
	public ListenableFuture<FileSystemItem> createFile(String fileName, String library, boolean overwrite,
			byte[] content) {

		if (fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("fileName cannot be null or empty");
		}

		String urlPart = urlEncode(String.format("Add(name='%s', overwrite='%s')", fileName,
				Boolean.toString(overwrite)));

		String url;
		if (library == null || library.length() == 0) {
			url = getSiteUrl() + "_api/files/" + urlPart;
		} else {
			url = getSiteUrl() + String.format("_api/web/lists/getbytitle('%s')/files/", urlEncode(library)) + urlPart;
		}
		final SettableFuture<FileSystemItem> result = SettableFuture.create();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/octet-stream");

		ListenableFuture<JSONObject> request = executeRequestJsonWithDigest(url, "POST", headers, content);

		Futures.addCallback(request, new FutureCallback<JSONObject>() {
			@Override
			public void onFailure(Throwable t) {
				result.setException(t);
			}

			@Override
			public void onSuccess(JSONObject json) {
				FileSystemItem item = new FileSystemItem();
				item.loadFromJson(json, true);
				result.set(item);
			}

		});
		return result;
	}

	/**
	 * Creates the file with a given file name and content
	 * 
	 * @param fileName
	 *            The file
	 * @param overwrite
	 *            True to overwrite
	 * @param content
	 *            The content
	 * @return OfficeFuture<FileSystemItem>
	 */
	public ListenableFuture<FileSystemItem> createFile(String fileName, boolean overwrite, byte[] content) {

		return createFile(fileName, null, overwrite, content);
	}

	/**
	 * Delete a file/folder with a given path
	 * 
	 * @param path
	 * @return OfficeFuture<Void>
	 */
	public ListenableFuture<Void> delete(String path) {

		if (path == null || path.length() == 0) {
			throw new IllegalArgumentException("path cannot be null or empty");
		}

		return delete(path, null);
	}

	/**
	 * Deletes a file/folder with a given path and library
	 * 
	 * @param path
	 *            The path
	 * @param library
	 *            The library
	 * @return
	 */
	public ListenableFuture<Void> delete(String path, String library) {

		final SettableFuture<Void> result = SettableFuture.create();

		String deleteUrl;
		if (library == null) {
			deleteUrl = getSiteUrl() + String.format("_api/Files('%s')", urlEncode(path));
		} else {
			deleteUrl = getSiteUrl()
					+ String.format("_api/web/Lists/GetByTitle('%s')/files('%s')", urlEncode(library), urlEncode(path));
		}

		ListenableFuture<JSONObject> request = executeRequestJson(deleteUrl, "DELETE");

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
		return result;
	}

	/**
	 * Moves an item from the given sourcePath to the given destinationPath.
	 * Returns the destination path when succeeds
	 * 
	 * @param sourcePath
	 * @param destinationPath
	 * @param overwrite
	 * @return OfficeFuture<String>
	 */
	public ListenableFuture<Void> move(String sourcePath, String destinationPath, boolean overwrite) {
		if (sourcePath == null) {
			throw new IllegalArgumentException("sourcePath cannot be null or empty");
		}

		if (destinationPath == null) {
			throw new IllegalArgumentException("destinationPath cannot be null or empty");
		}
		ListenableFuture<Void> result = fileOp("MoveTo", sourcePath, destinationPath, overwrite, null);
		return result;
	}

	/**
	 * Moves an item from the given sourcePath to the given destinationPath.
	 * Returns the destination path when succeeds
	 * 
	 * @param overwrite
	 *            flag
	 * @return OfficeFuture<String>
	 */
	public ListenableFuture<Void> move(String sourcePath, String destinationPath, boolean overwrite, String library) {
		if (sourcePath == null) {
			throw new IllegalArgumentException("sourcePath cannot be null or empty");
		}

		if (destinationPath == null) {
			throw new IllegalArgumentException("destinationPath cannot be null or empty");
		}
		ListenableFuture<Void> result = fileOp("MoveTo", sourcePath, destinationPath, overwrite, library);
		return result;
	}

	/**
	 * Copies an item from the given sourcePath to the given destinationPath.
	 * Returns the destination path when succeeds
	 * 
	 * @param sourcePath
	 * @param destinationPath
	 *            the destination path
	 * @param overwrite
	 * @return OfficeFuture<String>
	 */
	public ListenableFuture<Void> copy(String sourcePath, String destinationPath, boolean overwrite) {
		if (sourcePath == null) {
			throw new IllegalArgumentException("sourcePath cannot be null or empty");
		}

		if (destinationPath == null) {
			throw new IllegalArgumentException("destinationPath cannot be null or empty");
		}
		ListenableFuture<Void> result = fileOp("CopyTo", sourcePath, destinationPath, overwrite, null);
		return result;
	}

	/**
	 * Copies an item from the given sourcePath to the given destinationPath.
	 * Returns the destination path when succeeds
	 * 
	 * @param sourcePath
	 * @param destinationPath
	 *            the destination path
	 * @param overwrite
	 * @return OfficeFuture<String>
	 */
	public ListenableFuture<Void> copy(String sourcePath, String destinationPath, boolean overwrite, String library) {
		if (sourcePath == null) {
			throw new IllegalArgumentException("sourcePath cannot be null or empty");
		}

		if (destinationPath == null) {
			throw new IllegalArgumentException("destinationPath cannot be null or empty");
		}
		ListenableFuture<Void> result = fileOp("CopyTo", sourcePath, destinationPath, overwrite, library);
		return result;
	}

	/**
	 * Creates the empty.
	 * 
	 * @param path
	 * @param metadata
	 *            content for the file
	 * @return OfficeFuture<FileSystemItem>
	 */
	private ListenableFuture<FileSystemItem> createEmpty(String path, String library, String metadata) {

		final SettableFuture<FileSystemItem> result = SettableFuture.create();

		String postUrl = null;
		if (library == null) {
			postUrl = getSiteUrl() + "_api/files";
		} else {
			postUrl = getSiteUrl() + String.format("_api/web/lists/GetByTitle('%s')/files", urlEncode(library));
		}

		byte[] payload = null;
		try {
			String completeMetada = String.format(metadata, path);
			payload = completeMetada.getBytes(Constants.UTF8_NAME);
			ListenableFuture<JSONObject> request = executeRequestJsonWithDigest(postUrl, "POST", null, payload);

			Futures.addCallback(request, new FutureCallback<JSONObject>() {
				@Override
				public void onFailure(Throwable t) {
					result.setException(t);
				}

				@Override
				public void onSuccess(JSONObject json) {
					FileSystemItem item = new FileSystemItem();
					item.loadFromJson(json, true);
					result.set(item);
				}
			});

		} catch (UnsupportedEncodingException e) {
			result.setException(e);
		}
		return result;
	}

	private ListenableFuture<Void> fileOp(final String operation, String source, String destination, boolean overwrite,
			String library) {
		final SettableFuture<Void> result = SettableFuture.create();
		String url;

		String targetEncoded = urlEncode("target='" + destination + "', overwrite=" + Boolean.toString(overwrite));

		if (library == null || library.length() == 0) {
			url = getSiteUrl() + String.format("_api/files('%s')/%s(%s)", urlEncode(source), operation, targetEncoded);
		} else {
			url = getSiteUrl()
					+ String.format("_api/web/lists/getbytitle('%s')/files('%s')/%s(%s)", urlEncode(library),
							urlEncode(source), operation, targetEncoded);
		}

		ListenableFuture<JSONObject> request = executeRequestJsonWithDigest(url, "POST", null, null);

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
		return result;
	}

	/**
	 * Returns the URL component for a path
	 */
	private String getUrlPath(String path) {
		if (path == null) {
			path = "";
		}

		String urlPath;
		if (path.length() == 0) {
			urlPath = "";
		} else {
			urlPath = String.format("'%s'", urlEncode(path));
		}
		return urlPath;
	}
}
