package com.matlasystems.chat.protocol.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Packet;

/**
 * Unit tests for the util package's {@link PacketFactory}, which validates
 * every packet it builds before returning it.
 */
class PacketFactoryTest {

    @Test
    void buildsAValidatedRequest() {

        Packet request =
                PacketFactory.request(
                        CommandType.PING,
                        null);

        assertTrue(
                request.isRequest());

    }

    @Test
    void buildsACorrelatedResponse() {

        Packet request =
                PacketFactory.request(
                        CommandType.PING,
                        null);

        Packet response =
                PacketFactory.response(
                        request,
                        "pong");

        assertEquals(
                request.getHeader()
                        .getPacketId(),
                response.getHeader()
                        .getPacketId());

        assertTrue(
                response.isResponse());

    }

    @Test
    void buildsACorrelatedError() {

        Packet request =
                PacketFactory.request(
                        CommandType.PING,
                        null);

        Packet error =
                PacketFactory.error(
                        request,
                        "failure");

        assertTrue(
                error.isError());

    }

    @Test
    void buildsAnEvent() {

        Packet event =
                PacketFactory.event(
                        CommandType.USER_CONNECTED,
                        null);

        assertTrue(
                event.isEvent());

    }

    @Test
    void rejectsAResponseWithoutARequestHeader() {

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> PacketFactory.response(
                                null,
                                "pong"));

        assertNotNull(
                exception);

    }

}
