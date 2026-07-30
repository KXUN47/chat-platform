package com.matlasystems.chat.common.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link ValidationMessages}.
 */
class ValidationMessagesTest {

    @Test
    void exposesMessages() {

        assertEquals(
                "Username is required",
                ValidationMessages.USERNAME_REQUIRED);

        assertTrue(
                ValidationMessages.USERNAME_FORMAT
                        .contains("unsupported"));
    }

}
