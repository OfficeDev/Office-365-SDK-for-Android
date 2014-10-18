package com.microsoft.services.odata.impl.desktop;

import com.microsoft.services.odata.impl.Base64Encoder;

import javax.xml.bind.DatatypeConverter;

public class Base64EncoderImpl implements Base64Encoder {
    private Base64EncoderImpl() {
    }

    private static Base64EncoderImpl instance = new Base64EncoderImpl();
    public static Base64EncoderImpl getInstance() {
        return instance;
    }

    @Override
    public String encode(byte[] data) {
        return DatatypeConverter.printBase64Binary(data);
    }

    @Override
    public byte[] decode(String base64String) {
        return DatatypeConverter.parseBase64Binary(base64String);
    }
}
