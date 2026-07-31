package com.matlasystems.chat.protocol.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.matlasystems.chat.common.enums.CommandType;

/** Parses command values recognised by the protocol. */
public final class CommandParser {

    public ParseResult<CommandType> parse(JsonNode node) {
        return node == null || !node.isTextual()
                ? ParseResult.failure("command must be a string")
                : parse(node.asText());
    }

    public ParseResult<CommandType> parse(String value) {
        if (value == null || value.isBlank()) {
            return ParseResult.failure("command is required");
        }
        return CommandType.fromValue(value)
                .<ParseResult<CommandType>>map(ParseResult::success)
                .orElseGet(() -> ParseResult.failure("Unsupported command: " + value));
    }
}
