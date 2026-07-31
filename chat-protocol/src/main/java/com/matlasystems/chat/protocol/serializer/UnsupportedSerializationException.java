package com.matlasystems.chat.protocol.serializer;

/** Requested serialization format is not supported. */
public class UnsupportedSerializationException extends SerializationException {

    private static final long serialVersionUID = 1L;

    public UnsupportedSerializationException(String message) {
        super(message);
    }

    public UnsupportedSerializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
