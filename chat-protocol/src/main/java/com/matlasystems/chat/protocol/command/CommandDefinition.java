package com.matlasystems.chat.protocol.command;

import java.util.Objects;

import com.matlasystems.chat.protocol.version.ProtocolVersion;

/**
 * Immutable definition of a protocol command.
 *
 * <p>
 * A CommandDefinition describes a command supported by the chat protocol.
 * It contains metadata required for validation, routing,
 * documentation and protocol version compatibility.
 * </p>
 *
 * Example:
 *
 * LOGIN
 * SEND_MESSAGE
 * FILE_UPLOAD
 *
 * This class contains no business logic.
 *
 * @author Matla Systems
 * @version 1.0
 */
public final class CommandDefinition {

    /**
     * Unique protocol command name.
     */
    private final String name;

    /**
     * Logical category.
     */
    private final CommandCategory category;

    /**
     * Human readable description.
     */
    private final String description;

    /**
     * Whether authentication is required.
     */
    private final boolean authenticationRequired;

    /**
     * Required permission.
     */
    private final CommandPermission permission;

    /**
     * Supported protocol version.
     */
    private final ProtocolVersion version;

    /**
     * Creates a new immutable command definition.
     *
     * @param name command name
     * @param category command category
     * @param description description
     * @param authenticationRequired requires login
     * @param permission required permission
     * @param version protocol version
     */
    public CommandDefinition(
            String name,
            CommandCategory category,
            String description,
            boolean authenticationRequired,
            CommandPermission permission,
            ProtocolVersion version) {

        this.name = Objects.requireNonNull(name, "Command name cannot be null");
        this.category = Objects.requireNonNull(category, "Category cannot be null");
        this.description = Objects.requireNonNull(description, "Description cannot be null");
        this.permission = Objects.requireNonNull(permission, "Permission cannot be null");
        this.version = Objects.requireNonNull(version, "Version cannot be null");

        this.authenticationRequired = authenticationRequired;
    }

    /**
     * Returns command name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns category.
     */
    public CommandCategory getCategory() {
        return category;
    }

    /**
     * Returns description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether authentication is required.
     */
    public boolean requiresAuthentication() {
        return authenticationRequired;
    }

    /**
     * Returns required permission.
     */
    public CommandPermission getPermission() {
        return permission;
    }

    /**
     * Returns protocol version.
     */
    public ProtocolVersion getVersion() {
        return version;
    }

    /**
     * Convenience method.
     */
    public boolean isPublic() {
        return permission == CommandPermission.PUBLIC;
    }

    /**
     * Convenience method.
     */
    public boolean isAuthenticated() {
        return permission == CommandPermission.AUTHENTICATED;
    }

    /**
     * Convenience method.
     */
    public boolean isAdminOnly() {
        return permission == CommandPermission.ADMIN;
    }

    /**
     * Returns true if the command belongs
     * to the supplied category.
     */
    public boolean belongsTo(CommandCategory category) {
        return this.category == category;
    }

    /**
     * Equality based on command name.
     */
    @Override
    public boolean equals(Object object) {

        if (this == object)
            return true;

        if (!(object instanceof CommandDefinition other))
            return false;

        return Objects.equals(name, other.name);
    }

    /**
     * HashCode based on command name.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Human readable output.
     */
    @Override
    public String toString() {

        return "CommandDefinition{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", permission=" + permission +
                ", version=" + version +
                ", authenticationRequired=" + authenticationRequired +
                '}';
    }
}
