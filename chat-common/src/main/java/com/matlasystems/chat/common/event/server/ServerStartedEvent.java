package com.matlasystems.chat.common.event.server;

import com.matlasystems.chat.common.event.ApplicationEvent;
import com.matlasystems.chat.common.event.EventPriority;
import com.matlasystems.chat.common.event.EventType;

/**
 * Emitted when the server starts accepting connections.
 */
public final class ServerStartedEvent
        extends ApplicationEvent {

    private final String host;

    private final int port;

    /**
     * Creates a new server started event.
     *
     * @param host server host name or IP address
     * @param port server listening port
     */
    public ServerStartedEvent(
            String host,
            int port) {

        super(
                EventType.SERVER_STARTED,
                EventPriority.HIGH,
                "server");

        this.host = host;
        this.port = port;
    }

    /**
     * Returns the server host.
     *
     * @return server host
     */
    public String getHost() {
        return host;
    }

    /**
     * Returns the server listening port.
     *
     * @return server port
     */
    public int getPort() {
        return port;
    }

}
