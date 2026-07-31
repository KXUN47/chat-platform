package com.matlasystems.chat.protocol.validator;

/** Validates a protocol value without using exceptions for normal invalid input. */
@FunctionalInterface
public interface Validator<T> {

    ValidationResult validate(T value);

    default void validateOrThrow(T value) {
        validate(value).throwIfInvalid();
    }
}
