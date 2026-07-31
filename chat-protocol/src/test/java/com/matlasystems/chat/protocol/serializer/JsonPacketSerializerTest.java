package com.matlasystems.chat.protocol.serializer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.common.protocol.PacketStatus;

/**
 * Unit tests for {@link JsonPacketSerializer}.
 */
class JsonPacketSerializerTest {

    private final JsonPacketSerializer serializer =
            new JsonPacketSerializer();

    @Test
    void serializesAPacketAsUtf8Json() {

        Packet packet =
                new Packet(
                        Header.create(
                                CommandType.PING,
                                PacketStatus.REQUEST),
                        "hi");

        byte[] json =
                serializer.serialize(
                        packet);

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
                        () -> serializer.serialize(
                                null));

        assertNotNull(
                exception);

    }

}
