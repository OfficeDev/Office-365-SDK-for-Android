/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.mailservice;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

public class ErrorHandler {

	private static final String UNKNOWN_ERROR = "Unknown error";

	public static void handleError(Throwable e, final Activity activity) {
		String message = UNKNOWN_ERROR;
		if (e != null) {
			message = joinStackTrace(e);
			if (message == null) {
				message = UNKNOWN_ERROR;
			}
		}

		final String finalMessage = message;
		Log.e("exchange-sample-error", finalMessage);

		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(activity, "Error: " + finalMessage, Toast.LENGTH_LONG).show();
			}
		});
	}

	//http://stackoverflow.com/questions/1292858/getting-full-string-stack-trace-including-inner-exception
	public static String joinStackTrace(Throwable e) {
		StringWriter writer = null;
		try {
			writer = new StringWriter();
			joinStackTrace(e, writer);
			return writer.toString();
		} finally {
			if (writer != null)
				try {
					writer.close();
				} catch (IOException e1) {
					// ignore
				}
		}
	}

	public static void joinStackTrace(Throwable e, StringWriter writer) {
		PrintWriter printer = null;
		try {
			printer = new PrintWriter(writer);

			while (e != null) {

				printer.println(e);
				StackTraceElement[] trace = e.getStackTrace();
				for (int i = 0; i < trace.length; i++)
					printer.println("\tat " + trace[i]);

				e = e.getCause();
				if (e != null)
					printer.println("Caused by:\r\n");
			}
		} finally {
			if (printer != null)
				printer.close();
		}
	}
}
