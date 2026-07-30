package com.matlasystems.chat.common.enums;

/**
 * Represents the lifecycle state of a network connection.
 *
 * Used by both the chat client and chat server.
 *
 * @author MATLA Systems
 * @version 1.0.0
 */
public enum ConnectionStatus {

    /**
     * Connection is currently being established.
     */
    CONNECTING,

    /**
     * Connection has been successfully established.
     */
    CONNECTED,

    /**
     * Connection has been temporarily interrupted
     * and the client is attempting to reconnect.
     */
    RECONNECTING,

    /**
     * Connection has been intentionally closed.
     */
    DISCONNECTED,

    /**
     * Connection attempt failed.
     */
    FAILED,

    /**
     * Connection timed out.
     */
    TIMED_OUT;

    /**
     * Returns true if the connection is active.
     *
     * @return true when connected
     */
    public boolean isConnected() {
        return this == CONNECTED;
    }

    /**
     * Returns true if the connection is currently
     * being established.
     *
     * @return true when connecting
     */
    public boolean isConnecting() {
        return this == CONNECTING;
    }

    /**
     * Returns true if the client is attempting
     * to reconnect.
     *
     * @return true when reconnecting
     */
    public boolean isReconnecting() {
        return this == RECONNECTING;
    }

    /**
     * Returns true if the connection has ended.
     *
     * @return true when disconnected
     */
    public boolean isDisconnected() {
        return this == DISCONNECTED;
    }

    /**
     * Returns true if the connection has failed.
     *
     * @return true when failed
     */
    public boolean isFailed() {
        return this == FAILED;
    }

    /**
     * Returns true if the connection timed out.
     *
     * @return true when timed out
     */
    public boolean isTimedOut() {
        return this == TIMED_OUT;
    }

    /**
     * Returns true if the connection is usable.
     *
     * @return true only when connected
     */
    public boolean isActive() {
        return this == CONNECTED;
    }

}
