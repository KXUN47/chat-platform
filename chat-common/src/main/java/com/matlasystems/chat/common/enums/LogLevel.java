package com.matlasystems.chat.common.enums;

/**
 * Represents the severity level of an application log entry.
 *
 * These values are intended for application-level logging
 * and align with common logging frameworks such as SLF4J
 * and Logback.
 *
 * @author MATLA Systems
 * @version 1.0.0
 */
public enum LogLevel {

    /**
     * Detailed diagnostic information.
     */
    TRACE,

    /**
     * Debugging information used during development.
     */
    DEBUG,

    /**
     * General application information.
     */
    INFO,

    /**
     * Indicates an unexpected situation that does not
     * prevent the application from continuing.
     */
    WARN,

    /**
     * Indicates a serious problem or failure.
     */
    ERROR;

    /**
     * Determines whether this log level represents an error.
     *
     * @return true if the level is ERROR
     */
    public boolean isError() {
        return this == ERROR;
    }

    /**
     * Determines whether this log level represents a warning.
     *
     * @return true if the level is WARN
     */
    public boolean isWarning() {
        return this == WARN;
    }

    /**
     * Determines whether this log level represents
     * informational logging.
     *
     * @return true if INFO
     */
    public boolean isInfo() {
        return this == INFO;
    }

    /**
     * Determines whether this log level is used
     * for debugging.
     *
     * @return true if DEBUG
     */
    public boolean isDebug() {
        return this == DEBUG;
    }

    /**
     * Determines whether this log level is used
     * for detailed tracing.
     *
     * @return true if TRACE
     */
    public boolean isTrace() {
        return this == TRACE;
    }

}
