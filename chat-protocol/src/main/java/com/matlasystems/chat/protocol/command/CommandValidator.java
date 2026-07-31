package com.matlasystems.chat.protocol.command;

import java.util.Optional;

import com.matlasystems.chat.protocol.version.ProtocolVersion;

public class CommandValidator {

    private final CommandRegistry registry;

    public CommandValidator(CommandRegistry registry) {
        this.registry = registry;
    }

    /**
     * Validate an incoming command.
     */
    public CommandDefinition validate(
            String commandName,
            boolean authenticated,
            CommandPermission userPermission,
            ProtocolVersion protocolVersion) {

        Optional<CommandDefinition> optional =
                registry.find(commandName);

        if (optional.isEmpty()) {

            throw new UnknownCommandException(
                    "Unknown command: " + commandName);

        }

        CommandDefinition command = optional.get();

        validateVersion(command, protocolVersion);

        validateAuthentication(command, authenticated);

        validatePermission(command, userPermission);

        return command;

    }

    /**
     * Validate protocol version.
     */
    public void validateVersion(
            CommandDefinition command,
            ProtocolVersion version) {

        if (!command.getVersion().equals(version)) {

            throw new UnsupportedCommandException(
                    "Command not supported for protocol version: "
                            + version);

        }

    }

    /**
     * Validate authentication.
     */
    public void validateAuthentication(
            CommandDefinition command,
            boolean authenticated) {

        if (command.requiresAuthentication()
                && !authenticated) {

            throw new UnauthorizedCommandException(
                    "Authentication required.");

        }

    }

    /**
     * Validate permissions.
     */
    public void validatePermission(
            CommandDefinition command,
            CommandPermission permission) {

        if (permission.ordinal()
                < command.getPermission().ordinal()) {

            throw new UnauthorizedCommandException(
                    "Insufficient permissions.");

        }

    }

    /**
     * Validate payload.
     *
     * Placeholder for payload validation.
     */
    public void validatePayload(Object payload) {

        if (payload == null) {

            throw new InvalidCommandException(
                    "Payload cannot be null.");

        }

    }

}
