package com.matlasystems.chat.common.event.server;

import java.util.UUID;

import com.matlasystems.chat.common.event.ApplicationEvent;
import com.matlasystems.chat.common.event.EventPriority;
import com.matlasystems.chat.common.event.EventType;

/**
 * Emitted when a socket client disconnects from the server.
 */
public final class ClientDisconnectedEvent
        extends ApplicationEvent {

    private final UUID connectionId;

    private final String reason;

    /**
     * Creates a new client disconnected event.
     *
     * @param connectionId unique connection identifier
     * @param reason reason for the disconnection
     */
    public ClientDisconnectedEvent(
            UUID connectionId,
            String reason) {

        super(
                EventType.CLIENT_DISCONNECTED,
                EventPriority.NORMAL,
                "server");

        this.connectionId = connectionId;
        this.reason = reason;
    }

    /**
     * Returns the connection identifier.
     *
     * @return connection ID
     */
    public UUID getConnectionId() {
        return connectionId;
    }

    /**
     * Returns the reason for the disconnection.
     *
     * @return disconnection reason
     */
    public String getReason() {
        return reason;
    }

}
