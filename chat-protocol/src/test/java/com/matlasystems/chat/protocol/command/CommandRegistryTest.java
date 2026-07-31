package com.matlasystems.chat.protocol.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.protocol.version.ProtocolVersion;

/**
 * Unit tests for {@link CommandRegistry}.
 */
class CommandRegistryTest {

    private CommandDefinition login() {

        return new CommandDefinition(
                "LOGIN",
                CommandCategory.AUTHENTICATION,
                "Authenticate",
                false,
                CommandPermission.PUBLIC,
                ProtocolVersion.current());

    }

    @Test
    void registersAndFindsCommandsByName() {

        CommandRegistry registry =
                new CommandRegistry();

        registry.register(
                login());

        assertTrue(
                registry.exists("LOGIN"));

        assertEquals(
                "LOGIN",
                registry.find("LOGIN")
                        .orElseThrow()
                        .getName());

        assertEquals(
                1,
                registry.size());

        assertFalse(
                registry.isEmpty());

    }

    @Test
    void registerAllRegistersEveryDefinition() {

        CommandRegistry registry =
                new CommandRegistry();

        CommandDefinition logout =
                new CommandDefinition(
                        "LOGOUT",
                        CommandCategory.AUTHENTICATION,
                        "Logout",
                        true,
                        CommandPermission.AUTHENTICATED,
                        ProtocolVersion.current());

        registry.registerAll(
                List.of(
                        login(),
                        logout));

        assertEquals(
                2,
                registry.size());

    }

    @Test
    void rejectsNullDefinition() {

        CommandRegistry registry =
                new CommandRegistry();

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> registry.register(null));

        assertNotNull(
                exception.getMessage());

    }

    @Test
    void removeAndClearEmptyTheRegistry() {

        CommandRegistry registry =
                new CommandRegistry();

        registry.register(
                login());

        registry.remove(
                "LOGIN");

        assertFalse(
                registry.exists("LOGIN"));

        registry.register(
                login());

        registry.clear();

        assertTrue(
                registry.isEmpty());

    }

    @Test
    void getRegisteredCommandsIsUnmodifiable() {

        CommandRegistry registry =
                new CommandRegistry();

        registry.register(
                login());

        Map<String, CommandDefinition> registeredCommands =
                registry.getRegisteredCommands();

        UnsupportedOperationException exception =
                assertThrows(
                        UnsupportedOperationException.class,
                        registeredCommands::clear);

        assertNotNull(
                exception);

    }

}
