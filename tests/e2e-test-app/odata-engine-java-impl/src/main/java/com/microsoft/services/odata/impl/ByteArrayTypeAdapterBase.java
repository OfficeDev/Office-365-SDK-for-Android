package com.microsoft.services.odata.impl;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;

import java.lang.reflect.Type;

public abstract class ByteArrayTypeAdapterBase implements com.google.gson.JsonSerializer<byte[]>, com.google.gson.JsonDeserializer<byte[]>{

    @Override
    public byte[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        //return Base64.decode(json.getAsString(), Base64.DEFAULT);
        return getBase64Encoder().decode(json.getAsString());
    }

    @Override
    public JsonElement serialize(byte[] src, Type typeOfSrc, JsonSerializationContext context) {
        //return new JsonPrimitive(Base64.encodeToString(src, Base64.DEFAULT));
        return new JsonPrimitive(getBase64Encoder().encode(src));
    }

    protected abstract Base64Encoder getBase64Encoder();
}
