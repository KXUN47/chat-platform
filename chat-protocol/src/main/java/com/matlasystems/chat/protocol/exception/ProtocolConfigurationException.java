package com.matlasystems.chat.protocol.exception;

import com.matlasystems.chat.common.enums.ErrorCode;

/**
 * Exception thrown when the protocol configuration is missing,
 * invalid, or inconsistent.
 */
public class ProtocolConfigurationException
        extends ProtocolException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates an exception with the default message.
     */
    public ProtocolConfigurationException() {

        this(
                "Invalid protocol configuration.",
                null);

    }

    /**
     * Creates an exception with a custom message.
     *
     * @param message exception message
     */
    public ProtocolConfigurationException(
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
    public ProtocolConfigurationException(
            Throwable cause) {

        this(
                "Invalid protocol configuration.",
                cause);

    }

    /**
     * Creates an exception with a custom message and cause.
     *
     * @param message exception message
     * @param cause underlying cause
     */
    public ProtocolConfigurationException(
            String message,
            Throwable cause) {

        super(
                ErrorCode.INTERNAL_SERVER_ERROR,
                message,
                cause,
                null);

    }

}
