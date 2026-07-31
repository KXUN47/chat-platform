package com.matlasystems.chat.protocol.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;

/**
 * Unit tests for {@link MetadataParser}.
 */
class MetadataParserTest {

    private final MetadataParser parser = new MetadataParser();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void nullNodeYieldsAnEmptyMap() {

        assertEquals(Map.of(), parser.parse(null).getOrThrow());
        assertEquals(Map.of(), parser.parse(NullNode.getInstance()).getOrThrow());
    }

    @Test
    void objectNodeYieldsAScalarMap() throws Exception {

        var node = objectMapper.readTree("{\"clientVersion\":\"1.0\",\"platform\":\"web\"}");

        Map<String, String> metadata = parser.parse(node).getOrThrow();

        assertEquals("1.0", metadata.get("clientVersion"));
        assertEquals("web", metadata.get("platform"));
    }

    @Test
    void nonObjectNodeFails() throws Exception {

        assertTrue(parser.parse(objectMapper.readTree("\"not an object\"")).isFailure());
    }

    @Test
    void nonScalarFieldFails() throws Exception {

        assertTrue(parser.parse(objectMapper.readTree("{\"nested\":{\"a\":1}}")).isFailure());
    }

}
