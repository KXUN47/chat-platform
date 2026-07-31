package com.matlasystems.chat.protocol.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.common.protocol.PacketStatus;

/**
 * Unit tests for {@link ProtocolUtils}.
 */
class ProtocolUtilsTest {

    @Test
    void expectsResponseOnlyForRequests() {

        Packet request = new Packet(Header.create(CommandType.PING, PacketStatus.REQUEST), null);
        Packet event = new Packet(Header.create(CommandType.PING, PacketStatus.EVENT), null);

        assertTrue(ProtocolUtils.expectsResponse(request));
        assertFalse(ProtocolUtils.expectsResponse(event));
        assertFalse(ProtocolUtils.expectsResponse(null));
    }

    @Test
    void isCurrentVersionChecksHeaderVersion() {

        Packet packet = new Packet(Header.create(CommandType.PING, PacketStatus.REQUEST), null);

        assertTrue(ProtocolUtils.isCurrentVersion(packet));
        assertFalse(ProtocolUtils.isCurrentVersion(new Packet(null, null)));
    }

    @Test
    void isTerminalForResponsesAndErrorsOnly() {

        Packet response = new Packet(Header.create(CommandType.PING, PacketStatus.RESPONSE), null);
        Packet error = new Packet(Header.create(CommandType.PING, PacketStatus.ERROR), null);
        Packet request = new Packet(Header.create(CommandType.PING, PacketStatus.REQUEST), null);

        assertTrue(ProtocolUtils.isTerminal(response));
        assertTrue(ProtocolUtils.isTerminal(error));
        assertFalse(ProtocolUtils.isTerminal(request));
    }

}
