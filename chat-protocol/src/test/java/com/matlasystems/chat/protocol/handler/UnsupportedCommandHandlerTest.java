package com.matlasystems.chat.protocol.handler;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.common.protocol.PacketStatus;

/**
 * Unit tests for {@link UnsupportedCommandHandler}.
 */
class UnsupportedCommandHandlerTest {

    @Test
    void reportsFailureNamingTheUnsupportedCommand() {

        Packet packet = new Packet(Header.create(CommandType.FILE_UPLOAD, PacketStatus.REQUEST), null);

        HandlerResult result = new UnsupportedCommandHandler().handle(packet, new HandlerContext());

        assertFalse(result.isSuccess());
        assertTrue(result.getMessage().contains("FILE_UPLOAD"));
    }

}
