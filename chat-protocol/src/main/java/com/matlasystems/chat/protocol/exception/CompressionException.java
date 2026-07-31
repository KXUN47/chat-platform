package com.matlasystems.chat.protocol.exception;

/**
 * Exception thrown when packet compression or decompression fails.
 */
public class CompressionException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates an exception with the default message.
     */
    public CompressionException() {

        this("Packet compression failed.", null);

    }

    /**
     * Creates an exception with a custom message.
     *
     * @param message exception message
     */
    public CompressionException(
            String message) {

        this(message, null);

    }

    /**
     * Creates an exception with a cause.
     *
     * @param cause underlying cause
     */
    public CompressionException(
            Throwable cause) {

        this("Packet compression failed.", cause);

    }

    /**
     * Creates an exception with a custom message and cause.
     *
     * @param message exception message
     * @param cause underlying cause
     */
    public CompressionException(
            String message,
            Throwable cause) {

        super(message, cause);

    }

}
