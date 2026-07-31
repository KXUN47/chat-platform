package com.matlasystems.chat.protocol.handler;

import java.util.Objects;

import com.matlasystems.chat.protocol.exception.ProtocolException;

/**
 * Centralized protocol error handler.
 */
public class ErrorHandler {

    /**
     * Converts an exception into a handler result.
     *
     * @param exception exception to handle
     * @return handler result representing the failure
     */
    public HandlerResult handle(
            Exception exception) {

        Exception validatedException =
                Objects.requireNonNull(
                        exception,
                        "exception cannot be null");

        if (validatedException instanceof ProtocolException) {

            return HandlerResult.failure(
                    validatedException.getMessage(),
                    validatedException);

        }

        return HandlerResult.failure(
                "Unexpected protocol error.",
                validatedException);

    }

}
