package com.matlasystems.chat.common.constants;

/**
 * Client-safe error messages and stable error codes.
 */
public final class ErrorMessages {

    private ErrorMessages() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final String INVALID_CREDENTIALS_CODE = "AUTH-001";
    public static final String USER_ALREADY_LOGGED_IN_CODE = "AUTH-002";
    public static final String USER_NOT_FOUND_CODE = "USER-001";
    public static final String EMPTY_MESSAGE_CODE = "MSG-001";
    public static final String FILE_TOO_LARGE_CODE = "FILE-001";
    public static final String CONNECTION_LOST_CODE = "NET-001";
    public static final String INTERNAL_SERVER_ERROR_CODE = "SYS-001";
    public static final String VALIDATION_ERROR_CODE = "VAL-001";

    public static final String INVALID_CREDENTIALS = "Invalid username or password";
    public static final String USER_ALREADY_EXISTS = "Username is already in use";
    public static final String USER_ALREADY_LOGGED_IN = "User is already logged in";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String SESSION_EXPIRED = "Session has expired";
    public static final String UNAUTHORIZED = "Authentication is required";
    public static final String FORBIDDEN = "You are not permitted to perform this action";
    public static final String INVALID_REQUEST = "Invalid request";
    public static final String INVALID_PACKET = "Invalid protocol packet";
    public static final String UNSUPPORTED_COMMAND = "Unsupported command";
    public static final String EMPTY_MESSAGE = "Message cannot be empty";
    public static final String MESSAGE_TOO_LONG = "Message exceeds the maximum allowed length";
    public static final String FILE_TOO_LARGE = "File exceeds the maximum allowed size";
    public static final String INVALID_FILE_NAME = "Invalid file name";
    public static final String CONNECTION_LOST = "Connection to the server was lost";
    public static final String INTERNAL_SERVER_ERROR = "An internal server error occurred";
    public static final String CONFIGURATION_ERROR = "Server configuration is invalid";
}
