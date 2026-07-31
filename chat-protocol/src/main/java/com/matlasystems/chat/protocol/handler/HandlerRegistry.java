package com.matlasystems.chat.protocol.handler;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.matlasystems.chat.common.enums.CommandType;

/**
 * Thread-safe registry of protocol handlers.
 */
public class HandlerRegistry {

    private final Map<CommandType, CommandHandler> registry =
            new ConcurrentHashMap<>();

    /**
     * Registers a handler.
     */
    public void register(
            CommandType command,
            CommandHandler handler) {

        Objects.requireNonNull(command);
        Objects.requireNonNull(handler);

        registry.put(command, handler);

    }

    /**
     * Finds a handler.
     */
    public CommandHandler find(
            CommandType command) {

        CommandHandler handler =
                registry.get(command);

        if (handler == null) {

            return new UnsupportedCommandHandler();

        }

        return handler;

    }

    /**
     * Checks whether a handler exists.
     */
    public boolean contains(CommandType command) {

        return registry.containsKey(command);

    }

    /**
     * Removes a handler.
     */
    public void unregister(
            CommandType command) {

        registry.remove(command);

    }

    /**
     * Removes all handlers.
     */
    public void clear() {

        registry.clear();

    }

    /**
     * Number of registered handlers.
     */
    public int size() {

        return registry.size();

    }

    /**
     * Returns all handlers.
     */
    public Collection<CommandHandler> getHandlers() {

        return Collections.unmodifiableCollection(
                registry.values());

    }

}
