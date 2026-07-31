package com.matlasystems.chat.protocol.exception;

import com.matlasystems.chat.common.enums.ErrorCode;

/**
 * Exception thrown when an encoded protocol packet exceeds
 * the configured maximum allowed size.
 */
public class PacketTooLargeException
        extends ProtocolException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates an exception with the default message.
     */
    public PacketTooLargeException() {

        this(
                "Packet exceeds the maximum allowed size.",
                null);

    }

    /**
     * Creates an exception with a custom message.
     *
     * @param message exception message
     */
    public PacketTooLargeException(
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
    public PacketTooLargeException(
            Throwable cause) {

        this(
                "Packet exceeds the maximum allowed size.",
                cause);

    }

    /**
     * Creates an exception with a custom message and cause.
     *
     * @param message exception message
     * @param cause underlying cause
     */
    public PacketTooLargeException(
            String message,
            Throwable cause) {

        super(
                ErrorCode.FILE_TOO_LARGE,
                message,
                cause,
                null);

    }

}
