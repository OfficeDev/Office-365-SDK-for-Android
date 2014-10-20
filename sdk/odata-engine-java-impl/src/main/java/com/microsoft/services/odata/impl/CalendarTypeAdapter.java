package com.microsoft.services.odata.impl;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.microsoft.services.odata.CalendarSerializer;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Calendar;

/**
 * The type Calendar type adapter.
 */
public class CalendarTypeAdapter implements com.google.gson.JsonSerializer<Calendar>, com.google.gson.JsonDeserializer<Calendar> {

    @Override
    public Calendar deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String strVal = json.getAsString();

        try {
            return CalendarSerializer.deserialize(strVal);
        }
        catch (ParseException e) {
            throw new JsonParseException(e);
        }
    }


    @Override
    public JsonElement serialize(Calendar src, Type typeOfSrc, JsonSerializationContext context) {
        String formatted = CalendarSerializer.serialize(src);

        return new JsonPrimitive(formatted);
    }



}
