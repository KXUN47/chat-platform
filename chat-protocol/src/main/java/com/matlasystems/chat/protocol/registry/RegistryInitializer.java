package com.matlasystems.chat.protocol.registry;

import com.matlasystems.chat.common.constants.ProtocolConstants;
import com.matlasystems.chat.protocol.command.CommandDefinition;
import com.matlasystems.chat.protocol.command.CommandPermission;
import com.matlasystems.chat.protocol.command.CommandCategory;
import com.matlasystems.chat.protocol.version.ProtocolVersion;
import com.matlasystems.chat.protocol.version.VersionMetadata;

public final class RegistryInitializer {

    private final ProtocolRegistry protocolRegistry;

    private boolean initialized;

    public RegistryInitializer(ProtocolRegistry protocolRegistry) {

        this.protocolRegistry = protocolRegistry;

    }

    /**
     * Initializes every protocol registry.
     */
    public synchronized void initialize() {

        if (initialized) {
            return;
        }

        registerVersions();

        registerCommands();

        registerParsers();

        registerSerializers();

        registerValidators();

        registerHandlers();

        initialized = true;

    }

    /**
     * Register supported protocol versions.
     */
    private void registerVersions() {

        protocolRegistry
                .getVersionManager()
                .register(VersionMetadata.of(
                        ProtocolVersion.current(),
                        "Initial protocol version"));

    }

    /**
     * Register protocol commands.
     */
    private void registerCommands() {

        protocolRegistry
                .getCommandRegistry()
                .register(new CommandDefinition(
                        ProtocolConstants.LOGIN,
                        CommandCategory.AUTHENTICATION,
                        "Authenticate a user",
                        false,
                        CommandPermission.PUBLIC,
                        ProtocolVersion.current()
                ));

        protocolRegistry
                .getCommandRegistry()
                .register(new CommandDefinition(
                        ProtocolConstants.LOGOUT,
                        CommandCategory.AUTHENTICATION,
                        "Logout current user",
                        true,
                        CommandPermission.AUTHENTICATED,
                        ProtocolVersion.current()
                ));

        protocolRegistry
                .getCommandRegistry()
                .register(new CommandDefinition(
                        ProtocolConstants.SEND_MESSAGE,
                        CommandCategory.MESSAGING,
                        "Broadcast message",
                        true,
                        CommandPermission.AUTHENTICATED,
                        ProtocolVersion.current()
                ));

    }

    /**
     * Register packet parsers.
     */
    private void registerParsers() {

        /*


         */

    }

    /**
     * Register serializers.
     */
    private void registerSerializers() {

        /*


         */

    }

    /**
     * Register validators.
     */
    private void registerValidators() {

        /*


         */

    }

    /**
     * Register protocol handlers.
     */
    private void registerHandlers() {

        /*


         */

    }

    /**
     * Returns initialization state.
     */
    public boolean isInitialized() {
        return initialized;
    }

}
