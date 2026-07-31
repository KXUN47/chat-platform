package com.matlasystems.chat.protocol.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.node.TextNode;

/**
 * Unit tests for {@link TimestampParser}.
 */
class TimestampParserTest {

    private final TimestampParser parser = new TimestampParser();

    @Test
    void parsesAnIso8601Timestamp() {

        Instant now = Instant.parse("2026-01-01T00:00:00Z");

        assertEquals(now, parser.parse(now.toString()).getOrThrow());
        assertEquals(now, parser.parse(new TextNode(now.toString())).getOrThrow());
    }

    @Test
    void failsOnBlankInput() {

        assertTrue(parser.parse("").isFailure());
    }

    @Test
    void failsOnMalformedTimestamp() {

        assertTrue(parser.parse("not-a-timestamp").isFailure());
    }

    @Test
    void failsOnNonTextualNode() {

        assertTrue(parser.parse((com.fasterxml.jackson.databind.JsonNode) null).isFailure());
    }

}
