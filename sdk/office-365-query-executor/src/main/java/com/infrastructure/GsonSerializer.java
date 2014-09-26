package com.infrastructure;

/**
 * Created by marcote on 9/26/14.
 */
public class GsonSerializer implements JsonSerializer {
    @Override
    public <E> E deserialize(String payload, Class<E> clazz) {

        GsonSerializer serializer = new GsonSerializer();
        return serializer.deserialize(payload, clazz);
    }
}
