package com.matlasystems.chat.protocol.validator;

/** Base unchecked exception used when an invalid protocol value must halt processing. */
public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
