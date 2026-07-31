package com.matlasystems.chat.protocol.exception;

import com.matlasystems.chat.common.enums.ErrorCode;

/**
 * Exception thrown when a command requires an authenticated session.
 */
public class AuthenticationRequiredException
        extends ProtocolException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates an exception with the default message.
     */
    public AuthenticationRequiredException() {

        this("Authentication is required.", null);

    }

    /**
     * Creates an exception with a custom message.
     *
     * @param message exception message
     */
    public AuthenticationRequiredException(
            String message) {

        this(message, null);

    }

    /**
     * Creates an exception with a cause.
     *
     * @param cause underlying cause
     */
    public AuthenticationRequiredException(
            Throwable cause) {

        this("Authentication is required.", cause);

    }

    /**
     * Creates an exception with a custom message and cause.
     *
     * @param message exception message
     * @param cause underlying cause
     */
    public AuthenticationRequiredException(
            String message,
            Throwable cause) {

        super(
                ErrorCode.AUTHENTICATION_FAILED,
                message,
                cause,
                null);

    }

}
