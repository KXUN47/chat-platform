package com.matlasystems.chat.protocol.exception;

/**
 * Exception thrown when a required protocol packet field is missing.
 */
public class MissingFieldException
        extends ProtocolException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates an exception with the default message.
     */
    public MissingFieldException() {

        this("Required packet field is missing.", null);

    }

    /**
     * Creates an exception with a custom message.
     *
     * @param message exception message
     */
    public MissingFieldException(
            String message) {

        this(message, null);

    }

    /**
     * Creates an exception with an underlying cause.
     *
     * @param cause underlying cause
     */
    public MissingFieldException(
            Throwable cause) {

        this("Required packet field is missing.", cause);

    }

    /**
     * Creates an exception with a custom message and cause.
     *
     * @param message exception message
     * @param cause underlying cause
     */
    public MissingFieldException(
            String message,
            Throwable cause) {

        super(
                message,
                cause);

    }

}
