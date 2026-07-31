package com.matlasystems.chat.protocol.validator;

/** Raised when a packet header is invalid. */
public class InvalidHeaderException extends ValidationException {

    private static final long serialVersionUID = 1L;

    public InvalidHeaderException(String message) {
        super(message);
    }
}
