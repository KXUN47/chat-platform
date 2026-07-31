package com.matlasystems.chat.protocol.serializer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link JsonPayloadSerializer}.
 */
class JsonPayloadSerializerTest {

    private final JsonPayloadSerializer serializer = new JsonPayloadSerializer();

    @Test
    void serializesAPayloadAsUtf8Json() {

        byte[] json = serializer.serialize("hello");

        assertTrue(new String(json).contains("hello"));
    }

}
