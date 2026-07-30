package com.matlasystems.chat.common.constants;

/**
 * Application property and environment-variable names.
 */
public final class ConfigurationKeys {

    private ConfigurationKeys() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final String APPLICATION_ENVIRONMENT = "chat.application.environment";
    public static final String SERVER_HOST = "chat.server.host";
    public static final String SERVER_PORT = "chat.server.port";
    public static final String SERVER_BACKLOG = "chat.server.backlog";
    public static final String SERVER_SOCKET_TIMEOUT_MS = "chat.server.socket-timeout-ms";

    public static final String DATABASE_URL = "chat.database.url";
    public static final String DATABASE_USERNAME = "chat.database.username";
    public static final String DATABASE_PASSWORD = "chat.database.password";
    public static final String DATABASE_MAX_POOL_SIZE = "chat.database.max-pool-size";
    public static final String DATABASE_MIN_IDLE = "chat.database.min-idle";

    public static final String LOGGING_LEVEL = "chat.logging.level";
    public static final String LOGGING_DIRECTORY = "chat.logging.directory";
    public static final String FILE_STORAGE_DIRECTORY = "chat.files.storage-directory";
    public static final String FILE_MAX_SIZE_BYTES = "chat.files.max-size-bytes";
    public static final String SECURITY_SESSION_TIMEOUT_MINUTES = "chat.security.session-timeout-minutes";
    public static final String PROTOCOL_MAX_PACKET_SIZE_BYTES = "chat.protocol.max-packet-size-bytes";
    public static final String PROTOCOL_HEARTBEAT_INTERVAL_SECONDS = "chat.protocol.heartbeat-interval-seconds";

    public static final String ENV_SERVER_HOST = "CHAT_SERVER_HOST";
    public static final String ENV_SERVER_PORT = "CHAT_SERVER_PORT";
    public static final String ENV_DATABASE_URL = "CHAT_DATABASE_URL";
    public static final String ENV_DATABASE_USERNAME = "CHAT_DATABASE_USERNAME";
    public static final String ENV_DATABASE_PASSWORD = "CHAT_DATABASE_PASSWORD";
    public static final String ENV_LOGGING_LEVEL = "CHAT_LOGGING_LEVEL";
}
