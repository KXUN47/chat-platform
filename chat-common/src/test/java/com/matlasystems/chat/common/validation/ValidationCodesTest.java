package com.matlasystems.chat.common.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link ValidationCodes}.
 */
class ValidationCodesTest {

    @Test
    void exposesStableCodes() {

        assertEquals(
                "required",
                ValidationCodes.REQUIRED);

        assertEquals(
                "format",
                ValidationCodes.FORMAT);
    }

}
