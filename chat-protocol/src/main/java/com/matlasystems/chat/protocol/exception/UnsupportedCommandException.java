package com.matlasystems.chat.protocol.exception;

import com.matlasystems.chat.common.enums.ErrorCode;

/**
 * Exception thrown when a command is not supported by the
 * current protocol implementation.
 */
public class UnsupportedCommandException
        extends ProtocolException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates an exception with the default message.
     */
    public UnsupportedCommandException() {

        this(
                "Unsupported command.",
                null);

    }

    /**
     * Creates an exception with a custom message.
     *
     * @param message exception message
     */
    public UnsupportedCommandException(
            String message) {

        this(
                message,
                null);

    }

    /**
     * Creates an exception with an underlying cause.
     *
     * @param cause underlying cause
     */
    public UnsupportedCommandException(
            Throwable cause) {

        this(
                "Unsupported command.",
                cause);

    }

    /**
     * Creates an exception with a custom message and cause.
     *
     * @param message exception message
     * @param cause underlying cause
     */
    public UnsupportedCommandException(
            String message,
            Throwable cause) {

        super(
                ErrorCode.INVALID_COMMAND,
                message,
                cause,
                null);

    }

}
