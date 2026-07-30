package com.matlasystems.chat.common.event.server;

import java.util.UUID;

import com.matlasystems.chat.common.event.ApplicationEvent;
import com.matlasystems.chat.common.event.EventPriority;
import com.matlasystems.chat.common.event.EventType;

/**
 * Emitted when a socket client connects to the server.
 */
public final class ClientConnectedEvent
        extends ApplicationEvent {

    private final UUID connectionId;

    private final String remoteAddress;

    /**
     * Creates a new client connected event.
     *
     * @param connectionId unique connection identifier
     * @param remoteAddress client remote address
     */
    public ClientConnectedEvent(
            UUID connectionId,
            String remoteAddress) {

        super(
                EventType.CLIENT_CONNECTED,
                EventPriority.NORMAL,
                "server");

        this.connectionId = connectionId;
        this.remoteAddress = remoteAddress;
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
     * Returns the client's remote address.
     *
     * @return remote address
     */
    public String getRemoteAddress() {
        return remoteAddress;
    }

}
