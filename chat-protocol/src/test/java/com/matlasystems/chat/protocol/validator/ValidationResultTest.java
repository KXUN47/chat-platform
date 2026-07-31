package com.matlasystems.chat.protocol.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link ValidationResult}.
 */
class ValidationResultTest {

    @Test
    void validHasNoErrors() {

        ValidationResult result =
                ValidationResult.valid();

        assertTrue(
                result.isValid());

        assertFalse(
                result.isInvalid());

        assertTrue(
                result.getErrors().isEmpty());

    }

    @Test
    void invalidFactoryAddsASingleError() {

        ValidationResult result =
                ValidationResult.invalid(
                        "field",
                        "code",
                        "message");

        assertTrue(
                result.isInvalid());

        assertEquals(
                1,
                result.getErrors().size());

        assertEquals(
                "field",
                result.getErrors()
                        .get(0)
                        .getField());

    }

    @Test
    void addErrorAcceptsRawFieldsOrAnErrorObject() {

        ValidationResult result =
                ValidationResult.valid();

        ValidationError error =
                new ValidationError(
                        "b",
                        "codeB",
                        "messageB");

        result.addError(
                "a",
                "codeA",
                "messageA");

        result.addError(
                error);

        assertEquals(
                2,
                result.getErrors().size());

    }

    @Test
    void addErrorIgnoresNullErrorObject() {

        ValidationResult result =
                ValidationResult.valid();

        result.addError(
                (ValidationError) null);

        assertTrue(
                result.isValid());

    }

    @Test
    void mergeCombinesErrorsFromAnotherResult() {

        ValidationResult first =
                ValidationResult.invalid(
                        "a",
                        "code",
                        "message");

        ValidationResult second =
                ValidationResult.invalid(
                        "b",
                        "code",
                        "message");

        first.merge(
                second);

        assertEquals(
                2,
                first.getErrors().size());

    }

    @Test
    void mergeWithNullIsANoOp() {

        ValidationResult result =
                ValidationResult.valid();

        assertDoesNotThrow(
                () -> result.merge(
                        null));

        assertTrue(
                result.isValid());

    }

    @Test
    void throwIfInvalidThrowsOnlyWhenInvalid() {

        ValidationResult validResult =
                ValidationResult.valid();

        assertDoesNotThrow(
                validResult::throwIfInvalid);

        ValidationResult invalidResult =
                ValidationResult.invalid(
                        "field",
                        "code",
                        "message");

        ValidationException exception =
                assertThrows(
                        ValidationException.class,
                        invalidResult::throwIfInvalid);

        assertNotNull(
                exception);

    }

}
