package com.matlasystems.chat.protocol.command;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Thread-safe registry of protocol commands.
 */
public class CommandRegistry {

    private final Map<String, CommandDefinition> registry =
            new ConcurrentHashMap<>();

    /**
     * Registers a command.
     */
    public void register(CommandDefinition definition) {

        if (definition == null) {
            throw new IllegalArgumentException("Command definition cannot be null.");
        }

        registry.put(definition.getName(), definition);
    }

    /**
     * Registers multiple commands.
     */
    public void registerAll(Collection<CommandDefinition> definitions) {

        definitions.forEach(this::register);

    }

    /**
     * Finds a command.
     */
    public Optional<CommandDefinition> find(String commandName) {

        return Optional.ofNullable(registry.get(commandName));

    }

    /**
     * Returns true if command exists.
     */
    public boolean exists(String commandName) {

        return registry.containsKey(commandName);

    }

    /**
     * Removes a command.
     */
    public void remove(String commandName) {

        registry.remove(commandName);

    }

    /**
     * Clears registry.
     */
    public void clear() {

        registry.clear();

    }

    /**
     * Number of registered commands.
     */
    public int size() {

        return registry.size();

    }

    /**
     * Registry empty?
     */
    public boolean isEmpty() {

        return registry.isEmpty();

    }

    /**
     * Immutable registry view.
     */
    public Map<String, CommandDefinition> getRegisteredCommands() {

        return Collections.unmodifiableMap(registry);

    }

}
