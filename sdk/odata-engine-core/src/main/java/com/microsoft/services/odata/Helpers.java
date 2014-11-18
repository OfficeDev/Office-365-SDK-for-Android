package com.microsoft.services.odata;

import com.microsoft.services.odata.interfaces.DependencyResolver;
import com.microsoft.services.odata.interfaces.ODataURL;
import com.microsoft.services.odata.interfaces.Request;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The type Helpers.
 */
public class Helpers {

    private static final String ENCODE_EXCEPTIONS = "!$&'()*+,;=:@";

    private static final List<String> reservedNames;

    static {
        reservedNames = new ArrayList<String>();
        reservedNames.add("abstract");
        reservedNames.add("assert");
        reservedNames.add("boolean");
        reservedNames.add("break");
        reservedNames.add("byte");
        reservedNames.add("case");
        reservedNames.add("catch");
        reservedNames.add("char");
        reservedNames.add("class");
        reservedNames.add("const");
        reservedNames.add("continue");
        reservedNames.add("default");
        reservedNames.add("do");
        reservedNames.add("double");
        reservedNames.add("else");
        reservedNames.add("enum");
        reservedNames.add("extends");
        reservedNames.add("final");
        reservedNames.add("finally");
        reservedNames.add("float");
        reservedNames.add("for");
        reservedNames.add("if");
        reservedNames.add("goto");
        reservedNames.add("implements");
        reservedNames.add("import");
        reservedNames.add("instanceof");
        reservedNames.add("int");
        reservedNames.add("interface");
        reservedNames.add("long");
        reservedNames.add("native");
        reservedNames.add("new");
        reservedNames.add("package");
        reservedNames.add("private");
        reservedNames.add("protected");
        reservedNames.add("public");
        reservedNames.add("return");
        reservedNames.add("short");
        reservedNames.add("static");
        reservedNames.add("strictfp");
        reservedNames.add("super");
        reservedNames.add("switch");
        reservedNames.add("synchronized");
        reservedNames.add("this");
        reservedNames.add("throw");
        reservedNames.add("throws");
        reservedNames.add("transient");
        reservedNames.add("try");
        reservedNames.add("void");
        reservedNames.add("volatile");
        reservedNames.add("while");
    }

    public static List<String> getReservedNames() {
        return reservedNames;
    }

    public static String getReservedPrefix() {
        return "$$__$$";
    }

    public static String getReservedODataTypePrefix() {return "$$__ODataType"; }

    public static String getODataTypePrefix() {return "@odata.type";}

    /**
     * Add custom parameters to o data uRL.
     *
     * @param request the request
     * @param parameters the parameters
     * @param headers the custom headers
     */
    public static void addCustomParametersToODataRequest(Request request, Map<String, Object> parameters, Map<String, String> headers) {
        ODataURL url = request.getUrl();
        Set<String> parameterKeys = parameters.keySet();
        for (String name : parameterKeys) {
            Object val = parameters.get(name);
            url.addQueryStringParameter(name, toODataURLValue(val));
        }

        Set<String> headerKeys = headers.keySet();
        for (String name : headerKeys) {
            String val = headers.get(name);
            request.addHeader(name, val);
        }
    }

    public static String getFunctionParameters(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            if (sb.length() > 0) {
                sb.append(",");
            }

            sb.append(key);
            sb.append("=");

            String odataValue = toODataURLValue(map.get(key));
            sb.append(odataValue);
        }

        return sb.toString();
    }

    private static String toODataURLValue(Object o) {
       if (o instanceof String) {
           return "'" + o + "'";
       }

       if (o instanceof Calendar) {
           return "'" + CalendarSerializer.serialize((Calendar)o) + "'";
       }

       return o.toString();
    }

    /**
     * Url encode.
     *
     * @param s the s
     * @return the string
     */
    public static String urlEncode(String s) {
        return percentEncode(s, ENCODE_EXCEPTIONS);
    }

    private static String percentEncode(String s, String reserved) {
        if (s == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(s.length());

        int escapeStart = -1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || "-._~".indexOf(c) != -1 || reserved.indexOf(c) != -1) {
                if (escapeStart != -1) {
                    appendHex(builder, s.substring(escapeStart, i));
                    escapeStart = -1;
                }

                builder.append(c);
            } else if (escapeStart == -1) {
                escapeStart = i;
            }
        }

        if (escapeStart != -1) {
            appendHex(builder, s.substring(escapeStart, s.length()));
        }

        return builder.toString();
    }

    private static void appendHex(StringBuilder builder, String s) {
        try {
            for (byte b : s.getBytes(Constants.UTF8_NAME)) {
                appendHex(builder, b);
            }
        } catch (UnsupportedEncodingException e) {
            // UTF-8 should support any string
        }
    }

    private static void appendHex(StringBuilder sb, byte b) {
        sb.append('%');
        sb.append(String.format("%02X", b));
    }

    /**
     * Serialize to json byte array.
     *
     * @param entity the entity
     * @param resolver the resolver
     * @return the byte [ ]
     */
    public static byte[] serializeToJsonByteArray(Object entity, DependencyResolver resolver) {
        String payload = resolver.getJsonSerializer().serialize(entity);
        return payload.getBytes(Constants.UTF8);
    }
}
