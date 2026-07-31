package com.matlasystems.chat.protocol.command;

/**
 * High-level command grouping.
 */
public enum CommandCategory {

    /**
     * User authentication.
     */
    AUTHENTICATION,

    /**
     * Sending and receiving messages.
     */
    MESSAGING,

    /**
     * User presence.
     */
    PRESENCE,

    /**
     * File upload/download.
     */
    FILE_TRANSFER,

    /**
     * Typing indicators.
     */
    TYPING,

    /**
     * Heartbeat packets.
     */
    HEARTBEAT,

    /**
     * Server information.
     */
    SERVER,

    /**
     * Administrative commands.
     */
    ADMIN,

    /**
     * Internal protocol commands.
     */
    SYSTEM

}
