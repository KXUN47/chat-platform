package com.matlasystems.chat.protocol.exception;

/**
 * Exception thrown when a protocol packet is invalid or malformed.
 */
public class InvalidPacketException
        extends ProtocolException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates an exception with the default message.
     */
    public InvalidPacketException() {

        this("Invalid packet.", null);

    }

    /**
     * Creates an exception with a custom message.
     *
     * @param message exception message
     */
    public InvalidPacketException(
            String message) {

        this(message, null);

    }

    /**
     * Creates an exception with an underlying cause.
     *
     * @param cause underlying cause
     */
    public InvalidPacketException(
            Throwable cause) {

        this("Invalid packet.", cause);

    }

    /**
     * Creates an exception with a custom message and cause.
     *
     * @param message exception message
     * @param cause underlying cause
     */
    public InvalidPacketException(
            String message,
            Throwable cause) {

        super(
                message,
                cause);

    }

}
