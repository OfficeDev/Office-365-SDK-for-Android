/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.exchange.services.*;

public class EventODataComponent extends BaseEntityODataComponent<Event> implements Executable<Event> {

	 public EventODataComponent(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Event.class);
    }
	public CalendarODataComponent getCalendar() {
        return new CalendarODataComponent("Calendar", this);
    }
			
	public ListenableFuture<Integer> accept(String comment) {
        final SettableFuture<Integer> result = SettableFuture.create();

        ListenableFuture<byte[]> future = oDataExecute("Accept", null, HttpVerb.POST);

        Futures.addCallback(future, new FutureCallback<byte[]>() {
            @Override
            public void onSuccess(byte[] integer) {
                DependencyResolver resolver = getResolver();

                try {
                    result.set(resolver.getJsonSerializer().deserialize(new String(integer, Constants.UTF8), Integer.class));
                } catch (Throwable throwable) {
                    result.setException(throwable);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                result.setException(t);
            }
        });

        return result;
    }
			
	public ListenableFuture<Integer> decline(String comment) {
        final SettableFuture<Integer> result = SettableFuture.create();

        ListenableFuture<byte[]> future = oDataExecute("Decline", null, HttpVerb.POST);

        Futures.addCallback(future, new FutureCallback<byte[]>() {
            @Override
            public void onSuccess(byte[] integer) {
                DependencyResolver resolver = getResolver();

                try {
                    result.set(resolver.getJsonSerializer().deserialize(new String(integer, Constants.UTF8), Integer.class));
                } catch (Throwable throwable) {
                    result.setException(throwable);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                result.setException(t);
            }
        });

        return result;
    }
			
	public ListenableFuture<Integer> tentativelyaccept(String comment) {
        final SettableFuture<Integer> result = SettableFuture.create();

        ListenableFuture<byte[]> future = oDataExecute("TentativelyAccept", null, HttpVerb.POST);

        Futures.addCallback(future, new FutureCallback<byte[]>() {
            @Override
            public void onSuccess(byte[] integer) {
                DependencyResolver resolver = getResolver();

                try {
                    result.set(resolver.getJsonSerializer().deserialize(new String(integer, Constants.UTF8), Integer.class));
                } catch (Throwable throwable) {
                    result.setException(throwable);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                result.setException(t);
            }
        });

        return result;
    }
}