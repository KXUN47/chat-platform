package com.matlasystems.chat.protocol.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link ParseResult}.
 */
class ParseResultTest {

    @Test
    void successCarriesAValueAndNoError() {

        ParseResult<String> result =
                ParseResult.success(
                        "value");

        assertTrue(
                result.isSuccess());

        assertFalse(
                result.isFailure());

        assertEquals(
                "value",
                result.getValue()
                        .orElseThrow());

        assertTrue(
                result.getError()
                        .isEmpty());

        assertEquals(
                "value",
                result.getOrThrow());

    }

    @Test
    void failureCarriesAnErrorAndOptionalCause() {

        Throwable cause =
                new IllegalStateException(
                        "boom");

        ParseResult<String> result =
                ParseResult.failure(
                        "bad input",
                        cause);

        assertTrue(
                result.isFailure());

        assertEquals(
                "bad input",
                result.getError()
                        .orElseThrow());

        assertEquals(
                cause,
                result.getCause()
                        .orElseThrow());

        assertTrue(
                result.getValue()
                        .isEmpty());

    }

    @Test
    void getOrThrowThrowsParserExceptionOnFailure() {

        ParseResult<String> result =
                ParseResult.failure(
                        "bad input");

        ParserException exception =
                assertThrows(
                        ParserException.class,
                        result::getOrThrow);

        assertNotNull(
                exception);

    }

}
