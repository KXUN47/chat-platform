package com.matlasystems.chat.common.model;

import java.net.InetAddress;
import java.time.Instant;
import java.util.UUID;

/**
 * Represents a connected client.
 */
public record ClientInfo(

        UUID clientId,

        String username,

        String applicationVersion,

        InetAddress ipAddress,

        int port,

        String operatingSystem,

        String operatingSystemVersion,

        String javaVersion,

        Instant connectedAt,

        Instant lastSeen,

        ClientStatus status

) {

    /**
     * Returns true if the client is currently authenticated.
     */
    public boolean isAuthenticated() {
        return status == ClientStatus.AUTHENTICATED;
    }

}
