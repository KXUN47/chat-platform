package com.matlasystems.chat.protocol.exception;

import com.matlasystems.chat.common.enums.ErrorCode;

/**
 * Exception thrown when packet encryption or decryption fails.
 */
public class EncryptionException
        extends ProtocolException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates an exception with the default message.
     */
    public EncryptionException() {

        this("Packet encryption failed.", null);

    }

    /**
     * Creates an exception with a custom message.
     *
     * @param message exception message
     */
    public EncryptionException(
            String message) {

        this(message, null);

    }

    /**
     * Creates an exception with an underlying cause.
     *
     * @param cause underlying cause
     */
    public EncryptionException(
            Throwable cause) {

        this("Packet encryption failed.", cause);

    }

    /**
     * Creates an exception with a custom message and cause.
     *
     * @param message exception message
     * @param cause underlying cause
     */
    public EncryptionException(
            String message,
            Throwable cause) {

        super(
                ErrorCode.INVALID_TOKEN,
                message,
                cause,
                null);

    }

}
