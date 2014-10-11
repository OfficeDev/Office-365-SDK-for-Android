package com.microsoft.office365.odata;

import com.microsoft.office365.odata.interfaces.DependencyResolver;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Helpers {

    public static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, Constants.UTF8_NAME);
        } catch (UnsupportedEncodingException ignore) {
            return s;
        }
    }

    public static byte[] serializeToJsonByteArray(Object entity, DependencyResolver resolver) {
        String payload = resolver.getJsonSerializer().serialize(entity);
        return payload.getBytes(Constants.UTF8);
    }
}
