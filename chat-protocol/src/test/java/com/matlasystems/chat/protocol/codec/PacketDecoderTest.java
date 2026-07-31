package com.matlasystems.chat.protocol.codec;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.common.protocol.PacketStatus;
import com.matlasystems.chat.protocol.exception.ProtocolException;

/**
 * Unit tests for {@link PacketDecoder}.
 */
class PacketDecoderTest {

    private final PacketEncoder encoder =
            new PacketEncoder();

    private final PacketDecoder decoder =
            new PacketDecoder();

    @Test
    void roundTripsAValidPacket() {

        Packet original =
                new Packet(
                        Header.create(
                                CommandType.PING,
                                PacketStatus.REQUEST),
                        "payload");

        Packet decoded =
                decoder.decode(
                        encoder.encode(original));

        assertEquals(
                original.getHeader().getPacketId(),
                decoded.getHeader().getPacketId());

        assertEquals(
                original.getHeader().getCommand(),
                decoded.getHeader().getCommand());

    }

    @Test
    void rejectsAPacketMissingRequiredHeaderFields() {

        Header incomplete =
                new Header(
                        null,
                        null,
                        null,
                        null,
                        null);

        Packet packet =
                new Packet(
                        incomplete,
                        null);

        byte[] frame =
                encoder.encode(packet);

        ProtocolException exception =
                assertThrows(
                        ProtocolException.class,
                        () -> decoder.decode(frame));

        assertNotNull(
                exception.getMessage());

    }

}
