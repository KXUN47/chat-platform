package com.matlasystems.chat.common.model;

import java.time.Duration;
import java.time.Instant;

/**
 * Information describing the running server.
 */
public record ServerInfo(

        String serverName,

        String version,

        String host,

        int port,

        Instant startedAt,

        Duration uptime,

        ServerStatus status,

        int connectedClients,

        int activeSessions

) {

    /**
     * Indicates whether the server is accepting client connections.
     */
    public boolean isRunning() {
        return status == ServerStatus.RUNNING;
    }

}
