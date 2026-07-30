package com.matlasystems.chat.common.validation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link EmailValidator}.
 */
class EmailValidatorTest {

    @Test
    void validatesEmail() {

        EmailValidator validator =
                new EmailValidator();

        ValidationResult validResult =
                validator.validate(
                        "me@example.com");

        ValidationResult invalidResult =
                validator.validate(
                        "bad");

        assertTrue(
                validResult.isValid());

        assertTrue(
                invalidResult.isInvalid());
    }

}
