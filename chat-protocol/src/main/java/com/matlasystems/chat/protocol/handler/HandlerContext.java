package com.matlasystems.chat.protocol.handler;

import java.net.Socket;
import java.util.Objects;
import java.util.UUID;

import com.matlasystems.chat.common.protocol.ProtocolVersion;

/**
 * Shared execution context for protocol handlers.
 */
public class HandlerContext {

    /**
     * Client socket.
     */
    private Socket socket;

    /**
     * Authenticated user identifier.
     */
    private UUID userId;

    /**
     * Current protocol version.
     */
    private ProtocolVersion protocolVersion;

    /**
     * Indicates whether the client has authenticated.
     */
    private boolean authenticated;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public ProtocolVersion getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(
            ProtocolVersion protocolVersion) {

        this.protocolVersion = protocolVersion;

    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(
            boolean authenticated) {

        this.authenticated = authenticated;

    }

    /**
     * Clears the context.
     */
    public void clear() {

        socket = null;
        userId = null;
        protocolVersion = null;
        authenticated = false;

    }

    /**
     * Validates required state.
     */
    public void validate() {

        Objects.requireNonNull(
                socket,
                "Socket cannot be null.");

        Objects.requireNonNull(
                protocolVersion,
                "Protocol version cannot be null.");

    }

}
