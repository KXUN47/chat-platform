package com.matlasystems.chat.common.interfaces;

import com.matlasystems.chat.common.exception.SerializationException;

/**
 * Defines a contract for converting an object into a serialized format.
 *
 * @param <T> the object type to serialize
 */
@FunctionalInterface
public interface Serializer<T> {

    /**
     * Converts the supplied object into a serialized String.
     *
     * @param object object to serialize
     * @return serialized representation
     * @throws SerializationException if serialization fails
     */
    String serialize(T object);

}
