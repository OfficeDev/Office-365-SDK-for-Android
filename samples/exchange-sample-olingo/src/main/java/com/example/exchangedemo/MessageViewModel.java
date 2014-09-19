package com.example.exchangedemo;

import java.sql.Timestamp;

/**
 * Created by marcote on 9/18/14.
 */
public class MessageViewModel {

    private String senderName;
    private String subject;
    private Timestamp datetimeSent;

    public MessageViewModel(String senderName, String subject, Timestamp datetimeSent) {
        this.senderName = senderName;
        this.subject = subject;
        this.datetimeSent = datetimeSent;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getSubject() {
        return subject;
    }

    public Timestamp getDateTimeSent() { return datetimeSent; }
}
