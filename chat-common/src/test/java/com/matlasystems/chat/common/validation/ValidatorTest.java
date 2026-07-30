package com.matlasystems.chat.common.validation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Validator} functional interface.
 */
class ValidatorTest {

    @Test
    void supportsLambdaValidators() {

        Validator<String> validator =
                value -> ValidationResult.valid();

        ValidationResult result =
                validator.validate("value");

        assertTrue(
                result.isValid());
    }

}
