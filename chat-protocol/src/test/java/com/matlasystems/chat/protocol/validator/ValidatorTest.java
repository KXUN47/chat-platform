package com.matlasystems.chat.protocol.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Validator} default method {@code validateOrThrow}.
 */
class ValidatorTest {

    private final Validator<String> nonBlank = value ->
            value == null || value.isBlank()
                    ? ValidationResult.invalid("value", "required", "Value is required")
                    : ValidationResult.valid();

    @Test
    void doesNotThrowForAValidValue() {

        assertDoesNotThrow(() -> nonBlank.validateOrThrow("ok"));
    }

    @Test
    void throwsValidationExceptionForAnInvalidValue() {

        assertThrows(ValidationException.class, () -> nonBlank.validateOrThrow(""));
    }

}
