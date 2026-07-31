package com.matlasystems.chat.protocol.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.common.protocol.PacketStatus;

/**
 * Unit tests for {@link HandlerResult}.
 */
class HandlerResultTest {

    @Test
    void successWithoutResponseHasNoPacket() {

        HandlerResult result =
                HandlerResult.success();

        assertTrue(
                result.isSuccess());

        assertNull(
                result.getResponse());

        assertEquals(
                "Success",
                result.getMessage());

    }

    @Test
    void successWithResponseCarriesThePacket() {

        Packet response =
                new Packet(
                        Header.create(
                                CommandType.PONG,
                                PacketStatus.RESPONSE),
                        null);

        HandlerResult result =
                HandlerResult.success(
                        response);

        assertTrue(
                result.isSuccess());

        assertEquals(
                response,
                result.getResponse());

    }

    @Test
    void successRejectsNullResponse() {

        NullPointerException exception =
                assertThrows(
                        NullPointerException.class,
                        () -> HandlerResult.success(
                                null));

        assertNotNull(
                exception);

    }

    @Test
    void failureCarriesMessageAndOptionalCause() {

        Throwable cause =
                new RuntimeException(
                        "boom");

        HandlerResult result =
                HandlerResult.failure(
                        "failed",
                        cause);

        assertFalse(
                result.isSuccess());

        assertEquals(
                "failed",
                result.getMessage());

        assertEquals(
                cause,
                result.getCause());

        assertNull(
                result.getResponse());

    }

    @Test
    void failureWithoutCauseHasNullCause() {

        HandlerResult result =
                HandlerResult.failure(
                        "failed");

        assertNull(
                result.getCause());

    }

}
