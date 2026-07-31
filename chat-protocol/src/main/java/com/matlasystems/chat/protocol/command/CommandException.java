package com.matlasystems.chat.protocol.command;

import java.io.Serial;
import java.time.Instant;
import java.util.Objects;

/**
 * Base exception for all command-related errors within the protocol layer.
 *
 * <p>
 * Every exception thrown by the command package should extend this class.
 * This allows the server to handle protocol errors consistently and convert
 * them into standardized error responses.
 * </p>
 *
 * Examples:
 * <ul>
 *     <li>Unknown command</li>
 *     <li>Unauthorized command</li>
 *     <li>Unsupported protocol version</li>
 *     <li>Invalid payload</li>
 * </ul>
 */
public class CommandException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Name of the command that caused the error.
     */
    private final String commandName;

    /**
     * Optional application error code.
     */
    private final String errorCode;

    /**
     * Time the exception occurred.
     */
    private final Instant timestamp;

    /**
     * Constructs a new exception.
     *
     * @param message error message
     */
    public CommandException(String message) {
        this(message, null, null, null);
    }

    /**
     * Constructs a new exception.
     *
     * @param message error message
     * @param cause underlying cause
     */
    public CommandException(String message, Throwable cause) {
        this(message, null, null, cause);
    }

    /**
     * Constructs a new exception.
     *
     * @param message error message
     * @param commandName command responsible
     */
    public CommandException(
            String message,
            String commandName) {

        this(message, commandName, null, null);

    }

    /**
     * Constructs a new exception.
     *
     * @param message error message
     * @param commandName command responsible
     * @param errorCode application error code
     */
    public CommandException(
            String message,
            String commandName,
            String errorCode) {

        this(message, commandName, errorCode, null);

    }

    /**
     * Full constructor.
     *
     * @param message error message
     * @param commandName command responsible
     * @param errorCode application error code
     * @param cause root cause
     */
    public CommandException(
            String message,
            String commandName,
            String errorCode,
            Throwable cause) {

        super(message, cause);

        this.commandName = commandName;
        this.errorCode = errorCode;
        this.timestamp = Instant.now();

    }

    /**
     * Returns the command name.
     */
    public String getCommandName() {
        return commandName;
    }

    /**
     * Returns the application error code.
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Returns when the exception occurred.
     */
    public Instant getTimestamp() {
        return timestamp;
    }

    /**
     * Indicates whether a command name exists.
     */
    public boolean hasCommandName() {
        return commandName != null;
    }

    /**
     * Indicates whether an error code exists.
     */
    public boolean hasErrorCode() {
        return errorCode != null;
    }

    @Override
    public String toString() {

        return "CommandException{" +
                "message='" + getMessage() + '\'' +
                ", commandName='" + commandName + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", timestamp=" + timestamp +
                '}';

    }

    @Override
    public int hashCode() {

        return Objects.hash(
                getMessage(),
                commandName,
                errorCode,
                timestamp);

    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CommandException other)) {
            return false;
        }

        return Objects.equals(getMessage(), other.getMessage())
                && Objects.equals(commandName, other.commandName)
                && Objects.equals(errorCode, other.errorCode)
                && Objects.equals(timestamp, other.timestamp);

    }

}
