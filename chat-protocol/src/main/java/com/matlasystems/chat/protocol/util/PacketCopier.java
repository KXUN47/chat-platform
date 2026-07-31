package com.matlasystems.chat.protocol.util;

import java.io.Serializable;

import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.protocol.serializer.JsonPacketDeserializer;
import com.matlasystems.chat.protocol.serializer.JsonPacketSerializer;

/**
 * Creates defensive copies of {@link Packet} instances.
 */
public final class PacketCopier {

    private static final JsonPacketSerializer SERIALIZER = new JsonPacketSerializer();
    private static final JsonPacketDeserializer DESERIALIZER = new JsonPacketDeserializer();

    private PacketCopier() {

        throw new UnsupportedOperationException("Utility class");

    }

    /**
     * Creates a shallow copy of a packet: a new {@link Packet} and
     * {@link Header} instance with the same field values, sharing the
     * original payload reference.
     */
    public static Packet copy(Packet packet) {

        if (packet == null) {
            return null;
        }

        Header original = packet.getHeader();

        Header copiedHeader = original == null ? null : new Header(
                original.getPacketId(),
                original.getTimestamp(),
                original.getProtocolVersion(),
                original.getCommand(),
                original.getStatus());

        return new Packet(copiedHeader, (Serializable) packet.getPayload());

    }

    /**
     * Creates a deep copy of a packet, including its payload, by performing
     * a JSON serialize/deserialize round trip.
     */
    public static Packet deepCopy(Packet packet) {

        if (packet == null) {
            return null;
        }

        return DESERIALIZER.deserialize(SERIALIZER.serialize(packet));

    }

}
