package com.infrastructure;

import com.google.common.collect.Lists;
import com.google.gson.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by marcote on 9/26/14.
 */
public class GsonSerializer implements JsonSerializer {
    @Override
    public <E> E deserialize(String payload, Class<E> clazz) {

        Logger.log(payload);

        Gson serializer = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();

        return serializer.fromJson(payload, clazz);
    }

    @Override
    public <E> List<E> deserializeList(String payload, Class<E> clazz) {
        Class arrayClass = Array.newInstance(clazz, 0).getClass();

        Logger.log(payload);

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
