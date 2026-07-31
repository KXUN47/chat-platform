package com.matlasystems.chat.protocol.command;

import java.io.Serial;

/**
 * Thrown when a command or its payload is invalid.
 */
public class InvalidCommandException extends CommandException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidCommandException(String message) {
        super(message);
    }

    public InvalidCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCommandException(String message, String commandName) {
        super(message, commandName);
    }

    public InvalidCommandException(
            String message,
            String commandName,
            String errorCode) {

        super(message, commandName, errorCode);
    }

    public InvalidCommandException(
            String message,
            String commandName,
            String errorCode,
            Throwable cause) {

        super(message, commandName, errorCode, cause);
    }
}
