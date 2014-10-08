/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.exchange.services.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.Constants;
import com.microsoft.office365.odata.EntityFetcherHelper;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.exchange.services.model.*;

public class CalendarOperations extends ODataOperations {

	 public CalendarOperations(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent);
    }
			
	public ListenableFuture<Event> calendarView(java.util.Calendar startDate, java.util.Calendar endDate) {
        final SettableFuture<Event> result = SettableFuture.create();

        ListenableFuture<byte[]> future = oDataExecute("CalendarView", null, HttpVerb.POST);

        EntityFetcherHelper.addEntityResultCallback(result, future, getResolver(), Event.class);

        return result;
    }
}