/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.onedrivediscovery.datasource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.office365.DiscoveryInformation;
import com.microsoft.office365.OfficeClient;
import com.microsoft.office365.files.FileClient;
import com.microsoft.office365.files.FileSystemItem;
import com.microsoft.onedrivediscovery.Constants;
import com.microsoft.onedrivediscovery.DiscoveryAPIApplication;
import com.microsoft.onedrivediscovery.viewmodel.FileItem;
import com.microsoft.onedrivediscovery.viewmodel.FileViewItem;
import com.microsoft.onedrivediscovery.viewmodel.ServiceViewItem;

/**
 * The Class ListItemsDataSource.
 */
public class ListItemsDataSource {

	/** The m application. */
	private DiscoveryAPIApplication mApplication;

	/**
	 * Instantiates a new list items data source.
	 * 
	 * @param application
	 *            the application
	 */
	public ListItemsDataSource(DiscoveryAPIApplication application) {
		mApplication = application;
	}

	public ArrayList<ServiceViewItem> getServices() {
		final ArrayList<ServiceViewItem> serviceItems = new ArrayList<ServiceViewItem>();
		OfficeClient officeClient = mApplication.getOfficeClient(Constants.DISCOVERY_RESOURCE_ID);

		List<DiscoveryInformation> services = null;
		try {
			services = officeClient.getDiscoveryInfo().get();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (DiscoveryInformation service : services) {
			ServiceViewItem item = new ServiceViewItem();

			item.setSelectable(service.getCapability().equals(com.microsoft.onedrivediscovery.Constants.MYFILES_CAPABILITY));
			item.setName(service.getServiceName());
			item.setEndpointUri(service.getServiceEndpointUri().split("_api")[0]);
			item.setResourceId(service.getServiceResourceId());
			item.setCapability(service.getCapability());
			serviceItems.add(item);
		}

		return serviceItems;
	}

	public ServiceViewItem getFileService() {
		ServiceViewItem item = new ServiceViewItem();
		OfficeClient officeClient = mApplication.getOfficeClient(Constants.DISCOVERY_RESOURCE_ID);

		List<DiscoveryInformation> services = null;
		try {
			services = officeClient.getDiscoveryInfo().get();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (DiscoveryInformation service : services) {
			

			item.setSelectable(service.getCapability().equals(com.microsoft.onedrivediscovery.Constants.MYFILES_CAPABILITY));
			item.setName(service.getServiceName());
			item.setEndpointUri(service.getServiceEndpointUri().split("_api")[0]);
			item.setResourceId(service.getServiceResourceId());
			item.setCapability(service.getCapability());
			break;
		}

		return item;
	}
	
	
	public ArrayList<FileViewItem> getFiles(String resourceId, String endpoint) {
		FileClient fileClient = mApplication.getCurrentFileClient(resourceId, endpoint);

		ArrayList<FileViewItem> files = new ArrayList<FileViewItem>();
		try {
			List<FileSystemItem> items = fileClient.getFileSystemItems().get();

			for (FileSystemItem item : items) {
				FileViewItem file = new FileViewItem();
				file.setId(item.getData("Id").toString());
				file.setName(item.getName());
				file.setCreatedOn(item.getData("TimeCreated").toString());
				file.setSelectable(!item.getData("__metadata").toString().contains("MS.FileServices.Folder"));
				files.add(file);

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		return files;
	}

	public void saveFile(FileItem file) {
		FileClient fileClient = mApplication.getCurrentFileClient(file.getResourceId(), file.getEndpoint());

		try {
			fileClient.createFile(file.getName() + ".png", null, false, file.getContent()).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	public ListenableFuture<byte[]> getFile(FileItem file) {
		FileClient fileClient = mApplication.getCurrentFileClient(file.getResourceId(), file.getEndpoint());
		final SettableFuture<byte[]> result = SettableFuture.create();
		ListenableFuture<byte[]> future = fileClient.getFile(file.getId(), null);

		Futures.addCallback(future, new FutureCallback<byte[]>() {
			@Override
			public void onFailure(Throwable t) {
				result.setException(t);
			}

			@Override
			public void onSuccess(byte[] payload) {
				result.set(payload);
			}
		});
		return result;
	}
}