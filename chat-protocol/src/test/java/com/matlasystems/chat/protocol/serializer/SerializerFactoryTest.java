package com.matlasystems.chat.protocol.serializer;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link SerializerFactory}.
 */
class SerializerFactoryTest {

    @Test
    void createsJsonComponentsForTheJsonFormat() {

        assertInstanceOf(JsonPacketSerializer.class,
                SerializerFactory.packetSerializer(SerializationFormat.JSON));

        assertInstanceOf(JsonPacketDeserializer.class,
                SerializerFactory.packetDeserializer(SerializationFormat.JSON));

        assertInstanceOf(JsonPayloadSerializer.class,
                SerializerFactory.payloadSerializer(SerializationFormat.JSON));

        assertInstanceOf(JsonPayloadDeserializer.class,
                SerializerFactory.payloadDeserializer(SerializationFormat.JSON));

    }

    @Test
    void rejectsANullFormat() {

        assertThrows(NullPointerException.class,
                () -> SerializerFactory.packetSerializer(null));

    }

}
