package com.matlasystems.chat.common.model;

import java.net.InetAddress;
import java.time.Instant;
import java.util.UUID;

/**
 * Represents an active network connection.
 */
public record ConnectionInfo(

        UUID connectionId,

        InetAddress remoteAddress,

        int remotePort,

        InetAddress localAddress,

        int localPort,

        Instant connectedAt,

        Instant lastActivity,

        boolean secure,

        boolean authenticated

) {

    /**
     * Determines whether the connection has been authenticated.
     */
    public boolean isAuthenticated() {
        return authenticated;
    }

}
