package com.infrastructure;

/**
 * Created by marcote on 9/25/14.
 */
public interface JsonSerializer {
    public <E> E deserialize(String s, Class<E> clazz);
}
