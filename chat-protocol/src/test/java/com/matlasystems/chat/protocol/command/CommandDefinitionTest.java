package com.matlasystems.chat.protocol.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.protocol.version.ProtocolVersion;

/**
 * Unit tests for {@link CommandDefinition}.
 */
class CommandDefinitionTest {

    private CommandDefinition definition(CommandPermission permission) {

        return new CommandDefinition(
                "LOGIN",
                CommandCategory.AUTHENTICATION,
                "Authenticate a user",
                false,
                permission,
                ProtocolVersion.current());

    }

    @Test
    void exposesConstructorValues() {

        CommandDefinition command = definition(CommandPermission.PUBLIC);

        assertEquals("LOGIN", command.getName());
        assertEquals(CommandCategory.AUTHENTICATION, command.getCategory());
        assertEquals("Authenticate a user", command.getDescription());
        assertFalse(command.requiresAuthentication());
        assertEquals(CommandPermission.PUBLIC, command.getPermission());
        assertEquals(ProtocolVersion.current(), command.getVersion());
    }

    @Test
    void permissionConvenienceMethodsReflectPermission() {

        assertTrue(definition(CommandPermission.PUBLIC).isPublic());
        assertTrue(definition(CommandPermission.AUTHENTICATED).isAuthenticated());
        assertTrue(definition(CommandPermission.ADMIN).isAdminOnly());
    }

    @Test
    void belongsToChecksCategory() {

        CommandDefinition command = definition(CommandPermission.PUBLIC);

        assertTrue(command.belongsTo(CommandCategory.AUTHENTICATION));
        assertFalse(command.belongsTo(CommandCategory.MESSAGING));
    }

    @Test
    void equalityIsBasedOnNameAlone() {

        CommandDefinition first = definition(CommandPermission.PUBLIC);
        CommandDefinition second = definition(CommandPermission.ADMIN);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());

        CommandDefinition differentName = new CommandDefinition(
                "LOGOUT", CommandCategory.AUTHENTICATION, "Logout", true,
                CommandPermission.AUTHENTICATED, ProtocolVersion.current());

        assertNotEquals(first, differentName);
    }

}
