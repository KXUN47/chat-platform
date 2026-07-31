package com.matlasystems.chat.protocol.validator;

/** Raised when a packet command is invalid or unsupported. */
public class InvalidCommandException extends ValidationException {

    private static final long serialVersionUID = 1L;

    public InvalidCommandException(String message) {
        super(message);
    }
}
