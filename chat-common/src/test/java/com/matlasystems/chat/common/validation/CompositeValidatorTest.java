package com.matlasystems.chat.common.validation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link CompositeValidator}.
 */
class CompositeValidatorTest {

    @Test
    void mergesResults() {

        CompositeValidator<String> validator =
                new CompositeValidator<>();

        validator.add(
                value -> ValidationResult.invalid(
                        value,
                        "bad",
                        "bad"));

        ValidationResult result =
                validator.validate("x");

        assertTrue(
                result.isInvalid());
    }

}
