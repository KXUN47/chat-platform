package com.matlasystems.chat.protocol.serializer;

/** Converts a transport representation into an in-memory value. */
@FunctionalInterface
public interface Deserializer<T> {

    T deserialize(byte[] data);
}
