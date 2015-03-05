/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

import com.microsoft.outlookservices.*;
import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.*;
import com.microsoft.services.odata.Readable;
import com.microsoft.services.odata.interfaces.*;

/**
 * The type  ItemFetcher.
 */
public class ItemFetcher extends ODataEntityFetcher<Item,ItemOperations> 
                                     implements Readable<Item> {

     /**
     * Instantiates a new ItemFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
     public ItemFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Item.class, ItemOperations.class);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the fetcher
     */
    public ItemFetcher addParameter(String name, Object value) {
        addCustomParameter(name, value);
        return this;
    }

     /**
     * Add header.
     *
     * @param name the name
     * @param value the value
     * @return the fetcher
     */
    public ItemFetcher addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
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
