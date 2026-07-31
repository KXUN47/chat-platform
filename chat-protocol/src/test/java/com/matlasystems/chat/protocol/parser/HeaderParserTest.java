package com.matlasystems.chat.protocol.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.PacketStatus;

/**
 * Unit tests for {@link HeaderParser}.
 */
class HeaderParserTest {

    private final HeaderParser parser = new HeaderParser();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private String validHeaderJson(UUID packetId) {

        return """
                {
                  "packetId": "%s",
                  "timestamp": "2026-01-01T00:00:00Z",
                  "protocolVersion": "1.0",
                  "command": "PING",
                  "status": "REQUEST"
                }
                """.formatted(packetId);

    }

    @Test
    void parsesAFullyPopulatedHeader() throws Exception {

        UUID packetId = UUID.randomUUID();
        JsonNode node = objectMapper.readTree(validHeaderJson(packetId));

        Header header = parser.parse(node).getOrThrow();

        assertEquals(packetId, header.getPacketId());
        assertEquals(CommandType.PING, header.getCommand());
        assertEquals(PacketStatus.REQUEST, header.getStatus());
    }

    @Test
    void acceptsIdAsAnAliasForPacketId() throws Exception {

        UUID packetId = UUID.randomUUID();

        String json = """
                {
                  "id": "%s",
                  "timestamp": "2026-01-01T00:00:00Z",
                  "protocolVersion": "1.0",
                  "command": "PING",
                  "status": "REQUEST"
                }
                """.formatted(packetId);

        Header header = parser.parse(objectMapper.readTree(json)).getOrThrow();

        assertEquals(packetId, header.getPacketId());
    }

    @Test
    void failsWhenNodeIsNotAnObject() throws Exception {

        assertTrue(parser.parse(objectMapper.readTree("\"not an object\"")).isFailure());
    }

    @Test
    void failsWhenPacketIdIsMissing() throws Exception {

        String json = """
                {
                  "timestamp": "2026-01-01T00:00:00Z",
                  "protocolVersion": "1.0",
                  "command": "PING",
                  "status": "REQUEST"
                }
                """;

        assertTrue(parser.parse(objectMapper.readTree(json)).isFailure());
    }

    @Test
    void failsWhenCommandIsUnsupported() throws Exception {

        String json = """
                {
                  "packetId": "%s",
                  "timestamp": "2026-01-01T00:00:00Z",
                  "protocolVersion": "1.0",
                  "command": "NOT_A_COMMAND",
                  "status": "REQUEST"
                }
                """.formatted(UUID.randomUUID());

        assertTrue(parser.parse(objectMapper.readTree(json)).isFailure());
    }

    @Test
    void failsWhenStatusIsInvalid() throws Exception {

        String json = """
                {
                  "packetId": "%s",
                  "timestamp": "2026-01-01T00:00:00Z",
                  "protocolVersion": "1.0",
                  "command": "PING",
                  "status": "NOT_A_STATUS"
                }
                """.formatted(UUID.randomUUID());

        assertTrue(parser.parse(objectMapper.readTree(json)).isFailure());
    }

}
