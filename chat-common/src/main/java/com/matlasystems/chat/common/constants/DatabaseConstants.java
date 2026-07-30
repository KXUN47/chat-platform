package com.matlasystems.chat.common.constants;

/**
 * Database related constants.
 */
public final class DatabaseConstants {

    private DatabaseConstants() {
        throw new UnsupportedOperationException("Utility class");
    }

    /*
     * Database
     */
    public static final String DATABASE_NAME = "chatdb";

    public static final String DEFAULT_SCHEMA = "public";

    public static final String JDBC_DRIVER =
            "org.postgresql.Driver";

    public static final String JDBC_PROTOCOL =
            "jdbc:postgresql://";

    public static final String DEFAULT_HOST =
            "localhost";

    public static final int DEFAULT_PORT = 5432;

    /*
     * Connection Pool (HikariCP)
     */
    public static final int MAX_POOL_SIZE = 20;

    public static final int MIN_IDLE_CONNECTIONS = 5;

    public static final long CONNECTION_TIMEOUT_MS = 30000;

    public static final long IDLE_TIMEOUT_MS = 600000;

    public static final long MAX_LIFETIME_MS = 1800000;

    /*
     * Table Names
     */
    public static final String USERS_TABLE = "users";

    public static final String SESSIONS_TABLE = "sessions";

    public static final String MESSAGES_TABLE = "messages";

    public static final String FILES_TABLE = "files";

    public static final String AUDIT_LOGS_TABLE = "audit_logs";

    /*
     * Common Column Names
     */
    public static final String COLUMN_ID = "id";

    public static final String COLUMN_CREATED_AT = "created_at";

    public static final String COLUMN_UPDATED_AT = "updated_at";

    /*
     * SQL Defaults
     */
    public static final int DEFAULT_FETCH_SIZE = 100;

    public static final int DEFAULT_BATCH_SIZE = 100;

    /*
     * Flyway
     */
    public static final String MIGRATION_LOCATION =
            "classpath:db/migration";

}
