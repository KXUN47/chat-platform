package com.matlasystems.chat.common.enums;

/**
 * Standard application error codes.
 *
 * These values are shared by the client
 * and server so both sides understand
 * the exact error being returned.
 *
 * @author MATLA Systems
 * @version 1.0.0
 */
public enum ErrorCode {

    /*
     * General
     */
    SUCCESS(0, "Operation completed successfully"),

    UNKNOWN_ERROR(1000, "Unknown error"),

    INTERNAL_SERVER_ERROR(1001, "Internal server error"),

    INVALID_REQUEST(1002, "Invalid request"),

    INVALID_COMMAND(1003, "Invalid command"),

    INVALID_PACKET(1004, "Invalid packet"),

    /*
     * Authentication
     */
    INVALID_USERNAME(2000, "Invalid username"),

    INVALID_PASSWORD(2001, "Invalid password"),

    AUTHENTICATION_FAILED(2002, "Authentication failed"),

    USER_ALREADY_EXISTS(2003, "User already exists"),

    USER_NOT_FOUND(2004, "User not found"),

    ACCOUNT_LOCKED(2005, "Account locked"),

    SESSION_EXPIRED(2006, "Session expired"),

    /*
     * Messaging
     */
    MESSAGE_EMPTY(3000, "Message cannot be empty"),

    MESSAGE_TOO_LONG(3001, "Message exceeds maximum length"),

    RECIPIENT_OFFLINE(3002, "Recipient is offline"),

    MESSAGE_DELIVERY_FAILED(3003, "Message delivery failed"),

    /*
     * File Transfer
     */
    FILE_NOT_FOUND(4000, "File not found"),

    FILE_TOO_LARGE(4001, "File exceeds maximum size"),

    FILE_TYPE_NOT_ALLOWED(4002, "File type not allowed"),

    FILE_TRANSFER_FAILED(4003, "File transfer failed"),

    CHECKSUM_FAILED(4004, "File checksum verification failed"),

    /*
     * Network
     */
    CONNECTION_FAILED(5000, "Connection failed"),

    CONNECTION_TIMEOUT(5001, "Connection timeout"),

    NETWORK_ERROR(5002, "Network error"),

    CLIENT_DISCONNECTED(5003, "Client disconnected"),

    /*
     * Database
     */
    DATABASE_ERROR(6000, "Database error"),

    DUPLICATE_RECORD(6001, "Duplicate record"),

    RECORD_NOT_FOUND(6002, "Record not found"),

    TRANSACTION_FAILED(6003, "Transaction failed"),

    /*
     * Security
     */
    ACCESS_DENIED(7000, "Access denied"),

    INVALID_TOKEN(7001, "Invalid token"),

    PERMISSION_DENIED(7002, "Permission denied"),

    /*
     * Request throttling
     */
    RATE_LIMIT_EXCEEDED(8000, "Rate limit exceeded");

    private final int code;

    private final String message;

    ErrorCode(int code, String message) {

        this.code = code;
        this.message = message;
    }

    /**
     * Returns the numeric error code.
     */
    public int getCode() {
        return code;
    }

    /**
     * Returns the default error message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Indicates whether this represents a successful result.
     */
    public boolean isSuccess() {
        return this == SUCCESS;
    }

    /**
     * Indicates whether this represents an error.
     */
    public boolean isError() {
        return !isSuccess();
    }

    @Override
    public String toString() {

        return code + " - " + message;

    }

}
