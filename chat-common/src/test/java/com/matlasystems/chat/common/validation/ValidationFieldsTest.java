package com.matlasystems.chat.common.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link ValidationFields}.
 */
class ValidationFieldsTest {

    @Test
    void exposesFieldNames() {

        assertEquals(
                "email",
                ValidationFields.EMAIL);

        assertEquals(
                "packet",
                ValidationFields.PACKET);
    }

}
