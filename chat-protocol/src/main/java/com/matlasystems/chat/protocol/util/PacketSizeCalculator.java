package com.matlasystems.chat.protocol.util;

import com.matlasystems.chat.common.constants.ValidationConstants;
import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.protocol.exception.ProtocolException;
import com.matlasystems.chat.protocol.serializer.JsonPacketSerializer;

/**
 * Computes and validates the serialized size of protocol packets.
 */
public final class PacketSizeCalculator {

    private static final JsonPacketSerializer SERIALIZER = new JsonPacketSerializer();

    private PacketSizeCalculator() {

        throw new UnsupportedOperationException("Utility class");

    }

    /**
     * Returns the packet's serialized size in bytes.
     */
    public static int sizeOf(Packet packet) {

        if (packet == null) {
            return 0;
        }

        return SERIALIZER.serialize(packet).length;

    }

    /**
     * Determines whether a packet fits within the maximum allowed packet size.
     */
    public static boolean isWithinLimit(Packet packet) {

        return sizeOf(packet) <= ValidationConstants.MAX_PACKET_SIZE_BYTES;

    }

    /**
     * Validates that a packet fits within the maximum allowed packet size.
     *
     * @throws ProtocolException if the packet is too large
     */
    public static void requireWithinLimit(Packet packet) {

        int size = sizeOf(packet);

        if (size > ValidationConstants.MAX_PACKET_SIZE_BYTES) {

            throw new ProtocolException(
                    "Packet size " + size + " exceeds maximum of "
                            + ValidationConstants.MAX_PACKET_SIZE_BYTES + " bytes");

        }

    }

}
