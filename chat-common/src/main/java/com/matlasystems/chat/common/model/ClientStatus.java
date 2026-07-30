package com.matlasystems.chat.common.model;

/**
 * Represents the connection state of a client.
 *
 * @author MATLA Systems
 * @version 1.0.0
 */
public enum ClientStatus {

    /**
     * Client is attempting to connect.
     */
    CONNECTING("Connecting to server.", false),

    /**
     * TCP connection established.
     */
    CONNECTED("Connected to server.", false),

    /**
     * Login request in progress.
     */
    AUTHENTICATING("Authenticating.", false),

    /**
     * Successfully logged in.
     */
    AUTHENTICATED("Authenticated.", true),

    /**
     * Connected but currently inactive.
     */
    IDLE("Client is idle.", true),

    /**
     * Client is attempting to reconnect.
     */
    RECONNECTING("Reconnecting.", false),

    /**
     * Connection closed.
     */
    DISCONNECTED("Disconnected.", false),

    /**
     * Client encountered an unexpected error.
     */
    ERROR("Connection error.", false);

    /**
     * Human-readable description.
     */
    private final String description;

    /**
     * Indicates whether the client may exchange application messages.
     */
    private final boolean active;

    ClientStatus(String description,
                 boolean active) {

        this.description = description;
        this.active = active;
    }

    /**
     * Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns true if the client may participate in chat.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Returns true if authentication has completed.
     */
    public boolean isAuthenticated() {
        return this == AUTHENTICATED || this == IDLE;
    }

    /**
     * Returns true if the client currently has a network connection.
     */
    public boolean isConnected() {

        return switch (this) {
            case CONNECTED,
                 AUTHENTICATING,
                 AUTHENTICATED,
                 IDLE,
                 RECONNECTING -> true;

            default -> false;
        };
    }

}
