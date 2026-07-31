package com.matlasystems.chat.protocol.command;

import java.io.Serial;

/**
 * Thrown when a command is not supported by the current protocol version.
 */
public class UnsupportedCommandException extends CommandException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UnsupportedCommandException(String message) {
        super(message);
    }

    public UnsupportedCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedCommandException(String message, String commandName) {
        super(message, commandName);
    }

    public UnsupportedCommandException(
            String message,
            String commandName,
            String errorCode) {

        super(message, commandName, errorCode);
    }

    public UnsupportedCommandException(
            String message,
            String commandName,
            String errorCode,
            Throwable cause) {

        super(message, commandName, errorCode, cause);
    }
}
