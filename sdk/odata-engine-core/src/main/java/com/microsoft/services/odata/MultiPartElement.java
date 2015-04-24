package com.microsoft.services.odata;

public class MultiPartElement {
    private static final String HTML_CONTENT_TYPE = "text/html";
    private String name;
    private String contentType;
    private byte[] content;

    public MultiPartElement(String name, String content) {
        this(name, HTML_CONTENT_TYPE, content.getBytes(Constants.UTF8));
    }

    public MultiPartElement(String name, String contentType, byte[] content) {
        this.name = name;
        this.contentType = contentType;

        if (content == null) {
            throw new IllegalArgumentException("content");
        }
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
