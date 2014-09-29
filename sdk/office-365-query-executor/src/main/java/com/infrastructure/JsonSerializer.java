package com.infrastructure;

import java.util.List;

/**
 * Created by marcote on 9/25/14.
 */
public interface JsonSerializer {
    public <E> E deserialize(String s, Class<E> clazz);
    public <E> List<E> deserializeList(String s, Class<E> clazz);
}
