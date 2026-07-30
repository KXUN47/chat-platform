package com.matlasystems.chat.common.constants;

/**
 * Network configuration constants.
 */
public final class NetworkConstants {

    private NetworkConstants() {
        throw new UnsupportedOperationException("Utility class");
    }

    /*
     * Server
     */
    public static final String DEFAULT_HOST = "0.0.0.0";

    public static final int DEFAULT_PORT = 9000;

    /*
     * Socket
     */
    public static final int SOCKET_BACKLOG = 100;

    public static final int SOCKET_TIMEOUT_MS = 30000;

    public static final int CONNECT_TIMEOUT_MS = 10000;

    public static final boolean TCP_NO_DELAY = true;

    public static final boolean KEEP_ALIVE = true;

    public static final boolean REUSE_ADDRESS = true;

    /*
     * Buffer Sizes
     */
    public static final int BUFFER_SIZE = 8192;

    public static final int RECEIVE_BUFFER_SIZE = 8192;

    public static final int SEND_BUFFER_SIZE = 8192;

    /*
     * Thread Pool
     */
    public static final int CORE_THREAD_POOL_SIZE = 10;

    public static final int MAX_THREAD_POOL_SIZE = 100;

    public static final int THREAD_KEEP_ALIVE_SECONDS = 60;

    /*
     * Heartbeat
     */
    public static final int HEARTBEAT_INTERVAL_SECONDS = 30;

    public static final int HEARTBEAT_TIMEOUT_SECONDS = 90;

    /*
     * Reconnection
     */
    public static final int MAX_RECONNECT_ATTEMPTS = 5;

    public static final int RECONNECT_DELAY_MS = 5000;

    /*
     * Packet Limits
     */
    public static final int MAX_PACKET_SIZE =
            10 * 1024 * 1024; //10 MB

    /*
     * Queue Sizes
     */
    public static final int OUTGOING_QUEUE_SIZE = 500;

    public static final int INCOMING_QUEUE_SIZE = 500;

}
