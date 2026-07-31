package com.matlasystems.chat.protocol.parser;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.databind.JsonNode;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.Packet;

/** Parses a JSON tree into the standard protocol packet envelope. */
public final class PacketParser {

    private final HeaderParser headerParser;
    private final PayloadParser payloadParser;

    public PacketParser() {
        this(new HeaderParser(), new PayloadParser());
    }

    public PacketParser(HeaderParser headerParser, PayloadParser payloadParser) {
        this.headerParser = Objects.requireNonNull(headerParser, "headerParser must not be null");
        this.payloadParser = Objects.requireNonNull(payloadParser, "payloadParser must not be null");
    }

    public ParseResult<Packet> parse(JsonNode node) {
        if (node == null || !node.isObject()) {
            return ParseResult.failure("packet must be a JSON object");
        }

        JsonNode headerNode = node.has("header") ? node.get("header") : node;
        ParseResult<Header> header = headerParser.parse(headerNode);
        if (header.isFailure()) {
            return failure(header);
        }

        ParseResult<Serializable> payload = payloadParser.parse(node.get("payload"));
        if (payload.isFailure()) {
            return failure(payload);
        }

        return ParseResult.success(new Packet(header.getOrThrow(), payload.getOrThrow()));
    }

    private <T> ParseResult<Packet> failure(ParseResult<T> result) {
        return ParseResult.failure(result.getError().orElse("Invalid packet"),
                result.getCause().orElse(null));
    }
}
