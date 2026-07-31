package com.matlasystems.chat.protocol.exception;

import com.matlasystems.chat.common.enums.ErrorCode;

/**
 * Exception thrown when a caller lacks permission to execute a command.
 */
public class AuthorizationException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private static final ErrorCode ERROR_CODE =
            ErrorCode.ACCESS_DENIED;

    /**
     * Creates an exception with the default message.
     */
    public AuthorizationException() {

        this("Access denied.", null);

    }

    /**
     * Creates an exception with a custom message.
     *
     * @param message exception message
     */
    public AuthorizationException(
            String message) {

        this(message, null);

    }

    /**
     * Creates an exception with an underlying cause.
     *
     * @param cause underlying cause
     */
    public AuthorizationException(
            Throwable cause) {

        this("Access denied.", cause);

    }

    /**
     * Creates an exception with a custom message and cause.
     *
     * @param message exception message
     * @param cause underlying cause
     */
    public AuthorizationException(
            String message,
            Throwable cause) {

        super(
                message,
                cause);

    }

    /**
     * Returns the associated error code.
     *
     * @return error code
     */
    public ErrorCode getErrorCode() {

        return ERROR_CODE;

    }

}
