package com.matlasystems.chat.protocol.exception;

import com.matlasystems.chat.common.enums.ErrorCode;

/**
 * Exception thrown when a protocol packet fails validation.
 */
public class PacketValidationException
        extends ProtocolException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates an exception with the default message.
     */
    public PacketValidationException() {

        this(
                "Packet validation failed.",
                null);

    }

    /**
     * Creates an exception with a custom message.
     *
     * @param message exception message
     */
    public PacketValidationException(
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
    public PacketValidationException(
            Throwable cause) {

        this(
                "Packet validation failed.",
                cause);

    }

    /**
     * Creates an exception with a custom message and cause.
     *
     * @param message exception message
     * @param cause underlying cause
     */
    public PacketValidationException(
            String message,
            Throwable cause) {

        super(
                ErrorCode.INVALID_REQUEST,
                message,
                cause,
                null);

    }

}
