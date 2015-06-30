/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.sharepoint.http;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

/**
 * The type Sharepoint cookie credentials.
 */
public class SharepointCookieCredentials extends CookieCredentials {

    /**
     * Request credentials.
     *
     * @param sharepointSiteUrl the sharepoint site url
     * @param activity the activity
     * @return the listenable future
     */
    public static ListenableFuture<CookieCredentials> requestCredentials(String sharepointSiteUrl, Activity activity) {
		final SettableFuture<CookieCredentials> future = SettableFuture.create();

		ListenableFuture<String> login = showLoginForCookies(activity, sharepointSiteUrl);
				
		Futures.addCallback(login, new FutureCallback<String>(){
			@Override
			public void onFailure(Throwable t) {
				future.setException(t);
			}
			@Override 
			public void onSuccess(String cookies){
				future.set(new CookieCredentials(cookies));
			}
		});
				
		return future;
	}


    /**
     * Show login for cookies.
     *
     * @param activity the activity
     * @param startUrl the start url
     * @return the listenable future
     */
    @SuppressLint("SetJavaScriptEnabled")
	protected static ListenableFuture<String> showLoginForCookies(Activity activity, final String startUrl) {

		final SettableFuture<String> codeFuture = SettableFuture.create();
		if (startUrl == null || startUrl.equals("")) {
			throw new IllegalArgumentException("startUrl can not be null or empty");
		}

		if (activity == null) {
			throw new IllegalArgumentException("activity can not be null");
		}

		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		// Create the Web View to show the login page
		final WebView wv = new WebView(activity);
		builder.setOnCancelListener(new DialogInterface.OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				codeFuture.setException(new Exception("User cancelled"));
			}
		});

		wv.getSettings().setJavaScriptEnabled(true);

		wv.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		wv.getSettings().setLoadWithOverviewMode(true);
		wv.getSettings().setUseWideViewPort(true);

		DisplayMetrics displaymetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int webViewHeight = displaymetrics.heightPixels;

		wv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, webViewHeight));

		wv.requestFocus(View.FOCUS_DOWN);
		wv.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View view, MotionEvent event) {
				int action = event.getAction();
				if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_UP) {
					if (!view.hasFocus()) {
						view.requestFocus();
					}
				}

				return false;
			}
		});

		// Create a LinearLayout and add the WebView to the Layout
		LinearLayout layout = new LinearLayout(activity);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.addView(wv);

		// Add a dummy EditText to the layout as a workaround for a bug
		// that prevents showing the keyboard for the WebView on some devices
		EditText dummyEditText = new EditText(activity);
		dummyEditText.setVisibility(View.GONE);
		layout.addView(dummyEditText);

		// Add the layout to the dialog
		builder.setView(layout);

		final AlertDialog dialog = builder.create();

		wv.setWebViewClient(new WebViewClient() {

			boolean mResultReturned = false;
			final Object mSync = new Object();

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				synchronized (mSync) {
					// If the URL of the started page matches with the final URL
					// format, the login process finished
					if (cookieWasFound(view) && !mResultReturned) {
						mResultReturned = true;

						CookieManager cookieManager = CookieManager.getInstance();
						String cookie = cookieManager.getCookie(url);
						dialog.dismiss();
						codeFuture.set(cookie);
					}

					super.onPageStarted(view, url, favicon);
				}
			}

			private boolean cookieWasFound(WebView view) {
				CookieManager cookieManager = CookieManager.getInstance();
				String cookie = cookieManager.getCookie(startUrl);
                return cookie != null && cookie.contains("rtFa");
			}
		});

		wv.loadUrl(startUrl);
		dialog.show();

		return codeFuture;
	}
}
