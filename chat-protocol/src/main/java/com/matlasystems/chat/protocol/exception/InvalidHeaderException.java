package com.matlasystems.chat.protocol.exception;

/**
 * Exception thrown when a protocol packet header is missing
 * or invalid.
 */
public class InvalidHeaderException
        extends ProtocolException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates an exception with the default message.
     */
    public InvalidHeaderException() {

        this("Invalid packet header.", null);

    }

    /**
     * Creates an exception with a custom message.
     *
     * @param message exception message
     */
    public InvalidHeaderException(
            String message) {

        this(message, null);

    }

    /**
     * Creates an exception with an underlying cause.
     *
     * @param cause underlying cause
     */
    public InvalidHeaderException(
            Throwable cause) {

        this("Invalid packet header.", cause);

    }

    /**
     * Creates an exception with a custom message and cause.
     *
     * @param message exception message
     * @param cause underlying cause
     */
    public InvalidHeaderException(
            String message,
            Throwable cause) {

        super(
                message,
                cause);

    }

}
