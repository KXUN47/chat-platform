package com.matlasystems.chat.protocol.codec;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.common.protocol.PacketStatus;

/**
 * Unit tests for {@link PacketEncoder}.
 */
class PacketEncoderTest {

    private final PacketEncoder encoder =
            new PacketEncoder();

    @Test
    void encodesFramedJsonContainingPacketId() {

        Packet packet =
                new Packet(
                        Header.create(
                                CommandType.PING,
                                PacketStatus.REQUEST),
                        null);

        byte[] frame =
                encoder.encode(packet);

        ByteBuffer buffer =
                ByteBuffer.wrap(frame);

        int length =
                buffer.getInt();

        byte[] json =
                new byte[length];

        buffer.get(json);

        String jsonText =
                new String(
                        json,
                        StandardCharsets.UTF_8);

        assertTrue(
                jsonText.contains(
                        packet.getHeader()
                                .getPacketId()
                                .toString()));

    }

    @Test
    void rejectsNullPacket() {

        NullPointerException exception =
                assertThrows(
                        NullPointerException.class,
                        () -> encoder.encode(null));

        assertNotNull(
                exception.getMessage());

    }

}
