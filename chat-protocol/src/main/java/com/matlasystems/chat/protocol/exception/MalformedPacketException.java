package com.matlasystems.chat.protocol.exception;

/**
 * Exception thrown when a protocol packet does not conform
 * to the expected wire format.
 */
public class MalformedPacketException
        extends ProtocolException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates an exception with the default message.
     */
    public MalformedPacketException() {

        this("Malformed packet.", null);

    }

    /**
     * Creates an exception with a custom message.
     *
     * @param message exception message
     */
    public MalformedPacketException(
            String message) {

        this(message, null);

    }

    /**
     * Creates an exception with an underlying cause.
     *
     * @param cause underlying cause
     */
    public MalformedPacketException(
            Throwable cause) {

        this("Malformed packet.", cause);

    }

    /**
     * Creates an exception with a custom message and cause.
     *
     * @param message exception message
     * @param cause underlying cause
     */
    public MalformedPacketException(
            String message,
            Throwable cause) {

        super(
                message,
                cause);

    }

}
