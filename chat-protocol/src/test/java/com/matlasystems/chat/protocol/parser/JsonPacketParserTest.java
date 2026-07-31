package com.matlasystems.chat.protocol.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.protocol.Packet;

/**
 * Unit tests for {@link JsonPacketParser}.
 */
class JsonPacketParserTest {

    private final JsonPacketParser parser = new JsonPacketParser();

    private String validPacketJson(UUID packetId) {

        return """
                {
                  "packetId": "%s",
                  "timestamp": "2026-01-01T00:00:00Z",
                  "protocolVersion": "1.0",
                  "command": "PING",
                  "status": "REQUEST",
                  "payload": "hello"
                }
                """.formatted(packetId);

    }

    @Test
    void parsesAValidJsonString() {

        UUID packetId = UUID.randomUUID();

        Packet packet = parser.parse(validPacketJson(packetId)).getOrThrow();

        assertEquals(packetId, packet.getHeader().getPacketId());
        assertEquals("hello", packet.getPayload());
    }

    @Test
    void parsesAValidJsonByteArray() {

        UUID packetId = UUID.randomUUID();
        byte[] json = validPacketJson(packetId).getBytes(StandardCharsets.UTF_8);

        Packet packet = parser.parse(json).getOrThrow();

        assertEquals(packetId, packet.getHeader().getPacketId());
    }

    @Test
    void failsOnBlankInput() {

        assertTrue(parser.parse("").isFailure());
        assertTrue(parser.parse((byte[]) null).isFailure());
    }

    @Test
    void failsOnMalformedJson() {

        assertTrue(parser.parse("{ not valid json").isFailure());
    }

}
