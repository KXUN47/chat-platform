package com.matlasystems.chat.protocol.exception;

import com.matlasystems.chat.common.enums.ErrorCode;

/**
 * Exception thrown when a peer fails to respond to a heartbeat
 * within the expected timeout period.
 */
public class HeartbeatTimeoutException
        extends ProtocolException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates an exception with the default message.
     */
    public HeartbeatTimeoutException() {

        this("Heartbeat timed out.", null);

    }

    /**
     * Creates an exception with a custom message.
     *
     * @param message exception message
     */
    public HeartbeatTimeoutException(
            String message) {

        this(message, null);

    }

    /**
     * Creates an exception with an underlying cause.
     *
     * @param cause underlying cause
     */
    public HeartbeatTimeoutException(
            Throwable cause) {

        this("Heartbeat timed out.", cause);

    }

    /**
     * Creates an exception with a custom message and cause.
     *
     * @param message exception message
     * @param cause underlying cause
     */
    public HeartbeatTimeoutException(
            String message,
            Throwable cause) {

        super(
                ErrorCode.CONNECTION_TIMEOUT,
                message,
                cause,
                null);

    }

}
