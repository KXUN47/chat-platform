package com.matlasystems.chat.protocol.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link SupportedCommands}.
 */
class SupportedCommandsTest {

    @Test
    void allContainsEveryDeclaredCommand() {

        assertTrue(
                SupportedCommands.ALL.contains(
                        SupportedCommands.LOGIN));

        assertTrue(
                SupportedCommands.ALL.contains(
                        SupportedCommands.SEND_MESSAGE));

        assertTrue(
                SupportedCommands.ALL.contains(
                        SupportedCommands.FILE_UPLOAD));

        assertTrue(
                SupportedCommands.ALL.contains(
                        SupportedCommands.SHUTDOWN_SERVER));

        assertEquals(
                25,
                SupportedCommands.ALL.size());

    }

    @Test
    void sendMessageWireValueMatchesMessageCommandType() {

        assertEquals(
                "MESSAGE",
                SupportedCommands.SEND_MESSAGE);

    }

    @Test
    void allIsUnmodifiable() {

        UnsupportedOperationException exception =
                assertThrows(
                        UnsupportedOperationException.class,
                        () -> SupportedCommands.ALL.add(
                                "NEW_COMMAND"));

        assertNotNull(
                exception);

    }

}
