package com.matlasystems.chat.common.protocol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;

/**
 * Integration tests covering the protocol package.
 */
class ProtocolPackageTest {

    @Test
    void requestFactoryCreatesVersionedRequest() {

        Request request =
                PacketFactory.request(
                        CommandType.LOGIN,
                        "payload");

        assertTrue(request.isRequest());

        assertEquals(
                ProtocolVersion.current(),
                request.getHeader().getProtocolVersion());

        assertEquals(
                CommandType.LOGIN,
                request.getHeader().getCommand());

        assertEquals(
                "payload",
                request.getPayload());
    }

    @Test
    void responsesAndErrorsRetainRequestCorrelationId() {

        Request request =
                PacketFactory.request(
                        CommandType.PING);

        Response response =
                PacketFactory.response(
                        request,
                        "pong");

        Response errorResponse =
                PacketFactory.error(
                        request,
                        "failure");

        assertTrue(response.isSuccessful());

        assertTrue(errorResponse.isError());

        assertEquals(
                request.getHeader().getPacketId(),
                response.getHeader().getPacketId());

        assertEquals(
                request.getHeader().getPacketId(),
                errorResponse.getHeader().getPacketId());
    }

    @Test
    void factoryCreatesServerEventAndRejectsUncorrelatedResponse() {

        Packet event =
                PacketFactory.event(
                        CommandType.PING,
                        null);

        assertTrue(event.isEvent());

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> PacketFactory.response(
                                null,
                                null));

        assertTrue(
                exception.getMessage() == null
                        || !exception.getMessage().isBlank());
    }

    @Test
    void protocolEnumsExposeExpectedSemantics() {

        assertEquals(
                ProtocolVersion.V1_0,
                ProtocolVersion.fromValue("1.0")
                        .orElseThrow());

        assertTrue(
                ProtocolVersion.fromValue("2.0")
                        .isEmpty());

        assertTrue(
                PacketStatus.ERROR.isError());

        assertTrue(
                PacketStatus.REQUEST.isRequest());
    }

    @Test
    void headerPacketAndSpecializedPacketConstructorsMaintainTheirStatus() {

        Header header =
                Header.create(
                        CommandType.LOGIN,
                        PacketStatus.EVENT);

        Header sameIdentifier =
                new Header(
                        header.getPacketId(),
                        header.getTimestamp(),
                        header.getProtocolVersion(),
                        header.getCommand(),
                        header.getStatus());

        assertEquals(
                header,
                sameIdentifier);

        Packet packet =
                new Packet(
                        header,
                        "event");

        assertTrue(packet.isEvent());

        Request request =
                new Request(
                        header,
                        "request");

        assertTrue(request.isRequest());

        Response response =
                new Response(
                        CommandType.LOGIN,
                        "response");

        assertTrue(response.isResponse());

        assertEquals(
                "response",
                response.getPayload());
    }

}
