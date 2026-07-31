package com.matlasystems.chat.protocol.handler;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.common.protocol.PacketStatus;
import com.matlasystems.chat.protocol.exception.ProtocolException;

/**
 * Unit tests for {@link RequestHandler}.
 */
class RequestHandlerTest {

    @Test
    void delegatesRequestPacketsToTheDispatcher() {

        HandlerRegistry registry = new HandlerRegistry();
        registry.register(CommandType.LOGIN, (packet, context) -> HandlerResult.success());

        RequestHandler handler = new RequestHandler(
                new CommandDispatcher(registry, new HandlerContext()));

        Packet request = new Packet(Header.create(CommandType.LOGIN, PacketStatus.REQUEST), null);

        assertTrue(handler.handle(request).isSuccess());
    }

    @Test
    void rejectsANonRequestPacket() {

        RequestHandler handler = new RequestHandler(
                new CommandDispatcher(new HandlerRegistry(), new HandlerContext()));

        Packet response = new Packet(Header.create(CommandType.LOGIN, PacketStatus.RESPONSE), null);

        assertThrows(ProtocolException.class, () -> handler.handle(response));
    }

}
