package com.matlasystems.chat.protocol.exception;

/**
 * Exception thrown when protocol object serialization fails.
 */
public class SerializationException
        extends ProtocolException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates an exception with the default message.
     */
    public SerializationException() {

        this(
                "Protocol serialization failed.",
                null);

    }

    /**
     * Creates an exception with a custom message.
     *
     * @param message exception message
     */
    public SerializationException(
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
    public SerializationException(
            Throwable cause) {

        this(
                "Protocol serialization failed.",
                cause);

    }

    /**
     * Creates an exception with a custom message and cause.
     *
     * @param message exception message
     * @param cause underlying cause
     */
    public SerializationException(
            String message,
            Throwable cause) {

        super(
                message,
                cause);

    }

}
