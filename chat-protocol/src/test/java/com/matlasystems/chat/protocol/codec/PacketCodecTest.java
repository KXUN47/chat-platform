package com.matlasystems.chat.protocol.codec;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.ByteBuffer;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.common.protocol.PacketStatus;

/**
 * Unit tests for {@link PacketCodec}.
 */
class PacketCodecTest {

    private final PacketCodec codec = new PacketCodec();

    @Test
    void roundTripsAPacketThroughByteArray() {

        Packet original = new Packet(Header.create(CommandType.LOGIN, PacketStatus.REQUEST), "hi");

        Packet decoded = codec.decode(codec.encode(original));

        assertEquals(original.getHeader().getPacketId(), decoded.getHeader().getPacketId());
    }

    @Test
    void roundTripsAPacketThroughByteBuffer() {

        Packet original = new Packet(Header.create(CommandType.LOGIN, PacketStatus.REQUEST), "hi");

        ByteBuffer buffer = ByteBuffer.wrap(codec.encode(original));

        Packet decoded = codec.decode(buffer).orElseThrow();

        assertEquals(original.getHeader().getPacketId(), decoded.getHeader().getPacketId());
        assertTrue(codec.decode(buffer).isEmpty());
    }

}
