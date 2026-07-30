package com.matlasystems.chat.common.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link ValidationError}.
 */
class ValidationErrorTest {

    @Test
    void storesErrorDetails() {

        ValidationError validationError =
                new ValidationError(
                        "field",
                        "code",
                        "message");

        ValidationError sameValidationError =
                new ValidationError(
                        "field",
                        "code",
                        "message");

        assertEquals(
                "field",
                validationError.getField());

        assertEquals(
                "code",
                validationError.getCode());

        assertEquals(
                "message",
                validationError.getMessage());

        assertEquals(
                validationError,
                sameValidationError);

        assertTrue(
                validationError.toString()
                        .contains("message"));
    }

}
