package com.matlasystems.chat.protocol.exception;

/**
 * Exception thrown when a protocol packet cannot be decoded.
 */
public class PacketDecodingException
        extends ProtocolException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates an exception with the default message.
     */
    public PacketDecodingException() {

        this("Packet decoding failed.", null);

    }

    /**
     * Creates an exception with a custom message.
     *
     * @param message exception message
     */
    public PacketDecodingException(
            String message) {

        this(message, null);

    }

    /**
     * Creates an exception with an underlying cause.
     *
     * @param cause underlying cause
     */
    public PacketDecodingException(
            Throwable cause) {

        this("Packet decoding failed.", cause);

    }

    /**
     * Creates an exception with a custom message and cause.
     *
     * @param message exception message
     * @param cause underlying cause
     */
    public PacketDecodingException(
            String message,
            Throwable cause) {

        super(
                message,
                cause);

    }

}
