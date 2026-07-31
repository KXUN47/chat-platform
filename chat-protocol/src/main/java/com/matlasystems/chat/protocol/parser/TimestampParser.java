package com.matlasystems.chat.protocol.parser;

import java.time.Instant;
import com.fasterxml.jackson.databind.JsonNode;

/** Parses ISO-8601 packet timestamps. */
public final class TimestampParser {

    public ParseResult<Instant> parse(JsonNode node) {
        return node == null || !node.isTextual()
                ? ParseResult.failure("timestamp must be an ISO-8601 string")
                : parse(node.asText());
    }

    public ParseResult<Instant> parse(String value) {
        if (value == null || value.isBlank()) {
            return ParseResult.failure("timestamp is required");
        }
        try {
            return ParseResult.success(Instant.parse(value));
        } catch (RuntimeException exception) {
            return ParseResult.failure("Invalid timestamp: " + value, exception);
        }
    }
}
