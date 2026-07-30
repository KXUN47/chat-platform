package com.matlasystems.chat.common.validation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link PasswordValidator}.
 */
class PasswordValidatorTest {

    @Test
    void validatesPasswordPolicy() {

        PasswordValidator validator =
                new PasswordValidator();

        ValidationResult validResult =
                validator.validate(
                        "StrongPass1");

        ValidationResult invalidResult =
                validator.validate(
                        "weak");

        assertTrue(
                validResult.isValid());

        assertTrue(
                invalidResult.isInvalid());
    }

}
