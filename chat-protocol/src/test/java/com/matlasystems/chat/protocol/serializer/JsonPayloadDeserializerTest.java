package com.matlasystems.chat.protocol.serializer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link JsonPayloadDeserializer}.
 */
class JsonPayloadDeserializerTest {

    private final JsonPayloadSerializer serializer =
            new JsonPayloadSerializer();

    private final JsonPayloadDeserializer deserializer =
            new JsonPayloadDeserializer();

    @Test
    void deserializesToAGenericObjectByDefault() {

        byte[] serializedPayload =
                serializer.serialize(
                        "hello");

        Object value =
                deserializer.deserialize(
                        serializedPayload);

        assertEquals(
                "hello",
                value);

    }

    @Test
    void deserializesToARequestedType() {

        byte[] serializedPayload =
                serializer.serialize(
                        "hello");

        String value =
                deserializer.deserialize(
                        serializedPayload,
                        String.class);

        assertEquals(
                "hello",
                value);

    }

    @Test
    void rejectsMalformedJson() {

        byte[] malformedPayload =
                "not json".getBytes(
                        StandardCharsets.UTF_8);

        SerializationException exception =
                assertThrows(
                        SerializationException.class,
                        () -> deserializer.deserialize(
                                malformedPayload,
                                String.class));

        assertNotNull(
                exception);

    }

}
