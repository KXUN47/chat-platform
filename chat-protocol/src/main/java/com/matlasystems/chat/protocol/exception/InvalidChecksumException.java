package com.matlasystems.chat.protocol.exception;

import com.matlasystems.chat.common.enums.ErrorCode;

/**
 * Exception thrown when a packet checksum does not match
 * the packet contents.
 */
public class InvalidChecksumException
        extends ProtocolException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates an exception with the default message.
     */
    public InvalidChecksumException() {

        this("Invalid checksum.", null);

    }

    /**
     * Creates an exception with a custom message.
     *
     * @param message exception message
     */
    public InvalidChecksumException(
            String message) {

        this(message, null);

    }

    /**
     * Creates an exception with an underlying cause.
     *
     * @param cause underlying cause
     */
    public InvalidChecksumException(
            Throwable cause) {

        this("Invalid checksum.", cause);

    }

    /**
     * Creates an exception with a custom message and cause.
     *
     * @param message exception message
     * @param cause underlying cause
     */
    public InvalidChecksumException(
            String message,
            Throwable cause) {

        super(
                ErrorCode.CHECKSUM_FAILED,
                message,
                cause,
                null);

    }

}
