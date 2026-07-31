package com.matlasystems.chat.protocol.parser;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matlasystems.chat.common.protocol.Packet;

/** Entry point for parsing UTF-8 JSON packet documents. */
public final class JsonPacketParser {

    private final ObjectMapper objectMapper;
    private final PacketParser packetParser;

    public JsonPacketParser() {
        this(new ObjectMapper().findAndRegisterModules(), new PacketParser());
    }

    public JsonPacketParser(ObjectMapper objectMapper, PacketParser packetParser) {
        this.objectMapper = Objects.requireNonNull(objectMapper, "objectMapper must not be null");
        this.packetParser = Objects.requireNonNull(packetParser, "packetParser must not be null");
    }

    public ParseResult<Packet> parse(String json) {
        if (json == null || json.isBlank()) {
            return ParseResult.failure("JSON packet is required");
        }
        try {
            JsonNode node = objectMapper.readTree(json);
            return packetParser.parse(node);
        } catch (JsonProcessingException exception) {
            return ParseResult.failure("Malformed JSON packet", exception);
        }
    }

    public ParseResult<Packet> parse(byte[] json) {
        return json == null ? ParseResult.failure("JSON packet is required")
                : parse(new String(json, StandardCharsets.UTF_8));
    }
}
