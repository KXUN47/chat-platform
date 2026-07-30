package com.matlasystems.chat.common.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link ValidationResult}.
 */
class ValidationResultTest {

    @Test
    void tracksErrors() {

        ValidationResult result =
                ValidationResult.valid();

        assertTrue(
                result.isValid());

        result.addError(
                "x",
                "bad",
                "Bad");

        assertTrue(
                result.isInvalid());

        assertEquals(
                1,
                result.getErrors().size());
    }

}
