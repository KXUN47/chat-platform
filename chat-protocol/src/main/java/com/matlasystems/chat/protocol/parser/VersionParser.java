package com.matlasystems.chat.protocol.parser;

import com.matlasystems.chat.common.protocol.ProtocolVersion;
import com.fasterxml.jackson.databind.JsonNode;

/** Parses supported protocol versions. */
public final class VersionParser {

    public ParseResult<ProtocolVersion> parse(JsonNode node) {
        if (node == null || !node.isTextual()) {
            return ParseResult.failure("protocolVersion must be a string");
        }
        return parse(node.asText());
    }

    public ParseResult<ProtocolVersion> parse(String value) {
        if (value == null || value.isBlank()) {
            return ParseResult.failure("protocolVersion is required");
        }
        return ProtocolVersion.fromValue(value)
                .or(() -> {
                    try {
                        return java.util.Optional.of(ProtocolVersion.valueOf(value));
                    } catch (IllegalArgumentException exception) {
                        return java.util.Optional.empty();
                    }
                })
                .<ParseResult<ProtocolVersion>>map(ParseResult::success)
                .orElseGet(() -> ParseResult.failure("Unsupported protocol version: " + value));
    }
}
