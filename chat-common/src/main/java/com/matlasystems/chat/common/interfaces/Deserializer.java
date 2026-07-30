package com.matlasystems.chat.common.interfaces;

import com.matlasystems.chat.common.exception.SerializationException;

/**
 * Defines a contract for converting serialized data
 * into Java objects.
 *
 * @param <T> target object type
 */
@FunctionalInterface
public interface Deserializer<T> {

    /**
     * Converts serialized data into an object.
     *
     * @param data serialized input
     * @return deserialized object
     * @throws SerializationException if deserialization fails
     */
    T deserialize(String data);

}
