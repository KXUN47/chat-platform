package com.matlasystems.chat.protocol.exception;

/**
 * Exception thrown when a protocol packet cannot be encoded.
 */
public class PacketEncodingException
        extends ProtocolException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates an exception with the default message.
     */
    public PacketEncodingException() {

        this("Packet encoding failed.", null);

    }

    /**
     * Creates an exception with a custom message.
     *
     * @param message exception message
     */
    public PacketEncodingException(
            String message) {

        this(message, null);

    }

    /**
     * Creates an exception with an underlying cause.
     *
     * @param cause underlying cause
     */
    public PacketEncodingException(
            Throwable cause) {

        this("Packet encoding failed.", cause);

    }

    /**
     * Creates an exception with a custom message and cause.
     *
     * @param message exception message
     * @param cause underlying cause
     */
    public PacketEncodingException(
            String message,
            Throwable cause) {

        super(
                message,
                cause);

    }

}
