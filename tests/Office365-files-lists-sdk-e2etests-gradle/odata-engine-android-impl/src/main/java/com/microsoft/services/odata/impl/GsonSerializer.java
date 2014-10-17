package com.microsoft.services.odata.impl;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.services.odata.interfaces.JsonSerializer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * The type Gson serializer.
 */
public class GsonSerializer implements JsonSerializer {

    private Gson createGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .registerTypeAdapter(Calendar.class, new CalendarTypeAdapter())
                .registerTypeAdapter(GregorianCalendar.class, new CalendarTypeAdapter())
                .registerTypeAdapter(byte[].class, new ByteArrayTypeAdapter())
                .create();
    }

    @Override
    public String serialize(Object objectToSerialize) {
        Gson serializer = createGson();
        return serializer.toJson(objectToSerialize);
    }

    @Override
    public <E> E deserialize(String payload, Class<E> clazz) {
        Gson serializer = createGson();
        return serializer.fromJson(payload, clazz);
    }

    @Override
    public <E> List<E> deserializeList(String payload, Class<E> clazz) {
        Class arrayClass = Array.newInstance(clazz, 0).getClass();

        Gson serializer = createGson();

        JsonParser parser = new JsonParser();
        JsonObject json = (JsonObject) parser.parse(payload);

        JsonElement jsonArray = json.get("value");

        E[] array = (E[])serializer.fromJson(jsonArray, arrayClass);

        ArrayList<E> arrayList = new ArrayList<E>();

        for (int i = 0; i < array.length; i++) {
            arrayList.add(array[i]);
        }

        return arrayList;
    }
}