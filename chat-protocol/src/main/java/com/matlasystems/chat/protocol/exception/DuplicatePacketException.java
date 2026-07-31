package com.matlasystems.chat.protocol.exception;

/**
 * Exception thrown when a packet with the same identifier
 * has already been processed.
 */
public class DuplicatePacketException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates an exception with the default message.
     */
    public DuplicatePacketException() {

        this("Duplicate packet.", null);

    }

    /**
     * Creates an exception with a custom message.
     *
     * @param message exception message
     */
    public DuplicatePacketException(
            String message) {

        this(message, null);

    }

    /**
     * Creates an exception with an underlying cause.
     *
     * @param cause underlying cause
     */
    public DuplicatePacketException(
            Throwable cause) {

        this("Duplicate packet.", cause);

    }

    /**
     * Creates an exception with a custom message and cause.
     *
     * @param message exception message
     * @param cause underlying cause
     */
    public DuplicatePacketException(
            String message,
            Throwable cause) {

        super(
                message,
                cause);

    }

}
