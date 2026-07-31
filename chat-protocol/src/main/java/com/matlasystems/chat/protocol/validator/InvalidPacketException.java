package com.matlasystems.chat.protocol.validator;

/** Raised when a packet envelope is invalid. */
public class InvalidPacketException extends ValidationException {

    private static final long serialVersionUID = 1L;

    public InvalidPacketException(String message) {
        super(message);
    }
}
