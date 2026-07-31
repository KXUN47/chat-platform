package com.matlasystems.chat.protocol.command;

import java.util.List;
import java.util.Optional;

import com.matlasystems.chat.protocol.version.ProtocolVersion;

/**
 * Provides convenient lookup and filtering operations for registered commands.
 *
 * <p>This class is a read-only wrapper around {@link CommandRegistry}. It does
 * not modify the registry or execute commands.</p>
 *
 * <p>Responsibilities:</p>
 * <ul>
 *     <li>Find commands by name</li>
 *     <li>Check if a command exists</li>
 *     <li>Retrieve all registered commands</li>
 *     <li>Filter commands by category</li>
 *     <li>Filter commands by permission</li>
 *     <li>Filter commands by protocol version</li>
 * </ul>
 */
public class CommandLookup {

    private final CommandRegistry registry;

    /**
     * Creates a new command lookup.
     *
     * @param registry command registry
     */
    public CommandLookup(
            CommandRegistry registry) {

        if (registry == null) {
            throw new IllegalArgumentException(
                    "CommandRegistry cannot be null.");
        }

        this.registry = registry;
    }

    /**
     * Finds a command by name.
     *
     * @param commandName command name
     * @return matching command or empty
     */
    public Optional<CommandDefinition> find(
            String commandName) {

        return registry.find(commandName);
    }

    /**
     * Checks whether a command exists.
     *
     * @param commandName command name
     * @return {@code true} if registered
     */
    public boolean exists(
            String commandName) {

        return registry.exists(commandName);
    }

    /**
     * Returns every registered command.
     *
     * @return immutable list of commands
     */
    public List<CommandDefinition> getAllCommands() {

        return List.copyOf(
                registry.getRegisteredCommands().values());
    }

    /**
     * Returns commands belonging to a category.
     *
     * @param category command category
     * @return immutable list of matching commands
     */
    public List<CommandDefinition> findByCategory(
            CommandCategory category) {

        return registry.getRegisteredCommands()
                .values()
                .stream()
                .filter(command ->
                        command.getCategory() == category)
                .toList();
    }

    /**
     * Returns commands requiring a permission.
     *
     * @param permission command permission
     * @return immutable list of matching commands
     */
    public List<CommandDefinition> findByPermission(
            CommandPermission permission) {

        return registry.getRegisteredCommands()
                .values()
                .stream()
                .filter(command ->
                        command.getPermission() == permission)
                .toList();
    }

    /**
     * Returns commands supported by a protocol version.
     *
     * @param version protocol version
     * @return immutable list of matching commands
     */
    public List<CommandDefinition> findByVersion(
            ProtocolVersion version) {

        return registry.getRegisteredCommands()
                .values()
                .stream()
                .filter(command ->
                        command.getVersion().equals(version))
                .toList();
    }

    /**
     * Returns commands that require authentication.
     *
     * @return immutable list of authenticated commands
     */
    public List<CommandDefinition> getAuthenticatedCommands() {

        return registry.getRegisteredCommands()
                .values()
                .stream()
                .filter(CommandDefinition::requiresAuthentication)
                .toList();
    }

    /**
     * Returns commands that do not require authentication.
     *
     * @return immutable list of public commands
     */
    public List<CommandDefinition> getPublicCommands() {

        return registry.getRegisteredCommands()
                .values()
                .stream()
                .filter(command ->
                        !command.requiresAuthentication())
                .toList();
    }

    /**
     * Returns the total number of registered commands.
     *
     * @return command count
     */
    public int count() {

        return registry.size();
    }

    /**
     * Returns whether no commands are registered.
     *
     * @return {@code true} if the registry is empty
     */
    public boolean isEmpty() {

        return registry.isEmpty();
    }

}
