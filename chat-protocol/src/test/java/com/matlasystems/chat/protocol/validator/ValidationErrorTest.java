package com.matlasystems.chat.protocol.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link ValidationError}.
 */
class ValidationErrorTest {

    @Test
    void exposesConstructorValues() {

        ValidationError error =
                new ValidationError(
                        "field",
                        "code",
                        "message");

        assertEquals(
                "field",
                error.getField());

        assertEquals(
                "code",
                error.getCode());

        assertEquals(
                "message",
                error.getMessage());

    }

    @Test
    void rejectsNullArguments() {

        NullPointerException nullFieldException =
                assertThrows(
                        NullPointerException.class,
                        () -> new ValidationError(
                                null,
                                "code",
                                "message"));

        assertNotNull(
                nullFieldException);

        NullPointerException nullCodeException =
                assertThrows(
                        NullPointerException.class,
                        () -> new ValidationError(
                                "field",
                                null,
                                "message"));

        assertNotNull(
                nullCodeException);

        NullPointerException nullMessageException =
                assertThrows(
                        NullPointerException.class,
                        () -> new ValidationError(
                                "field",
                                "code",
                                null));

        assertNotNull(
                nullMessageException);

    }

    @Test
    void equalityIsBasedOnAllFields() {

        ValidationError first =
                new ValidationError(
                        "field",
                        "code",
                        "message");

        ValidationError second =
                new ValidationError(
                        "field",
                        "code",
                        "message");

        ValidationError different =
                new ValidationError(
                        "other",
                        "code",
                        "message");

        assertEquals(
                first,
                second);

        assertEquals(
                first.hashCode(),
                second.hashCode());

        assertNotEquals(
                first,
                different);

    }

}
