package com.microsoft.mygraphapp;

import com.microsoft.services.graph.Message;

public class MessageDisplayItem {

    public MessageDisplayItem(Message message){
        mMessage = message;
    }

    private boolean mIsSelected;

    private Message mMessage;

    private String mStatus;

    public Message getMessage(){
        return mMessage;
    }

    public void setMessage(Message message){
        mMessage = message;
    }

    public boolean getIsSelected(){
        return mIsSelected;
    }

    public void setIsSelected(boolean selected){
        mIsSelected = selected;
    }

    public String getStatus(){
        return mStatus;
    }

    public void setStatus(String status){
        mStatus = status;
    }
}
