package com.example.exchangedemo;

/**
 * Created by marcote on 9/18/14.
 */
public class MessageViewModel {

    private String senderName;
    private String subject;

    public MessageViewModel(String senderName, String subject) {
        this.senderName = senderName;
        this.subject = subject;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
