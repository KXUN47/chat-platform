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
 * Unit tests for {@link ResponseHandler}.
 */
class ResponseHandlerTest {

    @Test
    void delegatesResponsePacketsToTheDispatcher() {

        HandlerRegistry registry = new HandlerRegistry();
        registry.register(CommandType.LOGIN, (packet, context) -> HandlerResult.success());

        ResponseHandler handler = new ResponseHandler(
                new CommandDispatcher(registry, new HandlerContext()));

        Packet response = new Packet(Header.create(CommandType.LOGIN, PacketStatus.RESPONSE), null);

        assertTrue(handler.handle(response).isSuccess());
    }

    @Test
    void rejectsANonResponsePacket() {

        ResponseHandler handler = new ResponseHandler(
                new CommandDispatcher(new HandlerRegistry(), new HandlerContext()));

        Packet request = new Packet(Header.create(CommandType.LOGIN, PacketStatus.REQUEST), null);

        assertThrows(ProtocolException.class, () -> handler.handle(request));
    }

}
