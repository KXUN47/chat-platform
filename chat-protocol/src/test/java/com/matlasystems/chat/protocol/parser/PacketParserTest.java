package com.matlasystems.chat.protocol.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matlasystems.chat.common.protocol.Packet;

/**
 * Unit tests for {@link PacketParser}.
 */
class PacketParserTest {

    private final PacketParser parser = new PacketParser();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private String header(UUID packetId) {

        return """
                "packetId": "%s",
                "timestamp": "2026-01-01T00:00:00Z",
                "protocolVersion": "1.0",
                "command": "PING",
                "status": "REQUEST"
                """.formatted(packetId);

    }

    @Test
    void parsesAPacketWithANestedHeaderField() throws Exception {

        UUID packetId = UUID.randomUUID();

        String json = """
                {
                  "header": { %s },
                  "payload": "hello"
                }
                """.formatted(header(packetId));

        Packet packet = parser.parse(objectMapper.readTree(json)).getOrThrow();

        assertEquals(packetId, packet.getHeader().getPacketId());
        assertEquals("hello", packet.getPayload());
    }

    @Test
    void parsesAFlatPacketWithoutANestedHeaderField() throws Exception {

        UUID packetId = UUID.randomUUID();

        String json = "{ %s }".formatted(header(packetId));

        Packet packet = parser.parse(objectMapper.readTree(json)).getOrThrow();

        assertEquals(packetId, packet.getHeader().getPacketId());
    }

    @Test
    void payloadIsOptional() throws Exception {

        String json = "{ %s }".formatted(header(UUID.randomUUID()));

        Packet packet = parser.parse(objectMapper.readTree(json)).getOrThrow();

        assertEquals(null, packet.getPayload());
    }

    @Test
    void failsWhenNodeIsNotAnObject() throws Exception {

        assertTrue(parser.parse(objectMapper.readTree("\"not an object\"")).isFailure());
    }

    @Test
    void failsWhenHeaderIsInvalid() throws Exception {

        String json = """
                {
                  "header": { "command": "PING" },
                  "payload": "hello"
                }
                """;

        assertTrue(parser.parse(objectMapper.readTree(json)).isFailure());
    }

}
