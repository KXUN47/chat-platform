package com.matlasystems.chat.common.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link ValidationUtils}.
 */
class ValidationUtilsTest {

    @Test
    void validatesPrimitiveInput() {

        assertTrue(
                ValidationUtils.isValidUsername(
                        "user_1"));

        assertTrue(
                ValidationUtils.isValidEmail(
                        "a@b.co"));

        assertTrue(
                ValidationUtils.isValidFileName(
                        "file.txt"));

        assertTrue(
                ValidationUtils.isValidContentType(
                        "text/plain"));
    }

}
