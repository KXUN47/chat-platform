package com.matlasystems.chat.protocol.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link CommandException}.
 */
class CommandExceptionTest {

    @Test
    void tracksCommandNameAndErrorCodeWhenProvided() {

        CommandException exception = new CommandException("bad command", "LOGIN", "1003");

        assertEquals("bad command", exception.getMessage());
        assertEquals("LOGIN", exception.getCommandName());
        assertEquals("1003", exception.getErrorCode());
        assertNotNull(exception.getTimestamp());
        assertTrue(exception.hasCommandName());
        assertTrue(exception.hasErrorCode());
    }

    @Test
    void treatsMissingCommandNameAndErrorCodeAsAbsent() {

        CommandException exception = new CommandException("bad command");

        assertFalse(exception.hasCommandName());
        assertFalse(exception.hasErrorCode());
    }

    @Test
    void equalityIsReflexiveAndDistinguishesDifferentContent() {

        CommandException first =
                new CommandException(
                        "bad command",
                        "LOGIN",
                        "1003");

        CommandException different =
                new CommandException(
                        "different command",
                        "LOGOUT",
                        "1004");

        assertEquals(
                first,
                first);

        assertNotEquals(
                first,
                different);

        assertTrue(
                first.toString().contains("bad command"));

        assertTrue(
                first.toString().contains("LOGIN"));

    }

}
