package com.matlasystems.chat.common.protocol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;

/**
 * Unit tests for {@link Response}.
 */
class ResponseTest {

    @Test
    void createsResponsesWithoutChangingErrorHeaders() {

        Response response =
                new Response(
                        CommandType.LOGIN,
                        "ok");

        assertTrue(
                response.isSuccessful());

        assertEquals(
                CommandType.LOGIN,
                response.getHeader().getCommand());

        assertEquals(
                "ok",
                response.getPayload());

        Header errorHeader =
                Header.create(
                        CommandType.LOGIN,
                        PacketStatus.ERROR);

        Response errorResponse =
                new Response(
                        errorHeader,
                        "failed");

        assertTrue(
                errorResponse.isError());

        assertEquals(
                PacketStatus.ERROR,
                errorResponse.getHeader().getStatus());

        assertEquals(
                "failed",
                errorResponse.getPayload());
    }

}
