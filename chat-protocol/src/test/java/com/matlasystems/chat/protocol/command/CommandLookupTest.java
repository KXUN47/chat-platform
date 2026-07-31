package com.matlasystems.chat.protocol.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.protocol.version.ProtocolVersion;

/**
 * Unit tests for {@link CommandLookup}.
 */
class CommandLookupTest {

    /**
     * Creates a populated command lookup for testing.
     *
     * @return command lookup
     */
    private CommandLookup createLookup() {

        CommandRegistry registry =
                new CommandRegistry();

        registry.register(
                new CommandDefinition(
                        "LOGIN",
                        CommandCategory.AUTHENTICATION,
                        "Authenticate",
                        false,
                        CommandPermission.PUBLIC,
                        ProtocolVersion.current()));

        registry.register(
                new CommandDefinition(
                        "SEND_MESSAGE",
                        CommandCategory.MESSAGING,
                        "Send a message",
                        true,
                        CommandPermission.AUTHENTICATED,
                        ProtocolVersion.current()));

        registry.register(
                new CommandDefinition(
                        "KICK_USER",
                        CommandCategory.ADMIN,
                        "Kick a user",
                        true,
                        CommandPermission.ADMIN,
                        ProtocolVersion.current()));

        return new CommandLookup(
                registry);

    }

    @Test
    void rejectsNullRegistry() {

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> new CommandLookup(null));

        assertNotNull(
                exception.getMessage());

    }

    @Test
    void findsAndChecksExistence() {

        CommandLookup lookup =
                createLookup();

        assertTrue(
                lookup.exists("LOGIN"));

        assertEquals(
                "LOGIN",
                lookup.find("LOGIN")
                        .orElseThrow()
                        .getName());

        assertTrue(
                lookup.find("MISSING")
                        .isEmpty());

    }

    @Test
    void returnsAllCommands() {

        CommandLookup lookup =
                createLookup();

        assertEquals(
                3,
                lookup.getAllCommands().size());

        assertEquals(
                3,
                lookup.count());

        assertFalse(
                lookup.isEmpty());

    }

    @Test
    void filtersByCategory() {

        CommandLookup lookup =
                createLookup();

        List<CommandDefinition> messaging =
                lookup.findByCategory(
                        CommandCategory.MESSAGING);

        assertEquals(
                1,
                messaging.size());

        assertEquals(
                "SEND_MESSAGE",
                messaging.getFirst().getName());

    }

    @Test
    void filtersByPermission() {

        CommandLookup lookup =
                createLookup();

        List<CommandDefinition> adminOnly =
                lookup.findByPermission(
                        CommandPermission.ADMIN);

        assertEquals(
                1,
                adminOnly.size());

        assertEquals(
                "KICK_USER",
                adminOnly.getFirst().getName());

    }

    @Test
    void filtersByVersion() {

        CommandLookup lookup =
                createLookup();

        List<CommandDefinition> currentVersion =
                lookup.findByVersion(
                        ProtocolVersion.current());

        assertEquals(
                3,
                currentVersion.size());

    }

    @Test
    void filtersByAuthenticationRequirement() {

        CommandLookup lookup =
                createLookup();

        assertEquals(
                1,
                lookup.getPublicCommands().size());

        assertEquals(
                2,
                lookup.getAuthenticatedCommands().size());

    }

}
