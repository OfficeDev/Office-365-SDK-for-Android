/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.outlookservices.*; 
import com.microsoft.outlookservices.*;		

/**
 * The type  ItemFetcher.
 */
public class ItemFetcher extends ODataEntityFetcher<Item,ItemOperations> implements Readable<Item> {

     /**
     * Instantiates a new ItemFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
	 public ItemFetcher(String urlComponent, ODataExecutable parent) {
		super(urlComponent, parent, Item.class,ItemOperations.class);
    }

	     public MessageFetcher asMessage(){
	      return new MessageFetcher(this.urlComponent, this.parent);
     }	
	     public EventFetcher asEvent(){
	      return new EventFetcher(this.urlComponent, this.parent);
     }	
	     public ContactFetcher asContact(){
	      return new ContactFetcher(this.urlComponent, this.parent);
     }	
	}