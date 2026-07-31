package com.matlasystems.chat.protocol.command;

/**
 * Defines permission levels required
 * to execute protocol commands.
 */
public enum CommandPermission {

    /**
     * Anyone can execute.
     */
    PUBLIC,

    /**
     * Logged in users.
     */
    AUTHENTICATED,

    /**
     * Moderators.
     */
    MODERATOR,

    /**
     * Administrators.
     */
    ADMIN,

    /**
     * Internal server-only commands.
     */
    SYSTEM
}
