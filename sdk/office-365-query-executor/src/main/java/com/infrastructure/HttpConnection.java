/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.infrastructure;

import com.google.common.util.concurrent.ListenableFuture;
import com.infrastructure.http.Request;
import com.infrastructure.http.Response;


/**
 * Interface that defines a generic HttpConnection
 */
public interface HttpConnection {
	
	/**
	 * Executes an request
	 * @param request The request to execute
	 * @return A Future for the operation
	 */
	public ListenableFuture<Response> execute(final Request request);
}
