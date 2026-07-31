package com.matlasystems.chat.protocol.handler;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.common.protocol.PacketStatus;
import com.matlasystems.chat.protocol.exception.ProtocolException;

/**
 * Unit tests for {@link EventHandler}.
 */
class EventHandlerTest {

    @Test
    void delegatesEventPacketsToTheDispatcher() {

        HandlerRegistry registry =
                new HandlerRegistry();

        registry.register(
                CommandType.USER_CONNECTED,
                (packet, context) -> HandlerResult.success());

        HandlerContext context =
                new HandlerContext();

        CommandDispatcher dispatcher =
                new CommandDispatcher(
                        registry,
                        context);

        EventHandler handler =
                new EventHandler(
                        dispatcher);

        Packet event =
                new Packet(
                        Header.create(
                                CommandType.USER_CONNECTED,
                                PacketStatus.EVENT),
                        null);

        HandlerResult result =
                handler.handle(
                        event);

        assertTrue(
                result.isSuccess());

    }

    @Test
    void rejectsANonEventPacket() {

        HandlerRegistry registry =
                new HandlerRegistry();

        HandlerContext context =
                new HandlerContext();

        CommandDispatcher dispatcher =
                new CommandDispatcher(
                        registry,
                        context);

        EventHandler handler =
                new EventHandler(
                        dispatcher);

        Packet request =
                new Packet(
                        Header.create(
                                CommandType.USER_CONNECTED,
                                PacketStatus.REQUEST),
                        null);

        ProtocolException exception =
                assertThrows(
                        ProtocolException.class,
                        () -> handler.handle(
                                request));

        assertNotNull(
                exception);

    }

}
