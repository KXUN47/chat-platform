package com.matlasystems.chat.protocol.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.common.protocol.PacketStatus;

/**
 * Unit tests for {@link PacketUtils}.
 */
class PacketUtilsTest {

    @Test
    void isResponseToComparesPacketIds() {

        Packet request = new Packet(Header.create(CommandType.PING, PacketStatus.REQUEST), null);
        Packet response = new Packet(
                HeaderUtils.deriveResponseHeader(request.getHeader(), PacketStatus.RESPONSE), null);
        Packet unrelated = new Packet(Header.create(CommandType.PING, PacketStatus.REQUEST), null);

        assertTrue(PacketUtils.isResponseTo(response, request));
        assertFalse(PacketUtils.isResponseTo(unrelated, request));
        assertFalse(PacketUtils.isResponseTo(null, request));
    }

    @Test
    void payloadAsCastsWhenTypeMatches() {

        Packet packet = new Packet(Header.create(CommandType.PING, PacketStatus.REQUEST), "hello");

        assertTrue(PacketUtils.payloadAs(packet, String.class).isPresent());
        assertTrue(PacketUtils.payloadAs(packet, Integer.class).isEmpty());
    }

    @Test
    void hasNoPayloadReflectsAbsentPayload() {

        Packet withPayload = new Packet(Header.create(CommandType.PING, PacketStatus.REQUEST), "hello");
        Packet withoutPayload = new Packet(Header.create(CommandType.PING, PacketStatus.REQUEST), null);

        assertFalse(PacketUtils.hasNoPayload(withPayload));
        assertTrue(PacketUtils.hasNoPayload(withoutPayload));
        assertTrue(PacketUtils.hasNoPayload(null));
    }

}
