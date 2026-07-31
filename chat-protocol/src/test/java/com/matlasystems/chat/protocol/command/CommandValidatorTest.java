package com.matlasystems.chat.protocol.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.protocol.version.ProtocolVersion;

/**
 * Unit tests for {@link CommandValidator}.
 */
class CommandValidatorTest {

    /**
     * Creates a validator populated with test commands.
     *
     * @return configured command validator
     */
    private CommandValidator createValidator() {

        CommandRegistry registry =
                new CommandRegistry();

        registry.register(
                new CommandDefinition(
                        "SEND_MESSAGE",
                        CommandCategory.MESSAGING,
                        "Send a message",
                        true,
                        CommandPermission.AUTHENTICATED,
                        ProtocolVersion.current()));

        return new CommandValidator(
                registry);

    }

    @Test
    void validatesAKnownAuthenticatedCommand() {

        CommandValidator validator =
                createValidator();

        ProtocolVersion protocolVersion =
                ProtocolVersion.current();

        CommandDefinition result =
                validator.validate(
                        "SEND_MESSAGE",
                        true,
                        CommandPermission.AUTHENTICATED,
                        protocolVersion);

        assertEquals(
                "SEND_MESSAGE",
                result.getName());

    }

    @Test
    void rejectsAnUnknownCommand() {

        CommandValidator validator =
                createValidator();

        ProtocolVersion protocolVersion =
                ProtocolVersion.current();

        UnknownCommandException exception =
                assertThrows(
                        UnknownCommandException.class,
                        () -> validator.validate(
                                "DOES_NOT_EXIST",
                                true,
                                CommandPermission.AUTHENTICATED,
                                protocolVersion));

        assertNotNull(
                exception.getMessage());

    }

    @Test
    void rejectsAMismatchedProtocolVersion() {

        CommandValidator validator =
                createValidator();

        CommandDefinition command =
                new CommandDefinition(
                        "SEND_MESSAGE",
                        CommandCategory.MESSAGING,
                        "Send a message",
                        true,
                        CommandPermission.AUTHENTICATED,
                        ProtocolVersion.current());

        ProtocolVersion unsupportedVersion =
                ProtocolVersion.of(
                        99,
                        0);

        UnsupportedCommandException exception =
                assertThrows(
                        UnsupportedCommandException.class,
                        () -> validator.validateVersion(
                                command,
                                unsupportedVersion));

        assertNotNull(
                exception.getMessage());

    }

    @Test
    void rejectsAnUnauthenticatedCallerWhenAuthenticationIsRequired() {

        CommandValidator validator =
                createValidator();

        ProtocolVersion protocolVersion =
                ProtocolVersion.current();

        UnauthorizedCommandException exception =
                assertThrows(
                        UnauthorizedCommandException.class,
                        () -> validator.validate(
                                "SEND_MESSAGE",
                                false,
                                CommandPermission.AUTHENTICATED,
                                protocolVersion));

        assertNotNull(
                exception.getMessage());

    }

    @Test
    void rejectsInsufficientPermission() {

        CommandValidator validator =
                createValidator();

        ProtocolVersion protocolVersion =
                ProtocolVersion.current();

        UnauthorizedCommandException exception =
                assertThrows(
                        UnauthorizedCommandException.class,
                        () -> validator.validate(
                                "SEND_MESSAGE",
                                true,
                                CommandPermission.PUBLIC,
                                protocolVersion));

        assertNotNull(
                exception.getMessage());

    }

    @Test
    void rejectsANullPayload() {

        CommandValidator validator =
                createValidator();

        InvalidCommandException exception =
                assertThrows(
                        InvalidCommandException.class,
                        () -> validator.validatePayload(
                                null));

        assertNotNull(
                exception.getMessage());

    }

}
