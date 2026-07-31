package com.matlasystems.chat.protocol.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.Serializable;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;

/**
 * Unit tests for {@link PayloadParser}.
 */
class PayloadParserTest {

    private final PayloadParser parser = new PayloadParser();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void nullNodeYieldsANullPayload() {

        assertNull(parser.parse(null).getOrThrow());
        assertNull(parser.parse(NullNode.getInstance()).getOrThrow());
    }

    @Test
    void textNodeYieldsAStringPayload() throws Exception {

        Serializable payload = parser.parse(objectMapper.readTree("\"hello\"")).getOrThrow();

        assertEquals("hello", payload);
    }

    @Test
    void objectNodeYieldsAMapPayload() throws Exception {

        Serializable payload = parser.parse(objectMapper.readTree("{\"key\":\"value\"}")).getOrThrow();

        assertEquals("value", ((java.util.Map<?, ?>) payload).get("key"));
    }

}
