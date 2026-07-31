package com.matlasystems.chat.protocol.serializer;

/** Converts an in-memory value into its transport representation. */
@FunctionalInterface
public interface Serializer<T> {

    byte[] serialize(T value);
}
