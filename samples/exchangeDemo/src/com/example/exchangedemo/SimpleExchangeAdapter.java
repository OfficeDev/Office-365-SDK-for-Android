package com.example.exchangedemo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SimpleExchangeAdapter extends BaseAdapter {

	private List<Message> messageList;
	private MainActivity mActivity;
	private LayoutInflater inflater;

	public SimpleExchangeAdapter(MainActivity activity, List<Message> messages) {
		messageList = messages;
		mActivity = activity;
		inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return messageList.size();
	}

	@Override
	public Message getItem(int arg0) {
		// TODO Auto-generated method stub
		return messageList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		
		if(arg1==null)
		{		
			arg1 = inflater.inflate(R.layout.list_item, arg2,false);
		}
		
		TextView senderName = (TextView)arg1.findViewById(R.id.sender);
		TextView subject = (TextView)arg1.findViewById(R.id.subject);
		
		Message message = messageList.get(arg0);
		
		senderName.setText(message.From.Name);
		subject.setText(message.Subject);
		
		return arg1;
	}
	
	public Message getMessage(int position)
	{
		return messageList.get(position);
	}	
}