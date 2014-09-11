/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.mailservice;


import org.json.JSONObject;

import com.microsoft.office.microsoft.exchange.services.odata.model.types.ItemBody;
import com.microsoft.office.microsoft.exchange.services.odata.model.types.Message;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.TextView;


public class MailActivity extends Activity {

	Message mMessage;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mail_display);

		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			String data = bundle.getString("data");
			if (data != null) {
				try {
					JSONObject payload = new JSONObject(data);
					final int position = Integer.parseInt(payload.getString("position"));

					mMessage = (Message)MainActivity.mMailListView.getItemAtPosition(position);

					((TextView) findViewById(R.id.mail_sender)).setText(mMessage.getSender().getName());
					((TextView) findViewById(R.id.mail_subject)).setText(mMessage.getSubject());
					((TextView) findViewById(R.id.mail_sendOn)).setText(mMessage.getDateTimeSent().toString());

					ItemBody body = mMessage.getBody();

					WebView wv =(WebView) findViewById(R.id.mail_body);
					wv.loadData(body.getContent(),"text/html; charset=utf-8", null);
				}
				catch (Exception e) {
					ErrorHandler.handleError(e, this);
				}
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.context_menu, menu);
		return true;
	}
}
