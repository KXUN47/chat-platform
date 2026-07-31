package com.matlasystems.chat.protocol.exception;

/**
 * Exception thrown when a protocol packet payload is missing
 * or invalid.
 */
public class InvalidPayloadException
        extends ProtocolException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates an exception with the default message.
     */
    public InvalidPayloadException() {

        this("Invalid packet payload.", null);

    }

    /**
     * Creates an exception with a custom message.
     *
     * @param message exception message
     */
    public InvalidPayloadException(
            String message) {

        this(message, null);

    }

    /**
     * Creates an exception with an underlying cause.
     *
     * @param cause underlying cause
     */
    public InvalidPayloadException(
            Throwable cause) {

        this("Invalid packet payload.", cause);

    }

    /**
     * Creates an exception with a custom message and cause.
     *
     * @param message exception message
     * @param cause underlying cause
     */
    public InvalidPayloadException(
            String message,
            Throwable cause) {

        super(
                message,
                cause);

    }

}
