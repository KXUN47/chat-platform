package com.matlasystems.chat.protocol.command;

import java.io.Serial;

/**
 * Thrown when a client is not authorized to execute a command.
 */
public class UnauthorizedCommandException extends CommandException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UnauthorizedCommandException(String message) {
        super(message);
    }

    public UnauthorizedCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedCommandException(String message, String commandName) {
        super(message, commandName);
    }

    public UnauthorizedCommandException(
            String message,
            String commandName,
            String errorCode) {

        super(message, commandName, errorCode);
    }

    public UnauthorizedCommandException(
            String message,
            String commandName,
            String errorCode,
            Throwable cause) {

        super(message, commandName, errorCode, cause);
    }
}
