package com.matlasystems.chat.protocol.serializer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.common.protocol.PacketStatus;

/**
 * Unit tests for {@link JsonPacketDeserializer}.
 */
class JsonPacketDeserializerTest {

    private final JsonPacketSerializer serializer =
            new JsonPacketSerializer();

    private final JsonPacketDeserializer deserializer =
            new JsonPacketDeserializer();

    @Test
    void roundTripsAPacket() {

        Packet original =
                new Packet(
                        Header.create(
                                CommandType.PING,
                                PacketStatus.REQUEST),
                        "hi");

        byte[] serializedPacket =
                serializer.serialize(
                        original);

        Packet decoded =
                deserializer.deserialize(
                        serializedPacket);

        assertEquals(
                original.getHeader()
                        .getPacketId(),
                decoded.getHeader()
                        .getPacketId());

        assertEquals(
                original.getHeader()
                        .getCommand(),
                decoded.getHeader()
                        .getCommand());

    }

    @Test
    void rejectsNullData() {

        NullPointerException exception =
                assertThrows(
                        NullPointerException.class,
                        () -> deserializer.deserialize(
                                null));

        assertNotNull(
                exception);

    }

    @Test
    void rejectsMalformedJson() {

        byte[] malformedData =
                "not json".getBytes(
                        StandardCharsets.UTF_8);

        SerializationException exception =
                assertThrows(
                        SerializationException.class,
                        () -> deserializer.deserialize(
                                malformedData));

        assertNotNull(
                exception);

    }

}
