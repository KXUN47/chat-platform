package com.matlasystems.chat.protocol.validator;

/** Raised when a packet protocol version is unsupported. */
public class InvalidVersionException extends ValidationException {

    private static final long serialVersionUID = 1L;

    public InvalidVersionException(String message) {
        super(message);
    }
}
