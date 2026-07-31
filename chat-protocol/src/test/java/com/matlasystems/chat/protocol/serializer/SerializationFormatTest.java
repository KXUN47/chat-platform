package com.matlasystems.chat.protocol.serializer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link SerializationFormat}.
 */
class SerializationFormatTest {

    @Test
    void resolvesAKnownContentTypeCaseInsensitively() {

        assertEquals(SerializationFormat.JSON,
                SerializationFormat.fromContentType("APPLICATION/JSON"));

        assertEquals("application/json", SerializationFormat.JSON.getContentType());
    }

    @Test
    void rejectsAnUnknownContentType() {

        assertThrows(UnsupportedSerializationException.class,
                () -> SerializationFormat.fromContentType("application/xml"));

    }

}
