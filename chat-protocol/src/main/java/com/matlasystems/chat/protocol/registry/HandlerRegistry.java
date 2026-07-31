package com.matlasystems.chat.protocol.registry;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.matlasystems.chat.protocol.handler.ProtocolHandler;

/**
 * Stores all protocol handlers.
 *
 * Example:
 *
 * LOGIN -> LoginHandler
 * MESSAGE -> MessageHandler
 * FILE_UPLOAD -> FileUploadHandler
 */
public final class HandlerRegistry {

    private final Map<String, ProtocolHandler> handlers =
            new ConcurrentHashMap<>();

    /**
     * Register a handler.
     */
    public void register(
            String command,
            ProtocolHandler handler) {

        if (handlers.putIfAbsent(command, handler) != null) {
            throw new DuplicateRegistrationException(
                    "Handler already registered: " + command);
        }

    }

    /**
     * Find handler.
     */
    public Optional<ProtocolHandler> find(String command) {
        return Optional.ofNullable(handlers.get(command));
    }

    /**
     * Check existence.
     */
    public boolean exists(String command) {
        return handlers.containsKey(command);
    }

    /**
     * Remove handler.
     */
    public void remove(String command) {
        handlers.remove(command);
    }

    /**
     * Remove everything.
     */
    public void clear() {
        handlers.clear();
    }

    /**
     * Number of handlers.
     */
    public int size() {
        return handlers.size();
    }

    /**
     * Immutable collection.
     */
    public Collection<ProtocolHandler> getAll() {
        return Collections.unmodifiableCollection(
                handlers.values());
    }

}
