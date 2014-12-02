package com.microsoft.services.odata;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.services.odata.interfaces.DependencyResolver;
import com.microsoft.services.odata.interfaces.LogLevel;
import com.microsoft.services.odata.interfaces.ODataResponse;
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

    /**
     * Add custom parameters to o data uRL.
     *
     * @param request    the request
     * @param parameters the parameters
     * @param headers    the custom headers
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
            return CalendarSerializer.serialize((Calendar) o);
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
     * @param entity   the entity
     * @param resolver the resolver
     * @return the byte [ ]
     */
    public static byte[] serializeToJsonByteArray(Object entity, DependencyResolver resolver) {
        String payload = resolver.getJsonSerializer().serialize(entity);
        return payload.getBytes(Constants.UTF8);
    }

    /**
     * Apply string listenable future.
     *
     * @param future the future
     * @return the listenable future
     */
    public static ListenableFuture<String> transformToStringListenableFuture(ListenableFuture<ODataResponse> future) {

        return Futures.transform(future, new AsyncFunction<ODataResponse, String>() {
            @Override
            public ListenableFuture<String> apply(ODataResponse response) throws Exception {
                final SettableFuture<String> result = SettableFuture.create();
                result.set(new String(response.getPayload(), Constants.UTF8_NAME));
                return result;
            }
        });
    }


    /**
     * Apply string listenable future.
     *
     * @param future the future
     * @return the listenable future
     */
    public static <TEntity> ListenableFuture<TEntity> transformToEntityListenableFuture(
            ListenableFuture<String> future,
            final Class<TEntity> clazz,
            final DependencyResolver resolver) {

        return Futures.transform(future, new AsyncFunction<String, TEntity>() {
            @Override
            public ListenableFuture<TEntity> apply(String payload) throws Exception {
                final SettableFuture<TEntity> result = SettableFuture.create();
                TEntity entity = null;
                try {
                    resolver.getLogger().log("Entity Deserialization Started", LogLevel.VERBOSE);
                    entity = resolver.getJsonSerializer().deserialize(payload, clazz);
                    resolver.getLogger().log("Entity Deserialization Finished", LogLevel.VERBOSE);

                } catch (Throwable throwable) {
                    result.setException(throwable);
                }
                result.set(entity);
                return result;
            }

            ;
        });
    }

    /**
     * Add list result callback.
     *
     * @param future the future
     */
    public static <TEntity> ListenableFuture<List<TEntity>> transformToEntityListListenableFuture(
            ListenableFuture<String> future,
            final Class<TEntity> clazz,
            final DependencyResolver resolver) {

        return Futures.transform(future, new AsyncFunction<String, List<TEntity>>() {
            @Override
            public ListenableFuture<List<TEntity>> apply(String payload) throws Exception {
                SettableFuture<List<TEntity>> result = SettableFuture.create();
                List<TEntity> list;
                try {
                    resolver.getLogger().log("Entity collection Deserialization Started", LogLevel.VERBOSE);
                    list = resolver.getJsonSerializer().deserializeList(payload, clazz);
                    resolver.getLogger().log("Entity collection Deserialization Finished", LogLevel.VERBOSE);

                    result.set(list);
                } catch (Throwable t) {
                    result.setException(t);
                }

                return result;
            }
        });
    }

    /**
     * Add null result callback.
     *
     * @param future the future
     */
    public static ListenableFuture<Void> transformToVoidListenableFuture(ListenableFuture<ODataResponse> future) {
        return Futures.transform(future, new AsyncFunction<ODataResponse, Void>() {

            @Override
            public ListenableFuture<Void> apply(ODataResponse input) throws Exception {
                SettableFuture<Void> result = SettableFuture.create();
                result.set(null);
                return result;
            }
        });
    }


    /**
     * Transform to byte array listenable future.
     *
     * @param future the future
     * @return the listenable future
     */
    public static ListenableFuture<byte[]> transformToByteArrayListenableFuture(ListenableFuture<byte[]> future) {

        return Futures.transform(future, new AsyncFunction<byte[], byte[]>() {

            @Override
            public ListenableFuture<byte[]> apply(byte[] input) throws Exception {
                SettableFuture<byte[]> result = SettableFuture.create();
                result.set(input);
                return result;
            }
        });
    }

}
