package com.matlasystems.chat.common.validation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link MessageValidator}.
 */
class MessageValidatorTest {

    @Test
    void rejectsBlankMessages() {

        MessageValidator validator =
                new MessageValidator();

        ValidationResult validResult =
                validator.validate(
                        "hello");

        ValidationResult invalidResult =
                validator.validate(
                        " ");

        assertTrue(
                validResult.isValid());

        assertTrue(
                invalidResult.isInvalid());
    }

}
