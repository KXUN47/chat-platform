package com.matlasystems.chat.protocol.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.common.protocol.PacketStatus;

/**
 * Unit tests for {@link ProtocolHandler}.
 */
class ProtocolHandlerTest {

    /**
     * Creates a protocol handler for testing.
     *
     * @param registry handler registry
     *
     * @return configured protocol handler
     */
    private ProtocolHandler newHandler(
            HandlerRegistry registry) {

        HandlerContext context =
                new HandlerContext();

        CommandDispatcher dispatcher =
                new CommandDispatcher(
                        registry,
                        context);

        return new ProtocolHandler(
                new RequestHandler(
                        dispatcher),
                new ResponseHandler(
                        dispatcher),
                new EventHandler(
                        dispatcher),
                new ErrorHandler());

    }

    @Test
    void routesRequestPacketsToTheRequestHandler() {

        HandlerRegistry registry =
                new HandlerRegistry();

        registry.register(
                CommandType.LOGIN,
                (packet, context) -> HandlerResult.success());

        Packet request =
                new Packet(
                        Header.create(
                                CommandType.LOGIN,
                                PacketStatus.REQUEST),
                        null);

        HandlerResult result =
                newHandler(
                        registry)
                                .handle(
                                        request);

        assertTrue(
                result.isSuccess());

    }

    @Test
    void routesErrorPacketsToTheErrorHandler() {

        HandlerRegistry registry =
                new HandlerRegistry();

        Packet error =
                new Packet(
                        Header.create(
                                CommandType.LOGIN,
                                PacketStatus.ERROR),
                        null);

        ProtocolHandler handler =
                newHandler(
                        registry);

        HandlerResult result =
                handler.handle(
                        error);

        assertFalse(
                result.isSuccess());

        assertTrue(
                result.getMessage()
                        .contains(
                                "ERROR"));

    }

    @Test
    void routesUnexpectedExceptionsThroughTheErrorHandler() {

        HandlerRegistry registry =
                new HandlerRegistry();

        registry.register(
                CommandType.LOGIN,
                (packet, context) -> {

                    throw new IllegalStateException(
                            "boom");

                });

        Packet request =
                new Packet(
                        Header.create(
                                CommandType.LOGIN,
                                PacketStatus.REQUEST),
                        null);

        ProtocolHandler handler =
                newHandler(
                        registry);

        HandlerResult result =
                handler.handle(
                        request);

        assertFalse(
                result.isSuccess());

        assertEquals(
                "Unexpected protocol error.",
                result.getMessage());

    }

    @Test
    void rejectsAPacketWithoutAHeader() {

        ProtocolHandler handler =
                newHandler(
                        new HandlerRegistry());

        Packet packet =
                new Packet(
                        null,
                        null);

        NullPointerException exception =
                assertThrows(
                        NullPointerException.class,
                        () -> handler.handle(
                                packet));

        assertNotNull(
                exception);

    }

}
