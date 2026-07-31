package com.matlasystems.chat.protocol.exception;

/**
 * Exception thrown when protocol packet deserialization fails.
 */
public class DeserializationException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates an exception with the default message.
     */
    public DeserializationException() {

        this("Protocol deserialization failed.", null);

    }

    /**
     * Creates an exception with a custom message.
     *
     * @param message exception message
     */
    public DeserializationException(
            String message) {

        this(message, null);

    }

    /**
     * Creates an exception with an underlying cause.
     *
     * @param cause underlying cause
     */
    public DeserializationException(
            Throwable cause) {

        this("Protocol deserialization failed.", cause);

    }

    /**
     * Creates an exception with a custom message and cause.
     *
     * @param message exception message
     * @param cause underlying cause
     */
    public DeserializationException(
            String message,
            Throwable cause) {

        super(message, cause);

    }

}
