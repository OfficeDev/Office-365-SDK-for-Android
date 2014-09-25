package com.microsoft.office365.query;

/**
 * Created by marcote on 9/25/14.
 */
public class Message {
    private String id;
    private String subject;

    public Message(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
