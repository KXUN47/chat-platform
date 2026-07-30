package com.matlasystems.chat.common.protocol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;

/**
 * Unit tests for {@link PacketFactory}.
 */
class PacketFactoryTest {

    @Test
    void createsCorrelatedResponses() {

        Request request =
                PacketFactory.request(
                        CommandType.PING);

        Response response =
                PacketFactory.response(
                        request,
                        "pong");

        assertEquals(
                request.getHeader().getPacketId(),
                response.getHeader().getPacketId());

        Response errorResponse =
                PacketFactory.error(
                        request,
                        "error");

        assertTrue(
                errorResponse.isError());
    }

}
