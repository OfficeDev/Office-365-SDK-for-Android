/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.sharepoint.http;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.services.sharepoint.Platform;

/**
 * Java HttpConnection implementation, based on HttpURLConnection and threads
 * async operations
 */
public class JavaHttpConnection implements HttpConnection {

	/**
	 * User agent header name
	 */
	private static final String USER_AGENT_HEADER = "User-Agent";

	@Override
	public ListenableFuture<Response> execute(final Request request) {

		request.addHeader(USER_AGENT_HEADER, Platform.getUserAgent());

		final SettableFuture<Response> future = SettableFuture.create();
		final NetworkRunnable target = new NetworkRunnable(request, future);

		final NetworkThread networkThread = new NetworkThread(target) {
			@Override
			void releaseAndStop() {
				try {
					target.closeStreamAndConnection();
				} catch (Throwable error) {
				}
			}
		};
		
		Futures.addCallback(future, new FutureCallback<Response>() {
			@Override
			public void onFailure(Throwable arg0) {
				networkThread.releaseAndStop();				
			}
			
			@Override
			public void onSuccess(Response response) {
			}
		});
		
		networkThread.start();
		return future;
	}
}
