package com.microsoft.services.odata.impl;

import android.util.Base64;

public class Base64EncoderImpl implements Base64Encoder {
    private Base64EncoderImpl() {
    }

    private static Base64EncoderImpl instance = new Base64EncoderImpl();
    public static Base64EncoderImpl getInstance() {
        return instance;
    }

    @Override
    public String encode(byte[] data) {
        return Base64.encodeToString(data, Base64.DEFAULT);
    }

    @Override
    public byte[] decode(String base64String) {
        return Base64.decode(base64String, Base64.DEFAULT);
    }
}
