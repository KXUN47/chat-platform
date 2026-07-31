package com.matlasystems.chat.protocol.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.protocol.exception.ProtocolException;

/**
 * Unit tests for {@link ErrorHandler}.
 */
class ErrorHandlerTest {

    private final ErrorHandler handler =
            new ErrorHandler();

    @Test
    void usesTheExceptionMessageForProtocolExceptions() {

        HandlerResult result =
                handler.handle(
                        new ProtocolException(
                                "bad packet"));

        assertFalse(
                result.isSuccess());

        assertEquals(
                "bad packet",
                result.getMessage());

    }

    @Test
    void usesAGenericMessageForOtherExceptions() {

        HandlerResult result =
                handler.handle(
                        new IllegalStateException(
                                "boom"));

        assertFalse(
                result.isSuccess());

        assertEquals(
                "Unexpected protocol error.",
                result.getMessage());

    }

    @Test
    void rejectsNullException() {

        NullPointerException exception =
                assertThrows(
                        NullPointerException.class,
                        () -> handler.handle(
                                null));

        assertNotNull(
                exception);

    }

}
