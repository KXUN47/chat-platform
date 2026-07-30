package com.matlasystems.chat.common.constants;

/**
 * Logging categories, formats and retention defaults.
 */
public final class LoggingConstants {

    private LoggingConstants() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final String DEFAULT_LOG_LEVEL = "INFO";
    public static final String ERROR_LEVEL = "ERROR";
    public static final String WARN_LEVEL = "WARN";
    public static final String INFO_LEVEL = "INFO";
    public static final String DEBUG_LEVEL = "DEBUG";
    public static final String TRACE_LEVEL = "TRACE";

    public static final String SERVER_LIFECYCLE_LOGGER = "server.lifecycle";
    public static final String NETWORK_CONNECTION_LOGGER = "network.connection";
    public static final String SECURITY_AUTHENTICATION_LOGGER = "security.authentication";
    public static final String MESSAGING_DELIVERY_LOGGER = "messaging.delivery";
    public static final String PERSISTENCE_DATABASE_LOGGER = "persistence.database";
    public static final String AUDIT_LOGGER = "audit";

    public static final String LOG_PATTERN =
            "%d{yyyy-MM-dd'T'HH:mm:ss.SSSXXX} %-5level [%thread] %logger{36} - %msg%n";
    public static final String ROLLING_FILE_NAME_PATTERN =
            "logs/server.%d{yyyy-MM-dd}.%i.log.gz";
    public static final String ERROR_FILE_NAME_PATTERN =
            "logs/error.%d{yyyy-MM-dd}.%i.log.gz";

    public static final long MAX_LOG_FILE_SIZE_BYTES = 10L * 1024L * 1024L;
    public static final int MAX_HISTORY_DAYS = 30;
    public static final long TOTAL_SIZE_CAP_BYTES = 1L * 1024L * 1024L * 1024L;
}
