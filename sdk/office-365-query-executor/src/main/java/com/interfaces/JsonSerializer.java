package com.interfaces;

import java.util.List;

public interface JsonSerializer {
    public String serialize(Object objectToSerialize);

    public <E> E deserialize(String serializedObject, Class<E> clazz) throws Throwable;

    public <E> List<E> deserializeList(String serializedList, Class<E> clazz) throws Throwable;
}
