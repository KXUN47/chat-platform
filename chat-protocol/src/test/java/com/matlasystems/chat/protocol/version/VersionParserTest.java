package com.matlasystems.chat.protocol.version;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.node.TextNode;

/**
 * Unit tests for the version package's {@link VersionParser}, which parses
 * major.minor.patch strings into a {@link ProtocolVersion}.
 */
class VersionParserTest {

    private final VersionParser parser = new VersionParser();

    @Test
    void parsesAFullMajorMinorPatchVersion() {

        assertEquals(ProtocolVersion.of(1, 2, 3), parser.parse("1.2.3").getOrThrow());
    }

    @Test
    void parsesAMajorMinorVersionWithoutAPatch() {

        assertEquals(ProtocolVersion.of(1, 2, 0), parser.parse("1.2").getOrThrow());
    }

    @Test
    void parsesFromAJsonNode() {

        assertEquals(ProtocolVersion.of(1, 0, 0), parser.parse(new TextNode("1.0.0")).getOrThrow());
    }

    @Test
    void failsOnBlankInput() {

        assertTrue(parser.parse("").isFailure());
    }

    @Test
    void failsOnMalformedVersionStrings() {

        assertTrue(parser.parse("v1.0").isFailure());
        assertTrue(parser.parse("1").isFailure());
        assertTrue(parser.parse("1.2.3.4").isFailure());
    }

    @Test
    void failsOnNonTextualNode() {

        assertTrue(parser.parse((com.fasterxml.jackson.databind.JsonNode) null).isFailure());
    }

}
