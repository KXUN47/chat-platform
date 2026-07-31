package com.matlasystems.chat.protocol.parser;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/** Converts a JSON payload node into a serializable Java value. */
public final class PayloadParser {

    private final ObjectMapper objectMapper;

    public PayloadParser() {
        this(new ObjectMapper().findAndRegisterModules());
    }

    public PayloadParser(ObjectMapper objectMapper) {
        this.objectMapper = Objects.requireNonNull(objectMapper, "objectMapper must not be null");
    }

    public ParseResult<Serializable> parse(JsonNode node) {
        if (node == null || node.isNull()) {
            return ParseResult.success(null);
        }
        try {
            Object value = objectMapper.treeToValue(node, Object.class);
            if (!(value instanceof Serializable serializable)) {
                return ParseResult.failure("payload is not serializable");
            }
            return ParseResult.success(serializable);
        } catch (JsonProcessingException exception) {
            return ParseResult.failure("Unable to parse payload", exception);
        }
    }
}
