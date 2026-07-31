package com.matlasystems.chat.protocol.registry;

import com.matlasystems.chat.protocol.command.CommandRegistry;
import com.matlasystems.chat.protocol.version.VersionManager;

/**
 * Central registry for all protocol components.
 *
 * This class acts as the root container for every registry used by the
 * protocol layer.
 *
 * It should be created once during server startup and shared throughout
 * the application.
 */
public final class ProtocolRegistry {

    private final CommandRegistry commandRegistry;
    private final HandlerRegistry handlerRegistry;
    private final ParserRegistry parserRegistry;
    private final SerializerRegistry serializerRegistry;
    private final ValidatorRegistry validatorRegistry;
    private final VersionManager versionManager;

    public ProtocolRegistry() {

        this.commandRegistry = new CommandRegistry();
        this.handlerRegistry = new HandlerRegistry();
        this.parserRegistry = new ParserRegistry();
        this.serializerRegistry = new SerializerRegistry();
        this.validatorRegistry = new ValidatorRegistry();
        this.versionManager = new VersionManager();

    }

    public CommandRegistry getCommandRegistry() {
        return commandRegistry;
    }

    public HandlerRegistry getHandlerRegistry() {
        return handlerRegistry;
    }

    public ParserRegistry getParserRegistry() {
        return parserRegistry;
    }

    public SerializerRegistry getSerializerRegistry() {
        return serializerRegistry;
    }

    public ValidatorRegistry getValidatorRegistry() {
        return validatorRegistry;
    }

    public VersionManager getVersionManager() {
        return versionManager;
    }

}
