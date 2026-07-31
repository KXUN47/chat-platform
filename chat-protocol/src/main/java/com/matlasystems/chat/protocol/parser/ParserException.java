package com.matlasystems.chat.protocol.parser;

/** Thrown when a caller requests the value of an unsuccessful {@link ParseResult}. */
public class ParserException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ParserException(String message) {
        super(message);
    }

    public ParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
