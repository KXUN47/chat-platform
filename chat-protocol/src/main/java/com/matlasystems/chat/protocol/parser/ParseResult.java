package com.matlasystems.chat.protocol.parser;

import java.util.Objects;
import java.util.Optional;

/** Represents either a successfully parsed value or a parse failure. */
public final class ParseResult<T> {

    private final T value;
    private final String error;
    private final Throwable cause;

    private ParseResult(T value, String error, Throwable cause) {
        this.value = value;
        this.error = error;
        this.cause = cause;
    }

    public static <T> ParseResult<T> success(T value) {
        return new ParseResult<>(value, null, null);
    }

    public static <T> ParseResult<T> failure(String error) {
        return failure(error, null);
    }

    public static <T> ParseResult<T> failure(String error, Throwable cause) {
        return new ParseResult<>(null, Objects.requireNonNull(error, "error must not be null"), cause);
    }

    public boolean isSuccess() {
        return error == null;
    }

    public boolean isFailure() {
        return !isSuccess();
    }

    public Optional<T> getValue() {
        return Optional.ofNullable(value);
    }

    public Optional<String> getError() {
        return Optional.ofNullable(error);
    }

    public Optional<Throwable> getCause() {
        return Optional.ofNullable(cause);
    }

    public T getOrThrow() {
        if (isFailure()) {
            throw new ParserException(error, cause);
        }
        return value;
    }
}
