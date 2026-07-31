package com.matlasystems.chat.protocol.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.node.TextNode;
import com.matlasystems.chat.common.protocol.ProtocolVersion;

/**
 * Unit tests for the parser package's {@link VersionParser}, which parses
 * the wire-level {@link ProtocolVersion} carried in a packet header.
 */
class VersionParserTest {

    private final VersionParser parser = new VersionParser();

    @Test
    void parsesAKnownVersionValue() {

        assertEquals(ProtocolVersion.V1_0, parser.parse("1.0").getOrThrow());
        assertEquals(ProtocolVersion.V1_0, parser.parse(new TextNode("1.0")).getOrThrow());
    }

    @Test
    void parsesAnEnumNameAsAFallback() {

        assertEquals(ProtocolVersion.V1_0, parser.parse("V1_0").getOrThrow());
    }

    @Test
    void failsOnBlankInput() {

        assertTrue(parser.parse("").isFailure());
    }

    @Test
    void failsOnUnsupportedVersion() {

        assertTrue(parser.parse("9.9").isFailure());
    }

}
