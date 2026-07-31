package com.matlasystems.chat.protocol.handler;

import java.util.Objects;

import com.matlasystems.chat.common.protocol.Packet;

/**
 * Result returned from a protocol handler.
 */
public final class HandlerResult {

    private final boolean success;

    private final Packet response;

    private final String message;

    private final Throwable cause;

    private HandlerResult(
            boolean success,
            Packet response,
            String message,
            Throwable cause) {

        this.success = success;
        this.response = response;
        this.message = message;
        this.cause = cause;

    }

    /**
     * Successful execution without a response packet.
     */
    public static HandlerResult success() {

        return new HandlerResult(
                true,
                null,
                "Success",
                null);

    }

    /**
     * Successful execution with a response packet.
     */
    public static HandlerResult success(Packet response) {

        return new HandlerResult(
                true,
                Objects.requireNonNull(response),
                "Success",
                null);

    }

    /**
     * Failed execution.
     */
    public static HandlerResult failure(String message) {

        return new HandlerResult(
                false,
                null,
                Objects.requireNonNull(message),
                null);

    }

    /**
     * Failed execution with an exception.
     */
    public static HandlerResult failure(
            String message,
            Throwable cause) {

        return new HandlerResult(
                false,
                null,
                Objects.requireNonNull(message),
                cause);

    }

    public boolean isSuccess() {
        return success;
    }

    public Packet getResponse() {
        return response;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getCause() {
        return cause;
    }

}
