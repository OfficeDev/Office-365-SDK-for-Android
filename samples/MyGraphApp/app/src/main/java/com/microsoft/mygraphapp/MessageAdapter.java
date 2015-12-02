package com.microsoft.mygraphapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.microsoft.services.graph.Message;

public class MessageAdapter extends ArrayAdapter<MessageDisplayItem> {

    Context mContext;

    int mLayoutResourceId;

    public MessageAdapter(Context context, int layoutResourceId) {
        super(context, layoutResourceId);

        mContext = context;
        mLayoutResourceId = layoutResourceId;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;

        final MessageDisplayItem messageDisplayItem = getItem(position);

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, parent, false);
        }

        final CheckBox checkBox = (CheckBox) row.findViewById(R.id.checkMessage);

        String text = messageDisplayItem.getMessage().getSubject();
        String text2 = messageDisplayItem.getMessage().getSender().getEmailAddress().getName();

        if (messageDisplayItem.getMessage().getIsRead()) {
            checkBox.setTextColor(Color.GRAY);
        } else {
            checkBox.setTextColor(Color.BLACK);
        }

        checkBox.setText(text);
        checkBox.setChecked(messageDisplayItem.getIsSelected());

		/*
		checkBox.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				testResult.getTestCase().setSelected(checkBox.isChecked());
			}
		});
*/
        final TextView textView = (TextView) row.findViewById(R.id.sender);

        textView.setText(text2 + " " + messageDisplayItem.getStatus());

        return row;
    }
}
