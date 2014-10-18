package com.microsoft.services.odata.impl;

public interface Base64Encoder {
    public String encode(byte[] data);
    public byte[] decode(String base64String);
}
