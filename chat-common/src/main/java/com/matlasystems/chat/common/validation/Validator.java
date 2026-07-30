package com.matlasystems.chat.common.validation;

/** Validates a value and reports all detected client-safe errors. */
@FunctionalInterface
public interface Validator<T> {
    ValidationResult validate(T value);
}
