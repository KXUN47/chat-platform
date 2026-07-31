package com.matlasystems.chat.protocol.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.common.protocol.PacketStatus;

/**
 * Unit tests for {@link PacketCopier}.
 */
class PacketCopierTest {

    @Test
    void shallowCopyDuplicatesHeaderButSharesPayload() {

        String payload = "hello";
        Packet original = new Packet(Header.create(CommandType.PING, PacketStatus.REQUEST), payload);

        Packet copy = PacketCopier.copy(original);

        assertNotSame(original.getHeader(), copy.getHeader());
        assertEquals(original.getHeader().getPacketId(), copy.getHeader().getPacketId());
        assertSame(payload, copy.getPayload());
    }

    @Test
    void deepCopyProducesAnEqualButIndependentPacket() {

        Packet original = new Packet(Header.create(CommandType.PING, PacketStatus.REQUEST), "hello");

        Packet copy = PacketCopier.deepCopy(original);

        assertEquals(original.getHeader().getPacketId(), copy.getHeader().getPacketId());
        assertEquals(original.getPayload(), copy.getPayload());
    }

    @Test
    void nullPacketCopiesToNull() {

        assertNull(PacketCopier.copy(null));
        assertNull(PacketCopier.deepCopy(null));
    }

}
