package com.matlasystems.chat.protocol.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.node.TextNode;
import com.matlasystems.chat.common.enums.CommandType;

/**
 * Unit tests for {@link CommandParser}.
 */
class CommandParserTest {

    private final CommandParser parser = new CommandParser();

    @Test
    void parsesAKnownCommandFromAString() {

        assertEquals(CommandType.LOGIN, parser.parse("LOGIN").getOrThrow());
    }

    @Test
    void parsesAKnownCommandFromAJsonNode() {

        assertEquals(CommandType.PING, parser.parse(new TextNode("PING")).getOrThrow());
    }

    @Test
    void failsOnBlankInput() {

        assertTrue(parser.parse("").isFailure());
    }

    @Test
    void failsOnUnknownCommand() {

        assertTrue(parser.parse("DOES_NOT_EXIST").isFailure());
    }

    @Test
    void failsOnNonTextualNode() {

        assertTrue(parser.parse((com.fasterxml.jackson.databind.JsonNode) null).isFailure());
    }

}
