package com.matlasystems.chat.common.model;

/**
 * Represents the current operational state of the chat server.
 *
 * <p>The server transitions through these states during its lifecycle.
 *
 * @author MATLA Systems
 * @version 1.0.0
 */
public enum ServerStatus {

    /**
     * Server is starting.
     */
    STARTING("Server is starting.", false),

    /**
     * Server is accepting client connections.
     */
    RUNNING("Server is running.", true),

    /**
     * Server is running but with reduced functionality.
     */
    DEGRADED("Server is running with reduced functionality.", true),

    /**
     * Server is temporarily unavailable for maintenance.
     */
    MAINTENANCE("Server is under maintenance.", false),

    /**
     * Server is shutting down.
     */
    STOPPING("Server is shutting down.", false),

    /**
     * Server has completely stopped.
     */
    STOPPED("Server is stopped.", false);

    /**
     * Human-readable description.
     */
    private final String description;

    /**
     * Indicates whether new client connections are accepted.
     */
    private final boolean acceptingConnections;

    ServerStatus(String description,
                 boolean acceptingConnections) {

        this.description = description;
        this.acceptingConnections = acceptingConnections;
    }

    /**
     * Returns the status description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns true if the server accepts new connections.
     */
    public boolean isAcceptingConnections() {
        return acceptingConnections;
    }

    /**
     * Returns true if the server is operational.
     */
    public boolean isOperational() {
        return this == RUNNING || this == DEGRADED;
    }

    /**
     * Returns true if the server is offline.
     */
    public boolean isOffline() {
        return this == STOPPED;
    }

}
