package com.matlasystems.chat.protocol.serializer;

/** Deserializes a command-specific packet payload. */
public interface PayloadDeserializer extends Deserializer<Object> {

    <T> T deserialize(byte[] data, Class<T> targetType);
}
