package com.matlasystems.chat.common.validation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link UsernameValidator}.
 */
class UsernameValidatorTest {

    @Test
    void validatesUsername() {

        UsernameValidator validator =
                new UsernameValidator();

        ValidationResult validResult =
                validator.validate(
                        "user_1");

        ValidationResult invalidResult =
                validator.validate(
                        "bad name");

        assertTrue(
                validResult.isValid());

        assertTrue(
                invalidResult.isInvalid());
    }

}
