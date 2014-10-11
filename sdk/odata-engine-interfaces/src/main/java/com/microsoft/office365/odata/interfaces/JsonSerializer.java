package com.microsoft.office365.odata.interfaces;

import java.util.List;

/**
 * The interface Json serializer.
 */
public interface JsonSerializer {
    /**
     * Serialize string.
     *
     * @param objectToSerialize the object to serialize
     * @return the string
     */
    public String serialize(Object objectToSerialize);

    /**
     * Deserialize e.
     *
     * @param <E>  the type parameter
     * @param serializedObject the serialized object
     * @param clazz the clazz
     * @return the e
     * @throws Throwable the throwable
     */
    public <E> E deserialize(String serializedObject, Class<E> clazz) throws Throwable;

    /**
     * Deserialize list.
     *
     * @param <E>  the type parameter
     * @param serializedList the serialized list
     * @param clazz the clazz
     * @return the list
     * @throws Throwable the throwable
     */
    public <E> List<E> deserializeList(String serializedList, Class<E> clazz) throws Throwable;
}
