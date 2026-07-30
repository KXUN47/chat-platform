package com.matlasystems.chat.common.validation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link ValidationUtils}.
 */
class ValidationUtilsTest {

    @Test
    void delegatesValidation() {

        ValidationResult usernameResult =
                ValidationUtils.validateUsername(
                        "user_1");

        ValidationResult emailResult =
                ValidationUtils.validateEmail(
                        "bad");

        assertTrue(
                usernameResult.isValid());

        assertTrue(
                emailResult.isInvalid());
    }

}
