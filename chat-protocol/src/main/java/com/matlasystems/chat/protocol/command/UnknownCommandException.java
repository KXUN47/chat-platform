package com.matlasystems.chat.protocol.command;

import java.io.Serial;

/**
 * Thrown when a requested command cannot be found in the
 * {@link CommandRegistry}.
 *
 * <p>
 * This exception indicates that the client has attempted to execute a
 * command that is not registered or is not supported by the current
 * protocol version.
 * </p>
 *
 * <p>Examples:</p>
 * <ul>
 *     <li>LOGINN (misspelled)</li>
 *     <li>DELETE_DATABASE</li>
 *     <li>VOICE_CALL on a server that does not support it</li>
 * </ul>
 */
public class UnknownCommandException extends CommandException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new exception with an error message.
     *
     * @param message error message
     */
    public UnknownCommandException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with a message and root cause.
     *
     * @param message error message
     * @param cause underlying cause
     */
    public UnknownCommandException(
            String message,
            Throwable cause) {

        super(message, cause);

    }

    /**
     * Creates a new exception with command information.
     *
     * @param message error message
     * @param commandName command that caused the error
     */
    public UnknownCommandException(
            String message,
            String commandName) {

        super(message, commandName);

    }

    /**
     * Creates a new exception with command information and an
     * application error code.
     *
     * @param message error message
     * @param commandName command that caused the error
     * @param errorCode application error code
     */
    public UnknownCommandException(
            String message,
            String commandName,
            String errorCode) {

        super(message, commandName, errorCode);

    }

    /**
     * Creates a fully populated exception.
     *
     * @param message error message
     * @param commandName command that caused the error
     * @param errorCode application error code
     * @param cause underlying cause
     */
    public UnknownCommandException(
            String message,
            String commandName,
            String errorCode,
            Throwable cause) {

        super(message, commandName, errorCode, cause);

    }

}
