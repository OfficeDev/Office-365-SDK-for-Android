/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.exchange.services.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.exchange.services.model.Event;
import com.microsoft.office365.odata.Constants;
import com.microsoft.office365.odata.EntityFetcherHelper;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.exchange.services.*;
import static com.microsoft.office365.odata.Helpers.serializeToJsonByteArray;

public class CalendarOperations extends ODataOperations {

	 public CalendarOperations(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent);
    }
			
	public ListenableFuture<Event> calendarView(java.util.Calendar startDate, java.util.Calendar endDate) {
        final SettableFuture<Event> result = SettableFuture.create();

		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("StartDate", startDate);
	    map.put("EndDate", endDate);
	

        ListenableFuture<byte[]> future = oDataExecute("CalendarView", serializeToJsonByteArray(map, getResolver()), HttpVerb.POST);

        EntityFetcherHelper.addEntityResultCallback(result,future,getResolver(),Event.class);


        return result;
    }
}