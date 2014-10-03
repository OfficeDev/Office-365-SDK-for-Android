/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.mailservice.adapters;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.microsoft.mailservice.MainActivity;
import com.microsoft.mailservice.R;
import com.microsoft.office365.exchange.services.Message;
import com.microsoft.office365.exchange.services.Recipient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MessageItemAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private List<Message> mMessages;
    private MainActivity mActivity;
    private Resources mResources;
    private final LetterTileProvider mTileProvider;
    private final int mTileSize;

    public MessageItemAdapter(MainActivity activity, List<Message> messages) {
        mMessages = Lists.newArrayList(messages);
        mActivity = activity;
        inflater = (mActivity).getLayoutInflater();
        mResources = activity.getResources();
        mTileProvider = new LetterTileProvider(mActivity);
        mTileSize = mResources.getDimensionPixelSize(R.dimen.letter_tile_size);
    }

    @Override
    public int getCount() {
        return mMessages.size();
    }

    @Override
    public Object getItem(int position) {
        if (position >= mMessages.size()) {
            return null;
        }
        return mMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        MessageItemHolder holder = null;

        Message message = mMessages.get(position);

        Recipient sender = message.getSender();
        String subject = message.getSubject();
        Calendar date = message.getDateTimeSent();

        if (view == null) {

            view = inflater.inflate(R.layout.activity_mail_list_item, parent, false);
            holder = new MessageItemHolder();
            holder.sender = (TextView) view.findViewById(R.id.sender);
            holder.subject = (TextView) view.findViewById(R.id.subject);
            holder.sendOn = (TextView) view.findViewById(R.id.sendOn);
            holder.initials = (ImageView) view.findViewById(R.id.initials);

            view.setTag(holder);

        } else {
            holder = (MessageItemHolder) view.getTag();
        }

        if (sender != null) {

            holder.sender.setText(sender == null ? "" : sender.getName());
            holder.subject.setText(sender == null ? "" : (subject.length() > 30 ? subject.substring(0, 30) + "..."
                    : subject));
            holder.sendOn.setText(date == null ? "" : message.getDateTimeSent().toString());
            Bitmap bitmap = mTileProvider.getLetterTile(sender.getName().substring(0, 1), sender.getName(), mTileSize,
                    mTileSize);
            holder.initials.setImageBitmap(bitmap);
        }
        return view;
    }

    public void addMoreItems(List<Message> collection) {

        if (collection != null) {
            List<Message> messages = new ArrayList<Message>();
            List<Message> result = Lists.newArrayList(collection);

            for (Message m : mMessages) {
                if (m.getId() != null && m.getId() != "")
                    messages.add(m);
            }

            for (Message m : result) {
                messages.add(m);
            }
            mMessages = messages;
        }
    }

    public void clear() {
        mMessages.clear();
    }

    static class MessageItemHolder {
        TextView sender;
        TextView subject;
        TextView sendOn;
        ImageView initials;
    }

}