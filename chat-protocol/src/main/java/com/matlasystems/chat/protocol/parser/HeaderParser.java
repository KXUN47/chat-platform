package com.matlasystems.chat.protocol.parser;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;
import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.PacketStatus;
import com.matlasystems.chat.common.protocol.ProtocolVersion;

/** Parses and validates the standard metadata header of a protocol packet. */
public final class HeaderParser {

    private final VersionParser versionParser;
    private final TimestampParser timestampParser;
    private final CommandParser commandParser;

    public HeaderParser() {
        this(new VersionParser(), new TimestampParser(), new CommandParser());
    }

    public HeaderParser(VersionParser versionParser, TimestampParser timestampParser,
                        CommandParser commandParser) {
        this.versionParser = versionParser;
        this.timestampParser = timestampParser;
        this.commandParser = commandParser;
    }

    public ParseResult<Header> parse(JsonNode node) {
        if (node == null || !node.isObject()) {
            return ParseResult.failure("header must be a JSON object");
        }

        ParseResult<UUID> packetId = parsePacketId(field(node, "packetId", "id"));
        ParseResult<Instant> timestamp = timestampParser.parse(node.get("timestamp"));
        ParseResult<ProtocolVersion> version = versionParser.parse(node.get("protocolVersion"));
        ParseResult<CommandType> command = commandParser.parse(node.get("command"));
        ParseResult<PacketStatus> status = parseStatus(node.get("status"));

        if (packetId.isFailure()) {
            return failure(packetId);
        }
        if (timestamp.isFailure()) {
            return failure(timestamp);
        }
        if (version.isFailure()) {
            return failure(version);
        }
        if (command.isFailure()) {
            return failure(command);
        }
        if (status.isFailure()) {
            return failure(status);
        }

        return ParseResult.success(new Header(packetId.getOrThrow(), timestamp.getOrThrow(),
                version.getOrThrow(), command.getOrThrow(), status.getOrThrow()));
    }

    private JsonNode field(JsonNode node, String primary, String alias) {
        return node.has(primary) ? node.get(primary) : node.get(alias);
    }

    private ParseResult<UUID> parsePacketId(JsonNode node) {
        if (node == null || !node.isTextual()) {
            return ParseResult.failure("packetId is required");
        }
        try {
            return ParseResult.success(UUID.fromString(node.asText()));
        } catch (IllegalArgumentException exception) {
            return ParseResult.failure("Invalid packetId", exception);
        }
    }

    private ParseResult<PacketStatus> parseStatus(JsonNode node) {
        if (node == null || !node.isTextual()) {
            return ParseResult.failure("status is required");
        }
        try {
            return ParseResult.success(PacketStatus.valueOf(node.asText().toUpperCase()));
        } catch (IllegalArgumentException exception) {
            return ParseResult.failure("Invalid packet status", exception);
        }
    }

    private <T> ParseResult<Header> failure(ParseResult<T> result) {
        return ParseResult.failure(result.getError().orElse("Invalid header"), result.getCause().orElse(null));
    }
}
