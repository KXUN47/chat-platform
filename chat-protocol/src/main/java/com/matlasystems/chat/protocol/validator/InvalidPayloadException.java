package com.matlasystems.chat.protocol.validator;

/** Raised when a packet payload is invalid. */
public class InvalidPayloadException extends ValidationException {

    private static final long serialVersionUID = 1L;

    public InvalidPayloadException(String message) {
        super(message);
    }
}
