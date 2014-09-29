package com.impl;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.interfaces.JsonSerializer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GsonSerializer implements JsonSerializer {

    private Gson createGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
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

        Gson serializer = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();

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
