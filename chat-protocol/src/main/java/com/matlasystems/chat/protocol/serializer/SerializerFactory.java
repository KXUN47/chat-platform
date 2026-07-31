package com.matlasystems.chat.protocol.serializer;

import java.util.Objects;

/** Creates serializer components for a supported protocol format. */
public final class SerializerFactory {

    private SerializerFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static PacketSerializer packetSerializer(SerializationFormat format) {
        requireSupported(format);
        return new JsonPacketSerializer();
    }

    public static PacketDeserializer packetDeserializer(SerializationFormat format) {
        requireSupported(format);
        return new JsonPacketDeserializer();
    }

    public static PayloadSerializer payloadSerializer(SerializationFormat format) {
        requireSupported(format);
        return new JsonPayloadSerializer();
    }

    public static PayloadDeserializer payloadDeserializer(SerializationFormat format) {
        requireSupported(format);
        return new JsonPayloadDeserializer();
    }

    private static void requireSupported(SerializationFormat format) {
        if (Objects.requireNonNull(format, "format must not be null") != SerializationFormat.JSON) {
            throw new UnsupportedSerializationException("Unsupported serialization format: " + format);
        }
    }
}
